package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
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

	public ParksProgram(Menu theMenu) {
		Scanner scan = new Scanner(System.in);
		int menuSelection = 0;
		boolean isInteger = false;
		//SPANISH MENU
		String menuName = "Spanish Menu";
		
		StringBuilder middleText = new StringBuilder();
		middleText.append("This Menu is all in spanish.");
		
		ArrayList<String> menuList = new ArrayList<String>();
		menuList.add("Option Uno");
		menuList.add("Option Dos");
		menuList.add("Option Tres");

		theMenu.updateMenu(menuName, middleText, menuList);
		
		//get next menu selection
		menuSelection = scan.nextInt();
		while(menuSelection <= 0 || menuSelection > theMenu.getMenuOptions().size()) {
			System.out.println("Sorry, please try a number between 1 and " + theMenu.getMenuOptions().size());
			if(scan.hasNextInt()) {
				menuSelection = scan.nextInt();
			}
			else if(scan.hasNext()){
				//consumer extra input
				scan.next();
				//ask again for int input
				System.out.println("Sorry, selection must be a number value.");
				menuSelection = scan.nextInt();
			}
		}
		
		//ENGLISH MENU
		menuName = "English Menu";
		
		middleText = new StringBuilder();
		middleText.append("NOW this Menu is all in english!");
		
		menuList = new ArrayList<String>();
		menuList.add("Option One");
		menuList.add("Option Two");
		
		Menu menu2 = new Menu(menuName, middleText, menuList);
		theMenu.updateMenu(menu2);
//		try {
//			allUsers = SerialStartup.serialReadUsers();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			allUsers = new LinkedList<User>();
//		}
//		try {
//			allJobs = SerialStartup.serialReadJobs();						
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			allJobs = new LinkedList<Job>();
//		}
//		
//		checkForPastJobs();
//		User currentUser;
//		do {
//			currentUser = login();
//			if (currentUser != null) {
//				//We have a User and allJobs
//				run(currentUser, allJobs, allUsers);
//			}
//		} while (currentUser != null);
//		
//		if (allUsers != null) {
//			try {
//				SerialStartup.serialWriteUsers(allUsers);
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();			
//			}
//		}
//		if (allJobs != null) {
//			try {
//				SerialStartup.serialWriteJobs(allJobs);
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();			
//			}
//		}
	}

	private void run(User currentUser, Collection<Job> allJobs, Collection<User> allUsers) {
		
		
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
				
			 	int resultDays = jobDayOfYear - curDayOfYear;
				
				if (resultDays <= 0) {				
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