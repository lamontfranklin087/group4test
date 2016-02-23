package model;

import java.io.Serializable;
import java.util.ArrayList;
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
	private LinkedList<Job> jobsAtMyParks = new LinkedList<Job>();
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
	
	@Override
	public ArrayList<String> getMethodList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("submitNewJob");
		list.add("deleteJob");
		list.add("editJob");
		list.add("viewSumAllJobs");
		list.add("viewVolunteers");
		
		return list;
	}
	
	/**
	 * Prints a list of all volunteers for a job.
	 * @param allJobs is a list of all Jobs.
	 * @return 
	 */

	public StringBuilder viewVolunteers(Collection<Job> allJobs) {
		if (allJobs != null) {
			System.out.println("Enter Job's ID to view volunteers or 1 to exit");
			int temp = getNumber();
			if (temp != 1) {
				Job foundJob = findJob(temp, allJobs);
				if (foundJob != null) {
					LinkedList<Volunteer> volunteer = foundJob.getVolunteers();
					
					if (volunteer != null) {
						Iterator<Volunteer> itr = volunteer.iterator();
						StringBuilder allVolunteers = new StringBuilder();
						allVolunteers.append("\nFirst Name\tLastName\tEmail address");			
						while (itr.hasNext()) {
							Volunteer tempVol = itr.next();
							allVolunteers.append("\n" + tempVol.getFirstName() + "\t" +
											   tempVol.getLastName() + "\t" +
											   tempVol.getEmail());
						}
						return allVolunteers;
					} else {
						return new StringBuilder("No volunteers for this job");
					}
				}
			}
		}
		return null;
	}
	
	
	/**
	 * Create a list of all jobs this manager manages.
	 * Check what was the biggest job's ID and sets next ID.
	 * It is happening once during login time.
	 * @param allJobs is a list of all Jobs.
	 */
	private void managerJobList(Collection<Job> allJobs) {
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
				if (temp.getJobManager().equalsIgnoreCase(this.getFirstName() + " " + this.getLastName())) {
					jobsAtMyParks.add(temp);
				}
			}
			temp.setJobID(maxNumber);
		}
	}
	
	/**
	 * Displays all the currently pending jobs.
	 * 
	 * @param p a collection of the current jobs.
	 * @return 
	 */
	@Override
	public StringBuilder viewSumAllJobs(Collection<Job> allJobs){ 
		managerJobList(allJobs);
		StringBuilder sumAllJobs = new StringBuilder();
		
		if (jobsAtMyParks != null && jobsAtMyParks.size() > 0) {
			Iterator<Job> itr = jobsAtMyParks.iterator();
			sumAllJobs.append("\nID     " + "Date\t    " + "Duration\t" 
	                + "Slots\t" + "Manager\t\t" + "Locaton\t\t\t" + "Description");
			Job temp;
			while (itr.hasNext()) {
				temp = itr.next();				
				sumAllJobs.append("\n" + temp.toStringTable());										
			}			
		}
		return sumAllJobs;
	}
	
	/**
	 * Edit job's fields.
	 * @param allJobs is a list of all Jobs.
	 */
	public StringBuilder editJob(Collection<Job> allJobs) {
		System.out.println("Enter Job's ID number or 0 to exit:");
		int jobIDTemp = getNumber();
		if (jobIDTemp > 0) {
			Job foundJob = findJob(jobIDTemp, allJobs);			
			if (foundJob == null) {
				return new StringBuilder("\nNo job with ID " + jobIDTemp);
			} else {
				foundJob.editJob(parksManage, allJobs);
				return new StringBuilder("\nJob with ID " + jobIDTemp + " was succesfully changed");
			}
		}		
		return null;
	}

	/**
	 * Delete a job.
	 * @param allJobs is a list of all Jobs
	 */
	public StringBuilder deleteJob(Collection<Job> allJobs) {
		System.out.println("Enter Job's ID number or 0 to exit:");
		int jobIDTemp = getNumber();
		if (jobIDTemp > 0) {
			Job temp = findJob(jobIDTemp, allJobs);			
			if (temp == null) {
				return new StringBuilder("\nNo job with ID " + jobIDTemp);
			} else {
				allJobs.remove(temp);
				return new StringBuilder("\nJob with ID " + jobIDTemp + " was deleted.");
			}
		}
		return null;
	}

	/**
	 * Create new job.
	 * @param allJobs is a list of all Jobs.
	 */
	public boolean submitNewJob(Collection<Job> allJobs) {		
		Job newJob = new Job();			
		boolean result = newJob.createJob(getFirstName(), getLastName(), parksManage, allJobs);
		if (result) {
			System.out.println(newJob.toString());
			System.out.println("1. To confirm job\n2. To exit without saving job");
			if (getNumber() == 1) {
				allJobs.add(newJob);
				return true;
			}	
		}		
		return false;
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
