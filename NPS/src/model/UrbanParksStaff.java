package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

/**
 * Creates an Urban Parks staff object for use in the Parks Program.
 * 
 * @author dave1729
 * @version 02/13/2016 
 */
public final class UrbanParksStaff extends AbstractUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int MAIN_MENU_OPTIONS = 4;

	public UrbanParksStaff() {
		super();
	}
	
	/**
	 * Parameterized constructor
	 * @param theFirstName
	 * @param theLastName
	 * @param theEmail
	 * @param thePassword
	 */
	protected UrbanParksStaff(String theFirstName, String theLastName,
			String theEmail, String thePassword) {
		super(theFirstName, theLastName, theEmail, thePassword);
	}

	/** 
	 * Print's the main menu and returns the current menu choice.
	 * @return menuChoice	the next menu to be entered.
	 **/
	@Override
	public void mainMenu(Collection<Job> allJobs, Collection<User> allUsers) {
		boolean exit = false;
		while (!exit) {
			int menuChoice = 0;
			ParksProgram.menuHeader(this);
			System.out.println("            ___Menu___");
	   		System.out.println("1. View a summary of all upcoming jobs");
	   		System.out.println("2. View details of a selected upcoming job");
	   		System.out.println("3. Search volunteers by last name");
	   		System.out.println("4. Exit");
			
			menuChoice = getNumber();
			while(menuChoice < 1 || menuChoice > MAIN_MENU_OPTIONS) {
				System.out.print("Must select a menu option between 1 and " + MAIN_MENU_OPTIONS + "\nSelection: ");
				menuChoice = getNumber();
			}
			switch(menuChoice){
				case 1: viewSumAllJobs(allJobs);
					break;
				case 2: viewJobDetails(allJobs);
					break;
				case 3: searchVolunteer(allUsers);
					break;				
				case 4: System.out.println("Exiting...");
					exit = true;
					break;
			}
			
		}
	}
	
	/**
	 * Search volunteer by Last Name
	 * @param allUsers
	 */
	protected void searchVolunteer(Collection<User> allUsers) {
		System.out.println("Enter Volunteer's last name:");
		String volunt = getString();
		if (allUsers != null) {
			Iterator<User> itr = allUsers.iterator();
			User user;
			while (itr.hasNext()) {
				user = itr.next();
				if (user.getLastName().equals(volunt) 
						&& user.getSimpleName().equalsIgnoreCase("Volunteer")) {
					System.out.println("Last & First Name      Email");
					System.out.print(user.getLastName() + " ");
					System.out.print(user.getFirstName() + "    ");
					System.out.println(user.getEmail());									
				}				
			}
			System.out.println("Press Enter to return to the Main Menu.");
			keyboard.nextLine();//consumer
		} else {
			System.out.println("There is no " + volunt + " volunteer.");
		}
		System.out.println();
	}
	
	/**
	 * Return the type of user (Staff, Manager or Volunteer)
	 */
	@Override
	public String getSimpleName() {
		return "Urban Parks Staff";
	}
	
	/**
	 * String representation of the Urban Parks Staff Member.
	 * 
	 * @return the resulting string
	 */
	public String toString() {		
		StringBuilder userSummary = new StringBuilder();
		userSummary.append("Status: Urban Parks Staff");
		userSummary.append("\n");
		userSummary.append("Name: ");
		userSummary.append(getFirstName());
		userSummary.append(" ");
		userSummary.append(getLastName());
		userSummary.append("\n");
		userSummary.append(getEmail());
		return userSummary.toString();
	}	
}
