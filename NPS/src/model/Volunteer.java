package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
	
	@Override
	public ArrayList<String> getMethodList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("viewMyJobs");
		list.add("viewSumAllJobs");
		list.add("viewJobDetails");
		list.add("jobSignUp");
		
		return list;
	}
	
	/**
	 * Displays all the jobs that the current volunteer is signed up for.
	 * 
	 * @param allJobs  a collection of all the existing jobs
	 */
	public StringBuilder viewMyJobs(Collection<Job> allJobs) {				
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
			return new StringBuilder("You are not currently signed up for any jobs.");
		} else {
			StringBuilder string = new StringBuilder();
			string.append("ID     " + "Date\t    " + "Duration\t" 
					+ "Slots\t" + "Manager\t\t" + "Locaton\t\t"  
					+ "\t\tDescription");
			myJobs.forEach(job->string.append("\n" + job.toStringTable()));
			return string;
		}
	}
	
	/**
	 * Gives the current volunteer options to sign up for current jobs.
	 * 
	 * @param allJobs  a collection of all the existing jobs
	 */
	public StringBuilder jobSignUp(Collection<Job> allJobs) {
		System.out.println("Please enter Job ID to sign-up or 0 to quit: ");
		int id = getNumber();
		if (id != 0) {
			Iterator<Job> itr = allJobs.iterator();
			while (itr.hasNext()) {
				Job temp = itr.next();
				if (temp.getJobID() == id){
					if (dateAvailable(allJobs, temp)) {
						temp.addVolunteer(this);
						return new StringBuilder("You successfully signed up for a job");
					} else {
						return new StringBuilder("You are already committed on that day.");
					}
				}			
			}
		}
		return null;
	}
	
	private boolean dateAvailable(Collection<Job> allJobs, Job theJob) {
				
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

	/**
	 * A string representation of this volunteer.
	 * 
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
