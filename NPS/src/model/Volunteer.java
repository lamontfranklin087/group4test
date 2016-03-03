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

	/**
	 * Create a list of all jobs this volunteer is signed up for. 
	 * @param allJobs a collection of all the existing jobs
	 * @return a list jobs for this volunteer.
	 */
	public LinkedList<Job> viewMyJobs(Collection<Job> allJobs) {			
		LinkedList<Job> myJobs = new LinkedList<Job>();
		if (allJobs != null) {
			for (Job tempJob : allJobs) {
				if(matchVolunteer(tempJob.getVolunteers())) {
					myJobs.add(tempJob);
				}
			}
		}
		return myJobs;
	}
	
	public Boolean matchVolunteer( LinkedList<Volunteer> volunteers){
		if (volunteers != null) {
			for (Volunteer tempVolunteer : volunteers) {
				if(tempVolunteer.getEmail().equals(this.getEmail())) {
					return true;
				}
			}
		}
		
		return false;
	}
	/**
	 * Sign up this volunteer for a job.
	 * @param allJobs is a collection of all existing jobs.
	 * @param aJobID is a identification number for a specific job (must be > 0).
	 * @param aSlot is a slot name for which volunteer want to sign up (must be "light", "medium", or "heavy").
	 * @return true if volunteer was added to a job, otherwise false.
	 * @throws MyOwnException 
	 */
	public boolean jobSignUp(Collection<Job> allJobs, int aJobID, int aSlot) throws MyOwnException {
		if (aJobID > 0 && allJobs != null) {			
			for (Job temp : allJobs) {			
				if ((temp.getJobID() == aJobID) && dateAvailable(allJobs, temp)){					
					return temp.addVolunteer(this, aSlot);					
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
	 * @throws MyOwnException 
	 */
	private boolean dateAvailable(Collection<Job> allJobs, Job theJob) throws MyOwnException {	
		ArrayList<Integer> myBusyDates = new ArrayList<Integer>();
		LinkedList<Job> myJobs = viewMyJobs(allJobs);
		for (Job tempJob : myJobs) {			
			myBusyDates.add(tempJob.getDate().get(Calendar.DAY_OF_YEAR));
			if (tempJob.getJobDuration() == 2) {
				//adds another day if job duration is 2 days
				//It mean that volunteer not available for 2 days in a row.
				myBusyDates.add(tempJob.getDate().get(Calendar.DAY_OF_YEAR) + 1);
			}			
		}	
		int jobDate = theJob.getDate().get(Calendar.DAY_OF_YEAR);
		for (Integer tempDate : myBusyDates) {
			if (jobDate == tempDate) {
				throw new MyOwnException("You already signed up for a job on this day.");
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
