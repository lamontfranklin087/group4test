/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import model.Job;
import model.UrbanParksStaff;
import model.User;
import model.Volunteer;

/**
 * @author Student
 *
 */
public class UrbanParksStaffTest {

	private final int NUM_OF_TEST_JOBS = 10;
	
	private Collection<User> testUsers;
	
	private Collection<Job> testJobs;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Job tempJob;
		Volunteer testUser1 = new Volunteer("JUnitTestFirst1", "JUnitTestLast1",
								 "JUnitTestEmail1", "JUnitTestPassword1");
		Volunteer testUser2 = new Volunteer("JUnitTestFirst2", "JUnitTestLast2",
				 "JUnitTestEmail2", "JUnitTestPassword2");
		testJobs = new LinkedList<Job>();
		testUsers = new LinkedList<User>();
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
		int desiredJobID = 4;
		int desiredSlot = 1;
		((Volunteer)testUser1).jobSignUp(testJobs, desiredJobID, desiredSlot);
		testUsers.add(testUser1);
		((Volunteer)testUser1).jobSignUp(testJobs, desiredJobID + 1, desiredSlot);
		testUsers.add(testUser2);
	}

	/**
	 * Test method for {@link model.UrbanParksStaff#searchVolunteer(java.util.Collection, java.lang.String)}.
	 */
	@Test
	public void testSearchVolunteerExisting() {
		UrbanParksStaff testUser = new UrbanParksStaff("StaffFirst", "StaffLast",
				 "StaffEmail", "StaffPassword");
		User tempVol = ((UrbanParksStaff)testUser).searchVolunteer(testUsers, "JUnitTestLast1");
		assertEquals(tempVol.getLastName(), "JUnitTestLast1");
	}

	/**
	 * Test method for {@link model.UrbanParksStaff#searchVolunteer(java.util.Collection, java.lang.String)}.
	 */
	@Test
	public void testSearchVolunteerNotExisting() {
		UrbanParksStaff testUser = new UrbanParksStaff("StaffFirst", "StaffLast",
				 "StaffEmail", "StaffPassword");
		User tempVol = ((UrbanParksStaff)testUser).searchVolunteer(testUsers, "notExistLast1");
		assertEquals(tempVol, null);
	}
}
