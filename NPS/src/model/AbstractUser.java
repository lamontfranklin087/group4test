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
	
	
	/**
	   * This method sets the user's first name.
	   * This allows for users to update or change their first name.
	   * @param theFirstName The new first name the user would like.
	   * @return Nothing.
	   */
	protected void setFirstName(String theFirstName) {
		this.firstName = theFirstName;
	}
	
	/**
	   * Gets the user's first name.
	   * This allows for displaying of the user's first name.
	   * @param None.
	   * @return firstName The user's first name.
	   */
	protected void getFirstName() {
		return firstName;
	}
	
	/**
	   * This method sets the user's last name.
	   * This allows for users to update or change their last name.
	   * @param theLastName The new last name the user would like.
	   * @return Nothing.
	   */
	protected void setLastName(String theLastName) {
		this.lastName = lastName;
	}
	
	/**
	   * Gets the user's last name.
	   * This allows for displaying of the user's last name.
	   * @param None.
	   * @return lastName The user's last name.
	   */
	protected void getLastName() {
		return lastName;
	}
	
	/**
	   * This method sets the user's email.
	   * This allows for users to update or change their email.
	   * @param email The new email the user would like to be contacted at.
	   * @return Nothing.
	   */
	protected void setEmail(String theEmail) {
		this.email = theEmail;
	}
	
	
	/**
	   * Gets the user's email.
	   * This allows for displaying of the user's email.
	   * @param None.
	   * @return email The user's email.
	   */
	protected void getEmail() {
		return email;
	}
	
	/**
	   * This method sets the user's password.
	   * This allows for users to update or change their password.
	   * @param thePassword The new password the user would like.
	   * @return Nothing.
	   */
	protected void setPassword(String thePassword) {
		this.password = thePassword;
	}
	
	/**
	   * Gets the user's password name.
	   * This allows for comparing of the user's password.
	   * @param None.
	   * @return password The user's password.
	   */
	protected void getPassword() {
		return password;
	}
}
