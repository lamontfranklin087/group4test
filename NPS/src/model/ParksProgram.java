package model;

import java.util.Collection;

public class ParksProgram {
	private Collection<Job> allJobs;
	
	public ParksProgram() {
		User currentUser = login();
		run(currentUser);
	}

   /** Runs the main menu of the program.
    * 
    * @param theUser
    */
   private void run(User theUser) {
	   String currentUserType = theUser.getSimpleName();
	   switch (currentUserType) {
	   	   case "Urban Parks Staff"://Urban Parks Staff Menu
        	   urbanParksStaffMenu();
                    break;
           case "Park Manager"://Manager Menu
        	   managerMenu();
                    break;
           case "Volunteer"://Volunteer Menu
        	   volunteerMenu();
                    break;
           default://Error Menu
                    break;
		   }
		   
	   }
	
   private void volunteerMenu() {
	   System.out.println("Volunteer MENU!");
	   
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

private void managerMenu() {
	System.out.println("Manager MENU!");
	
}

private void urbanParksStaffMenu() {
	   System.out.println("Park Staff MENU!");

}

   /**This class logs the user in and returns the resulting user information.
    * 
    * @return 
    */
   private User login() {
	   return new Manager();
   }

}