package model;

import java.util.Collection;

public final class UrbanParksStaff extends AbstractUser {

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

	@Override
	public void mainMenu(Collection<Job> allJobs, Collection<User> allUsers) {
		// TODO Auto-generated method stub
		
	}
    
}
