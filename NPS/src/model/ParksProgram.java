package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class ParksProgram {
	private Collection<Job> allJobs;
	private Collection<User> allUsers;		
	private Scanner keyboard;	
	
	public ParksProgram() {
		User testUser123 = new Manager();
		allJobs = new LinkedList<Job>();
		allUsers = new LinkedList<User>();
		allUsers.add(testUser123);
		
		User currentUser = login();
		if (currentUser != null) {
			run(currentUser);
		}
	}

   /** 
    * Runs the main menu of the program.
    * @param currentUser
    */
   private void run(User currentUser) {
	   currentUser.mainMenu();
	   
	   
//	   Ihar's Version
//	   if (currentUser instanceof UrbanParksStaff) {
//		   urbanParksStaffMenu((UrbanParksStaff) currentUser);
//	   } else if (currentUser instanceof Manager) {
//		   managerMenu((Manager) currentUser);
//	   } else if (currentUser instanceof Volunteer) {
//		   volunteerMenu((Volunteer) currentUser);
//	   }
   }

   /**
    * Volunteer MENU!
    * @param theUser 
    */
   private void volunteerMenu(Volunteer theUser) {
	   while(true) {	   
		   System.out.println("-------------Urban Parks Collective!------------");
		   System.out.println("You are logged in as...");
		   System.out.println(theUser.getSimpleName() + ", " + theUser.getFirstName()
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
			   
		   } else {
			   break;
		   }
	   }
   }

   private void managerMenu(Manager theUser) {
	   while(true) {	   
		   System.out.println("-------------Urban Parks Collective!------------");
		   System.out.println("You are logged in as...");
		   System.out.println(theUser.getSimpleName() + ", " + theUser.getFirstName()
		   					  + " " + theUser.getLastName());
		   System.out.println();
		   System.out.println("            ___Menu___");
		   System.out.println("1. Submit a new job");
		   System.out.println("2. Delete a job");
		   System.out.println("3. Edit the details of a job");
		   System.out.println("4. View a summary of all upcoming jobs");
		   System.out.println("5. View the Volunteers for a job");
		   System.out.println("6. Exit");
		   	   
		   int temp = getNumber();
		   if (temp == 1) {
			   allJobs.add(theUser.submitNewJob());
		   } else if (temp == 2) {
			   System.out.println("Enter Job's ID number or 0 to exit:");
			   int jobIDTemp = getNumber();
			   if (jobIDTemp > 0) {
				   Job foundJob = findJob(jobIDTemp);
				   if (foundJob == null) {
					   System.out.println("No job with ID " + jobIDTemp);
				   } else {
					   allJobs.remove(foundJob);
					   System.out.println("Job with ID " + jobIDTemp + " was deleted.");
				   }
			   }
		   } else if (temp == 3) {
			   System.out.println("Enter Job's ID number or 0 to exit:");
			   int jobIDTemp = getNumber();
			   if (jobIDTemp > 0) {
				   Job foundJob = findJob(jobIDTemp);
				   if (foundJob == null) {
					   System.out.println("No job with ID " + jobIDTemp);
				   } else {
					   foundJob.editJob();
				   }
			   }
		   } else if (temp == 4) {
			   theUser.viewSumAllJobs(allJobs);
		   } else if (temp == 5) {
			   theUser.viewVolunteers(allJobs);
		   } else {
			   break;
		   }
	   }
   }

   private void urbanParksStaffMenu(UrbanParksStaff theUser) {
	   while(true) {	   
		   System.out.println("-------------Urban Parks Collective!------------");
		   System.out.println("You are logged in as...");
		   System.out.println(theUser.getSimpleName() + ", " + theUser.getFirstName()
		   					  + " " + theUser.getLastName());
		   System.out.println();
		   System.out.println("            ___Menu___");
		   System.out.println("1. Search volunteers by last name");
		   System.out.println("2. View a summary of all upcoming jobs");
		   System.out.println("3. View details of a selected upcoming job");
		   System.out.println("4. Exit");
		   	   
		   int temp = getNumber();
		   if (temp == 1) {
			   
		   } else if (temp == 2) {
			   
		   } else if (temp == 3) {
			   
		   } else {
			   break;
		   }
	   }
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
			   
			   if (temp instanceof UrbanParksStaff) {
				   if (((UrbanParksStaff) temp).getEmail().equals(email)
						   && ((UrbanParksStaff) temp).getPassword().equals(password)) {
					   return temp;
				   }
			   } else if (temp instanceof Manager) {
				   if (((Manager) temp).getEmail().equals(email)
						   && ((Manager) temp).getPassword().equals(password)) {
					   return temp;
				   }
			   } else if (temp instanceof Volunteer) {
				   if (((Volunteer) temp).getEmail().equals(email)
						   && ((Volunteer) temp).getPassword().equals(password)) {
					   return temp;
				   }
			   }	
		   }
	   }    
	   return null;
   }
   
   private Job findJob(int jobID) {
	   Iterator<Job> itr = allJobs.iterator();	  
	   while (itr.hasNext()) {
		   Job temp = itr.next();
		   if (temp.getJobID() == jobID) {
			   return temp;
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