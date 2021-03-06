package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import controller.CheckBusinessRules;

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
	private ArrayList<String> parksManage;

	/**
	 * Default constructor.
	 */
	public Manager() {
		super();
	}
	/**
	 * Parameterized constructor
	 * @param theFirstName manager's first name.
	 * @param theLastName manager's last name.
	 * @param theEmail manager's email address.
	 * @param thePassword password for manager's email.
	 * @param parksList list of parks this manager manages.
	 */	
	public Manager(String theFirstName, String theLastName, String theEmail,
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
	
	/**
	 * Accessor for all volunteers for a specific job.
	 * @param anAllJobs is a list of all Jobs.
	 * @param aJobID is an identification number for a specific job (must be greater 0);
	 * @return a list of volunteers, if no volunteers return null.
	 */
	public LinkedList<Volunteer> viewVolunteers(int aJobID, Collection<Job> anAllJobs) {
		if (anAllJobs != null && aJobID > 0) {
			Job foundJob = findJob(aJobID, anAllJobs);				
			if (foundJob != null) {							
				return foundJob.getVolunteers();
			}
		}
		return null;
	}	
	
	/**
	 * Create a list of all jobs this manager manages.
	 * @param anAllJobs list of all jobs.
	 */
	public void managerJobList(Collection<Job> anAllJobs) {
		if (anAllJobs != null && anAllJobs.size() > 0) {
			jobsAtMyParks = new LinkedList<Job>();
			Job temp = anAllJobs.iterator().next();
			int maxNumber = 0;
			Iterator<Job> itr = anAllJobs.iterator();
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
		}
	}
	
	/**
	 * Accessor to all currently pending jobs for this manager.
	 * @return a list of all jobs for this manager, or null if there is no jobs for this manager.
	 */
	@Override
	public Collection<Job> viewSumAllJobs(Collection<Job> anAllJobs){ 
		managerJobList(anAllJobs);		
		if (jobsAtMyParks != null && jobsAtMyParks.size() > 0) {
			return	jobsAtMyParks;	
		}
		return null;
	}
	
	/**
	 * Edit job's fields.
	 * @param aJobLocation job's location - park name.
	 * @param aJobDate job's date.
	 * @param aDuration job's duration period.
	 * @param aLightSlot number of light slots for this job.
	 * @param aMediumSlot number of medium slots for this job.
	 * @param aHeavySlot number of heavy slots for this job.
	 * @param aDescription general description for this job.
	 * @param aStartTime job's start time.
	 * @param anAllJobs list of all jobs.
	 * @param jobID jobs ID number.
	 * @return true if changes were applied, otherwise false.
	 * @throws MyOwnException if something wasn't right.
	 */
	public boolean editJob(String aJobLocation, Calendar aJobDate, int aDuration,
			 int aLightSlot, int aMediumSlot, int aHeavySlot, String aDescription,
			 String aStartTime, Collection<Job> anAllJobs, int jobID) throws MyOwnException {
		
		Job tempJob = findJob(jobID, anAllJobs);
		
		boolean result = true;
		if (aJobLocation != null) {
			result = tempJob.setJobLocation(aJobLocation);
			if (!result) return result;
		} else if(aJobDate != null) {
			result = tempJob.setDate(aJobDate);
			if (!result) return result;
		} else if (aDuration > 0) {
			result = tempJob.setJobDuration(aDuration);
			if (!result) return result;
		} else if (aLightSlot > 0 || aMediumSlot > 0 || aHeavySlot > 0) {
			result = tempJob.setJobSlot(aLightSlot, aMediumSlot, aHeavySlot);
			if (!result) return result;
		} else if (aDescription != null) {
			result = tempJob.setJobDescription(aDescription);
			if (!result) return result;
		} else if (aStartTime != null) {
			result = tempJob.setStartTime(aStartTime);
			if (!result) return result;
		}
		return result;		
	}

	/**
	 * Delete requested job.
	 * @param anAllJobs is a Collection of all Jobs.
	 * @param aJobID is an identification number for a specific job (must be greater 0);
	 * @return true if job was deleted, otherwise false.
	 */
	public boolean deleteJob(int aJobID, Collection<Job> anAllJobs) {
		if (aJobID > 0) {
			Job temp = findJob(aJobID, anAllJobs);			
			if (temp != null && temp.getJobManager().equalsIgnoreCase(
								getFirstName() + " " + getLastName())) {
				anAllJobs.remove(temp);
				managerJobList(anAllJobs);
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
	 * @param aJobManager first and last name of manger for this job.
	 * @param aJobLocation job's location - park name.
	 * @param aJobDate job's date.
	 * @param aDuration job's duration period.
	 * @param aLightSlot number of light slots for this job.
	 * @param aMediumSlot number of medium slots for this job.
	 * @param aHeavySlot number of heavy slots for this job.
	 * @param aDescription general description for this job.
	 * @param aStartTime job's start time.
	 * @param anAllJobs list of all jobs.
	 * @return true if changes were applied, otherwise false.
	 * @throws MyOwnException if something wasn't right.
	 */
	public boolean submitNewJob(String aJobManager, String aJobLocation, Calendar aJobDate, 
			int aDuration, int aLightSlot, int aMediumSlot, 
			int aHeavySlot, String aDescription, String aStartTime, Collection<Job> anAllJobs) throws MyOwnException {
		
		int nextID  = findNextJobID(anAllJobs);
		Job newJob = new Job();
		boolean result = true;
		newJob.setNextJobID(nextID);
		result = newJob.setJobLocation(aJobLocation);
		if (!result) return result;
		result = newJob.setDate(aJobDate);
		if (!result) return result;
		CheckBusinessRules tempcheckDuration = new CheckBusinessRules();
		tempcheckDuration.checkJobDuration(aDuration);		
		result = newJob.setJobDuration(aDuration);
		if (!result) return result;
		result = newJob.setJobSlot(aLightSlot, aMediumSlot, aHeavySlot);
		if (!result) return result;
		result = newJob.setJobDescription(aDescription);
		if (!result) return result;
		result = newJob.setStartTime(aStartTime);
		if (!result) return result;
		result = newJob.setJobManager(aJobManager);
		if (!result) return result;
		
		anAllJobs.add(newJob);
		managerJobList(anAllJobs);
		return result;			
	}
	
	private int findNextJobID(Collection<Job> anAllJobs) {
		int maxNumber = 0;
		if (anAllJobs != null && anAllJobs.size() > 0) {
			for (Job temp : anAllJobs) {
				if (temp.getJobID() > maxNumber) {
					maxNumber = temp.getJobID();
				}				
			}
		}
		return maxNumber + 1;
	}
		
	/**
	 * Create String with managers First and Last name, and his status.
	 * @return string full of data.
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
