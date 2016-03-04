/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;
import model.Job;
import model.UrbanParksStaff;

/**
 * @author Student
 *
 */
public class AbstractUserTest {
	
	private final int STARTING_NUM_TEST_JOBS = 10;
	
	private Collection<Job> testJobs;
	
	private UrbanParksStaff testUser = new UrbanParksStaff();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		UrbanParksStaff testUser = new UrbanParksStaff("JUnitTestFirst", "JUnitTestLast",
				 									   "JUnitTestEmail", "JUnitTestPassword");
		
		
		testJobs = new LinkedList<Job>();
		for (int i = 1; i <= STARTING_NUM_TEST_JOBS; i++) {
			Job tempJob = new Job();
			//tempJob.setJobSlot(5, 5, 5);
			tempJob.setJobID(i);
			tempJob.setJobManager("Test Manager");
			tempJob.setJobDescription("TestJob#:" + i);
			testJobs.add(tempJob);
		}
	}

	/**
	 * Test method for {@link model.AbstractUser#findJob(int, java.util.Collection)}.
	 */
	@Test
	public void testFindJob() {
		Job testJob;

		for(int i = STARTING_NUM_TEST_JOBS; i > 0; i--) {
			System.out.println(i);
			testJob = testUser.findJob(i, testJobs);
			System.out.println(i + " " + testJob.getJobID());
			assertEquals("Job not found in findJobs()", i,
					testJob.getJobID());
		}
	}

}