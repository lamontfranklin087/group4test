package model;

/**
 * Creates a Manager object.
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
		return "Park Manager";
	}
    
}
