package model;

import java.io.Serializable;
import java.util.Collection;

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
	/**
	 * Default constructor.
	 */
	public UrbanParksStaff() {
		super();
	}
	
	/**
	 * Parameterized constructor
	 * @param theFirstName manager's first name.
	 * @param theLastName manager's last name.
	 * @param theEmail manager's email address.
	 * @param thePassword password for manager's email.
	 */
	public UrbanParksStaff(String theFirstName, String theLastName,
			String theEmail, String thePassword) {
		super(theFirstName, theLastName, theEmail, thePassword);
	}

	/**
	 * Search volunteer by Last Name.
	 * @param allUsers is a collection of all users.
	 * @param aVolunteerLastName is a volunteer's last name.
	 * @return a volunteer if found, otherwise return null.
	 */
	public User searchVolunteer(Collection<User> allUsers, String aVolunteerLastName) {
		if (allUsers != null) {
			for (User tempUser : allUsers) {
				if (tempUser.getLastName().equals(aVolunteerLastName) 
						&& tempUser.getSimpleName().equalsIgnoreCase("Volunteer")) {
					return tempUser;
				}
			}
		}
		return null;
	}
	
	/**
	 * Accessor to a user type (Staff, Manager or Volunteer).
	 * @return the type of this user (Staff, Manager or Volunteer)
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
