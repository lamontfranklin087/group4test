package model;

import java.util.Collection;

public interface User {


	
	public abstract void viewSumAllJobs(Collection<Job> allJobs); // OK for all
	public abstract void viewJobDetails(); // OK for Volunteer and Staff only
	//protected abstract void printVolunteers();// OK for staff only (recommend to move in staff class)
	public abstract String getSimpleName();
	/** Print's the main menu for that user and starts their chain of menu's 
	 * @param allUsers 
	 * @param allJobs */
	public abstract void mainMenu(Collection<Job> allJobs, Collection<User> allUsers);
	
	
	/**
	   * This method sets the user's first name.
	   * This allows for users to update or change their first name.
	   * @param theFirstName The new first name the user would like.
	   * @return Nothing.
	   */
	public void setFirstName(String theFirstName);
	
	/**
	   * Gets the user's first name.
	   * This allows for displaying of the user's first name.
	   * @param None.
	 * @return 
	   * @return firstName The user's first name.
	   */
	public String getFirstName();
	
	/**
	   * This method sets the user's last name.
	   * This allows for users to update or change their last name.
	   * @param theLastName The new last name the user would like.
	   * @return Nothing.
	   */
	public void setLastName(String theLastName);
	
	/**
	   * Gets the user's last name.
	   * This allows for displaying of the user's last name.
	   * @param None.
	 * @return 
	   * @return lastName The user's last name.
	   */
	public String getLastName();
	
	/**
	   * This method sets the user's email.
	   * This allows for users to update or change their email.
	   * @param email The new email the user would like to be contacted at.
	   * @return Nothing.
	   */
	public void setEmail(String theEmail);
	
	
	/**
	   * Gets the user's email.
	   * This allows for displaying of the user's email.
	   * @param None.
	 * @return 
	   * @return email The user's email.
	   */
	public String getEmail();
	
	/**
	   * This method sets the user's password.
	   * This allows for users to update or change their password.
	   * @param thePassword The new password the user would like.
	   * @return Nothing.
	   */
	public void setPassword(String thePassword);
	
	/**
	   * Gets the user's password name.
	   * This allows for comparing of the user's password.
	   * @param None.
	 * @return 
	   * @return password The user's password.
	   */
	public String getPassword();
	
}
