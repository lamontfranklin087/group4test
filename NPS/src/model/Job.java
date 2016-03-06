package model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

/**
 * Create a job object.
 * @author Ihar Lavor
 * @version 02/06/2016
 * revision Lamont Franklin 2/10/2016 added duty level functionality
 * @version 02/13/2016
 */
public class Job implements java.io.Serializable {
	
	public final int DUTY_OPTIONS = 4;
	/** Serial number since job is Serializable.*/
	private static final long serialVersionUID = 1L;
	
	/** The maximum id will be up to, and not including, MAX_ID_VAL*/
	private static int totalJobs;	
	
	/** The maximum id will be up to FIRST_ID_NUM*/
	private static int FIRST_ID_NUM = 0;

	/** Job id for this job.*/
	private int jobID;
	
	/** The manager who manager this job. */
	private String jobManager;
	
	/** The City where the job is to take place. */
	private String jobLocation;
	
	/** Date of job saved as a Calendar object. */
	private Calendar jobDate;
	
	/** The number of days the job is expected to last. */
	private int jobDuration;
	
	/** The number of total available volunteer slots for this job. */
	private int totalSlotsAvailable;
	
	/** The number of light available volunteer slots for this job. */
	private int lightSlotsAvailable;
	
	/** The number of medium available volunteer slots for this job. */
	private int mediumSlotsAvailable;
	
	/** The number of heavy available volunteer slots for this job. */
	private int heavySlotsAvailable;
	
	/** A short job description of the tasks to be completed in this job. */
	private String jobDescription;
	
	/** The start time. e.g. "8:00 AM" */
	private String startTime;
	
	/** The number of total volunteers currently signed up to work. */
	private int totalVolunteers;
	
	/** The number of light volunteers currently signed up to work. */
	private LinkedList<Volunteer> lightVolunteers;
	
	/** The number of medium volunteers currently signed up to work. */
	private LinkedList<Volunteer> mediumVolunteers;
	
	/** The number of heavy volunteers currently signed up to work. */
	private LinkedList<Volunteer> heavyVolunteers;

	/**
	 * No parameter job constructor.
	 */
	public Job() {
		jobID = FIRST_ID_NUM + (++totalJobs);
		jobManager = null;
		jobLocation = null;
		jobDate = null;
		jobDuration = 0;
		totalSlotsAvailable = 0;
		lightSlotsAvailable = 0;
		mediumSlotsAvailable = 0;
		heavySlotsAvailable = 0;
		jobDescription = null;
		startTime = null;
		totalVolunteers = 0;
		lightVolunteers = null;
		mediumVolunteers = null;
		heavyVolunteers = null;
	}
	

	/**
	 * Setter for sequential JobIds
	 * 
	 * @param nextID the new job id.
	 */
	public void setNextJobID(int nextID) {
		totalJobs = nextID;
	}
	
	/**
	 * Setter for JobId
	 * 
	 * @param nextID the new job id.
	 */
	public void setJobID(int nextID) {
		jobID = nextID;
	}
	
	/**
	 * Set job's location.
	 * @param aJobLocation a park name for this job.
	 * @throws MyOwnException 
	 */
	public boolean setJobLocation(String aJobLocation) throws MyOwnException {	
		if (aJobLocation != null && aJobLocation.length() > 0) {
			jobLocation = aJobLocation;
			return true;
		} else {
			throw new MyOwnException("Job's location can't be null or empty string.");
		}
	}
	
	/**
	 * Doesn't  allow to enter past dates.
	 * @param aJobDate is a date for a future job.
	 * @return true if date was set, otherwise false.
	 * @throws MyOwnException if date equals to null.
	 */
	public boolean setDate(Calendar aJobDate) throws MyOwnException {		
		if (aJobDate != null) {				
			Calendar currentDate = new GregorianCalendar();
			
			int curYear = currentDate.get(Calendar.YEAR);							
			int jobDayOfYear = aJobDate.get(Calendar.DAY_OF_YEAR);
			int curDayOfYear = currentDate.get(Calendar.DAY_OF_YEAR);
			
			if (curYear < aJobDate.get(Calendar.YEAR)) {
				jobDayOfYear = jobDayOfYear + 365;
			}		
			
		 	int resultDays = jobDayOfYear - curDayOfYear;
//		 	System.out.println(jobDayOfYear + " " + curDayOfYear);
		 	if (resultDays <= 0) {
		 		throw new MyOwnException("Job's date can't be past.");
		 	} else {
				jobDate = aJobDate;
				return true;
			}
		} else {
			throw new MyOwnException("Job's date can't be null.");
		}
	}
		
	/**
	 * Set job's duration time in days.
	 * @return true if job duration is 1 or 2 otherwise false.
	 * @throws MyOwnException 
	 */
	public boolean setJobDuration(int aDuration) throws MyOwnException {
		if (aDuration > 0) {
			jobDuration = aDuration;
			return true;
		} else {
			throw new MyOwnException("Job's duration can't be less than 0 or greater than 2.");	
		}		
	}
	
	/**
	 * Set job's manager.
	 * @return true if job's manager was set, otherwise false.
	 * @throws MyOwnException 
	 */
	public boolean setJobManager(String aJobManager) throws MyOwnException {
		if (aJobManager != null && aJobManager.length() > 0) {
			jobManager = aJobManager;
			return true;
		}
		throw new MyOwnException("Job's manager can't be null or empty string.");
	}
	
	/**
	 * Set the number of available slots for each duty level.
	 * @param aLightSlot represent a number of light slots for this job (must be > 0).
	 * @param aMediumSlot represent a number of medium slots for this job (must be > 0).
	 * @param aHeavySlot represent a number of heavy slots for this job (must be > 0).
	 * @return true if number of slots are > 0 and were set successfully, otherwise false.
	 * @throws MyOwnException 
	 */		
	public boolean setJobSlot(int aLightSlot, int aMediumSlot, int aHeavySlot) throws MyOwnException {	
		totalSlotsAvailable = 0;
		if ((aLightSlot > 0) || (aMediumSlot > 0) || (aHeavySlot > 0)) {
			lightSlotsAvailable = aLightSlot;
			mediumSlotsAvailable = aMediumSlot;
			heavySlotsAvailable = aHeavySlot;
			totalSlotsAvailable = lightSlotsAvailable 
								+ mediumSlotsAvailable
								+ heavySlotsAvailable;
			return true;
		}
		throw new MyOwnException("All job's slots can't be 0.");
	}
	
	/**
	 * Set a job's description.
	 * @param aDescription describes the job (must be > 0 and != null).
	 * @return true if description was set successfully, otherwise false.
	 * @throws MyOwnException 
	 */	
	public boolean setJobDescription(String aDescription) throws MyOwnException {
		if (aDescription != null && aDescription.length() > 0) {
			jobDescription = aDescription;
			return true;
		}
		throw new MyOwnException("Add job's description.");
	}
	
	/**
	 * Set a job's start time.
	 * @param aStartTime is time when job will start (must be > 0 and != null).
	 * @return true if startTime was set successfully, otherwise false.
	 * @throws MyOwnException 
	 */	
	public boolean setStartTime(String aStartTime) throws MyOwnException {
		if (aStartTime != null && aStartTime.length() > 0) {
			startTime = aStartTime;
			return true;
		}
		throw new MyOwnException("Job's start time can't be null or empty string.");
	}
	
	/**
	 * Accessor.
	 * @return manager name for this job.
	 */
	public String getJobManager() {
		return jobManager;
	}
	
	/**
	 * Accessor.
	 * @return job's ID number.
	 */
	public int getJobID() {
		return jobID;
	}
	/**
	 * Accessor.
	 * @return park's name for this job.
	 */
	public String getJobLocation() {
		return jobLocation;
	}
	
	/**
	 * Accessor.
	 * @return job's date
	 */
	public Calendar getDate(){
		return jobDate;
	}
	
	/**
	 * Accessor.
	 * @return job's duration time.
	 */
	public int getJobDuration(){
		return jobDuration;		
	}

	/**
	 * Accessor.
	 * @return number of available slots for a job.
	 */
	public int getAvailableSlots(){
		return totalSlotsAvailable;
	}
	
	/**
	 * Accessor.
	 * @return job's description.
	 */
	public String getDescription(){
		return jobDescription;		
	}

	/**
	 * Accessor.
	 * @return job's start time.
	 */
	public String getStartTime(){
		return startTime;
	}
	
	/**
	 * Accessor.
	 * @return a number of light, medium, and heavy slots available for this job.
	 */
	public int getTotalSlotsAvailable() {
		return totalSlotsAvailable;
	}
	
	/**
	 * Accessor.
	 * @return a number of light slots available for this job.
	 */
	public int getLightSlotsAvailable() {
		return lightSlotsAvailable;
	}
	
	/**
	 * Accessor.
	 * @return a number of medium slots available for this job.
	 */
	public int getMediumSlotsAvailable() {
		return mediumSlotsAvailable;
	}
	
	/**
	 * Accessor.
	 * @return a number of heavy slots available for this job.
	 */
	public int getHeavySlotsAvailable() {
		return heavySlotsAvailable;
	}
	
	/**
	 * Accessor.
	 * @return a number of volunteers signed up for this job.
	 */
	public int getTotalVolunteers() {
		return totalVolunteers;
	}
	/**
	 * Accessor.
	 * @return a list of volunteers for a job.
	 */
	public LinkedList<Volunteer> getVolunteers() {
		LinkedList<Volunteer> volunteers = new LinkedList<Volunteer>();
		if (lightVolunteers != null) {
			volunteers.addAll(lightVolunteers);
		}
		if (mediumVolunteers != null) {
			volunteers.addAll(mediumVolunteers);
		}
		if (heavyVolunteers != null) {
			volunteers.addAll(heavyVolunteers);
		}
		if (volunteers.size() != 0) {
			return volunteers;
		} else {
			return null;
		}
	}

	/**
	 * Add volunteer to a job.
	 * @param newVolunteer must be Volunteer type and != null.
	 * @param aSlot is a slot name, only 3 options allowed: light, medium, or heavy.
	 * @return true if volunteer was added to a job, otherwise false.
	 * @throws MyOwnException 
	 */
	public boolean addVolunteer(Volunteer newVolunteer, int aSlot) throws MyOwnException {		
		if (newVolunteer != null && aSlot > 0) {
			switch(aSlot){
			case 1: //light
				return addToSlot(newVolunteer, aSlot, lightSlotsAvailable);				
			case 2: //medium
				return addToSlot(newVolunteer, aSlot, mediumSlotsAvailable);
			case 3: //heavy
				return addToSlot(newVolunteer, aSlot, heavySlotsAvailable);
			}
		} else if (newVolunteer == null) {
			throw new MyOwnException("Volunteer object can't be null.");
		}
		throw new MyOwnException("All slots are busy for this job.");
	}	
	
	/**
	 * Add volunteer to a specific slot.
	 * @param newVolunteer must be Volunteer type and != null.
	 * @param dutyType is slot type: 1 - light, 2 - medium, and 3 - heavy
	 * @param max is a number of available spots for this slot.
	 * @return true if volunteer was added to a job, otherwise false.
	 * @throws MyOwnException 
	 */
	protected boolean addToSlot(Volunteer newVolunteer, int dutyType, int max) throws MyOwnException{
		if (max == 0){
			throw new MyOwnException("All slots are busy for this job.");
		} else {
			if (dutyType == 1) {
				if (lightVolunteers == null) {
					lightVolunteers = new LinkedList<Volunteer>();					
				} 
				lightVolunteers.add(newVolunteer);
				totalVolunteers = totalVolunteers + 1;
				totalSlotsAvailable = totalSlotsAvailable - 1;
				lightSlotsAvailable = lightSlotsAvailable - 1;
				return true;								
			} else if (dutyType == 2) {
				if (mediumVolunteers == null) {
					mediumVolunteers = new LinkedList<Volunteer>();
				}
				mediumVolunteers.add(newVolunteer);
				totalVolunteers = totalVolunteers + 1;
				totalSlotsAvailable = totalSlotsAvailable - 1;
				mediumSlotsAvailable = mediumSlotsAvailable - 1;
				return true;				
			} else if (dutyType == 3) {
				if (heavyVolunteers == null) {
					heavyVolunteers = new LinkedList<Volunteer>();
				} 
				heavyVolunteers.add(newVolunteer);
				totalVolunteers = totalVolunteers + 1;
				totalSlotsAvailable = totalSlotsAvailable - 1;
				heavySlotsAvailable = heavySlotsAvailable - 1;
				return true;								
			} 
		}		
		throw new MyOwnException("Wrong selection for job's slot.");
	}
	/**
	 * Append job's information to a string.
	 * @return job's information as a string.
	 */
	public String toString() {	
		StringBuilder jobSummary = new StringBuilder(140);
		jobSummary.append("Job ID:                 " + jobID + "\n");
		jobSummary.append("Park Manager:           " + jobManager + "\n");
		jobSummary.append("Job Date:               ");
		jobSummary.append(jobDate.get(Calendar.MONTH) + 1 + "/" +
						  jobDate.get(Calendar.DAY_OF_MONTH) + "/" +
						  jobDate.get(Calendar.YEAR) + "\n");
		jobSummary.append("Start time:             ");
		jobSummary.append(startTime);
		jobSummary.append("\n");
		jobSummary.append("Job Location:           ");
		jobSummary.append(jobLocation);
		jobSummary.append("\n");
		jobSummary.append("Light slots avaliable:  ");
		jobSummary.append(lightSlotsAvailable);
		jobSummary.append("\n");		
		jobSummary.append("Medium slots avaliable: ");
		jobSummary.append(mediumSlotsAvailable);
		jobSummary.append("\n");		
		jobSummary.append("Heavy slots avaliable:  ");
		jobSummary.append(heavySlotsAvailable);
		jobSummary.append("\n");		
		jobSummary.append("Job Description:        ");
		jobSummary.append(jobDescription);
		jobSummary.append("\n");		
		jobSummary.append("Duration:               ");
		jobSummary.append(jobDuration + " days");
		jobSummary.append("\n");
		jobSummary.append("Registered Volunteers:  ");
		jobSummary.append(totalVolunteers);
		jobSummary.append("\n");
		return jobSummary.toString();
	}

	public String toStringTable() {
		StringBuilder jobSummary = new StringBuilder(140);
				
		jobSummary.append(jobID + "   ");
		jobSummary.append(jobDate.get(Calendar.MONTH) + 1 + "/" +
						  jobDate.get(Calendar.DAY_OF_MONTH) + "/" +
						  jobDate.get(Calendar.YEAR) + "    ");
		jobSummary.append(jobDuration + " days\t");
		jobSummary.append(totalSlotsAvailable + "\t");
		jobSummary.append(jobManager + "\t");
		jobSummary.append(jobLocation + "\t   ");		
		jobSummary.append(jobDescription + "\t");
		return jobSummary.toString();
	}
}
