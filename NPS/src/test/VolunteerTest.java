package test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import model.Job;
import model.MyOwnException;
import model.User;
import model.Volunteer;

public class VolunteerTest {
	
	private final int NUM_OF_TEST_JOBS = 10;
	
	private User testUser = new Volunteer("JUnitTestFirst", "JUnitTestLast",
			 							"JUnitTestEmail", "JUnitTestPassword");
	
	private Collection<Job> testJobs;

	@Before
	public void setUp() throws Exception {
		Job tempJob;
		testUser = new Volunteer("JUnitTestFirst", "JUnitTestLast",
								 "JUnitTestEmail", "JUnitTestPassword");
		testJobs = new LinkedList<Job>();
		for (int i = 0; i < NUM_OF_TEST_JOBS; i++) {
			
			int day = 5 + i;
			int month = 4;
			int year = 2016;
			
			Calendar jobDate = new GregorianCalendar();			
			jobDate.set(Calendar.YEAR, year);
			jobDate.set(Calendar.MONTH, month);
			jobDate.set(Calendar.DAY_OF_MONTH, day);
			
			tempJob = new Job();			
			tempJob.setDate(jobDate);
			tempJob.setJobDuration(1);
			tempJob.setJobSlot(5, 5, 5);
			tempJob.setJobID(i + 1);
			tempJob.setJobDescription("TestJob#:" + (i + 1));
			testJobs.add(tempJob);
		}
	}

	/**
	 * Tests that the correct Simple Name is returned.
	 */
	@Test
	public void testGetSimpleName() {
		assertEquals("getSimpleName() Test failed!", "Volunteer", testUser.getSimpleName());
	}
	
	@Test
	public void testviewMyJobsSignedUpForOneJob() throws MyOwnException {	
		int desiredJobID = 4;
		int desiredSlot = 2;
		((Volunteer)testUser).jobSignUp(testJobs, desiredJobID, desiredSlot);
		LinkedList<Job> myJobs = ((Volunteer)testUser).viewMyJobs(testJobs);
		
		assertEquals("myJobs size test failed in testviewMyJobs()", 1, myJobs.size());	
		assertEquals("light job not found in testviewMyJobs()", desiredJobID, myJobs.get(0).getJobID());
		
		assertEquals("myJobs size test failed in testviewMyJobs()", 1, myJobs.size());
	}
	
	@Test
	public void testviewMyJobsSignedUpForMoreThanOneJob() throws MyOwnException {	
		int desiredJobID = 4;
		int desiredSlot = 1;
		((Volunteer)testUser).jobSignUp(testJobs, desiredJobID, desiredSlot);
		((Volunteer)testUser).jobSignUp(testJobs, desiredJobID + 2, desiredSlot);
		((Volunteer)testUser).jobSignUp(testJobs, desiredJobID - 2, desiredSlot);
		LinkedList<Job> myJobs = ((Volunteer)testUser).viewMyJobs(testJobs);
		
		assertEquals("myJobs size test failed in testviewMyJobs()", 3, myJobs.size());	
		assertEquals("light job not found in testviewMyJobs()", desiredJobID - 2, myJobs.get(0).getJobID());
		assertEquals("light job not found in testviewMyJobs()", desiredJobID, myJobs.get(1).getJobID());
		assertEquals("light job not found in testviewMyJobs()", desiredJobID + 2, myJobs.get(2).getJobID());		
	}
	
	@Test
	public void testviewMyJobsSignedUpForZeroJob() throws MyOwnException {	
		LinkedList<Job> myJobs = ((Volunteer)testUser).viewMyJobs(testJobs);		
		assertEquals("myJobs size test failed in testviewMyJobs()", 0, myJobs.size());	
	}

	@Test
	public void testJobSignUp() throws MyOwnException {
		//Set up a boolean to save jobSignUp() result
		boolean jobAddedResult;
		//Set up jobsVolunteer to be assigned the volunteers that were signed up
		LinkedList<Volunteer> jobsVolunteers = new LinkedList<Volunteer>();
		
		//Make a job to add the testUser to and add it to testJobs
		Job temp = new Job();
		temp.setDate(new GregorianCalendar(2020, 1, 1));
		temp.setJobSlot(1, 1, 1);
		temp.setJobID(123);
		testJobs.add(temp);
		
		//Sign up testUser for the job
		jobAddedResult = ((Volunteer) testUser).jobSignUp(testJobs, 123, 1);

		//Check to see that the result is true and that the number of volunteers is just one
		assertEquals("JobAddedResult failed to be true in testJobSignUp()", true, jobAddedResult);
		assertEquals("Number of Volunteers failed in testJobSignUp()", 1, temp.getTotalVolunteers());
		
		//get that jobs volunteer's list
		jobsVolunteers = temp.getVolunteers();
		
		//Check that the volunteer for the job is our testUser
		Volunteer returnedVolunteer = jobsVolunteers.pop();
		assertEquals("Correct Volunteer signup test failed in testJobSignUp()", testUser.getEmail(), returnedVolunteer.getEmail());
	}
	
	@Test
	public void testJobSignUpForNotExistingJob() {
		int desiredJobID = 132;
		int desiredSlot = 1;
		boolean exception = false;
		try {
			((Volunteer)testUser).jobSignUp(testJobs, desiredJobID, desiredSlot);
		} catch (MyOwnException e) {			
			exception = true;
		}	
		assertTrue(exception);	
	}
}
