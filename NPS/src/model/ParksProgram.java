package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

public class ParksProgram {
	private Collection<Job> allJobs;
	private Collection<User> allUsers;
	
	
	private Scanner keyboard;	
	
	public ParksProgram() {
		User currentUser = login();
		if (currentUser != null) {
			run(currentUser);
		}
	}

   /** Runs the main menu of the program.
    * 
    * @param theUser
    */
   private void run(User theUser) {
	   String currentUserType = theUser.getUserType();
	   switch (currentUserType) {
	   	   case "Urban Parks Staff"://Urban Parks Staff Menu
        	   urbanParksStaffMenu(theUser);
                    break;
           case "Park Manager"://Manager Menu
        	   managerMenu(theUser);
                    break;
           case "Volunteer"://Volunteer Menu
        	   volunteerMenu(theUser);
                    break;
           default://Error Menu
                    break;
		   }
		   
	   }

   /**
    * Volunteer MENU!
    * @param theUser 
    */
   private void volunteerMenu(User theUser) {
	   while(true) {	   
		   System.out.println("-------------Urban Parks Collective!------------");
		   System.out.println("You are logged in as...");
		   System.out.println(theUser.getUserType() + ", " + theUser.getFirstName()
		   					  + " " + theUser.getLastName());
		   System.out.println();
		   System.out.println("            ___Menu___");
		   System.out.println("1. View the jobs I am signed up for");
		   System.out.println("2. View a summary of all upcoming jobs");
		   System.out.println("3. View details of a selected upcoming job");
		   System.out.println("4. Volunteer for a job");
		   System.out.println("5. Exit");
		   	   
		   int temp = getNumber();
		   if (temp == 1) {
			   
		   } else if (temp == 2) {
			   
		   } else if (temp == 3) {
			   
		   } else if (temp == 4) {
			   
		   } else if (temp == 5) {
			   
		   }
	   }
   }

   private void managerMenu(User theUser) {
	   System.out.println("Manager MENU!");
	
//	   -------------Urban Parks Collective!------------
//	   You are logged in as...
//	   Park Manager, Tasha Pace!
//
//
//	               ___Menu___
//	   1) New job
//	   2) View all pending jobs
//	   3) Exit
//
//	   Selection:
//	   -------------------------------------------------

	
   }

   private void urbanParksStaffMenu(User theUser) {
	   System.out.println("Park Staff MENU!");

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
	        	if (Integer.parseInt(temp) > 0) {
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