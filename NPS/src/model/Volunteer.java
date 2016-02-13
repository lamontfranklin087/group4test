package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Create a volunteer object.
 * @author Ihar Lavor
 * @version 02/12/2016 
 */
public final class Volunteer extends AbstractUser implements Serializable {

	private static final long serialVersionUID = 1L;
	private final int MAIN_MENU_OPTIONS = 5;

	public Volunteer() {
		super();
	}

	protected Volunteer(String theFirstName, String theLastName,
			String theEmail, String thePassword) {
		super(theFirstName, theLastName, theEmail, thePassword);
	}

	@Override
	public void mainMenu(Collection<Job> allJobs, Collection<User> allUsers) {
		boolean exit = false;
		while (!exit) {
			int menuChoice = 0;
			ParksProgram.menuHeader(this);
			System.out.println("            ___Menu___");
			System.out.println("1. View the jobs I am signed up for");
			System.out.println("2. View a summary of all upcoming jobs");
			System.out.println("3. View details of a selected upcoming job");
			System.out.println("4. Volunteer for a job");
			System.out.println("5. Exit");

			menuChoice = getNumber();//scan.nextInt();
			while(menuChoice < 1 || menuChoice > MAIN_MENU_OPTIONS) {
				System.out.print("Must select a menu option between 1 and " + MAIN_MENU_OPTIONS + "\nSelection: ");
				menuChoice = getNumber();//scan.nextInt();
			}
			switch(menuChoice){
				case 1: viewMyJobs(allJobs);
					break;
				case 2: viewSumAllJobs(allJobs);
					break;
				case 3: viewJobDetails(allJobs);
					break;
				case 4: jobSignUp(allJobs);
					break;
				case 5: System.out.println("Exiting...");
					exit = true;
					break;
			}
		}
	}
	
	private void viewMyJobs(Collection<Job> allJobs) {
		
		ParksProgram.menuHeader(this);
		System.out.println("            ___MyJobs___");		
		
		LinkedList<Job> myJobs = new LinkedList<Job>();
		
		allJobs.forEach(job->{
				LinkedList<Volunteer> volunteers = job.getVolunteers();
				if (volunteers!=null){
					volunteers.forEach(member->{
						if(member.getEmail().equals(this.getEmail()))
							myJobs.add(job);
						
						});
					}
				});
			
		if (myJobs.isEmpty()) {
			System.out.println("You are not currently signed up for any jobs.");
		} else {
			System.out.println("ID     " + "Date\t    " + "Start     " + "Duration\t" 
					+ "Slots\t" + "Volun.\t"+ "Locaton\t\t" + "Manager\t\t" 
					+ "Description");
			myJobs.forEach(job->System.out.println(job.toStringTable()));
		}
	}
	
	private void jobSignUp(Collection<Job> allJobs) {
		System.out.println("Please enter Job ID to sign-up or 0 to quit: ");
		int id = getNumber();
		if (id != 0) {
			allJobs.forEach(job->{
				if (job.getJobID() == id){
					if (dateAvailable(allJobs, job)) {
						job.addVolunteer(this);
					} else {
						System.out.println("You are already committed on that day.");
					}
				}
			});
		}
	}
	
	public boolean dateAvailable(Collection<Job> allJobs, Job theJob) {
				
		LinkedList<Job> myJobs = new LinkedList<Job>();
		
		allJobs.forEach(job->{
			LinkedList<Volunteer> volunteers = job.getVolunteers();
			if (volunteers != null){
				volunteers.forEach(member->{
				if (member.getEmail().equals(this.getEmail())) {						
					myJobs.add(job);
				}
			});
			}
		});
			
		if (myJobs.isEmpty()) {
			return true;
		} else {
			boolean scheduled = false;
			for (Job job:myJobs) {
				if(job.getDate().equals(theJob.getDate())) {
					scheduled = true;
				}
			}
			if (scheduled){
				return false;
			} else {
				return true;
			}			
		}
	}
	
	@Override
	public String getSimpleName() {
		return "Volunteer";
	}

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
