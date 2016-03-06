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
import org.junit.BeforeClass;
import org.junit.Test;
import model.Job;
import model.Manager;
import model.Volunteer;

/**
 * @author Student
 *
 */
public class AbstractUserTest {
	
	private static int STARTING_NUM_TEST_JOBS = 10;	
	
	private static Collection<Job> testJobs;

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
	 * Test method for {@link model.AbstractUser#findJob(int, java.util.Collection)}.
	 */
	@Test
	public void testFindJobJobThere() {
		Job testJob;
		Volunteer testUser = new Volunteer();

		for(int i = STARTING_NUM_TEST_JOBS; i > 0; i--) {
			//System.out.println(i);
			testJob = testUser.findJob(i, testJobs);
			//System.out.println(i + " " + testJob.getJobID());
			assertEquals("Job found in findJobs()", i,
					testJob.getJobID());
		}		
	}
	
	/**
	 * Test method for {@link model.AbstractUser#findJob(int, java.util.Collection)}.
	 */
	@Test
	public void testFindJobJobIsNotThere() {
		Job testJob;
		Volunteer testUser = new Volunteer();
		for(int i = 80; i > STARTING_NUM_TEST_JOBS; i--) {			
			testJob = testUser.findJob(i, testJobs);
			assertTrue(testJob == null);
		}		
	}

}