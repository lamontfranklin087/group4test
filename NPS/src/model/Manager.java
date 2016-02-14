package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Creates a Truck object for use in the Easy Street GUI.
 * 
 * @author dave1729
 * @version 305-3
 * 
 * @author Ihar Lavor
 * @version 02/13/2016 
 * Added Business rules number 1, 2
 */
public final class Manager extends AbstractUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int MAIN_MENU_OPTIONS = 6;
	private final int MAX_NUMBER_JOBS = 30;
	private final int MAX_JOBS_IN_7_CONSECUTIVE_DAYS = 5;
	private LinkedList<Job> jobsAtMyParks = new LinkedList<Job>();
	ArrayList<String> parksManage;

	/**
	 * Default constructor.
	 */
	public Manager() {
		super();
	}
	
	protected Manager(String theFirstName, String theLastName, String theEmail,
			String thePassword) {
		super(theFirstName, theLastName, theEmail, thePassword);
	}
	
	@Override
	public void viewSumAllJobs(Collection<Job> allJobs) {
		Iterator<Job> itr = allJobs.iterator();
		
		while (itr.hasNext()) {
			Job temp = itr.next();
			if (temp.getJobManager().equals(getFirstName() + " " + getLastName())) {
				System.out.println("ID     " + "Date\t    " + "Start     " + "Duration\t" 
		                + "Slots\t" + "Volun.\t"+ "Locaton\t\t" + "Manager\t\t" 
						 + "Description");
				System.out.println(temp.toStringTable());
			}			
		}
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

	@Override
	public String getSimpleName() {
		return "Park Manager";
	}
	
	/**
	 * Prints a list of all volunteers for a job.
	 * @param allJobs is a list of all Jobs.
	 */
	public void viewVolunteers(LinkedList<Job> allJobs) {
		if (allJobs != null) {
			System.out.println("Enter Job's ID to view volunteers or 1 to exit");
			int temp = getNumber();
			if (temp != 1) {
				Job foundJob = findJob(temp, allJobs);
				if (foundJob != null) {
					LinkedList<Volunteer> volunteer = foundJob.getVolunteers();
					
					if (volunteer != null) {
						Iterator<Volunteer> itr = volunteer.iterator();
						System.out.println("First Name\tLastName\tEmail address");			
						while (itr.hasNext()) {
							Volunteer tempVol = itr.next();
							System.out.println(tempVol.getFirstName() + "\t" +
											   tempVol.getLastName() + "\t" +
											   tempVol.getEmail());
						}
					} else {
						System.out.println("No volunteers for this job");
					}
				}
			}
		}
	}
	
	/** 
	 * Print's the main menu and returns the current menu choice.
	 * @return menuChoice	the next menu to be entered.
	 * */
	@Override
	public void mainMenu(Collection<Job> allJobs, Collection<User> allUsers) {
		
		managerJobList(allJobs);
		
		boolean exit = false;
		while (!exit) {
			int menuChoice = 0;
			ParksProgram.menuHeader(this);
			System.out.println("            ___Menu___");
	   		System.out.println("1. Submit a new job");
	   		System.out.println("2. Delete a job");
	   		System.out.println("3. Edit the details of a job");
	   		System.out.println("4. View a summary of all upcoming jobs");
	   		System.out.println("5. View the Volunteers for a job");
	   		System.out.println("6. Exit");
			
			menuChoice = getNumber();
			while(menuChoice < 1 || menuChoice > MAIN_MENU_OPTIONS) {
				System.out.print("Must select a menu option between 1 and " + MAIN_MENU_OPTIONS + "\nSelection: ");
				menuChoice = getNumber();
			}
			switch(menuChoice){
				case 1: submitNewJob(allJobs);
					break;
				case 2: deleteJob(allJobs);
					break;
				case 3: editJob(allJobs);
					break;
				case 4: viewSumAllJobs(allJobs);
					break;
				case 5: viewVolunteers(jobsAtMyParks);
					break;
				case 6: System.out.println("Exiting...");
					exit = true;
					break;
			}			
		}
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
	 * Edit job's fields.
	 * @param allJobs is a list of all Jobs.
	 */
	private void editJob(Collection<Job> allJobs) {
		System.out.println("Enter Job's ID number or 0 to exit:");
		int jobIDTemp = getNumber();
		if (jobIDTemp > 0) {
			Job foundJob = findJob(jobIDTemp, allJobs);
			if (foundJob == null) {
				System.out.println("No job with ID " + jobIDTemp);
			} else {
				foundJob.editJob(parksManage);
			}
		}		
	}

	/**
	 * Delete a job.
	 * @param allJobs is a list of all Jobs
	 */
	private void deleteJob(Collection<Job> allJobs) {
		System.out.println("Enter Job's ID number or 0 to exit:");
		int jobIDTemp = getNumber();
		if (jobIDTemp > 0) {
			Job temp = findJob(jobIDTemp, allJobs);			
			if (temp == null) {
				System.out.println("No job with ID " + jobIDTemp);
			} else {
				allJobs.remove(temp);
				update(allJobs);
				System.out.println("Job with ID " + jobIDTemp + " was deleted.");
			}
		}
	}

	/**
	 * Create new job.
	 * @param allJobs is a list of all Jobs.
	 */
	private void submitNewJob(Collection<Job> allJobs) {
		if (allJobs.size() < MAX_NUMBER_JOBS) {
			Job newJob = new Job();
			newJob.createJob(getFirstName(), getLastName(), parksManage);			
			boolean allowCreateJob = jobsIn7Days(newJob.getDate(), allJobs);
			if (allowCreateJob) {			
				System.out.println(newJob.toString());
				System.out.println("1. To confirm job\n2. To exit without saving job");
				if (getNumber() == 1) {
					allJobs.add(newJob);
					update(allJobs);
				}
			} else {
				System.out.println("A job can't be added because you are to busy for that week.");
			}
		} else {
			System.out.println("A job can't be added because the total number of pending jobs is currently 30.");
		}		
	}
	/**
	 * Checking: if there during any consecutive 7 day period 
	 * more than 5 jobs or not.
	 * @param newly createdJob
	 * @param allJobs is a list of all Jobs.
	 * @return false if there more 5 or more jobs, otherwise true.
	 */
	private boolean jobsIn7Days(Calendar createdJob, Collection<Job> allJobs) {
		int jobsIn7Days = 0;	
		int jobDayOfYear = createdJob.get(Calendar.DAY_OF_YEAR);
				
		Iterator<Job> itr = jobsAtMyParks.iterator();
		if (jobsAtMyParks != null && jobsAtMyParks.size() > 0) {		
			while (itr.hasNext()) {
				int temp = itr.next().getDate().get(Calendar.DAY_OF_YEAR);
				if ((temp <= (jobDayOfYear + 3)) && (temp >= (jobDayOfYear - 3))) {					
					jobsIn7Days = jobsIn7Days + 1;
				}				
			}
			if (jobsIn7Days < MAX_JOBS_IN_7_CONSECUTIVE_DAYS) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Updates a list of jobs current manager manages.
	 * @param allJobs is a list of all Jobs.
	 */
	private void update(Collection<Job> allJobs) {
		jobsAtMyParks = new LinkedList<Job>();
		boolean iHaveJobs = false;
		if (allJobs != null) {			
			Job temp;
			Iterator<Job> itr = allJobs.iterator();
			while (itr.hasNext()) {
				temp = itr.next();				
				if (temp.getJobManager().equalsIgnoreCase(this.getFirstName() + " " + this.getLastName())) {
					jobsAtMyParks.add(temp);
					iHaveJobs = true;
				}
			}
		}
		if (!iHaveJobs) {
			jobsAtMyParks = null;
		}
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
