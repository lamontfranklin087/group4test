package model;

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
	protected void viewSumAllJobs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void viewJobDetails() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void printVolunteers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getSimpleName() {
		return "Volunteer";
	}
    
}
