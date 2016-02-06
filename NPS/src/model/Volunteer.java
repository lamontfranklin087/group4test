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
	public void viewSumAllJobs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewJobDetails() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printVolunteers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSimpleName() {
		return "Volunteer";
	}
    
}
