package model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Create a job object.
 * @author Ihar Lavor
 * @version 02/06/2016
 *
 */
public class Job implements java.io.Serializable{
	
	/* Serial number since job is Serializable.*/
	private static final long serialVersionUID = 1L;
	
	/* The maximum id will be up to, and not including, MAX_ID_VAL*/
	private static int totalJobs = 0;	
	
	/* The maximum id will be up to FIRST_ID_NUM*/
	private static int FIRST_ID_NUM = 1000;

	private int jobID;
	
	private String jobManager;
	
	private String jobLocation;
	
	private Calendar jobDate;
	
	private int jobDuration;
	
	private int slotsAvailable;
	
	private String jobDescription;
	
	private String startTime;
	
	private LinkedList<Volunteer> volunteers;
	
	private Scanner keyboard;
	
	/**
	 * Default constructor.
	 * Set job's fields: jobID = 0, jobLocation = null, jobDate = null, 
	 * jobDuration = -1, slotsAvailable = -1, 
	 * jobDescription = null, startTime = null, volunteers = null.
	 */
	public Job() {
		jobID = FIRST_ID_NUM + (++totalJobs);
		jobManager = null;
		jobLocation = null;
		jobDate = null;
		jobDuration = -1;
		slotsAvailable = -1;
		jobDescription = null;
		startTime = null;
		volunteers = null;
	}
			
	/**
	 * Set job's fields: jobLocation, jobDate, jobDuration,
	 * slotsAvailable, jobDescription, startTime, volunteers.
	 * @param lastName 
	 * @param firstName 
	 */
	public void createJob(String firstName, String lastName) {
		jobManager = firstName + " " + lastName;
		enterJobLocation();		
		enterDate();
		enterStartTime();
		enterJobDuration();
		enterJobSlot();		
		enterJobDescription();
	}

	/**
	 * Set job's location.
	 */
	protected void enterJobLocation() {
		System.out.print("\nEnter job location: ");
		keyboard = new Scanner(System.in);
		jobLocation = keyboard.nextLine();		
	}
	
	/**
	 * Set job's date MM/DD/YYYY.
	 */
	protected void enterDate() {		
		keyboard = new Scanner(System.in);
		do {								
			try {				
				System.out.print("\nEnter job date: (MM/dd/yyyy) ");			
				String[] mystring = (keyboard.nextLine()).split("/");
				Calendar currentDate = new GregorianCalendar();
				Calendar mydate = new GregorianCalendar();
				
				int myDate = Integer.parseInt(mystring[1]);
				int myYear = Integer.parseInt(mystring[2]);
				int myMonth = Integer.parseInt(mystring[0]) - 1;
								
				mydate.set(Calendar.YEAR, myYear);
				mydate.set(Calendar.MONTH, myMonth);
				mydate.set(Calendar.DAY_OF_MONTH, myDate);	
				
				int curDate = currentDate.get(Calendar.DAY_OF_MONTH);
				int curMonth = currentDate.get(Calendar.MONTH);
				int curYear = currentDate.get(Calendar.YEAR);
								
				if (myYear < curYear || myMonth > 11 || myDate > 31 || myDate <= 0) {
					System.out.print("\nYou can't enter past date. ");
				} else if (myYear == curYear && myMonth < curMonth) {
					System.out.print("\nYou can't enter past date. ");
				} else if (myYear == curYear && myMonth == curMonth && myDate < curDate) {
					System.out.print("\nYou can't enter past date. ");
				} else {
					jobDate = mydate;
					break;
				}
			} catch (NumberFormatException e) {
				System.out.print("\nWrong Date Format ");
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.print("\nWrong Date Format ");
			}
		} while (true);
		
	}
	
	/**
	 * Set job's duration time in days.
	 */
	protected void enterJobDuration() {
		while (true) {
			System.out.print("\nEnter job duration: (Number of days, for example 2) ");
			
			int temp = getNumber();
			
			if (temp == 1 || temp == 2) {
				jobDuration = temp;
				break;
			} else {
				System.out.print("\nJob duration can't be 0 or exceed 2 days. ");	
			}
		}
	}
	
	/**
	 * Set the number of available slots.
	 */
	protected void enterJobSlot() {						
		while(true){
			System.out.print("\nEnter job slots: (For example 7) ");
			
			int temp = getNumber();
			
        	if (temp > 0) {
        		slotsAvailable = temp;
        		break;
        	} else {
        		System.out.print("\nJob slots can't be 0.  ");
        	}
		}
	}
	
	/**
	 * Set job's description.
	 */
	protected void enterJobDescription() {
		System.out.print("\nEnter short job description: ");
		keyboard = new Scanner(System.in);
		jobDescription = keyboard.nextLine();
		System.out.print("\n");
	}
	
	/**
	 * Set job's start time.
	 */
	protected void enterStartTime() {
		System.out.print("\nEnter time when job starts: (for example 8:00 AM)  ");
		keyboard = new Scanner(System.in);
		startTime = keyboard.nextLine();		
	}
	
	/**
	 * To edit job's fields.
	 */
	public void editJob() {			
		while (true) {
			System.out.println("\nSelect one of the folowing options:");
			System.out.println("1. Change Job's Location:   " + jobLocation);
			System.out.println("2. Change Job's Date:        " 
								+ jobDate.get(Calendar.MONTH) + "/"
								+ jobDate.get(Calendar.DATE) + "/"
								+ jobDate.get(Calendar.YEAR));
			System.out.println("3. Change Job's Duration:    " + jobDuration + " days");
			System.out.println("4. Change Job's Slots:       " + slotsAvailable);
			System.out.println("5. Change Job's Description: " + jobDescription);
			System.out.println("6. Change Job's Start Time:  " + startTime);
			System.out.println("7. Exit ");
			
	    	int userTyped = getNumber();
	    	
	    	if (userTyped == 1) {
	    		enterJobLocation();
	    	} else if (userTyped == 2) {	    		
	    		enterDate();
	    	} else if (userTyped == 3) {
	    		enterJobDuration();
	    	} else if (userTyped == 4) {
	    		enterJobSlot();
	    	} else if (userTyped == 5) {
	    		enterJobDescription();
	    	} else if (userTyped == 6) {
	    		enterStartTime();
	    	} else if (userTyped == 7) {	    		
	    		break;
	    	} 
		}
	}

	/**
	 * Parse string to integer.
	 * @return an integer number from 1 to ...
	 */
	private int getNumber() {
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
	            System.out.print("\nThat's not a whole number. Try again. ");	            
	        }	
		}
		return result;
	}
	
	/**
	 * Delete job by setting: jobLocation, jobDate, jobDescription, 
	 * startTime, volunteers fields to NULL 
	 * and jobDuration, slotsAvailable fields to -1.
	 */
	public void deleteJob(){
		jobManager = null;
		jobLocation = null;
		jobDate = null;
		jobDuration = -1;
		slotsAvailable = -1;
		jobDescription = null;		
		startTime = null;
		volunteers = null;
	}

	/**
	 * Accessor.
	 * @return job's ID number.
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
	 * @return job's location.
	 */
	public String getJobLocation() {
		return jobLocation;
	}
	
	/**
	 * Accessor.
	 * @return job's date (MM/DD/YYYY where 0..11/1..31
	 */
	public Calendar getDate(){
		return jobDate;
	}
	
	/**
	 * Accessor.
	 * @return job's duration time (number of days).
	 */
	public int getJobDuration(){
		return jobDuration;		
	}

	/**
	 * Accessor.
	 * @return number of available sots for a job.
	 */
	public int getAvailableSlots(){
		return slotsAvailable;
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
	 * @return a list of volunteers for a job.
	 */
	public LinkedList<Volunteer> getVolunteers() {
		return volunteers;
	}

	/**
	 * Add volunteer to a job.
	 * @param newVolunteer must be Volunteer type.
	 */
	public void addVolunteer(Volunteer newVolunteer){
		if (volunteers == null) {
			volunteers = new LinkedList<Volunteer>();
		} 
		if (volunteers.size() <= slotsAvailable) {
			volunteers.add(newVolunteer);
		} else {
			System.out.print("\nNo more available slots.\n");
		}	
	}	
	
	/**
	 * Append job's information to a string.
	 * @return job's information as a string.
	 */
	public String toString() {	
		StringBuilder jobSummary = new StringBuilder(140);
		jobSummary.append("Job ID:          " + jobID + "\n");
		jobSummary.append("Park Manager:    " + jobManager + "\n");
		jobSummary.append("Job Date:        ");
		jobSummary.append(jobDate.get(Calendar.MONTH) + 1 + "/" +
						  jobDate.get(Calendar.DAY_OF_MONTH) + "/" +
						  jobDate.get(Calendar.YEAR));
		jobSummary.append("\n");
		jobSummary.append("Job Location:    ");
		jobSummary.append(jobLocation);
		jobSummary.append("\n");
		jobSummary.append("Slots Avaliable: ");
		jobSummary.append(slotsAvailable);
		jobSummary.append("\n");
		jobSummary.append("Job Description: ");
		jobSummary.append(jobDescription);
		jobSummary.append("\n");
		jobSummary.append("Start time:      ");
		jobSummary.append(startTime);
		jobSummary.append("\n");
		jobSummary.append("Duration:        ");
		jobSummary.append(jobDuration + " days");
		jobSummary.append("\n");
		jobSummary.append("Registered Volunteers ");
		jobSummary.append(volunteers == null ? 0 : volunteers.size());
		jobSummary.append("\n");
		return jobSummary.toString();
	}

	public String toStringTable() {
		StringBuilder jobSummary = new StringBuilder(140);
				
		jobSummary.append(jobID + "   ");
		jobSummary.append(jobDate.get(Calendar.MONTH) + 1 + "/" +
						  jobDate.get(Calendar.DAY_OF_MONTH) + "/" +
						  jobDate.get(Calendar.YEAR) + "    ");
		jobSummary.append(startTime + "   ");
		jobSummary.append(jobDuration + " days\t");
		jobSummary.append(slotsAvailable + "\t");
		jobSummary.append((volunteers == null ? 0 : volunteers.size()) + "\t");
		jobSummary.append(jobLocation + "\t\t");		
		jobSummary.append(jobManager + "\t");		
		jobSummary.append(jobDescription + "\t");
		return jobSummary.toString();
	}
}
