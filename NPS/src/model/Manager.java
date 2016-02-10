package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Creates a Truck object for use in the Easy Street GUI.
 * 
 * @author dave1729
 * @version 305-3
 */
public final class Manager extends AbstractUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final int MAIN_MENU_OPTIONS = 6;

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
	

	@Override
	public String getSimpleName() {
		return "Park Manager";
	}
	
	public void viewVolunteers(Collection<Job> allJobs) {
		viewSumAllJobs(allJobs);
		System.out.println("Enter Job's ID to view volunteers or 1 to exit");
		int temp = getNumber();
		if (temp != 1) {
			Job foundJob = findJob(temp, allJobs);
			LinkedList<Volunteer> volunteer = foundJob.getVolunteers();
			
			if (volunteer != null) {
				Iterator<Volunteer> itr = volunteer.iterator();
				System.out.println("First Name\tLastName\tEmail address");			
				while (itr.hasNext()) {
					System.out.println(itr.next().getFirstName() + "\t" +
										itr.next().getLastName() + "\t" +
										itr.next().getEmail());
				}
			} else {
				System.out.println("No volunteers for this job");
			}
		}
	}
	
	/** Print's the main menu and returns the current menu choice.
	 * 
	 * @return menuChoice	the next menu to be entered.
	 * */
	@Override
	public void mainMenu(Collection<Job> allJobs, Collection<User> allUsers) {
		boolean exit = false;
		while (!exit) {
			int menuChoice = 0;
			System.out.println("\n-------------Urban Parks Collective!------------");
			System.out.println("You are logged in as...");
			System.out.println(getSimpleName() + ", " + getFirstName()
		   					  + " " + getLastName());
			System.out.println();
			System.out.println("            ___Menu___");
	   		System.out.println("1. Submit a new job");
	   		System.out.println("2. Delete a job");
	   		System.out.println("3. Edit the details of a job");
	   		System.out.println("4. View a summary of all upcoming jobs");
	   		System.out.println("5. View the Volunteers for a job");
	   		System.out.println("6. Exit");
			
			menuChoice = getNumber();//scan.nextInt();
			while(menuChoice < 1 || menuChoice > MAIN_MENU_OPTIONS) {
				System.out.print("Must select a menu option between 1 and " + MAIN_MENU_OPTIONS + "\nSelection: ");
				menuChoice = getNumber();//scan.nextInt();
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
				case 5: viewVolunteers(allJobs);
					break;
				case 6: System.out.println("Exiting...");
					exit = true;
					break;
			}
		}
	}
	
	private void editJob(Collection<Job> allJobs) {
		System.out.println("Enter Job's ID number or 0 to exit:");
		int jobIDTemp = getNumber();
		if (jobIDTemp > 0) {
			Job foundJob = findJob(jobIDTemp, allJobs);
			if (foundJob == null) {
				System.out.println("No job with ID " + jobIDTemp);
			} else {
				foundJob.editJob();
			}
		}		
	}

	public void deleteJob(Collection<Job> allJobs) {
		System.out.println("Enter Job's ID number or 0 to exit:");
		int jobIDTemp = getNumber();
		if (jobIDTemp > 0) {
			Job foundJob = findJob(jobIDTemp, allJobs);
			if (foundJob == null) {
				System.out.println("No job with ID " + jobIDTemp);
			} else {
				allJobs.remove(foundJob);
				System.out.println("Job with ID " + jobIDTemp + " was deleted.");
			}			   
		}
	}

	public void submitNewJob(Collection<Job> allJobs) {
		Job newJob = new Job();
		newJob.createJob(getFirstName(), getLastName());		
		System.out.println(newJob.toString());
		System.out.println("1. To confirm job\n2. To exit without saving job");
		if (getNumber() == 1) {
			allJobs.add(newJob);
		}
	}
	
	@Override
	public void viewJobDetails() {		
	}
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
