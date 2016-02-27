package model;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import view.UI;

/**
 * Login menu, read and write from/to text files.
 * @author Ihar Lavor
 * @author Lamont Franklin
 * @version 02/06/2016
 * 
 * revision Lamont Franklin 2/10/2016 added duty level functionality
 * revision Ihar Lavor 2/12/2016 
 * revision Ihar Lavor 2/22/2016 
 */
public class ParksProgram {
	private Collection<Job> allJobs;
	private Collection<User> allUsers;	
	private UI userInterface;
	/* The number of maximum allowable jobs to be pending for any 7 day period. */
	private int MAX_JOBS_IN_7_CONSECUTIVE_DAYS = 5;
	
	/* The max number of jobs that can be pending for all dates. */
	private final int MAX_NUMBER_JOBS = 30;	
	
	private Scanner keyboard = new Scanner(System.in);


	public ParksProgram() {	
		readSerialFile();
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
	
	private void run(User currentUser) {
		int menuSelection = 0;
		do {
			userInterface.clearScreen();
			userInterface.menuHeader(currentUser);
			menuSelection = userInterface.printMenuOptions(currentUser.getMainMenu());
			if (currentUser.getSimpleName().equalsIgnoreCase("Park Manager")) {
				managerHere((Manager) currentUser, menuSelection);
			} else if (currentUser.getSimpleName().equalsIgnoreCase("Volunteer")) {
				volunteerHere(currentUser, menuSelection);
			} else if (currentUser.getSimpleName().equalsIgnoreCase("Urban Parks Staff")) {
				staffHere(currentUser, menuSelection);
			} 					
		} while (menuSelection != currentUser.getMainMenu().size());
	}
	
	private void managerHere(Manager currentUser, int menuSelection) {
		userInterface.clearScreen();
		userInterface.menuHeader(currentUser);
		keyboard = new Scanner(System.in);
		switch (menuSelection) {
		case 1:
			if (allJobs == null || allJobs.size() < MAX_NUMBER_JOBS) {
				userInterface.printText("        Creating new job\n");
				if (getNewJobData(currentUser)) {
					userInterface.printText("New job was successfully created.");
				} else {
					userInterface.printText("Something went wrong and job wasn't created. \nTry again.");
				}
			} else {
				userInterface.printText("You reached maximum limit of jobs.");
			}
			break;
		case 2:			
			userInterface.printText("        Deleting a job\n");
			userInterface.printText("Enter job ID: ");
			if (currentUser.deleteJob(getNumber())) {
				userInterface.printText("\nJob was successfully deleted.");
			} else {
				userInterface.printText("\nJob wasn't found.");
			}
			break;
		case 3:
			break;
		case 4:
			userInterface.printText("           All jobs you manage: ");
			Collection<Job> availableJobs = currentUser.viewSumAllJobs();
			for (Job tempJobs : availableJobs) {
				userInterface.printText(tempJobs.toStringTable());
			}
			userInterface.waitForReturnKey();
			break;
		case 5:
			userInterface.printText("         View the Volunteers for a job\n");
			userInterface.printText("Enter job ID: ");			
			LinkedList<Volunteer> volunteersForJob = currentUser.viewVolunteers(getNumber());
			for (User tempVolunteer : volunteersForJob) {
				userInterface.printText(tempVolunteer.toString());
			}
			userInterface.waitForReturnKey();
			break;
		case 6:
			userInterface.printText("Exiting...");
			break;
		}			
//		middleText.add("3 Edit the details of a job");
	}
	
	private void staffHere(User currentUser, int menuSelection) {
		
	}

	private void volunteerHere(User currentUser, int menuSelection) {
		
	}
	
	
	
	
	
	
	
	
	
	/**
	 * This class logs the user in and returns the resulting user information.
	 * @param userInterface 
	 * @return 
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
			
			userInterface.printText("Enter your email address: ");						
			String email = keyboard.nextLine();
			
			userInterface.printText("Enter your password: ");						
			String password = keyboard.nextLine();			

			for (User tempUser : allUsers) {
				if (tempUser.getEmail().equals(email)
						&& tempUser.getPassword().equals(password)) {
					return tempUser;
				}
			}			
		}
		return null;
	}
	
	private boolean getNewJobData(Manager currentUser) {
		
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
			return false;
	 	} else if (resultDays > 90) {
	 		userInterface.printText("You can't enter date more then 90 days ahead. ");
			return false;
	 	} else if (!jobsIn7Days(allJobs, futureJobDate)) {
	 		userInterface.printText("A job can't be added because you are to busy for that week.");
			return false;
		}
	 	
	 	userInterface.printText("Select job location: ");
	 	int parkName = userInterface.printMenuOptions(currentUser.getParksList());
	 	System.out.println(currentUser.getParksList().size());
	 	String aJobLocation = currentUser.getParksList().get(parkName);
	 	
	 	userInterface.printText("Enter job Duration: ");
	 	int aDuration = getNumber();
	 	if (aDuration != 1 || aDuration != 2) {
	 		return false;
	 	}
	 	
	 	userInterface.printText("Enter the number of light slots: ");
	 	int aLightSlot = getNumber();
	 	
	 	userInterface.printText("Enter the number of medium slots: ");
	 	int aMediumSlot = getNumber();
	 	
	 	userInterface.printText("Enter the number of heavy slots: ");
	 	int aHeavySlot = getNumber();
	 	
	 	userInterface.printText("Enter job's start time (for example 8:00AM): ");
	 	String aStartTime = keyboard.nextLine();
	 	
	 	userInterface.printText("Enter job's start time (for example 8:00AM): ");
	 	String aDescription = keyboard.nextLine();
	 	
		boolean result = currentUser.submitNewJob(aJobLocation, futureJobDate, aDuration, aLightSlot, aMediumSlot, 
				aHeavySlot, aDescription, aStartTime);
		
		return result;
	}
	
	/**
	 * Checking: if there during any consecutive 7 day period 
	 * more than 5 jobs or not.	 
	 * @param allJobs is a list of all Jobs.
	 * @param mydate is user entered date.
	 * @return false if there more 5 or more jobs, otherwise true.
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