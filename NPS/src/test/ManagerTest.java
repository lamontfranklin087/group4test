package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import model.AbstractUser;
import model.Job;
import model.Manager;
import model.MyOwnException;
import model.User;
import model.Volunteer;

public class ManagerTest {
	
	private final int STARTING_NUM_TEST_JOBS = 10;
	
	private final int NUM_OF_TEST_PARKS = 3;
	
	private User testUser = new Volunteer();
	
	private ArrayList<String> testParksManage;
	
	private Collection<Job> testJobs;

	@Before
	public void setUp() throws Exception {
		Job tempJob;
		testJobs = new LinkedList<Job>();
		for (int i = 1; i <= STARTING_NUM_TEST_JOBS; i++) {
			tempJob = new Job();
			tempJob.setJobID(i);
			tempJob.setJobManager("Test Manager");
			tempJob.setJobDescription("TestJob#:" + i);
			testJobs.add(tempJob);
		}
		
		testParksManage = new ArrayList<String>();
		for (int i = 1; i <= STARTING_NUM_TEST_JOBS; i++) {
			testParksManage.add("Park#:" + i);
		}
		
		testUser = new Manager("JUnitTestFirst", "JUnitTestLast",
								 "JUnitTestEmail", "JUnitTestPassword", testParksManage);

	}

	@Test
	public void testGetSimpleName() {
		assertEquals("getSimpleName() Test failed!", "Park Manager", testUser.getSimpleName());
	}

	@Test
	public void testViewSumAllJobs() throws MyOwnException {
		//number of jobs to make for this manager
		int testUserJobsExpected = NUM_OF_TEST_PARKS;
		
		Collection<Job> testUserJobsActual;
		
		//make jobs for this manager
		Job tempJob;
		for (int i = 1; i <= testUserJobsExpected; i++) {
			tempJob = new Job();
			//tempJob.setJobSlot(5, 5, 5);
			tempJob.setJobID(i + STARTING_NUM_TEST_JOBS);
			tempJob.setJobManager("JUnitTestFirst JUnitTestLast");
			//tempJob.setJobDescription("TestJob#:" + (i + 1));
			testJobs.add(tempJob);
		}
		
		//run our method we are testing with those jobs included
		testUserJobsActual = testUser.viewSumAllJobs(testJobs);
		
		//check that we were returned the correct number of jobs
		assertEquals("Number of Jobs failed in viewSumAllJobs()", testUserJobsExpected, testUserJobsActual.size());
		
		//check that they are the correct jobs, this assumes they return in the same order
		for (int i = 1; i <= testUserJobsExpected; i++) {
			assertEquals("Actual Jobs failed in viewSumAllJobs()", i + STARTING_NUM_TEST_JOBS,
						((LinkedList<Job>) testUserJobsActual).pop().getJobID());
		}
	}

	@Test
	public void testDeleteJob() {
		//check that initial numbers of jobs are correct
		assertEquals("Number of INITIAL Jobs failed in deleteJob()", STARTING_NUM_TEST_JOBS, testJobs.size());
		
		//remove half the jobs
		int numToRemove = STARTING_NUM_TEST_JOBS / 2;
		for(int i = 1; i <= numToRemove; i++) {
			((Manager) testUser).deleteJob(i, testJobs);
		}
		int actualJobID;
		//check that that number of jobs was removed
		assertEquals("Number of RESULTING Jobs failed in deleteJob()", STARTING_NUM_TEST_JOBS - numToRemove, testJobs.size());
		for(int i = (numToRemove + 1); i < STARTING_NUM_TEST_JOBS; i++) {
			actualJobID = ((LinkedList<Job>) testJobs).pop().getJobID();
			assertEquals("A remaining jobs after deletion were incorrect in deleteJob()", i, actualJobID);
		}
	}

	@Test
	public void testManagerJobList() throws MyOwnException {
		//number of jobs to make for this manager
		int testUserJobsExpected = NUM_OF_TEST_PARKS;
		
		Collection<Job> testUserJobsActual;
		
		//make jobs for this manager
		Job tempJob;
		for (int i = 1; i <= testUserJobsExpected; i++) {
			tempJob = new Job();
			//tempJob.setJobSlot(5, 5, 5);
			tempJob.setJobID(i + STARTING_NUM_TEST_JOBS);
			tempJob.setJobManager("JUnitTestFirst JUnitTestLast");
			//tempJob.setJobDescription("TestJob#:" + (i + 1));
			testJobs.add(tempJob);
		}
		
		//run our method we are testing with those jobs included
		((Manager) testUser).managerJobList(testJobs);
		
		//testUser.viewSumAllJobs(testJobs);
		
		//check that we were returned the correct number of jobs
		assertEquals("Number of Jobs failed in viewSumAllJobs()", STARTING_NUM_TEST_JOBS + testUserJobsExpected, testJobs.size());
		testUserJobsActual = testUser.viewSumAllJobs(testJobs);
		//check that they are the correct jobs, this assumes they return in the same order
		for (int i = 1; i <= testUserJobsExpected; i++) {
			assertEquals("Actual Jobs failed in viewSumAllJobs()", i + STARTING_NUM_TEST_JOBS,
					((LinkedList<Job>) testUserJobsActual).pop().getJobID());
		}
	}
}
