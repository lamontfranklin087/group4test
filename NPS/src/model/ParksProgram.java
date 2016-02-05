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
	   UserType currentUserType = theUser.getUserType();
	   switch (currentUserType.getVal()) {
	   	   case 1://Urabn Parks Staff Menu
        	   urbanParksStaffMenu();
                    break;
           case 2://Manager Menu
        	   managerMenu();
                    break;
           case 3://Volunteer Menu
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