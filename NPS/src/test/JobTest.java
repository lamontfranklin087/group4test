/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Job;
import model.MyOwnException;

/**
 * @author Student
 *
 */
public class JobTest {
	
	private Job testJob;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testJob = new Job();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link model.Job#setJobLocation(java.lang.String)}.
	 * @throws MyOwnException 
	 */
	@Test
	public void testSetJobLocation() throws MyOwnException {
		String actualLocation, expectedLocation = "testLocation";
		testJob.setJobLocation(expectedLocation);
		actualLocation = testJob.getJobLocation();
		assertEquals("setLocation failed.", expectedLocation, actualLocation);
	}

	/**
	 * Test method for {@link model.Job#setDate(java.util.Calendar)}.
	 * @throws MyOwnException 
	 */
	@Test
	public void testSetDatePresent() throws MyOwnException {
		Calendar actualDate;
		Calendar today = new GregorianCalendar();
		
		today.roll(Calendar.DATE, true);
		
		testJob.setDate(today);
		actualDate = testJob.getDate();
		assertEquals("setLocation failed.", today, actualDate);
	}

	/**
	 * Test method for {@link model.Job#setDate(java.util.Calendar)}.
	 * @throws MyOwnException 
	 */
	@Test(expected = MyOwnException.class)
	public void testSetDatePast() throws MyOwnException{
		//make a calendar set to today
		Calendar yesterday = new GregorianCalendar();
		
		//roll that back one day to yesterday
		yesterday.roll(Calendar.DATE, false);
		
		//this line should then throw the expected exception
		testJob.setDate(yesterday);
	}
	
	/**
	 * Test method for {@link model.Job#setDate(java.util.Calendar)}.
	 * @throws MyOwnException 
	 */
	@Test(expected = MyOwnException.class)
	public void testSetDateFuture() throws MyOwnException{
		//make a calendar set to today
		Calendar threeMonthsLater = new GregorianCalendar();
		
		//roll that forward MAX_FUTURE_DAYS_FOR_NEW_JOBS + 1 days
		threeMonthsLater.roll(Calendar.DAY_OF_YEAR, 91);
		
		//this line should then throw the expected exception
		testJob.setDate(threeMonthsLater);
	}

	/**
	 * Test method for {@link model.Job#setJobDuration(int)}.
	 * @throws MyOwnException 
	 */
	@Test
	public void testSetJobDurationGreaterThanZero() throws MyOwnException {
		testJob.setJobDuration(1);
		assertEquals("SetJobDuration() failed.", 1, testJob.getJobDuration());		
	}
	
	/**
	 * Test method for {@link model.Job#setJobDuration(int)}.
	 * @throws MyOwnException 
	 */
	@Test (expected = MyOwnException.class)
	public void testSetJobDurationLessOrEqualsToZero() throws MyOwnException {
		testJob.setJobDuration(0);
		
	}

	/**
	 * Test method for {@link model.Job#setJobManager(java.lang.String)}.
	 * @throws MyOwnException 
	 */
	@Test (expected = MyOwnException.class)
	public void testSetJobManagerNull() throws MyOwnException {
		testJob.setJobManager(null);		
	}
	
	/**
	 * Test method for {@link model.Job#setJobManager(java.lang.String)}.
	 * @throws MyOwnException 
	 */
	@Test (expected = MyOwnException.class)
	public void testSetJobManagerName() throws MyOwnException {
		testJob.setJobManager("Test Manager");	
		assertEquals("setJobManager() failed.", "Test Manager", testJob.getJobManager());
	}

	/**
	 * Test method for {@link model.Job#setJobSlot(int, int, int)}.
	 * @throws MyOwnException 
	 */
	@Test (expected = MyOwnException.class)
	public void testSetJobSlotWithZeroSlots() throws MyOwnException {
		testJob.setJobSlot(0, 0, 0);
	}
	
	/**
	 * Test method for {@link model.Job#setJobSlot(int, int, int)}.
	 * @throws MyOwnException 
	 */
	@Test
	public void testSetJobSlotNotZeroLightSlots() throws MyOwnException {
		testJob.setJobSlot(4, 0, 0);
		assertEquals("setJobSlot() failed.", 5, testJob.getLightSlotsAvailable());
	}
	
	/**
	 * Test method for {@link model.Job#setJobSlot(int, int, int)}.
	 * @throws MyOwnException 
	 */
	@Test
	public void testSetJobSlotNotZeroMediumSlots() throws MyOwnException {
		testJob.setJobSlot(0, 3, 0);
		assertEquals("setJobSlot() failed.", 3, testJob.getMediumSlotsAvailable());
	}

	/**
	 * Test method for {@link model.Job#setJobSlot(int, int, int)}.
	 * @throws MyOwnException 
	 */
	@Test
	public void testSetJobSlotNotZeroHeavySlots() throws MyOwnException {
		testJob.setJobSlot(0, 0, 7);
		assertEquals("setJobSlot() failed.", 7, testJob.getHeavySlotsAvailable());
	}
	
	/**
	 * Test method for {@link model.Job#setJobDescription(java.lang.String)}.
	 * @throws MyOwnException 
	 */
	@Test
	public void testSetJobDescriptionNotNull() throws MyOwnException {
		testJob.setJobDescription("Testing");
		assertEquals("setJobDescription() failed.", "Testing", testJob.getDescription());
	}
	
	/**
	 * Test method for {@link model.Job#setJobDescription(java.lang.String)}.
	 * @throws MyOwnException 
	 */
	@Test (expected = MyOwnException.class)
	public void testSetJobDescriptionNull() throws MyOwnException {
		testJob.setJobDescription(null);
	}

	/**
	 * Test method for {@link model.Job#setStartTime(java.lang.String)}.
	 */
	@Test
	public void testSetStartTime() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Job#addVolunteer(model.Volunteer, int)}.
	 */
	@Test
	public void testAddVolunteer() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link model.Job#addToSlot(model.Volunteer, int, int)}.
	 */
	@Test
	public void testAddToSlot() {
		fail("Not yet implemented");
	}

}
