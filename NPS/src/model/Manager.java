package model;

import java.util.Collection;

/**
 * Creates a Truck object for use in the Easy Street GUI.
 * 
 * @author dave1729
 * @version 305-3
 */
public final class Manager extends AbstractUser {

	public Manager() {
		super();
	}
	
	protected Manager(String theFirstName, String theLastName, String theEmail,
			String thePassword) {
		super(theFirstName, theLastName, theEmail, thePassword);
		// TODO Auto-generated constructor stub
	}
	
	public Job submitNewJob() {
		Job newJob = new Job();
		newJob.createJob();
		return newJob;
	}

	@Override
	protected void viewSumAllJobs(Collection<Job> allJobs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void viewJobDetails() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getSimpleName() {
		return "Park Manager";
	}
	
	protected void viewVolunteers(Collection<Job> allJobs) {
		
	}
    
}
