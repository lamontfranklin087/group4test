package view;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import view.UI;
import model.User;
import model.Job;
import model.Manager;
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
	/* The number of maximum allowable jobs to be pending for any 7 day period. */
	private int MAX_JOBS_IN_7_CONSECUTIVE_DAYS = 5;
	
	/* The max number of jobs that can be pending for all dates. */
	private final int MAX_NUMBER_JOBS = 30;	
	//Scanner to receive input from user.
	private Scanner keyboard = new Scanner(System.in);

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
		readSerialFile();
		if (allJobs == null || allJobs.size() == 0) {
			allJobs = new LinkedList<Job>();
		}
		checkForPastJobs();		
		
		userInterface = new UI();
		
		User currentUser;
		do {
			currentUser = login(userInterface);
			if (currentUser != null) {				
				run(currentUser);
			}
		} while (currentUser != null);
		writeSerialFile();
	}
	
	/**
	 * Checking user type and call corresponding method.
	 * @param currentUser user just logged in.
	 */
	private void run(User currentUser) {
		int menuSelection = 0;
		do {
			userInterface.clearScreen();
			userInterface.menuHeader(currentUser);
			menuSelection = userInterface.printMenuOptions(currentUser.getMainMenu());
			if (currentUser.getSimpleName().equalsIgnoreCase("Park Manager")) {
				managerHere((Manager) currentUser, menuSelection);
			} else if (currentUser.getSimpleName().equalsIgnoreCase("Volunteer")) {
				volunteerHere((Volunteer)currentUser, menuSelection);
			} else if (currentUser.getSimpleName().equalsIgnoreCase("Urban Parks Staff")) {
				staffHere((UrbanParksStaff)currentUser, menuSelection);
			} 					
		} while (menuSelection != currentUser.getMainMenu().size());
	}
	
	/**
	 * Handles inputs from manager.
	 * @param currentUser is user object.
	 * @param menuSelection option user selected from manager main menu.
	 */
	private void managerHere(Manager currentUser, int menuSelection) {
		userInterface.clearScreen();
		userInterface.menuHeader(currentUser);
		keyboard = new Scanner(System.in);
		switch (menuSelection) {
		case 1:
			if (allJobs == null || allJobs.size() < MAX_NUMBER_JOBS) {
				userInterface.printText("        Creating new job\n");
				String managerName = (currentUser.getFirstName() + " " + currentUser.getLastName());				
				Calendar jobDate = getJobDateFromUser();
				boolean result = false;
				if (jobDate != null) {
					String jobLocation = getJobLocationFromUser(currentUser);				
					int jobDuration = getJobDurationFromUser();
					int jobLightSlot = getLightSlotFromUser();
					int jobMediumSlot = getMediumSlotFromUser();
					int jobHeavySlot = getHeavySlotFromUser();
					String jobDescription = getJobDescriptionFromUser();
					String jobStartTime = getJobStartTimeFromUser();
					result = currentUser.submitNewJob(managerName, jobLocation, jobDate, 
							jobDuration, jobLightSlot, jobMediumSlot, jobHeavySlot, 
							jobDescription, jobStartTime, allJobs);
					}				
				if (result) {
					userInterface.printText("New job was successfully created.");
				} else {
					userInterface.printText("Something went wrong and job wasn't created. \nTry again.");
				}
			} else {
				userInterface.printText("You reached maximum limit of jobs.");
			}
			userInterface.waitForReturnKey();
			break;
		case 2:			
			userInterface.printText("        Deleting a job\n");
			userInterface.printText("Enter job ID: ");
			if (currentUser.deleteJob(getNumber(), allJobs)) {
				userInterface.printText("\nJob was deleted successfully .");
			} else {
				userInterface.printText("\nJob wasn't found.");
			}
			userInterface.waitForReturnKey();
			break;
		case 3:
			if (editJob(currentUser)) {
				userInterface.printText("\nJob was edited successfully .");
			} else {
				userInterface.printText("Something went wrong and job wasn't edited. \nTry again.");
			}
			userInterface.waitForReturnKey();
			break;
		case 4:
			userInterface.printText("           All jobs you manage: ");
			Collection<Job> availableJobs = currentUser.viewSumAllJobs(allJobs);
			if (availableJobs != null) {
				for (Job tempJobs : availableJobs) {
					userInterface.printText(tempJobs.toStringTable());
				}
			}
			userInterface.waitForReturnKey();
			break;
		case 5:
			userInterface.printText("         View the Volunteers for a job\n");
			userInterface.printText("Enter job ID: ");			
			LinkedList<Volunteer> volunteersForJob = currentUser.viewVolunteers(getNumber(), allJobs);
			if (volunteersForJob != null) {
				for (User tempVolunteer : volunteersForJob) {
					userInterface.printText(tempVolunteer.toString());
				}
			}
			userInterface.waitForReturnKey();
			break;
		case 6:
			userInterface.printText("Exiting...");
			break;
		}
	}	
	
	/**
	 * Handles inputs from Urban Parks Staff.
	 * @param currentUser is user object.
	 * @param menuSelection option user selected from Urban Parks Staff main menu.
	 */
	private void staffHere(UrbanParksStaff currentUser, int menuSelection) {
		userInterface.clearScreen();
		userInterface.menuHeader(currentUser);
		
		switch (menuSelection) {
		case 1:
			userInterface.printText("           All jobs: ");
			Collection<Job> tempAllJobs = currentUser.viewSumAllJobs(allJobs);
			if (tempAllJobs != null) {
				for (Job tempJobs : tempAllJobs) {
					userInterface.printText(tempJobs.toStringTable());
				}	
			}
			userInterface.waitForReturnKey();
			break;
		case 2:
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
			userInterface.waitForReturnKey();
			break;
		case 3:
			userInterface.printText("           Searching volunteer by last name: ");
			userInterface.printText("Enter volunteer's last name: ");
			keyboard = new Scanner(System.in);
			String tempLastName = keyboard.nextLine();
			User tempUser = currentUser.searchVolunteer(allUsers, tempLastName);
			if (tempUser != null) { 
				userInterface.printText(tempUser.toString());
			} else {
				userInterface.printText("There is no such user.");
			}
			userInterface.waitForReturnKey();
			break;
		case 4:
			break;
		}
	}

	/**
	 * Handles inputs from Volunteer.
	 * @param currentUser is user object.
	 * @param menuSelection option user selected from volunteer main menu.
	 */
	private void volunteerHere(Volunteer currentUser, int menuSelection) {
		userInterface.clearScreen();
		userInterface.menuHeader(currentUser);
		Collection<Job> tempAllJobs;
		
		switch (menuSelection) {
		case 1:
			userInterface.printText("        View the jobs you signed up for: ");
			tempAllJobs = currentUser.viewMyJobs(allJobs);
			if (tempAllJobs != null) {
				for (Job tempJobs : tempAllJobs) {
					userInterface.printText(tempJobs.toStringTable());
				}	
			}
			userInterface.waitForReturnKey();
			break;
		case 2:
			userInterface.printText("        Summary of all upcoming jobs");
			tempAllJobs = currentUser.viewSumAllJobs(allJobs);
			if (tempAllJobs != null) {
				for (Job tempJobs : tempAllJobs) {
					userInterface.printText(tempJobs.toStringTable());
				}	
			}
			currentUser.viewSumAllJobs(allJobs);
			userInterface.waitForReturnKey();
			break;
		case 3:
			userInterface.printText("           Job's details: ");
			userInterface.printText("Enter job ID: ");			
			Job availableJobs = currentUser.viewJobDetails(getNumber(), allJobs);
			userInterface.clearScreen();
			userInterface.menuHeader(currentUser);			
			userInterface.printText(availableJobs.toString());
			userInterface.waitForReturnKey();
			break;
		case 4:
			userInterface.printText("           Volunteer for a job: ");
			userInterface.printText("Enter job ID: ");
			int tempJobID = getNumber();
			userInterface.printText("Select slot: ");//\n1. Light\n2. Medium \n3. Heavy\n\n");
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
			userInterface.waitForReturnKey();
			break;
		case 5:
			break;
		}
		
	}
	
	/**
	 * Handle the input from user to edit job.
	 * @param currentUser is user object.
	 * @return true if job was edited successfully, otherwise false.
	 */
	private boolean editJob(Manager currentUser) {
		ArrayList<String> tempEditMenu = currentUser.getEditMenu();
		userInterface.printText("        Editnig a job\n");
		userInterface.printText("Enter job ID: ");
		int tempJobID = getNumber();
		userInterface.clearScreen();
		userInterface.menuHeader(currentUser);
		int editMenuSelection = userInterface.printMenuOptions(tempEditMenu);
		
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
				aDuration = getJobDurationFromUser();
				break;
			case 4:
				aLightSlot = getLightSlotFromUser();
				aMediumSlot = getMediumSlotFromUser(); 
				aHeavySlot = getHeavySlotFromUser();
				break;
			case 5:
				aDescription = getJobDescriptionFromUser();
				break;
			case 6:
				aStartTime = getJobStartTimeFromUser();
				break;
			case 7:
				break;
		}
		return currentUser.editJob(aJobLocation, aJobDate, aDuration, 
				aLightSlot, aMediumSlot, aHeavySlot, aDescription, 
				aStartTime, allJobs, tempJobID);
	}
	
	/**
	 * This class logs the user in and returns the resulting user information.
	 * @param userInterface.
	 * @return user object corresponding to provided email and password.
	 */	
	private User login(UI userInterface) {
		userInterface.clearScreen();
		userInterface.menuHeader(null);
		
		ArrayList<String> menuList = new ArrayList<String>();
		StringBuilder textString = new StringBuilder();
		textString.append("                 Login page");
		textString.append("\n");
		userInterface.printText(textString.toString());
				
		menuList.add("Login");
		menuList.add("Terminate program");	
		
		int userSelection = userInterface.printMenuOptions(menuList);		
		
		if (userSelection == 1) {
			userInterface.clearScreen();
			userInterface.printMenuOptions(null);
			userInterface.menuHeader(null);
			userInterface.printText("Enter your email address: ");						
			String email = keyboard.nextLine();
			
			userInterface.printText("Enter your password: ");						
			String password = keyboard.nextLine();			
			if (allUsers != null) {
				for (User tempUser : allUsers) {
					if (tempUser.getEmail().equals(email)
							&& tempUser.getPassword().equals(password)) {
						return tempUser;
					}
				}	
			}
		}
		return null;
	}
	
	/**
	 * Handle the input from user to get job's date.
	 * @return Calendar object which represent job's date.
	 */
	private Calendar getJobDateFromUser() {		
		userInterface.printText("Enter Job's date (format mm/dd/yyyy): ");				
		String[] mystring = (keyboard.nextLine()).split("/");
		Calendar currentDate = new GregorianCalendar();
		Calendar futureJobDate = new GregorianCalendar();		
		
		int myDate = Integer.parseInt(mystring[1]);
		int myYear = Integer.parseInt(mystring[2]);
		int myMonth = Integer.parseInt(mystring[0]) - 1;
						
		futureJobDate.set(Calendar.YEAR, myYear);
		futureJobDate.set(Calendar.MONTH, myMonth);
		futureJobDate.set(Calendar.DAY_OF_MONTH, myDate);	
		
		int curYear = currentDate.get(Calendar.YEAR);		
		int jobDayOfYear = futureJobDate.get(Calendar.DAY_OF_YEAR);
		int curDayOfYear = currentDate.get(Calendar.DAY_OF_YEAR);
		
		if (curYear < myYear) {
			jobDayOfYear = jobDayOfYear + 365;
		}		
	 	int resultDays = jobDayOfYear - curDayOfYear;	 	
	 	if (resultDays <= 0) {
	 		userInterface.printText("You can't enter past date. ");
			return null;
	 	} else if (resultDays > 90) {
	 		userInterface.printText("You can't enter date more then 90 days ahead. ");
			return null;
	 	} else if (!jobsIn7Days(allJobs, futureJobDate)) {
	 		userInterface.printText("A job can't be added because you are to busy for that week.");
			return null;
		}
	 	return futureJobDate;
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
	 * Handle the input from user to get job's duration.
	 * @return job's duration.
	 */
	private int getJobDurationFromUser() {
	 	userInterface.printText("Enter job Duration: ");	 	
	 	return getNumber();
	}
	/**
	 * Handle the input from user to get the number of light slots for job's.
	 * @return number of light slots for this job.
	 */
	private int getLightSlotFromUser() {
	 	userInterface.printText("Enter the number of light slots: ");
	 	return getNumber();
	}
	/**
	 * Handle the input from user to get the number of medium slots for job's.
	 * @return number of medium slots for this job.
	 */
	private int getMediumSlotFromUser() {
	 	userInterface.printText("Enter the number of medium slots: ");
	 	return getNumber();	 	
	}
	/**
	 * Handle the input from user to get the number of heavy slots for job's.
	 * @return number of heavy slots for this job.
	 */
	private int getHeavySlotFromUser() {
	 	userInterface.printText("Enter the number of heavy slots: ");
	 	return getNumber();
	}
	/**
	 * Handle the input from user to get job's start time.
	 * @return job's start time.
	 */
	private String getJobStartTimeFromUser() {
	 	userInterface.printText("Enter job's start time (for example 8:00AM): ");
	 	return keyboard.nextLine();
	}
	/**
	 * Handle the input from user to get job's description.
	 * @return job's description.
	 */
	private String getJobDescriptionFromUser() {
	 	userInterface.printText("Enter job's description: ");
	 	return keyboard.nextLine();
	}		
	
	/**
	 * Checking: if there during any consecutive 7 day period 
	 * more than 5 jobs or not.	 
	 * @param allJobs is a list of all Jobs.
	 * @param mydate is user entered date.
	 * @return false if there 5 or more jobs in 7 days, otherwise true.
	 */
	protected boolean jobsIn7Days(Collection<Job> allJobs, Calendar mydate) {
		int jobsIn7Days = 0;	
		int jobDayOfYear = mydate.get(Calendar.DAY_OF_YEAR);		
		if (allJobs.size() == 0) {
			return true;
		}
		Iterator<Job> itr = allJobs.iterator();
		if (allJobs != null && allJobs.size() > 0) {		
			while (itr.hasNext()) {
				int temp = itr.next().getDate().get(Calendar.DAY_OF_YEAR);
				if ((temp <= (jobDayOfYear + 3)) && (temp >= (jobDayOfYear - 3))) {					
					jobsIn7Days = jobsIn7Days + 1;
				}				
			}
			if (jobsIn7Days < MAX_JOBS_IN_7_CONSECUTIVE_DAYS) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Read all jobs and user from a file.
	 */
	private void readSerialFile() {
		try {
			allUsers = SerialStartup.serialReadUsers();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			allUsers = new LinkedList<User>();
		}
		try {
			allJobs = SerialStartup.serialReadJobs();						
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			allJobs = new LinkedList<Job>();
		}
	}
	
	/**
	 * Writes all jobs and user to a file.
	 */
	private void writeSerialFile() {
		if (allUsers != null) {
			try {
				SerialStartup.serialWriteUsers(allUsers);
			} catch (FileNotFoundException e) {
				e.printStackTrace();			
			}
		}
		if (allJobs != null) {
			try {
				SerialStartup.serialWriteJobs(allJobs);
			} catch (FileNotFoundException e) {
				e.printStackTrace();			
			}
		}
	}
	
	/**
	 * Check for past jobs and remove them from list before user see them.
	 */
	private void checkForPastJobs() {
		Calendar currentDate = new GregorianCalendar();		
		int curYear = currentDate.get(Calendar.YEAR);
		
		Calendar jobDate;
		int jobYear;
		boolean ifDeleted = false;
		
		if (allJobs != null && allJobs.size() > 0) {
			Iterator<Job> itr = allJobs.iterator();
			Job temp = null;
			while (itr.hasNext()) {				
				if (!ifDeleted) {
					temp = itr.next();					
				}
				jobDate = temp.getDate();
				jobYear = jobDate.get(Calendar.YEAR);	
								
				int jobDayOfYear = jobDate.get(Calendar.DAY_OF_YEAR);
				int curDayOfYear = currentDate.get(Calendar.DAY_OF_YEAR);
				
				if (curYear < jobYear) {
					jobDayOfYear = jobDayOfYear + 365;
				}		
								
				if ((jobDayOfYear - curDayOfYear) <= 0) {				
					allJobs.remove(temp);
					itr = allJobs.iterator();
					temp = itr.next();
					ifDeleted = true;
				} else {
					ifDeleted = false;
				}
			}
		}	
	}
	
	/**
	 * Parse string to integer.
	 * @return an integer number from 1 to ...
	 */
	private int getNumber() {	
		int result = 0;
		keyboard = new Scanner(System.in);

		try {	        	
			String temp = keyboard.nextLine();
			if (Integer.parseInt(temp) >= 0) {
				result = Integer.parseInt(temp);				
			}
		} catch(NumberFormatException ne) {	}				
		return result;
	}
}