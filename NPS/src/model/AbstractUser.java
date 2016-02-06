package model;

/**
 * An abstract class that is the basis for several subclasses of instantiatable vehicles.
 * (note: I get a spell check error for "instantiatable" but that is the correct spelling)
 *
 * @author dave1729
 * @version 305-3
 */
public abstract class AbstractUser implements User {
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String password;
	
	protected AbstractUser() {
		firstName = "Test";
		lastName = "User";
		email = "TestUser123@uw.edu";
		password = "pass";
	}
	
	protected AbstractUser(String theFirstName, String theLastName, String theEmail, String thePassword) {
		firstName = theFirstName;
		lastName = theLastName;
		email = theEmail;
		password = thePassword;
	}
	
	public abstract void viewSumAllJobs();
	public abstract void viewJobDetails();
	public abstract void printVolunteers();
	public abstract String getSimpleName()
}
