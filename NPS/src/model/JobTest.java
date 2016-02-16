/**
 * 
 */
package model;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;

import org.junit.Test;

/**
 * Job Test.
 * @author Ihar Lavor
 * @version 02/13/2016
 *
 */
public class JobTest {
	
	/**
	 * Test method for {@link model.Job#enterJobLocation()}.
	 */
	@Test
	public void testEnterJobLocation() {
		Job testJob = new Job();		
		String input = "1";
		
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    ArrayList<String> parks = new ArrayList<String>();
	    parks.add("Dash Point Park in Federal Way");
	    testJob.enterJobLocation(parks);
	    
	    assertTrue(testJob.getJobLocation().equals("Dash Point Park in Federal Way"));
	}

	/**
	 * Test method for {@link model.Job#enterDate()}.
	 * Test enterDate.
	 */
	@Test
	public void testEnterDate() {
		Collection<Job> allJobs = new LinkedList<Job>();
		Job testJob = new Job();
		
		String input = "5/1/2016";	//correct Date
		InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    testJob.enterDate(allJobs);
	    
	    assertTrue(testJob.getDate().get(Calendar.DATE) == 1);
	    assertTrue(testJob.getDate().get(Calendar.MONTH) == 5 - 1);
	    assertTrue(testJob.getDate().get(Calendar.YEAR) == 2016);
	    
	    input = "5/16/2016";	//Future date - more then 3 months	
		in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);	
	    
	    assertFalse(testJob.enterDate(allJobs));
	    	    
	    input = "2/14/2016";	//Past date	
		in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
	    assertFalse(testJob.enterDate(allJobs));	    
	}
	
	/**
	 * Test method for {@link model.Job#addToSlot()}.
	 */
	@Test
	public void testAddToSlot() {
		Job testJob = new Job();
		Volunteer newVolunteer = new Volunteer("Raylene", "Depew",
				"RayleneDepew@mail.com",  "pass");
		
		testJob.addToSlot(newVolunteer, 1, 1);		
		assertTrue(testJob.getVolunteers().size() == 1);		
		testJob.addToSlot(newVolunteer, 1, testJob.getLightSlotsAvailable());
		assertTrue(testJob.getVolunteers().size() == 1);
		
		testJob.addToSlot(newVolunteer, 2, 1);		
		assertTrue(testJob.getVolunteers().size() == 2);		
		testJob.addToSlot(newVolunteer, 2, testJob.getMediumSlotsAvailable());
		assertTrue(testJob.getVolunteers().size() == 2);
		
		testJob.addToSlot(newVolunteer, 3, 1);		
		assertTrue(testJob.getVolunteers().size() == 3);		
		testJob.addToSlot(newVolunteer, 3, testJob.getHeavySlotsAvailable());
		assertTrue(testJob.getVolunteers().size() == 3);
	}
	
	
	/**
	 * Test method for {@link model.Job#setJobID()}.
	 */
	@Test
	public void testSetJobID() {
		//Setting up next ID happening once before login
		Job testJob = new Job();				
	    testJob.setJobID(1006);	
	    
	    //This is after login when manager tries to create new job
	    Job testJob2 = new Job();
	    System.out.println(testJob2.getJobID());
	    assertTrue(testJob2.getJobID() == 1007);
	}
	

	/**
	 * Test method for {@link model.Job#enterJobDuration()}.
	 * Test only if duration time is 1 or 2, 
	 * otherwise method will loop until you enter correct number.
	 */
	@Test
	public void testEnterJobDuration() {
		Job testJob = new Job();
		String input = "2";
		
		InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    testJob.enterJobDuration();
	    
	    assertTrue(testJob.getJobDuration() == 2);	    
	}

	/**
	 * Test method for {@link model.Job#enterJobDescription()}.
	 */
	@Test
	public void testEnterJobDescription() {
		Job testJob = new Job();
		String input = "Build Walk Path";
		
		InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    testJob.enterJobDescription();
	    
	    assertTrue(testJob.getDescription().equals("Build Walk Path"));	
	}

	/**
	 * Test method for {@link model.Job#enterStartTime()}.
	 */
	@Test
	public void testEnterStartTime() {
		Job testJob = new Job();
		String input = "8:00 AM";
		
		InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    testJob.enterStartTime();
	    
	    assertTrue(testJob.getStartTime().equals("8:00 AM"));  
	}
		
	/**
	 * Test method for {@link model.Job#deleteJob()}.
	 */
	@Test
	public void testToString() {
		Collection<Job> allJobs = new LinkedList<Job>();
		Job testJob = new Job();
		String input = "3/16/2016";		
		InputStream in = new ByteArrayInputStream(input.getBytes());	    
	    				
		in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    testJob.enterDate(allJobs);
	    	    
	    input = "Build Walk Path";		
		in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    testJob.enterJobDescription();
	    
	    input = "2";		
		in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    testJob.enterJobDuration();
	    
	    input = "1";		
	    in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);	    
	    ArrayList<String> parks = new ArrayList<String>();
	    parks.add("Dash Point Park in Federal Way");
        testJob.enterJobLocation(parks);
	    	    
	    input = "8:00 AM";		
		in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    testJob.enterStartTime();
	    	            
	    assertTrue(testJob.getDate().get(Calendar.DAY_OF_MONTH) == 16);
	    assertTrue(testJob.getDate().get(Calendar.MONTH) == 3 - 1);
	    assertTrue(testJob.getDate().get(Calendar.YEAR) == 2016);
	    assertTrue(testJob.getDescription().equals("Build Walk Path"));
	    assertTrue(testJob.getJobDuration() == 2);
	    assertTrue(testJob.getJobLocation().equals("Dash Point Park in Federal Way"));
	    assertTrue(testJob.getStartTime().equals("8:00 AM"));
	    
	    System.out.println("++++++++++++++++++++++++++++++++\n" + 
	    					testJob.toString() + 
	    				   "++++++++++++++++++++++++++++++\n");
	    System.out.println("ID     " + "Date\t    " + "Start     " + "Duration\t" 
                + "Slots\t" + "Volun.\t"+ "Locaton\t\t" + "Manager\t\t" 
				 + "Description");
	    System.out.println(testJob.toStringTable() + 
	    					"\n\n++++++++++++++++++++++++++++++");
	    
	}
}
