/**
 * 
 */
package model;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
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
		
		String input = "Tacoma Park";
		
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    testJob.enterJobLocation();
	    
	    assertTrue(testJob.getJobLocation().equals("Tacoma Park"));
	}

	/**
	 * Test method for {@link model.Job#enterDate()}.
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
	 * Test method for {@link model.Job#enterJobDuration()}.
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
	 * Test method for {@link model.Job#enterJobSlot()}.
	 */
	@Test
	public void testEnterJobSlot() {
		Job testJob = new Job();
		String input = "6";
		
		InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    testJob.enterJobSlot();
	    
	    assertTrue(testJob.getAvailableSlots() == 6);
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
	public void testDeleteJob() {
		Job testJob = new Job();
		
		String input = "Tacoma Park";
		
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    testJob.enterJobLocation();
	    
	    input = "3/16/2016";
	    in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    testJob.getDate();
	    
	    input = "2";
	    in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    testJob.getJobDuration();
	    
	    input = "6";
	    in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    testJob.getAvailableSlots();
	    
	    input = "Build Walk Path";
	    in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    testJob.enterJobDescription();
	    
	    input = "8:00 AM";
	    in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    testJob.enterStartTime();
	    
	    Volunteer vol = new Volunteer(null, null, null, null);
	    testJob.addVolunteer(vol);
	    
	    assertTrue(testJob != null);
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
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    PrintStream old = System.out;
	    System.setOut(ps);    
	    testJob.addVolunteer(vol);
	    System.out.flush();
	    System.setOut(old);
	    
	    assertTrue(baos.toString().equals("No more available slots.\n"));
	    
	    String input = "2";
	    
		InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    testJob.enterJobSlot();
	    
	    testJob.addVolunteer(vol);
	    assertTrue(testJob.getVolunteers().size() == 1);
	    
	    testJob.addVolunteer(vol);
	    assertTrue(testJob.getVolunteers().size() == 2);
	    
	    testJob.addVolunteer(vol);
	    baos = new ByteArrayOutputStream();
	    ps = new PrintStream(baos);
	    old = System.out;
	    System.setOut(ps);    
	    testJob.addVolunteer(vol);
	    System.out.flush();
	    System.setOut(old);
	    
	    assertTrue(baos.toString().equals("No more available slots.\n"));
	}
}
