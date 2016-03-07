package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;

import org.junit.BeforeClass;
import org.junit.Test;

import model.Job;
import model.Manager;
import model.MyOwnException;
import model.Volunteer;
/**
 * @author Ihar Lavor
 * @version March/04/2016
 */
public class VolunteerTest {
	/**
	 * Number of jobs to be generated.
	 */
	private static int STARTING_NUM_TEST_JOBS = 10;
	/**
	 * Store all generated jobs.
	 */
	private static Collection<Job> testJobs;
	/**
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ArrayList<String> testParksManage1 = new ArrayList<String>();
		for (int i = 1; i <= 3; i++) {
			testParksManage1.add("Park#:" + i);
		}
		Manager testUser1 = new Manager("JUnitTest1_First", "JUnitTest1_Last",
					"JUnitTest1_Email", "JUnitTest1_Password", testParksManage1);		
		
		testJobs = new LinkedList<Job>();
		
		for (int i = 0; i < STARTING_NUM_TEST_JOBS; i++) {			
			int day = 5 + i;
			int month = 5;
			int year = 2016;
			
			Calendar jobDate = new GregorianCalendar();			
			jobDate.set(year, month, day);
			((Manager)testUser1).submitNewJob("JUnitTest1_First JUnitTest1_Last",
					"Steel Lake Park", jobDate, 1, 3, 3, 3, "Testing", "8:00AM", testJobs);
		}
	}

	/**
	 * Test method for {@link model.Volunteer#getSimpleName()}.
	 */
	@Test
	public void testGetSimpleName() {
		Volunteer testVolunteer = new Volunteer("Vol1_First", "Vol1_Last",
				"Vol1_Email", "Vol1_Password");
		assertEquals("getSimpleName() Test failed!", "Volunteer", testVolunteer.getSimpleName());
	}
	/**
	 * Test method for {@link model.Volunteer#viewMyJobs(java.util.Collection)}.
	 * @throws Exception if there is no jobs.
	 */
	@Test
	public void testviewMyJobsSignedUpForZeroJob() throws MyOwnException {	
		Volunteer testVolunteer = new Volunteer("Vol1_First", "Vol1_Last",
				"Vol1_Email", "Vol1_Password");
		LinkedList<Job> myJobs = testVolunteer.viewMyJobs(testJobs);		
		assertEquals("myJobs size test failed in testviewMyJobs()", 0, myJobs.size());	
	}
	/**
	 * Test method for {@link model.Volunteer#viewMyJobs(java.util.Collection)}.
	 * @throws Exception if there is no jobs.
	 */
	@Test
	public void testviewMyJobsSignedUpForOneJob() throws MyOwnException {	
		int desiredJobID = 4;
		int desiredSlot = 2;
		
		Volunteer testVolunteer = new Volunteer("Vol1_First", "Vol1_Last",
				"Vol1_Email", "Vol1_Password");		
		testVolunteer.jobSignUp(testJobs, desiredJobID, desiredSlot);
		LinkedList<Job> myJobs = testVolunteer.viewMyJobs(testJobs);
		
		assertEquals("myJobs size test failed in testviewMyJobs()", 1, myJobs.size());	
		assertEquals("light job not found in testviewMyJobs()", desiredJobID, myJobs.get(0).getJobID());
		
		assertEquals("myJobs size test failed in testviewMyJobs()", 1, myJobs.size());
	}
	/**
	 * Test method for {@link model.Volunteer#viewMyJobs(java.util.Collection)}.
	 * @throws Exception if there is no jobs.
	 */
	@Test
	public void testviewMyJobsSignedUpForMoreThanOneJob() throws MyOwnException {	
		int desiredJobID = 4;
		int desiredSlot = 2;
		
		Volunteer testVolunteer = new Volunteer("Vol2_First", "Vol2_Last",
				"Vol2_Email", "Vol2_Password");
		testVolunteer.jobSignUp(testJobs, desiredJobID, desiredSlot);
		testVolunteer.jobSignUp(testJobs, desiredJobID + 2, desiredSlot);
		testVolunteer.jobSignUp(testJobs, desiredJobID - 2, desiredSlot);
		LinkedList<Job> myJobs = testVolunteer.viewMyJobs(testJobs);
		
		assertEquals("myJobs size test failed in testviewMyJobs()", 3, myJobs.size());	
		assertEquals("light job not found in testviewMyJobs()", desiredJobID - 2, myJobs.get(0).getJobID());
		assertEquals("light job not found in testviewMyJobs()", desiredJobID, myJobs.get(1).getJobID());
		assertEquals("light job not found in testviewMyJobs()", desiredJobID + 2, myJobs.get(2).getJobID());		
	}
	/**
	 * Test method for {@link model.Volunteer#jobSignUp(java.util.Collection, int, int)}.
	 * @throws Exception if there is no jobs or wrong Job ID.
	 */
	@Test
	public void testJobSignUp() throws MyOwnException {
		//Set up a boolean to save jobSignUp() result
		boolean jobAddedResult;
		//Set up jobsVolunteer to be assigned the volunteers that were signed up
		Volunteer testVolunteer = new Volunteer("Vol1_First", "Vol1_Last",
				"Vol1_Email", "Vol1_Password");
				
		//Sign up testUser for the job
		jobAddedResult = testVolunteer.jobSignUp(testJobs, 3, 1);

		//Check to see that the result is true and that the number of volunteers is just one
		assertEquals("JobAddedResult failed to be true in testJobSignUp()", true, jobAddedResult);
		
		//Check that the volunteer for the job is our testUser
		int assigend = 0;
		for (int i = 0; i < testJobs.size(); i++) {
			LinkedList<Volunteer> tempList = ((LinkedList<Job>)testJobs).get(i).getVolunteers();
			if (tempList != null && tempList.size() == 1) {
				Iterator<Volunteer> itr = tempList.iterator();
				while (itr.hasNext()) {
					Volunteer temp = itr.next();
					if (temp.getEmail().equals(testVolunteer.getEmail())) {
						assigend = assigend + 1;
					}
				}
			}
		}
		assertEquals("Correct Volunteer signup test failed in testJobSignUp()", 1, assigend);
	}
	
	/**
	 *  Test method for {@link model.Volunteer#jobSignUp(java.util.Collection, int, int)}.
	 */
	@Test
	public void testJobSignUpForNotExistingJob() {
		int desiredJobID = 132;
		int desiredSlot = 1;
		Volunteer testVolunteer = new Volunteer("Vol1_First", "Vol1_Last",
				"Vol1_Email", "Vol1_Password");
		boolean exception = false;
		try {
			testVolunteer.jobSignUp(testJobs, desiredJobID, desiredSlot);
		} catch (MyOwnException e) {			
			exception = true;
		}	
		assertTrue(exception);	
	}
}
