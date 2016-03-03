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
	
	private User testUser = new Volunteer();
	
	private Collection<Job> testJobs;

	@Before
	public void setUp() throws Exception {
		Job temp = new Job();
		testUser = new Volunteer("JUnitTestFirst", "JUnitTestLast",
								 "JUnitTestEmail", "JUnitTestPassword");
		testJobs = new LinkedList<Job>();
		for (int i = 0; i < NUM_OF_TEST_JOBS; i++) {
			temp.setJobSlot(5, 5, 5);
			temp.setJobID(i + 1);
			temp.setJobDescription("TestJob#:" + (i + 1));
			testJobs.add(temp);
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
	public void testVolunteerStringStringStringString() {
		assertEquals("testVolunteer() FirstName Test failed!", "JUnitTestFirst", testUser.getFirstName());
		assertEquals("testVolunteer() LastName Test failed!", "JUnitTestLast", testUser.getLastName());
		assertEquals("testVolunteer() Email Test failed!", "JUnitTestEmail", testUser.getEmail());
		assertEquals("testVolunteer() Password Test failed!", "JUnitTestPassword", testUser.getPassword());
	}
	
	@Test
	public void testviewMyJobs() throws MyOwnException {
		//Set up myJobs and a temp Job
		LinkedList<Job> myJobs = new LinkedList<Job>();
		Job temp = new Job();
		
		temp.setJobSlot(1, 1, 1);
		temp.setJobLocation("Test Job Location");
		temp.addVolunteer((Volunteer) testUser, 1);
		testJobs.add(temp);
		
		//Test that viewMyJobs() finds the correct jobs
		myJobs = ((Volunteer) testUser).viewMyJobs(testJobs);
		assertEquals("light job not found in testviewMyJobs()", "Test Job Location", myJobs.get(0).getJobLocation());
		assertEquals("myJobs size test failed in testviewMyJobs()", 1, myJobs.size());
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
}
