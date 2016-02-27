package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * An abstract class that is the basis for several subclasses of instantiatable Users.
 * 
 * @author dave1729
 * @version 360-1
 * @author Ihar Lavor
 * @version 02/12/2016 added findJob(), getNumber() functions and modified other functions.
 * revision Ihar Lavor 2/22/2016 
 */
public abstract class AbstractUser implements User,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected transient Scanner scan = new Scanner(System.in);
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	//protected Collection<Job> allJobs;
	//private Collection<User> allUsers;	
	
	/**
	 * Default constructor for a User.
	 */
	protected AbstractUser() {
		firstName = "Test";
		lastName = "User";
		email = "TestUser@uw.edu";
		password = "pass";
	}
		
	/**
	 * Parameterized constructor for a User.
	 * @param theFirstName User's first name.
	 * @param theLastName User's last name.
	 * @param theEmail User's email address.
	 * @param thePassword User's password.
	 */
	protected AbstractUser(String theFirstName, String theLastName, 
						   String theEmail, String thePassword) {		
		firstName = theFirstName;
		lastName = theLastName;
		email = theEmail;
		password = thePassword;		
	}
	
	/**
	 * Accessor to a user type: manager, UrbanParkStaff, or volunteer.
	 * @return a user type as a string.
	 */
	public abstract String getSimpleName();
	
	/** 
	 * Accessor to a menu for a user.
	 * @return an array list of strings where each string represent one menu option.
	 */
	public abstract ArrayList<String> getMainMenu();
			
	/**
	 * This method sets the user's first name.
	 * @param theFirstName is user's first name (must be a string).
	 */
	public void setFirstName(String theFirstName) {
		this.firstName = theFirstName;
	}
	
	/**
	 * Accessor to the user's first name.
	 * @return The user's first name as a String.
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * This method sets the user's last name.
	 * @param theLastName The user's last name.
	 */
	public void setLastName(String theLastName) {
		this.lastName = theLastName;
	}
	
	/**
	 * Accessor to a user's last name.
	 * @return The user's last name as a String.
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * This method sets the user's email.
	 * @param theEmail The new user's email.
	 */
	public void setEmail(String theEmail) {
		this.email = theEmail;
	}	
	
	/**
	 * Accessor to the user's email.
	 * @return The user's email as a String.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * This method sets the user's password.
	 * @param thePassword The user's password.
	 */
	public void setPassword(String thePassword) {
		this.password = thePassword;
	}
	
	/**
	 * Accessor to the user's password.
	 * @return The user's password as a String.
	 */
	public String getPassword() {
		return password;
	}	
	
	/**
	   * Accessor to a list of all jobs.
	   * @return list of all jobs.
	   */
	public Collection<Job> viewSumAllJobs(Collection<Job> anAllJobs) {		
		return anAllJobs;	
	}
	
	/**
     * Accessor to a specific job.
     * @param aJobID is a job's ID number.
     * @return found job, if there is no job with aJobID then return null.
     */
	public Job viewJobDetails(int aJobID, Collection<Job> anAllJobs) {	
		return findJob(aJobID, anAllJobs);			
	}
		
	/**
	 * Find a job from a list of all jobs.
	 * @param jobID job's ID.
	 * @param anAllJobs 
	 * @return found job.
	 */
	protected Job findJob(int jobID, Collection<Job> anAllJobs) {
		if (anAllJobs != null) {
			for (Job tempJob : anAllJobs) {
				if (tempJob.getJobID() == jobID) {
					   return tempJob;
				}
			}
		}
	   return null;
   }
}
