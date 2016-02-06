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
	
	protected abstract void viewSumAllJobs();
	protected abstract void viewJobDetails();
	protected abstract void printVolunteers();
	protected abstract String getSimpleName();
	
	protected void setFirstName(String theFirstName) {
		this.firstName = theFirstName;
	}
	protected void getFirstName() {
		return firstName;
	}
	protected void setLastName(String theLastName) {
		this.lastName = lastName;
	}
	protected void getLastName() {
		return lastName;
	}
	protected void setEmail(String theEmail) {
		this.email = theEmail;
	}
	protected void getEmail() {
		return email;
	}
	protected void setPassword(String thePassword) {
		this.password = thePassword;
	}
	protected void getPassword() {
		return password;
	}
}
