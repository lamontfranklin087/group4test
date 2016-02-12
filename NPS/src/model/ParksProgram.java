package model;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class ParksProgram {
	private Collection<Job> allJobs;
	private Collection<User> allUsers;		
	private Scanner keyboard;	

	public ParksProgram() {
		try {
			allUsers = SerialStartup.serialReadUsers();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			allUsers = new LinkedList<User>();
		}
		try {
			allJobs = SerialStartup.serialReadJobs();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			allJobs = new LinkedList<Job>();
		}

		User currentUser = login();
		
		while (currentUser != null) {
			currentUser = login();
			if (currentUser != null) {
				currentUser.mainMenu(allJobs, allUsers);
			}
		}
		System.out.println("done 100%");
		
		if (allUsers != null) {
			try {
				SerialStartup.serialWriteUsers(allUsers);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();			
			}
		}
		if (allJobs != null) {
			try {
				SerialStartup.serialWriteJobs(allJobs);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();			
			}
		}
	}

	/*
	 * Alternative menu option for easier consistency
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