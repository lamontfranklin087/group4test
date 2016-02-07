package model;

import java.util.Collection;

public final class Volunteer extends AbstractUser {

	public Volunteer() {
		super();
	}
	
	protected Volunteer(String theFirstName, String theLastName,
			String theEmail, String thePassword) {
		super(theFirstName, theLastName, theEmail, thePassword);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void viewSumAllJobs(Collection<Job> allJobs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void viewJobDetails() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getSimpleName() {
		return "Volunteer";
	}
	
	public String toString() {		
		StringBuilder userSummary = new StringBuilder();
		userSummary.append("Status: Volunteer");
		userSummary.append("\n");
		userSummary.append("Name: ");
		userSummary.append(firstName);
		userSummary.append(" ");
		userSummary.append(lastName);
		userSummary.append("\n");
		userSummary.append(email);
		return userSummary.toString();
	}
    
}
