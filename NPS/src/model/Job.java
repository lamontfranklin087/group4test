package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Scanner;

public class Job implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String jobLocation;
	
	private Calendar jobDate;
	
	private int jobDuration;
	
	private int slotsAvailable;
	
	private String jobDescription;
	
	private String startTime;
	
	private LinkedList<Volunteer> volunteers;
	
	Scanner keyboard = new Scanner(System.in);
	
	public void createJob() {
		
		enterJobLocation();		
		enterDate();
		enterStartTime();
		enterJobDuration();
		enterJobSlot();		
		enterJobDescription();
	}

	private void enterJobLocation() {
		System.out.println("Enter job location:");
		jobLocation = keyboard.nextLine();
	}
	
	private void enterDate() {
		
		int diffDays = -1;
		do {
			System.out.println("Enter job date: (MM/dd/yyyy)");			
			
			Calendar mydate = new GregorianCalendar();
			String mystring = keyboard.nextLine();//"January 2, 2010";
			Date thedate;
			try {
				thedate = new SimpleDateFormat("MM/dd/yyyy").parse(mystring);
			
				mydate.setTime(thedate);
				
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");		
				Calendar calobj = Calendar.getInstance();
				
				Date thedate2 = new SimpleDateFormat("MM/dd/yyyy").parse(df.format(calobj.getTime()));
				mydate.setTime(thedate);
				calobj.setTime(thedate2);
				
				long diff = thedate.getTime() - thedate2.getTime();
				diffDays = (int) diff / (24 * 60 * 60 * 1000);			
				
				if (diffDays < 0) {
					System.out.println("You can't create past Jobs");
				}
			
			} catch (ParseException e) {
				System.out.println("Wrong Date Format");
			}
		} while (diffDays < 0);
	}
	
	private void enterJobDuration() {
		System.out.println("Enter job duration: (Number of days, for example 2)");
		
		String temp = keyboard.next();
		keyboard.nextLine();
		
		while (true) {
			if (temp.compareTo("1") == 0 || temp.compareTo("2") == 0) {
				jobDuration = Integer.parseInt(temp);
				break;
			}
			System.out.println("Job duration can't exceed 2 days. ");
			System.out.println("Enter job duration:");
			temp = keyboard.next();
			keyboard.nextLine();
		}
	}
	
	private void enterJobSlot() {
		System.out.println("Enter job slots: (Only integers allowed)");
		String temp = keyboard.next();
		keyboard.nextLine();
		
		while(true){
	        try {	        	
	        	if (Integer.parseInt(temp) > 0) {
	        		slotsAvailable = Integer.parseInt(temp);
	        		break;
	        	} else {
	        		System.out.println("Job slots can't be 0.");
	        	}	            
	        } catch(NumberFormatException ne) {
	            System.out.println("That's not a whole number.");	            
	        }	    	
			System.out.println("Enter job slots:");
	        temp = keyboard.next();
			keyboard.nextLine();
		}
	}
	
	private void enterJobDescription() {
		System.out.println("Enter short job description:");
		jobDescription = keyboard.nextLine();
	}
	
	private void enterStartTime() {
		System.out.println("Enter time when job starts: (for example 8:00 AM)");
		startTime = keyboard.nextLine();
	}
	public void editJob(){
		System.out.println("Select one of the folowing options:");
		System.out.println("1. Change Job's Location");
		System.out.println("2. Change Job's Date");
		System.out.println("3. Change Job's Duration");
		System.out.println("4. Change Job's Slots");
		System.out.println("5. Change Job's Description");
		System.out.println("6. Change Job's Start Time");
		System.out.println("7. Exit ");
		
		int userTyped = keyboard.nextInt();
		switch (userTyped) {
	   	   case 1: enterJobLocation(); break;
	   	   case 2: enterDate(); break;
	   	   case 3: enterJobDuration(); break;
	   	   case 4: enterJobSlot(); break; 
		   case 5: enterJobDescription(); break;
		   case 6: enterStartTime(); break;
		   default: break;	   	   
		}
	}

	public void deleteJob(){
		
	}

	public String getJobLocation() {
		return jobLocation;
	}
	
	public String getDescription(){
		return jobDescription;		
	}

	public Calendar getDate(){
		return jobDate;
	}

	public String getTime(){
		return startTime;
	}

	public void addVolunteer(Volunteer newVolunteer){
		if (volunteers.size() < slotsAvailable) {
			volunteers.add(newVolunteer);
		} else {
			System.out.println("No more available slots.");
		}	
	}	
}
