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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static int jobID;
	
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
		jobID = 0;
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
	 */
	public void createJob() {
		setJobID();
		enterJobLocation();		
		enterDate();
		enterStartTime();
		enterJobDuration();
		enterJobSlot();		
		enterJobDescription();
	}

	private void setJobID() {
		jobID = jobID + 1;		
	}

	/**
	 * Set job's location.
	 */
	protected void enterJobLocation() {
		System.out.println("Enter job location:");
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
				System.out.println("Enter job date: (MM/dd/yyyy)");			
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
					System.out.println("You can't enter past date.");
				} else if (myYear == curYear && myMonth < curMonth) {
					System.out.println("You can't enter past date.");
				} else if (myYear == curYear && myMonth == curMonth && myDate < curDate) {
					System.out.println("You can't enter past date.");
				} else {
					jobDate = mydate;
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Wrong Date Format");
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Wrong Date Format");
			}
		} while (true);
		
	}
	
	/**
	 * Set job's duration time in days.
	 */
	protected void enterJobDuration() {
		while (true) {
			System.out.println("Enter job duration: (Number of days, for example 2)");
			
			int temp = getNumber();
			
			if (temp == 1 || temp == 2) {
				jobDuration = temp;
				break;
			} else {
				System.out.println("Job duration can't be 0 or exceed 2 days.");	
			}
		}
	}
	
	/**
	 * Set the number of available slots.
	 */
	protected void enterJobSlot() {						
		while(true){
			System.out.println("Enter job slots: (For example 7)");
			
			int temp = getNumber();
			
        	if (temp > 0) {
        		slotsAvailable = temp;
        		break;
        	} else {
        		System.out.println("Job slots can't be 0.");
        	}
		}
	}
	
	/**
	 * Set job's description.
	 */
	protected void enterJobDescription() {
		System.out.println("Enter short job description:");
		keyboard = new Scanner(System.in);
		jobDescription = keyboard.nextLine();
	}
	
	/**
	 * Set job's start time.
	 */
	protected void enterStartTime() {
		System.out.println("Enter time when job starts: (for example 8:00 AM)");
		keyboard = new Scanner(System.in);
		startTime = keyboard.nextLine();		
	}
	
	/**
	 * To edit job's fields.
	 */
	public void editJob() {			
		while (true) {
			System.out.println("Select one of the folowing options:");
			System.out.println("1. Change Job's Location");
			System.out.println("2. Change Job's Date");
			System.out.println("3. Change Job's Duration");
			System.out.println("4. Change Job's Slots");
			System.out.println("5. Change Job's Description");
			System.out.println("6. Change Job's Start Time");
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
	        	if (Integer.parseInt(temp) > 0 || Integer.parseInt(temp) <= 0) {
	        		result = Integer.parseInt(temp);
	        		break;
	        	}
	        } catch(NumberFormatException ne) {
	            System.out.println("That's not a whole number.");	            
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
			System.out.print("No more available slots.\n");
		}	
	}	
	
	/**
	 * Append job's information to a string.
	 * @return job's information as a string.
	 */
	public String toString() {	
		StringBuilder jobSummary = new StringBuilder();
		jobSummary.append("Job Date: ");
		jobSummary.append(jobDate.toString());
		jobSummary.append("\n");
		jobSummary.append("Job Location: ");
		jobSummary.append(jobLocation);
		jobSummary.append("\n");
		jobSummary.append("Slots Avaliable: ");
		jobSummary.append(slotsAvailable);
		jobSummary.append("\n");
		jobSummary.append("Job Description: ");
		jobSummary.append(jobDescription);
		jobSummary.append("\n");
		jobSummary.append("Start time: ");
		jobSummary.append(startTime);
		jobSummary.append("\n");
		jobSummary.append("Duration: ");
		jobSummary.append(jobDuration);
		jobSummary.append("\n");
		jobSummary.append("Registered Volunteers ");
		jobSummary.append(volunteers.toString());
		jobSummary.append("\n");
		return jobSummary.toString();
	}
}
