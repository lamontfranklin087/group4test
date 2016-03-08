package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;

import model.Job;
import model.Manager;
import model.User;
/**
 * @author Ihar Lavor
 * @version March/04/2016
 */
public class ManagerTest {
	/**
	 * Number of jobs to be generated.
	 */
	private final int STARTING_NUM_TEST_JOBS = 5;
	/**
	 * Store generated users.
	 */
	private User testUser1;
	/**
	 * Store generated users.
	 */
	private User testUser2;
	/**
	 * List of parks for manager 1.
	 */
	private ArrayList<String> testParksManage1;
	/**
	 * List of parks for manager 2.
	 */
	private ArrayList<String> testParksManage2;
	/**
	 * Store all generated jobs.
	 */
	private Collection<Job> testJobs;

	/**
	 * Set two different mangers with 5 jobs each.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {					
		testParksManage1 = new ArrayList<String>();
		for (int i = 1; i <= 3; i++) {
			testParksManage1.add("Park#:" + i);
		}
		testUser1 = new Manager("JUnitTest1_First", "JUnitTest1_Last",
					"JUnitTest1_Email", "JUnitTest1_Password", testParksManage1);
		
		testParksManage2 = new ArrayList<String>();
		for (int i = 4; i <= 7; i++) {
			testParksManage1.add("Park#:" + i);
		}
		testUser2 = new Manager("JUnitTest2_First", "JUnitTest2_Last",
				"JUnitTest2_Email", "JUnitTest2_Password", testParksManage2);
		
		testJobs = new LinkedList<Job>();
		
		for (int i = 0; i < STARTING_NUM_TEST_JOBS; i++) {			
			int day = 5 + i;
			int month = 5;
			int year = 2016;
			
			Calendar jobDate = new GregorianCalendar();			
			jobDate.set(year, month, day);
			((Manager)testUser1).submitNewJob("JUnitTest1_First JUnitTest1_Last",
					"Steel Lake Park", jobDate, 1, 3, 3, 3, "Testing", "8:00AM", testJobs);
			
			day = 15 + i;
			month = 5;
			year = 2016;
			jobDate.set(year, month, day);						
			((Manager)testUser2).submitNewJob("JUnitTest2_First JUnitTest2_Last",
					"Steel Park", jobDate, 1, 3, 3, 3, "Testing", "8:00AM", testJobs);
		}
	}
	/**
	 * Test method for {@link model.Manager#getSimpleName()}.
	 */
	@Test
	public void testGetSimpleName() {
		assertEquals("getSimpleName() Test failed!", "Park Manager", testUser1.getSimpleName());
	}
	/**
	 * Test method for {@link model.Manager#viewSumAllJobs(java.util.Collection)}.
	 */
	@Test
	public void testViewSumAllJobsForManagerWithJobs() {		
		//run our method we are testing with those jobs included
		LinkedList<Job> testUserJobsActual = (LinkedList<Job>) testUser2.viewSumAllJobs(testJobs);
		int testUserJobsExpected = STARTING_NUM_TEST_JOBS;
		
		//check that we were returned the correct number of jobs
		assertEquals("Number of Jobs failed in viewSumAllJobs()", testUserJobsExpected, testUserJobsActual.size());
		
		//check if manager name is correct for specific manager
		for (int i = 0; i < testUserJobsActual.size(); i++) {
			assertEquals("Actual Jobs failed in viewSumAllJobs()", testUserJobsActual.get(i).getJobManager(),
					"JUnitTest2_First JUnitTest2_Last");
		}
	}
	/**
	 * Test method for {@link model.Manager#viewSumAllJobs(java.util.Collection)}.
	 */
	@Test
	public void testViewSumAllJobsForManagerWithNoJobs() {	
		ArrayList<String> testParksManage3 = new ArrayList<String>();
		for (int i = 15; i <= 18; i++) {
			testParksManage3 .add("Park#:" + i);
		}
		User testUser3 = new Manager("JUnitTest3_First", "JUnitTest3_Last",
				"JUnitTest3_Email", "JUnitTest3_Password", testParksManage3);
		
		//run our method we are testing with those jobs included
		LinkedList<Job> testUserJobsActual = (LinkedList<Job>) testUser3.viewSumAllJobs(testJobs);
		Object testUserJobsExpected = null;
		
		//check that we were returned the correct number of jobs
		assertEquals("Number of Jobs failed in viewSumAllJobs()", testUserJobsExpected, testUserJobsActual);		
	}
	/**
	 * Test method for {@link model.Manager#deleteJob(int, java.util.Collection)}.
	 */
	@Test
	public void testDeleteJobFromNotEmptyList() {
		
		//check that initial numbers of jobs are correct
		assertEquals("Number of INITIAL Jobs failed in deleteJob()", STARTING_NUM_TEST_JOBS, 
				(testUser1.viewSumAllJobs(testJobs)).size());
		
		//remove jobs
		int numToRemove = 2;

		for(int i = 1; i <= numToRemove; i++) {
			((Manager) testUser1).deleteJob(i, testJobs);
		}
		LinkedList<Job> tempList = (LinkedList<Job>) testUser1.viewSumAllJobs(testJobs);
		//check that that number of jobs was removed
		
		//check if manager name is correct for specific manager
		for (int i = 0; i < tempList.size(); i++) {
			assertEquals("Actual Jobs failed in viewSumAllJobs()", tempList.get(i).getJobManager(),
							"JUnitTest1_First JUnitTest1_Last");
		}
	}
	/**
	 * Test method for {@link model.Manager#deleteJob(int, java.util.Collection)}.
	 */
	@Test
	public void testDeleteJobFromEmptyList() {
		Collection<Job> tempJob = new LinkedList<Job>();
		assertFalse(((Manager) testUser2).deleteJob(1, tempJob));
		
	}
	/**
	 * Test method for {@link model.Manager#managerJobList(java.util.Collection)}.
	 */
	@Test
	public void testManagerJobList() {
		//number of jobs to make for this manager
		int testUserJobsExpected = STARTING_NUM_TEST_JOBS;
								
		//run our method we are testing with those jobs included
		((Manager) testUser2).managerJobList(testJobs);
				
		LinkedList<Job> testUserJobsActual = (LinkedList<Job>) testUser2.viewSumAllJobs(testJobs);
		//check that we were returned the correct number of jobs
		assertEquals("Number of Jobs failed in viewSumAllJobs()", testUserJobsExpected, testUserJobsActual.size());
				
		//check if manager name is correct for specific manager
		for (int i = 0; i < testUserJobsActual.size(); i++) {
			assertEquals("Actual Jobs failed in viewSumAllJobs()", testUserJobsActual.get(i).getJobManager(),
							"JUnitTest2_First JUnitTest2_Last");
		}
	}
}
