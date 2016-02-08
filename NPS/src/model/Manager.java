package model;

import java.util.Collection;

/**
 * Creates a Truck object for use in the Easy Street GUI.
 * 
 * @author dave1729
 * @version 305-3
 */
public final class Manager extends AbstractUser {
	
	public final int MAIN_MENU_OPTIONS = 6;

	public Manager() {
		super();
	}
	
	protected Manager(String theFirstName, String theLastName, String theEmail,
			String thePassword) {
		super(theFirstName, theLastName, theEmail, thePassword);
		// TODO Auto-generated constructor stub
	}
	
	public Job submitNewJob() {
		Job newJob = new Job();
		newJob.createJob();
		return newJob;
	}

	@Override
	public void viewSumAllJobs(Collection<Job> allJobs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewJobDetails() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSimpleName() {
		return "Park Manager";
	}
	
	public void viewVolunteers(Collection<Job> allJobs) {
		
	}
	
	/** Print's the main menu and returns the current menu choice.
	 * 
	 * @return menuChoice	the next menu to be entered.
	 * */
	@Override
	public void mainMenu() {
		int menuChoice = 0;
//		System.out.println( "-------------Urban Parks Collective!-----------\n\n" +
//							"You are logged in as...\n" +
//							"Park Manager, " + firstName + " " + lastName + "!\n\n" +
//							"            ___Menu___\n" +
//							"1) Add New job\n" +
//							"2) View all pending jobs\n" +
//							"3) Exit\n\n" +
//							"Selection: ");
		
		System.out.println("-------------Urban Parks Collective!------------");
		System.out.println("You are logged in as...");
		System.out.println(getSimpleName() + ", " + getFirstName()
	   					  + " " + getLastName());
		System.out.println();
		System.out.println("            ___Menu___");
   		System.out.println("1. Submit a new job");
   		System.out.println("2. Delete a job");
   		System.out.println("3. Edit the details of a job");
   		System.out.println("4. View a summary of all upcoming jobs");
   		System.out.println("5. View the Volunteers for a job");
   		System.out.println("6. Exit");
		
		menuChoice = getNumber();//scan.nextInt();
		while(menuChoice < 1 || menuChoice > MAIN_MENU_OPTIONS) {
			System.out.print("Must select a menu option between 1 and " + MAIN_MENU_OPTIONS + "\nSelection: ");
			menuChoice = getNumber();//scan.nextInt();
		}
		switch(menuChoice){
			case 1: submitNewJob();
				break;
			case 2: 
				break;
			case 3: 
				break;
			case 4:
				break;
			case 5: 
				break;
			case 6: System.out.println("Exiting...");
				break;
		}
		
	}

	public void viewMyJobs() {
		// TODO Auto-generated method stub
		
	}
}
