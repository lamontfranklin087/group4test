package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Create a job object.
 * @author Ihar Lavor
 * @version 02/06/2016
 * revision Lamont Franklin 2/10/2016 added duty level functionality
 * @version 02/13/2016
 */
public class Job implements java.io.Serializable{
	
	public final int DUTY_OPTIONS = 3;
	/* Serial number since job is Serializable.*/
	private static final long serialVersionUID = 1L;
	
	/* The maximum id will be up to, and not including, MAX_ID_VAL*/
	private static int totalJobs;	
	
	/* The maximum id will be up to FIRST_ID_NUM*/
	private static int FIRST_ID_NUM = 0;

	private int jobID;
	
	private String jobManager;
	
	private String jobLocation;
	
	private Calendar jobDate;
	
	private int jobDuration;
	
	private int totalSlotsAvailable;
	
	private int lightSlotsAvailable;
	
	private int mediumSlotsAvailable;
	
	private int heavySlotsAvailable;
	
	private String jobDescription;
	
	private String startTime;
	
	private int totalVolunteers;
		
	private LinkedList<Volunteer> lightVolunteers;
	
	private LinkedList<Volunteer> mediumVolunteers;
	
	private LinkedList<Volunteer> heavyVolunteers;
	
	private transient Scanner keyboard;
	
	
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
			
	
	public void createJob(String firstName, String lastName, ArrayList<String> parksManage) {
		jobManager = firstName + " " + lastName;
		enterJobLocation(parksManage);		
		enterDate();
		enterStartTime();
		enterJobDuration();
		enterJobSlot();		
		enterJobDescription();
	}

	protected void setJobID(int nextID) {
		totalJobs = nextID;
	}
	/**
	 * Set job's location.
	 * @param parksManage 
	 */
	protected void enterJobLocation(ArrayList<String> parksManage) {
		if (parksManage != null && parksManage.size() > 0) {
			System.out.println("\nSelect Park: ");	
			
			for (int i = 0; i < parksManage.size(); i++) {
				System.out.println((i + 1) + ". " + parksManage.get(i));
			}
			int responce = getNumber();
			while (responce < 1 || responce > parksManage.size()) {
				responce = getNumber();
			}			
			jobLocation = parksManage.get(responce - 1);	
		}
	}
	
	/**
	 * Set job's date MM/DD/YYYY. 
	 * Doesn't  allow to enter past dates and dates more them 90 days in future .
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
				
				int resultDays = (myYear - currentDate.get(Calendar.YEAR)) * 12 * 30 
						+ Math.abs(myMonth - currentDate.get(Calendar.MONTH) - 1) * 30
						+ Math.abs(myDate - currentDate.get(Calendar.DATE));
								
				if (myYear < curYear || myMonth > 11 || myDate > 31 || myDate <= 0) {
					System.out.print("\nYou can't enter past date. ");
				} else if (myYear == curYear && myMonth < curMonth) {
					System.out.print("\nYou can't enter past date. ");
				} else if (myYear == curYear && myMonth == curMonth && myDate < curDate) {
					System.out.print("\nYou can't enter past date. ");
				} else if (resultDays > 90) {
					System.out.print("\nYou can't enter date more then 90 days ahead. ");
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
	 * edited 2/10/16 LF slot method to add job duty levels
	 */
	protected int fillJobSlot(String duty) {	

		while(true){
			System.out.print("\nEnter " + duty + " duty job slots: (For example 7) ");
			int temp = getNumber();
			if (temp >= 0) {
				return temp;
			} else {
				System.out.print("\nPlease input a valid number  ");
			}
		}
	}
	
	protected void enterJobSlot() {	
		totalSlotsAvailable = 0;
		while (totalSlotsAvailable <= 0) {
			lightSlotsAvailable = fillJobSlot("light");
			mediumSlotsAvailable = fillJobSlot("medium");
			heavySlotsAvailable = fillJobSlot("heavy");
			totalSlotsAvailable = lightSlotsAvailable 
								+ mediumSlotsAvailable
								+ heavySlotsAvailable;
			if(totalSlotsAvailable <= 0) {
				System.out.print("\nJob can not have 0 volunteer slots, please try again");
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
	public void editJob(ArrayList<String> parksManage) {			
		while (true) {
			System.out.println("\nSelect one of the folowing options:");
			System.out.println("1. Change Job's Location:   " + jobLocation);
			System.out.println("2. Change Job's Date:        " 
								+ jobDate.get(Calendar.MONTH) + "/"
								+ jobDate.get(Calendar.DATE) + "/"
								+ jobDate.get(Calendar.YEAR));
			System.out.println("3. Change Job's Duration:    " + jobDuration + " days");
			System.out.println("4. Change Job's Slots:       " + totalSlotsAvailable);
			System.out.println("5. Change Job's Description: " + jobDescription);
			System.out.println("6. Change Job's Start Time:  " + startTime);
			System.out.println("7. Exit ");
			
	    	int userTyped = getNumber();
	    	
	    	if (userTyped == 1) {
	    		enterJobLocation(parksManage);
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
	 * @return job's date (MM/DD/YYYY where 0..11/1..31/2016
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
	 * @param newVolunteer must be Volunteer type.
	 */
	public void addVolunteer(Volunteer newVolunteer){
		
		int menuChoice = 0;
		boolean exit = false;
		while(!exit){
			if (totalVolunteers <= totalSlotsAvailable) {
				ParksProgram.menuHeader(newVolunteer);
				System.out.println("            ___Sign-Up___");
				System.out.println();
				System.out.println("\t    Available Slots");
				System.out.println("\tLight\tMedium\tHeavy");
				System.out.println("\t"+lightSlotsAvailable+"\t"+mediumSlotsAvailable+"\t"+heavySlotsAvailable);
				System.out.println("1. Volunteer for a light duty");
				System.out.println("2. Volunteer for a medium duty");
				System.out.println("3. Volunteer for a heavy duty");
				System.out.println("4. Exit");

				menuChoice = getNumber();
				while(menuChoice < 1 || menuChoice > DUTY_OPTIONS) {
					System.out.print("Must select a menu option between 1 and " + DUTY_OPTIONS + "\nSelection: ");
					menuChoice = getNumber();
				}
				switch(menuChoice){
					case 1: exit = addToSlot(newVolunteer, 1, lightSlotsAvailable);						
						break;
					case 2: exit = addToSlot(newVolunteer, 2, mediumSlotsAvailable);
						break;
					case 3: exit = addToSlot(newVolunteer, 3, heavySlotsAvailable);
						break;
					case 4: System.out.println("Exiting...");
						exit = true;
						break;
				}			
			} 
			else System.out.print("\nNo more available slots.\n");
		}	
	}	
	
	protected boolean addToSlot(Volunteer newVolunteer, int dutyType, int max){
		if (max == 0){
			System.out.println("No slots available for this duty level.");
			return false;
		} else {
			if (dutyType == 1) {
				if (lightVolunteers == null) {
					lightVolunteers = new LinkedList<Volunteer>();
				} else {
					lightVolunteers.add(newVolunteer);
					totalVolunteers = totalVolunteers + 1;
					totalSlotsAvailable = totalSlotsAvailable - 1;
					lightSlotsAvailable = lightSlotsAvailable - 1;
					System.out.println("Congratulation, you have successfully sign-up to volunteer!\n");				
					return true;
				}				
			} else if (dutyType == 2) {
				if (mediumVolunteers == null) {
					mediumVolunteers = new LinkedList<Volunteer>();
				} else {
					mediumVolunteers.add(newVolunteer);
					totalVolunteers = totalVolunteers + 1;
					totalSlotsAvailable = totalSlotsAvailable - 1;
					mediumSlotsAvailable = mediumSlotsAvailable - 1;
					System.out.println("Congratulation, you have successfully sign-up to volunteer!\n");				
					return true;
				}				
			} else if (dutyType == 3) {
				if (heavyVolunteers == null) {
					heavyVolunteers = new LinkedList<Volunteer>();
				} else {
					heavyVolunteers.add(newVolunteer);
					totalVolunteers = totalVolunteers + 1;
					totalSlotsAvailable = totalSlotsAvailable - 1;
					heavySlotsAvailable = heavySlotsAvailable - 1;
					System.out.println("Congratulation, you have successfully sign-up to volunteer!\n");				
					return true;
				}				
			} 
		}		
		return false;
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


	public void deleteJob() {
		// To Do
	}
}
