package controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Scanner;

import view.UI;
import model.User;
import model.Job;
import model.Manager;
import model.MyOwnException;
import model.UrbanParksStaff;
import model.Volunteer;

/**
 * Login menu, read and write from/to text files.
 * @author Ihar Lavor
 * @version 02/27/2016
 */
public class ParksProgram {
	private Collection<Job> allJobs;
	private Collection<User> allUsers;	
	private UI userInterface;	
	private int EXIT = 0;
		
	/** The max number of jobs that can be pending for all dates. */
	private final int MAX_NUMBER_JOBS = 30;	
		
	private CheckBusinessRules checkBusRule = new CheckBusinessRules();

	/**
	 * Main driver to run program.
	 * @param args additional parameters.
	 * @throws ParseException exception.
	 */
    public static void main(String[] args) throws ParseException {	
		new ParksProgram();		
	}	
	
    /**
     * Initializer for this program.
     */
	public ParksProgram() {	
		ReadWriteSerialFile readFile = new ReadWriteSerialFile();
		readFile.readSerialFile();
		allJobs = readFile.getAllJobs();
		allUsers = readFile.getAllUsers();			
		
		checkBusRule.checkForPastJobs(allJobs);		
		
		userInterface = new UI();
		
		User currentUser;
		
		do {
			currentUser = login(userInterface);
			if (currentUser != null) {				
				serveCurrentUser(currentUser);
			}
		} while (currentUser != null);
		readFile.writeSerialFile(allJobs, allUsers);
	}
	
	/**
	 * This class logs the user in and returns the resulting user information.
	 * @param userInterface.
	 * @return user object corresponding to provided email and password.
	 */	
	private User login(UI userInterface) {
		ArrayList<String> userData = userInterface.loginScreen(userInterface);
		if (allUsers != null && userData != null && userData.size() > 0) {
			for (User tempUser : allUsers) {
				if (tempUser.getEmail().equals(userData.get(0))
						&& tempUser.getPassword().equals(userData.get(1))) {
					return tempUser;
				}
			}	
		}		
		return null;
	}
	
	/**
	 Serving current user until user select EXIT option.
	 * @param currentUser is user object.
	 */
	private void serveCurrentUser(User currentUser) {			
		int menuSelection = 0;		
		do {
			try {
				userInterface.clearScreen();
				userInterface.menuHeader(currentUser);	
				menuSelection = userInterface.printMenu(currentUser);
				switch (menuSelection) {
				case 1:				
					createJobMenu((Manager) currentUser);				
					break;
				case 2:			
					deleteJobMenu((Manager) currentUser);
					break;
				case 3:
					editJobMenu((Manager) currentUser);
					break;
				case 4:
					viewAllJobsMenu(currentUser);
					break;
				case 5:			
					viewVolunteersForJob((Manager) currentUser);
					break;		
				case 6:
					viewAllJobsMenu(currentUser);
					break;
				case 7:
					viewJobDetailsMenu(currentUser);
					break;
				case 8:
					searchVoluntByLastName((UrbanParksStaff) currentUser);
					break;
				case 9:
					viewSighendUpJobs((Volunteer) currentUser);
					break;
				case 10:
					viewAllJobsMenu(currentUser);
					break;
				case 11:
					viewJobDetailsMenu(currentUser);
					break;
				case 12:
					vounteerForJobMenu((Volunteer) currentUser);
					break;
				case 0:
					userInterface.printText("Exiting...");
					break;
				}
				if (menuSelection != EXIT) {
					userInterface.waitForReturnKey();
				}
			} catch (MyOwnException e) {
				e.printStackTrace();
				userInterface.waitForReturnKey();
			}
		} while (menuSelection != EXIT);
	}	
	
	private void viewVolunteersForJob(Manager currentUser) {
		userInterface.printText("         View the Volunteers for a job\n");
		userInterface.printText("Enter job ID: ");			
		LinkedList<Volunteer> volunteersForJob = currentUser.viewVolunteers(getNumber(), allJobs);
		if (volunteersForJob != null) {
			for (User tempVolunteer : volunteersForJob) {
				userInterface.printText(tempVolunteer.toString());
				userInterface.printText("");
			}
		}
	}
	
	private void deleteJobMenu(Manager currentUser) {
		userInterface.printText("        Deleting a job\n");
		userInterface.printText("Enter job ID: ");
		if (currentUser.deleteJob(getNumber(), allJobs)) {
			userInterface.printText("\nJob was deleted successfully .");
		} else {
			userInterface.printText("\nJob wasn't found.");
		}		
	}
	/**
	 * 
	 * @param currentUser
	 * @throws MyOwnException 
	 */
	private void createJobMenu(Manager currentUser) throws MyOwnException {
		if (allJobs == null || allJobs.size() < MAX_NUMBER_JOBS) {
			userInterface.printText("        Creating new job\n");
			String managerName = (currentUser.getFirstName() + " " + currentUser.getLastName());				
			Calendar jobDate = getJobDateFromUser();
			boolean result = false;
			if (jobDate != null) {
				String jobLocation = getJobLocationFromUser(currentUser);	
				userInterface.printText("Enter job Duration: ");	 	
				int jobDuration = getNumber();						
				userInterface.printText("Enter the number of light slots: ");				 	
				int jobLightSlot = getNumber();
				userInterface.printText("Enter the number of medium slots: ");
				int jobMediumSlot  = getNumber(); 
				userInterface.printText("Enter the number of heavy slots: ");
				int jobHeavySlot  = getNumber();				
				userInterface.printText("Enter job's description: ");
				String jobDescription  = userInterface.getNextLine();				
				userInterface.printText("Enter job's start time (for example 8:00AM): ");
				String jobStartTime = userInterface.getNextLine();
				result = currentUser.submitNewJob(managerName, jobLocation, jobDate, 
						jobDuration, jobLightSlot, jobMediumSlot, jobHeavySlot, 
						jobDescription, jobStartTime, allJobs);
				}				
			if (result) {
				userInterface.printText("New job was successfully created.");
			} 
		} else {
			userInterface.printText("You reached maximum limit of jobs.");
		}		
	}
		
	private void vounteerForJobMenu(Volunteer currentUser) throws MyOwnException{
		userInterface.printText("           Volunteer for a job: ");
		userInterface.printText("Enter job ID: ");
		int tempJobID = getNumber();
		userInterface.printText("Select slot: ");
		ArrayList<String> theSlotOptions = new ArrayList<String>();
		theSlotOptions.add("Light");
		theSlotOptions.add("Medium");
		theSlotOptions.add("Heavy");
		int userSlotSelection = userInterface.printMenuOptions(theSlotOptions);
		if (currentUser.jobSignUp(allJobs, tempJobID, userSlotSelection)) {
			userInterface.printText("\nYou successfully signed up for a job.");
		} else {
			userInterface.printText("Something went wrong and you didn't sign up for a job. \nTry again.");
		}
	}
	
	private void viewSighendUpJobs(Volunteer currentUser) {
		Collection<Job> tempAllJobs;
		userInterface.printText("        View the jobs you signed up for: ");
		tempAllJobs = currentUser.viewMyJobs(allJobs);
		if (tempAllJobs != null) {
			for (Job tempJobs : tempAllJobs) {
				userInterface.printText(tempJobs.toStringTable());
			}	
		}
	}	
		
	private void viewJobDetailsMenu(User currentUser) {
		userInterface.printText("           Job's details: ");
		userInterface.printText("Enter job ID: ");			
		int tempJobID = getNumber();
		Job availableJobs = currentUser.viewJobDetails(tempJobID, allJobs);
		userInterface.clearScreen();
		userInterface.menuHeader(currentUser);		
		if (availableJobs != null) {
			userInterface.printText(availableJobs.toString());
		} else {
			userInterface.printText("There is no job with ID " + tempJobID);
		}
	}
	
	private void viewAllJobsMenu(User currentUser) {
		userInterface.printText("           All jobs: ");
		Collection<Job> tempAllJobs = currentUser.viewSumAllJobs(allJobs);
		if (tempAllJobs != null) {
			for (Job tempJobs : tempAllJobs) {
				userInterface.printText(tempJobs.toStringTable());
			}	
		}
	}
	
	private void searchVoluntByLastName(UrbanParksStaff currentUser) {
		userInterface.printText("           Searching volunteer by last name: ");
		userInterface.printText("Enter volunteer's last name: ");
		
		String tempLastName = userInterface.getNextLine();
		User tempUser = currentUser.searchVolunteer(allUsers, tempLastName);
		if (tempUser != null) { 
			userInterface.printText(tempUser.toString());
		} else {
			userInterface.printText("There is no such user.");
		}
	}
	/**
	 * Handle the input from user to edit job.
	 * @param currentUser is user object.
	 * @return true if job was edited successfully, otherwise false.
	 * @throws MyOwnException 
	 */
	private void editJobMenu(Manager currentUser) throws MyOwnException {
		userInterface.printText("        Editnig a job\n");
		userInterface.printText("Enter job ID: ");
		int tempJobID = getNumber();
		
		checkBusRule.isThereVolunteer(currentUser, tempJobID, allJobs);
		userInterface.clearScreen();
		userInterface.menuHeader(currentUser);
		int editMenuSelection = userInterface.printEditMenu(currentUser);
		
		String aJobLocation = null;
		Calendar aJobDate = null; 
		int aDuration = 0;
		int aLightSlot = 0;
		int aMediumSlot = 0;
		int aHeavySlot = 0;
		String aDescription = null;
		String aStartTime = null;
		
		switch (editMenuSelection) {
			case 1:
				aJobDate = getJobDateFromUser();
				break;
			case 2:
				aJobLocation = getJobLocationFromUser(currentUser);
				break;
			case 3:
				userInterface.printText("Enter job Duration: ");	 	
			 	aDuration = getNumber();
			 	checkBusRule.checkJobDuration(aDuration);
				break;
			case 4:
				userInterface.printText("Enter the number of light slots: ");				 	
				aLightSlot = getNumber();
				userInterface.printText("Enter the number of medium slots: ");
				aMediumSlot = getNumber(); 
				userInterface.printText("Enter the number of heavy slots: ");
				aHeavySlot = getNumber();
				break;
			case 5:
				userInterface.printText("Enter job's description: ");
				aDescription = userInterface.getNextLine();
				break;
			case 6:
				userInterface.printText("Enter job's start time (for example 8:00AM): ");
				aStartTime = userInterface.getNextLine();
				break;
			case 7:
				break;
		}				
		if (currentUser.editJob(aJobLocation, aJobDate, aDuration, 
			aLightSlot, aMediumSlot, aHeavySlot, aDescription, 
			aStartTime, allJobs, tempJobID)) {
			userInterface.printText("\nJob was edited successfully .");
		}
		
	}
	
	/**
	 * Handle the input from user to get job's date.
	 * @return Calendar object which represent job's date.
	 * @throws MyOwnException 
	 */
	private Calendar getJobDateFromUser() throws MyOwnException {		
		userInterface.printText("Enter Job's date (format mm/dd/yyyy): ");				
		String[] mystring = (userInterface.getNextLine()).split("/");
		Calendar futureJobDate = new GregorianCalendar();		
		if (mystring.length == 3) {
			int myDate = Integer.parseInt(mystring[1]);
			int myYear = Integer.parseInt(mystring[2]);
			int myMonth = Integer.parseInt(mystring[0]) - 1;
							
			futureJobDate.set(Calendar.YEAR, myYear);
			futureJobDate.set(Calendar.MONTH, myMonth);
			futureJobDate.set(Calendar.DAY_OF_MONTH, myDate);	
			
			checkBusRule.checkForPastDate(futureJobDate);
			checkBusRule.checkForFutureDate(futureJobDate);
			checkBusRule.jobsIn7Days(allJobs, futureJobDate);
			return futureJobDate;
		} else {
			throw new MyOwnException("Job's date can't be null.");
		}		
	}
		
	/**
	 * Handle the input from user to get job's location.
	 * @param currentUser is user object.
	 * @return job's location.
	 */
	private String getJobLocationFromUser(Manager currentUser) {
		userInterface.printText("Select job location: ");
	 	int parkName = userInterface.printMenuOptions(currentUser.getParksList());
	 	String aJobLocation = currentUser.getParksList().get(parkName - 1);
	 	return aJobLocation;
	}
	
	/**
	 * Parse string to integer.
	 * @return an integer number from 1 to ...
	 */
	private int getNumber() {	
		int result = 0;
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);

		try {	        	
			String temp = keyboard.nextLine();
			if (Integer.parseInt(temp) >= 0) {
				result = Integer.parseInt(temp);				
			}
		} catch(NumberFormatException ne) {	}				
		return result;
	}
}