package model;

import java.io.Serializable;
import java.util.Collection;

public final class UrbanParksStaff extends AbstractUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UrbanParksStaff() {
		super();
	}

	protected UrbanParksStaff(String theFirstName, String theLastName,
			String theEmail, String thePassword) {
		super(theFirstName, theLastName, theEmail, thePassword);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void viewSumAllJobs(Collection<Job> allJobs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewJobDetails() {
		// TODO Auto-generated method stub
		
	}

	protected void printVolunteers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSimpleName() {
		return "Urban Parks Staff";
	}
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
	@Override
	public void mainMenu(Collection<Job> allJobs, Collection<User> allUsers) {
		// TODO Auto-generated method stub
		
	}
    
}
