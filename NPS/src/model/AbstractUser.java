package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

/**
 * An abstract class that is the basis for several subclasses of instantiatable Users.
 * 
 * @author dave1729
 * @version 360-1
 * @author Ihar Lavor
 * @version 02/12/2016 added findJob(), getNumber() functions and modified other functions.
 */
public abstract class AbstractUser implements User,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final transient Scanner scan = new Scanner(System.in);
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private transient Scanner keyboard;	
	
	protected AbstractUser() {
		firstName = "Test";
		lastName = "User";
		email = "TestUser@uw.edu";
		password = "pass";
	}
	
	protected AbstractUser(String theFirstName, String theLastName, String theEmail, String thePassword) {
		
		firstName = theFirstName;
		lastName = theLastName;
		email = theEmail;
		password = thePassword;
	}
		
	public abstract String getSimpleName();
	
	/** Print's the main menu for that user and starts their chain of menu's */
	public abstract void mainMenu(Collection<Job> allJobs, Collection<User> allUsers);
	
	/**
	   * This method sets the user's first name.
	   * This allows for users to update or change their first name.
	   * @param theFirstName The new first name the user would like.
	   */
	public void setFirstName(String theFirstName) {
		this.firstName = theFirstName;
	}
	
	/**
	   * Gets the user's first name.
	   * This allows for displaying of the user's first name.
	   * @return firstName The user's first name.
	   */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	   * This method sets the user's last name.
	   * This allows for users to update or change their last name.
	   * @param theLastName The new last name the user would like.
	   */
	public void setLastName(String theLastName) {
		this.lastName = theLastName;
	}
	
	/**
	   * Gets the user's last name.
	   * This allows for displaying of the user's last name.
	   * @return lastName The user's last name.
	   */
	public String getLastName() {
		return lastName;
	}
	
	/**
	   * This method sets the user's email.
	   * This allows for users to update or change their email.
	   * @param email The new email the user would like to be contacted at.
	   */
	public void setEmail(String theEmail) {
		this.email = theEmail;
	}	
	
	/**
	   * Gets the user's email.
	   * This allows for displaying of the user's email.
	   * @return email The user's email.
	   */
	public String getEmail() {
		return email;
	}
	
	/**
	   * This method sets the user's password.
	   * This allows for users to update or change their password.
	   * @param thePassword The new password the user would like.
	   */
	public void setPassword(String thePassword) {
		this.password = thePassword;
	}
	
	/**
	   * Gets the user's password name.
	   * This allows for comparing of the user's password.
	   * @return password The user's password.
	   */
	public String getPassword() {
		return password;
	}
	
	public void viewSumAllJobs(Collection<Job> allJobs){ 
		if (allJobs != null && allJobs.size() > 0) {
			Iterator<Job> itr = allJobs.iterator();
			System.out.println("ID     " + "Date\t    " + "Duration\t" 
	                + "Slots\t" + "Manager\t\t" + "Locaton\t\t" + "Description");
			Job temp;
			while (itr.hasNext()) {
				temp = itr.next();				
				System.out.println(temp.toStringTable());										
			}
		} else {
			System.out.println("No jibs available at this time.");
		}
	}
	
	public void viewJobDetails(Collection<Job> allJobs) {
		System.out.println("Please enter Job ID to view job details or 0 to quit: ");
		int id = getNumber();
		if(id != 0){
			allJobs.forEach(job->{
				if(job.getJobID() == id){
				ParksProgram.menuHeader(this);
				System.out.println("            ___Job Details___");
				System.out.println(job.toString());
				}
			});
		}
		
	}
	
	/**
	 * Parse string to integer.
	 * @return an integer number from 1 to ...
	 */
	protected int getNumber() {
		int result = -1;
		keyboard = new Scanner(System.in);
		
		while(true){
	        try {	        	
	        	String temp = keyboard.nextLine();
	        	if (Integer.parseInt(temp) >= 0) {
	        		result = Integer.parseInt(temp);
	        		break;
	        	}
	        } catch(NumberFormatException ne) {
	            System.out.println("That's not a write number.");	            
	        }	
		}
		return result;
	}
	/**
	 * Reads user's input in console.
	 * @return String
	 */
	protected String getString() {
		keyboard = new Scanner(System.in);		
		return keyboard.nextLine();
	}
	
	/**
	 * Iterator for a job list.
	 * @param jobID
	 * @param allJobs
	 * @return
	 */
	protected Job findJob(int jobID, Collection<Job> allJobs) {
		if (allJobs != null) {
		   Iterator<Job> itr = allJobs.iterator();	  
		   while (itr.hasNext()) {
			   Job temp = itr.next();
			   if (temp.getJobID() == jobID) {
				   return temp;
			   }
		   }
		}
	   return null;
   }	
}
