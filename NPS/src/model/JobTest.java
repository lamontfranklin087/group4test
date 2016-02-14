/**
 * 
 */
package model;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Test;

/**
 * Job Test.
 * @author Ihar Lavor
 * @version 02/06/2016
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
	 * Test enterDate only if data is in range from today to 90 days ahead.
	 */
	@Test
	public void testEnterDate() {
		Job testJob = new Job();
		
		String input = "3/16/2016";		
		InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    testJob.enterDate();
	    
	    assertTrue(testJob.getDate().get(Calendar.DAY_OF_MONTH) == 16);
	    assertTrue(testJob.getDate().get(Calendar.MONTH) == 3 - 1);
	    assertTrue(testJob.getDate().get(Calendar.YEAR) == 2016);
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
	 * @throws IOException 
	 */
	@Test
	public void testDeleteJob() throws IOException {
		
		Job testJob = new Job();
		String input = "6";
		
		InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    testJob.enterJobSlot();
	    		
	    input = "3/16/2016";		
		in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    testJob.enterDate();
	    	    
	    input = "Build Walk Path";		
		in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    testJob.enterJobDescription();
	    
	    input = "2";		
		in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    testJob.enterJobDuration();
	    
	    input = "Tacoma Park";		
	    in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    	    
	    input = "8:00 AM";		
		in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    testJob.enterStartTime();
	    
	    Volunteer vol = new Volunteer(null, null, null, null);
	    testJob.addVolunteer(vol);
	    	    
	    assertTrue(testJob.getAvailableSlots() == 6);
	    assertTrue(testJob.getDate().get(Calendar.DAY_OF_MONTH) == 16);
	    assertTrue(testJob.getDate().get(Calendar.MONTH) == 3 - 1);
	    assertTrue(testJob.getDate().get(Calendar.YEAR) == 2016);
	    assertTrue(testJob.getDescription().equals("Build Walk Path"));
	    assertTrue(testJob.getJobDuration() == 2);
	    assertTrue(testJob.getJobLocation().equals("Tacoma Park"));
	    assertTrue(testJob.getStartTime().equals("8:00 AM"));
	    assertTrue(testJob.getVolunteers().size() == 1);
	    
	    testJob.deleteJob();
	    
	    assertTrue(testJob.getAvailableSlots() == -1);
	    assertTrue(testJob.getDate() == null);
	    assertTrue(testJob.getDescription() == null);
	    assertTrue(testJob.getJobDuration() == -1);
	    assertTrue(testJob.getJobLocation() == null);
	    assertTrue(testJob.getStartTime() == null);
	    assertTrue(testJob.getVolunteers() == null);
	}

	/**
	 * Test method for {@link model.Job#addVolunteer(model.Volunteer)}.
	 */
	@Test
	public void testAddVolunteer() {
		Job testJob = new Job();
		
		Volunteer vol = new Volunteer(null, null, null, null);
	}
		
	/**
	 * Test method for {@link model.Job#deleteJob()}.
	 */
	@Test
	public void testToString() {
		
		Job testJob = new Job();
		String input = "3/16/2016";		
		InputStream in = new ByteArrayInputStream(input.getBytes());	    
	    				
		in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    testJob.enterDate();
	    	    
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
	    
	    Volunteer vol = new Volunteer(null, null, null, null);
	    testJob.addVolunteer(vol);
	    	    	    
	    assertTrue(testJob.getAvailableSlots() == 6);
	    	            
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
