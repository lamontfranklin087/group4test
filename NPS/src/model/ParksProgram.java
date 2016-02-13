package model;

import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Login menu, read and write from/to text files.
 * @author Ihar Lavor
 * @author Lamont Franklin
 * @version 02/06/2016
 * 
 * revision Lamont Franklin 2/10/2016 added duty level functionality
 * revision Ihar Lavor 2/12/2016 
 */
public class ParksProgram {
	private Collection<Job> allJobs;
	private Collection<User> allUsers;		
	private Scanner keyboard;	

	public ParksProgram() {
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
		
		checkForPastJobs();
		User currentUser;
		do {
			currentUser = login();
			if (currentUser != null) {
				currentUser.mainMenu(allJobs, allUsers);
			}
		} while (currentUser != null);
		
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
		int curMonth = currentDate.get(Calendar.MONTH) + 1;
		int curDate = currentDate.get(Calendar.DATE);
		int curYear = currentDate.get(Calendar.YEAR);
		
		Calendar jobDate;
		int jobDay;
		int jobYear;
		int jobMonth;
		
		if (allJobs != null && allJobs.size() > 0) {
			Iterator<Job> itr = allJobs.iterator();
			while (itr.hasNext()) {
				Job temp = itr.next();
				jobDate = temp.getDate();
				
				jobMonth = jobDate.get(Calendar.MONTH) + 1;
				jobDay = jobDate.get(Calendar.DATE);
				jobYear = jobDate.get(Calendar.YEAR);
				
				int resultDays = (jobYear - curYear) * 12 * 30 
						+ (jobMonth - curMonth) * 30
						+ (jobDay - curDate);
				if (resultDays < 0) {
					//add code here.....
				}
			}
		}	
	}

	/**
	 * Header for all menus
	 * @param theUser
	 */
	public static void menuHeader(User theUser){
		System.out.println();
		System.out.println("-------------Urban Parks Collective!------------");
		System.out.println("You are logged in as...");
		System.out.println(theUser.getSimpleName() + ", " + theUser.getFirstName()
		+ " " + theUser.getLastName());
		System.out.println();
	}

	/**This class logs the user in and returns the resulting user information.
	 * 
	 * @return 
	 */
	private User login() {
		System.out.println("-------------Urban Parks Collective!------------");
		System.out.println();
		System.out.println("1. Login");
		System.out.println("2. Terminate program");

		if (getNumber() == 1) {		   
			keyboard = new Scanner(System.in);

			System.out.println("Enter your email address:");
			String email = keyboard.nextLine();

			System.out.println("Enter your password:");
			String password = keyboard.nextLine();

			Iterator<User> itr = allUsers.iterator();
			
			while (itr.hasNext()) {
				User temp = itr.next();

				if (temp.getEmail().equals(email)
						&& temp.getPassword().equals(password)) {
					return temp;
				}				
			}
		}    
		return null;
	}

	/**
	 * Parse string to integer.
	 * @return an integer number from 1 to ...
	 */
	private int getNumber() {
		int result = -1;
		keyboard = new Scanner(System.in);

		while(true){
			try {	        	
				String temp = keyboard.nextLine();
				if (Integer.parseInt(temp) >= 0) {
					result = Integer.parseInt(temp);
					break;
				}
			} catch(NumberFormatException ne) {
				System.out.println("That's not a write number.");	            
			}	
		}
		return result;
	}

}