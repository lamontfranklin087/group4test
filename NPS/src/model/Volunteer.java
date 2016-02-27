package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Create a volunteer object.
 * @author Ihar Lavor
 * @version 02/12/2016 
 */
public final class Volunteer extends AbstractUser implements Serializable {

	private static final long serialVersionUID = 1L;

	public Volunteer() {
		super();
	}

	/**
	 * Constructor for the volunteer class.
	 * 
	 * @param theFirstName  first name of this volunteer
	 * @param theLastName  last name for this volunteer
	 * @param theEmail  the email address registered for this volunteer
	 * @param thePassword  this volunteer's password
	 */
	public Volunteer(String theFirstName, String theLastName,
			String theEmail, String thePassword) {
		super(theFirstName, theLastName, theEmail, thePassword);
	}

	@Override
	public ArrayList<String> getMainMenu() {		
		ArrayList<String>  middleText = new ArrayList<String>();
		middleText.add("View the jobs I am signed up for");
		middleText.add("View a summary of all upcoming jobs");
		middleText.add("View details of a selected upcoming job");
		middleText.add("Volunteer for a job");		
		middleText.add("Exit");				
		return middleText;
	}
	
	/**
	 * Create a list of all jobs this volunteer is signed up for. 
	 * @param allJobs a collection of all the existing jobs
	 * @return a list jobs for this volunteer.
	 */
	public LinkedList<Job> viewMyJobs(Collection<Job> allJobs) {			
		LinkedList<Job> myJobs = new LinkedList<Job>();
		for (Job tempJob : allJobs) {
			LinkedList<Volunteer> volunteers = tempJob.getVolunteers();			
			for (Volunteer tempVolunteer : volunteers) {
				if(tempVolunteer.getEmail().equals(this.getEmail())) {
					myJobs.add(tempJob);
				}
			}								
		}
		return myJobs;
	}
	
	/**
	 * Sign up this volunteer for a job.
	 * @param allJobs is a collection of all existing jobs.
	 * @param aJobID is a identification number for a specific job (must be > 0).
	 * @param aSlot is a slot name for which volunteer want to sign up (must be "light", "medium", or "heavy").
	 * @return true if volunteer was added to a job, otherwise false.
	 */
	public boolean jobSignUp(Collection<Job> allJobs, int aJobID, String aSlot) {
		if (aJobID > 0) {
			for (Job temp : allJobs) {			
				if (temp.getJobID() == aJobID){
					if (dateAvailable(allJobs, temp)) {
						temp.addVolunteer(this, aSlot);
						return temp.addVolunteer(this, aSlot);
					} 
				}			
			}
		}
		return false;
	}
	
	/**
	 * Checks if this volunteer already committed on that day.
	 * @param allJobs is a collection of all existing jobs.
	 * @param theJob is a job for which volunteer want to sign up.
	 * @return false if volunteer was committed on that day, otherwise true.
	 */
	private boolean dateAvailable(Collection<Job> allJobs, Job theJob) {	
		ArrayList<Integer> myBusyDates = new ArrayList<Integer>();
		
		for (Job tempJob : allJobs) {
			LinkedList<Volunteer> volunteers = tempJob.getVolunteers();
			if (volunteers != null){
				for (Volunteer tempVolunteer : volunteers) {				
					if (tempVolunteer.getEmail().equals(this.getEmail())) {	
						myBusyDates.add(tempJob.getDate().get(Calendar.DAY_OF_YEAR));
						if (tempJob.getJobDuration() == 2) {
							//adds another day if job duration is 2 days
							//It mean that volunteer not available for 2 days in a row.
							myBusyDates.add(tempJob.getDate().get(Calendar.DAY_OF_YEAR) + 1);
						}
					}
				}
			}
		}	
		int jobDate = theJob.getDate().get(Calendar.DAY_OF_YEAR);
		for (Integer tempDate : myBusyDates) {
			if (jobDate == tempDate) {
				return false;
			}
		}	
		return true;		
	}
	
	@Override
	public String getSimpleName() {
		return "Volunteer";
	}

	/**
	 * A string representation of this volunteer.	 * 
	 * @return  the resulting string
	 */
	public String toString() {		
		StringBuilder userSummary = new StringBuilder();
		userSummary.append("Status: Volunteer");
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
