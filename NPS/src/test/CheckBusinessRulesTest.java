/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import controller.CheckBusinessRules;
import model.Job;
import model.Manager;
import model.MyOwnException;
import model.User;
import model.Volunteer;

/**
 * @author Ihar Lavor
 * @version March/04/2016
 */
public class CheckBusinessRulesTest {
	/**
	 * Number of jobs to be generated.
	 */
	private final int NUM_OF_TEST_JOBS = 5;
	/**
	 * Store all generated users.
	 */
	private User testUser;
	/**
	 * Store all generated jobs.
	 */
	private Collection<Job> testJobs;
	private int jobDuration = 1;
	private int lightSlot = 3;
	private int mediumSlot = 4;
	private int heavySlot = 5;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ArrayList<String> parksList = new ArrayList<String>();
		parksList.add("Steel Lake Park");
		parksList.add("Auburn Environmental Park");
		parksList.add("Lake Land North");
		
		testUser = new Manager("JUnitTestFirst", "JUnitTestLast",
					"JUnitTestEmail", "JUnitTestPassword", parksList);
		
		testJobs = new LinkedList<Job>();
		
		for (int i = 0; i < NUM_OF_TEST_JOBS; i++) {
			
			int day = 5 + i;
			int month = 5;
			int year = 2016;
			
			Calendar jobDate = new GregorianCalendar();			
			jobDate.set(year, month, day);
			
			((Manager)testUser).submitNewJob("JUnitTestFirst JUnitTestLast",
					"Steel Lake Park", jobDate, 1, 3, 3, 3, "Testing", "8:00AM", testJobs);
		}
	}


	/**
	 * Test method for {@link controller.CheckBusinessRules#checkJobDuration(int)}.
	 * @throws MyOwnException 
	 */
	@Test (expected = MyOwnException.class)
	public void testCheckJobDurationZero() throws MyOwnException {
		Calendar jobDate = new GregorianCalendar();			
		jobDate.set(2016, 4, 2);
		jobDuration = 0;
		((Manager)testUser).submitNewJob("JUnitTestFirst JUnitTestLast", "Auburn Environmental Park", 
				jobDate, jobDuration, lightSlot, mediumSlot, heavySlot, "Testing", "8:00AM", testJobs);
			
	}
	
	/**
	 * Test method for {@link controller.CheckBusinessRules#checkJobDuration(int)}.
	 * @throws MyOwnException 
	 */
	@Test (expected = MyOwnException.class)
	public void testCheckJobDurationThree() throws MyOwnException {
		Calendar jobDate = new GregorianCalendar();			
		jobDate.set(2016, 4, 2);
		jobDuration = 3;
		((Manager)testUser).submitNewJob("JUnitTestFirst JUnitTestLast", "Auburn Environmental Park", 
				jobDate, jobDuration, lightSlot, mediumSlot, heavySlot, "Testing", "8:00AM", testJobs);
			
	}
	
	/**
	 * Test method for {@link controller.CheckBusinessRules#checkJobDuration(int)}.
	 */
	@Test
	public void testCheckJobDurationOne() {
		boolean testError = false;
		try {				
			Calendar jobDate = new GregorianCalendar();			
			jobDate.set(2016, 4, 2);
			jobDuration = 1;
			testError = ((Manager)testUser).submitNewJob("JUnitTestFirst JUnitTestLast", "Auburn Environmental Park", 
					jobDate, jobDuration, lightSlot, mediumSlot, heavySlot, "Testing", "8:00AM", testJobs);
		} catch (MyOwnException e) {
			testError = true;
		}
		assertTrue(testError);	
	}
	
	/**
	 * Test method for {@link controller.CheckBusinessRules#checkJobDuration(int)}.
	 */
	@Test
	public void testCheckJobDurationTwo() {
		boolean testError = false;
		try {				
			Calendar jobDate = new GregorianCalendar();			
			jobDate.set(2016, 4, 2);
			jobDuration = 2;
			testError = ((Manager)testUser).submitNewJob("JUnitTestFirst JUnitTestLast", "Auburn Environmental Park", 
					jobDate, jobDuration, lightSlot, mediumSlot, heavySlot, "Testing", "8:00AM", testJobs);
		} catch (MyOwnException e) {
			
		}
		assertTrue(testError);	
	}
	
	/**
	 * Test method for {@link controller.CheckBusinessRules#jobsIn7Days(java.util.Collection, java.util.Calendar)}.
	 * @throws MyOwnException 
	 */
	@Test (expected = MyOwnException.class)
	public void testJobsIn7Days() throws MyOwnException {
		CheckBusinessRules check = new CheckBusinessRules();
		int day = 7;
		int month = 5;
		int year = 2016;
		
		Calendar jobDate = new GregorianCalendar();			
		jobDate.set(year, month, day);
		check.jobsIn7Days(testJobs, jobDate);			
	}

	/**
	 * Test method for {@link controller.CheckBusinessRules#checkForPastDate(java.util.Calendar)}.
	 * @throws MyOwnException 
	 */
	@Test (expected = MyOwnException.class)
	public void testCheckForPastDate() throws MyOwnException {
		CheckBusinessRules check = new CheckBusinessRules();
		Calendar jobDate = new GregorianCalendar();
		jobDate.add(Calendar.DAY_OF_YEAR, -1);
		check.checkForPastDate(jobDate);		
	}

	/**
	 * Test method for {@link controller.CheckBusinessRules#checkForFutureDate(java.util.Calendar)}.
	 * @throws MyOwnException 
	 */
	@Test (expected = MyOwnException.class)
	public void testCheckForFutureDate() throws MyOwnException {
		CheckBusinessRules check = new CheckBusinessRules();
		Calendar jobDate = new GregorianCalendar();
		jobDate.add(Calendar.DAY_OF_YEAR, 91);
		check.checkForFutureDate(jobDate);		
	}
	/**
	 * Test method for {@link controller.CheckBusinessRules#checkForFutureDate(java.util.Calendar)}.
	 * @throws MyOwnException 
	 */
	@Test (expected = MyOwnException.class)
	public void testCheckForTodayDate() throws MyOwnException {
		CheckBusinessRules check = new CheckBusinessRules();
		Calendar jobDate = new GregorianCalendar();
		jobDate.add(Calendar.DAY_OF_YEAR, 0);
		check.checkForPastDate(jobDate);		
	}
	
	/**
	 * Test method for {@link controller.CheckBusinessRules#isThereVolunteer(model.Manager, int, java.util.Collection)}.
	 * @throws MyOwnException 
	 */
	@Test (expected = MyOwnException.class)
	public void testIsThereVolunteerYes() throws MyOwnException {		
		CheckBusinessRules check = new CheckBusinessRules();
		Volunteer tempVolunteer = new Volunteer("testVolFirst", "testVolLast", "testVolEmail", "testVolPassword");
		tempVolunteer.jobSignUp(testJobs, 2, 1);
		check.isThereVolunteer(new Manager(), 2, testJobs);		
	}
	
	/**
	 * Test method for {@link controller.CheckBusinessRules#isThereVolunteer(model.Manager, int, java.util.Collection)}.
	 * @throws MyOwnException 
	 */
	@Test
	public void testIsThereVolunteerNo() throws MyOwnException {		
		CheckBusinessRules check = new CheckBusinessRules();
		Volunteer tempVolunteer = new Volunteer("testVolFirst", "testVolLast", "testVolEmail", "testVolPassword");
		tempVolunteer.jobSignUp(testJobs, 1, 1);
		boolean result = check.isThereVolunteer(new Manager(), 2, testJobs);
		assertTrue(result);
	}
}
