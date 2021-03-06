package model;

import java.util.Collection;

/**
 * User interface.
 * @author dave1729
 * @version 02/06/2016
 * revision Ihar Lavor 2/22/2016 
 */
public interface User {	
	
	/**
	 * Accessor to all currently pending jobs for this User.
	 * @param anAllJobs list of all jobs.
	 * @return a list of all jobs for this User, or null if there is no jobs.
	 */
	public abstract Collection<Job> viewSumAllJobs(Collection<Job> anAllJobs);
	
	/**
     * Accessor to a specific job.
     * @param aJobID is a job's ID number.
     * @param anAllJobs list of all jobs.
     * @return found job, if there is no job with aJobID then return null.
     */
	public Job viewJobDetails(int aJobID, Collection<Job> anAllJobs);
	
	/**
	 * Accessor to a user type: manager, UrbanParkStaff, or volunteer.
	 * @return a user type as a string.
	 */
	public abstract String getSimpleName();
				
	/**
	 * This method sets the user's first name.
	 * @param theFirstName is user's first name (must be a string).
	 */
	public void setFirstName(String theFirstName);
	
	/**
	 * Accessor to the user's first name.
	 * @return The user's first name as a String.
	 */
	public String getFirstName();
	
	/**
	 * This method sets the user's last name.
	 * @param theLastName The user's last name.
	 */
	public void setLastName(String theLastName);
	
	/**
	 * Accessor to a user's last name.
	 * @return The user's last name as a String.
	 */
	public String getLastName();
	
	/**
	 * This method sets the user's email.
	 * @param theEmail The new user's email.
	 */
	public void setEmail(String theEmail);
		
	/**
	 * Accessor to the user's email.
	 * @return The user's email as a String.
	 */
	public String getEmail();
	
	/**
	 * This method sets the user's password.
	 * @param thePassword The user's password.
	 */
	public void setPassword(String thePassword);
	
	/**
	 * Accessor to the user's password.
	 * @return The user's password as a String.
	 */
	public String getPassword();
	
}
