package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Creates a Manager object for use in the Parks Program.
 * 
 * @author dave1729
 * @version 2/13/16
 * @author Ihar Lavor
 * @version 2/22/2016
 * 
 */
public final class Manager extends AbstractUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	private Collection<Job> jobsAtMyParks = new LinkedList<Job>();
	ArrayList<String> parksManage;

	/**
	 * Default constructor.
	 */
	public Manager() {
		super();
	}
	/**
	 * Parameterized constructor
	 * @param theFirstName
	 * @param theLastName
	 * @param theEmail
	 * @param thePassword  
	 * @param parksList  
	 */	
	protected Manager(String theFirstName, String theLastName, String theEmail,
			String thePassword, ArrayList<String> parksList) {
		super(theFirstName, theLastName, theEmail, thePassword);
		parksManage = parksList; 
	}

	/**
	 * Simple name of Manager Class.
	 */
	@Override
	public String getSimpleName() {
		return "Park Manager";
	}
	
	@Override
	public ArrayList<String> getMainMenu() {		
		ArrayList<String>  middleText = new ArrayList<String>();
		middleText.add("Submit a new job");
		middleText.add("Delete a job");
		middleText.add("Edit the details of a job");
		middleText.add("View a summary of all upcoming jobs");
		middleText.add("View the Volunteers for a job");
		middleText.add("Exit");				
		return middleText;
	}
		
	/**
	 * Accessor for all volunteers for a specific job.
	 * @param allJobs is a list of all Jobs.
	 * @param aJobID is an identification number for a specific job (must be > 0);
	 * @return a list of volunteers, if no volunteers return null.
	 */
	public LinkedList<Volunteer> viewVolunteers(int aJobID) {
		if (allJobs != null && aJobID > 0) {
			Job foundJob = findJob(aJobID);				
			if (foundJob != null) {							
				return foundJob.getVolunteers();
			}
		}
		return null;
	}	
	
	/**
	 * Create a list of all jobs this manager manages.
	 * @param allJobs is a collection of all current jobs.
	 */
	private void managerJobList() {
		if (allJobs != null && allJobs.size() > 0) {
			jobsAtMyParks = new LinkedList<Job>();
			Job temp = allJobs.iterator().next();
			int maxNumber = 0;
			Iterator<Job> itr = allJobs.iterator();
			while (itr.hasNext()) {
				temp = itr.next();
				if (temp.getJobID() > maxNumber) {
					maxNumber = temp.getJobID();
				}
				if (temp.getJobManager().equalsIgnoreCase(this.getFirstName() 
										+ " " + this.getLastName())) {
					jobsAtMyParks.add(temp);
				}
			}
			temp.setJobID(maxNumber);
		}
	}
	
	/**
	 * Accessor to all currently pending jobs for this manager.
	 * @return a list of all jobs for this manager, or null if there is no jobs for this manager.
	 */
	@Override
	public Collection<Job> viewSumAllJobs(){ 
		managerJobList();		
		if (jobsAtMyParks != null && jobsAtMyParks.size() > 0) {
			return	jobsAtMyParks;	
		}
		return null;
	}
	
//	/**
//	 * Edit job's fields.
//	 * @param allJobs is a list of all Jobs.
//	 */
//	public StringBuilder editJob(Collection<Job> allJobs) {
//		System.out.println("Enter Job's ID number or 0 to exit:");
//		int jobIDTemp = getNumber();
//		if (jobIDTemp > 0) {
//			Job foundJob = findJob(jobIDTemp, allJobs);			
//			if (foundJob == null) {
//				return new StringBuilder("\nNo job with ID " + jobIDTemp);
//			} else {
//				foundJob.editJob(parksManage, allJobs);
//				return new StringBuilder("\nJob with ID " + jobIDTemp + " was succesfully changed");
//			}
//		}		
//		return null;
//	}

	/**
	 * Delete requested job.
	 * @param allJobs is a Collection of all Jobs.
	 * @param aJobID is an identification number for a specific job (must be > 0);
	 * @return true if job was deleted, otherwise false.
	 */
	public boolean deleteJob(int aJobID) {
		if (aJobID > 0) {
			Job temp = findJob(aJobID);			
			if (temp != null) {
				allJobs.remove(temp);
				managerJobList();
				return true;
			} 
		}
		return false;
	}

	/**
	 * Accessor to a list of parks this manager manages.
	 * @return an array list of all parks for this manager.
	 */
	public ArrayList<String> getParksList() {
		return parksManage;
	}
	/**
	 * Create new job.
	 * @param allJobs is a list of all Jobs.
	 */
	public boolean submitNewJob(String aJobLocation, Calendar aJobDate, 
			int aDuration, int aLightSlot, int aMediumSlot, 
			int aHeavySlot, String aDescription, String aStartTime) {
			
		Job newJob = new Job();
		boolean result = true;
		result = newJob.enterJobLocation(aJobLocation);
		if (!result) return result;
		result = newJob.enterDate(aJobDate);
		if (!result) return result;
		result = newJob.enterJobDuration(aDuration);
		if (!result) return result;
		result = newJob.enterJobSlot(aLightSlot, aMediumSlot, aHeavySlot);
		if (!result) return result;
		result = newJob.enterJobDescription(aDescription);
		if (!result) return result;
		result = newJob.enterStartTime(aStartTime);
		if (!result) return result;
		allJobs.add(newJob);
		return result;			
	}
		
	/**
	 * Create String with managers First and Last name, and his status.
	 */
	public String toString() {		
		StringBuilder userSummary = new StringBuilder();
		userSummary.append("Status: Park Manager");
		userSummary.append("\n");
		userSummary.append("Name: ");
		userSummary.append(getFirstName());
		userSummary.append(" ");
		userSummary.append(getLastName());
		userSummary.append("\n");
		userSummary.append(getEmail());
		return userSummary.toString();
	}
}
