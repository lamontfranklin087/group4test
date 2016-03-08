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
import model.UrbanParksStaff;
import model.User;
import model.Volunteer;

/**
 * @author Ihar Lavor
 * @version March/04/2016
 */
public class UrbanParksStaffTest {
	/**
	 * Number of jobs to be generated.
	 */
	private final static int NUM_OF_TEST_JOBS = 10;
	/**
	 * Store all generated users.
	 */
	private static Collection<User> testUsers;
	/**
	 * Store all generated jobs.
	 */
	private static Collection<Job> testJobs;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUp() throws Exception {
		Volunteer testUser1 = new Volunteer("JUnitTestFirst1", "JUnitTestLast1",
								 "JUnitTestEmail1", "JUnitTestPassword1");
		Volunteer testUser2 = new Volunteer("JUnitTestFirst2", "JUnitTestLast2",
				 "JUnitTestEmail2", "JUnitTestPassword2");
		testJobs = new LinkedList<Job>();
		testUsers = new LinkedList<User>();
		
		ArrayList<String> testParksManage1 = new ArrayList<String>();
		for (int i = 1; i <= 3; i++) {
			testParksManage1.add("Park#:" + i);
		}
		Manager testManager = new Manager("JUnitTest1_First", "JUnitTest1_Last",
					"JUnitTest1_Email", "JUnitTest1_Password", testParksManage1);		
		
		testJobs = new LinkedList<Job>();
		
		for (int i = 0; i < NUM_OF_TEST_JOBS; i++) {			
			int day = 5 + i;
			int month = 5;
			int year = 2016;
			
			Calendar jobDate = new GregorianCalendar();			
			jobDate.set(year, month, day);
			((Manager)testManager).submitNewJob("JUnitTest1_First JUnitTest1_Last",
					"Steel Lake Park", jobDate, 1, 3, 3, 3, "Testing", "8:00AM", testJobs);
		}
		
		int desiredJobID = 4;
		int desiredSlot = 1;
		((Volunteer)testUser1).jobSignUp(testJobs, desiredJobID, desiredSlot);
		testUsers.add(testUser1);
		((Volunteer)testUser2).jobSignUp(testJobs, desiredJobID + 1, desiredSlot);
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
