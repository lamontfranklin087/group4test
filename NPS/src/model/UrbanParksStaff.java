package model;


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
	protected void viewSumAllJobs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void viewJobDetails() {
		// TODO Auto-generated method stub
		
	}

	protected void printVolunteers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getSimpleName() {
		return "Urban Parks Staff";
	}
    
}
