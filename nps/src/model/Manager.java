package model;

/**
 * Creates a Truck object for use in the Easy Street GUI.
 * 
 * @author dave1729
 * @version 305-3
 */
public final class Manager extends AbstractUser {

	public Manager() {
		super();
	}
	
	protected Manager(String theFirstName, String theLastName, String theEmail,
			String thePassword) {
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
		return "Park Manager";
	}
    
}
