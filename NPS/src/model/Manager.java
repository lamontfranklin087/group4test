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
			String thePassword, UserType theType) {
		super(theFirstName, theLastName, theEmail, thePassword, theType);
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
	public UserType getUserType() {
		return UserType.MANAGER;
	}
    
}
