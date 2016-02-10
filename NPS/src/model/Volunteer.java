package model;

import java.io.Serializable;
import java.util.Collection;

public final class Volunteer extends AbstractUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Volunteer() {
		super();
	}
	
	protected Volunteer(String theFirstName, String theLastName,
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

	@Override
	public String getSimpleName() {
		return "Volunteer";
	}
	
	public String toString() {		
		StringBuilder userSummary = new StringBuilder();
		userSummary.append("Status: Volunteer");
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
