package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Creates an Urban Parks staff object for use in the Parks Program.
 * 
 * @author dave1729
 * @version 02/13/2016 
 * revision Ihar Lavor 2/22/2016 
 */
public final class UrbanParksStaff extends AbstractUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	@Override
	public ArrayList<String> getMainMenu() {		
		ArrayList<String>  middleText = new ArrayList<String>();
		middleText.add("View a summary of all upcoming jobs");
		middleText.add("View details of a selected upcoming job");
		middleText.add("Search volunteers by last name");
		middleText.add("Exit");				
		return middleText;
	}
	
	@Override
	public ArrayList<String> getMethodList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("viewSumAllJobs");
		list.add("viewJobDetails");
		list.add("searchVolunteer");
		
		return list;
	}
	
	/**
	 * Search volunteer by Last Name
	 * @param allUsers
	 * @return 
	 */
	public StringBuilder searchVolunteer(Collection<User> allUsers) {
		System.out.println("Enter Volunteer's last name:");
		scan = new Scanner(System.in);		
		String volunt = scan.nextLine();
		StringBuilder volunteerData = new StringBuilder();
		if (allUsers != null) {
			Iterator<User> itr = allUsers.iterator();
			User user;
			while (itr.hasNext()) {
				user = itr.next();
				if (user.getLastName().equals(volunt) 
						&& user.getSimpleName().equalsIgnoreCase("Volunteer")) {
					volunteerData.append("\nLast & First Name      Email");
					volunteerData.append("\n" + user.getLastName() + " "
										      + user.getFirstName() + "    "
										      +	user.getEmail());									
				}				
			}
		} else {
			return new StringBuilder("There is no " + volunt + " volunteer.");
		}
		return volunteerData;
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
