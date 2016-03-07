/**
 * @author Ihar Lavor
 * @version March/06/2016
 */
package test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import model.Job;
import model.MyOwnException;
import model.Volunteer;

/**
 * @author Student
 *
 */
public class JobTest {
	
	private Job testJob;
		
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testJob = new Job();
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
	@Test
	public void testSetDateFuture() throws MyOwnException{
		//make a calendar set to today
		Calendar threeMonthsLater = new GregorianCalendar();
		
		//roll that forward MAX_FUTURE_DAYS_FOR_NEW_JOBS + 1 days
		threeMonthsLater.roll(Calendar.DAY_OF_YEAR, 91);
		
		Calendar compareDate = new GregorianCalendar();
		compareDate.roll(Calendar.DAY_OF_YEAR, 91);
		//this line should then throw the expected exception
		testJob.setDate(threeMonthsLater);
		assertEquals("setDate() failed.", compareDate, testJob.getDate());
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
	@Test
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
		assertEquals("setJobSlot() failed.", 4, testJob.getLightSlotsAvailable());
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
	 * @throws MyOwnException 
	 */
	@Test (expected = MyOwnException.class)
	public void testSetStartTimeNull() throws MyOwnException {
		testJob.setStartTime(null);
	}
	
	/**
	 * Test method for {@link model.Job#setStartTime(java.lang.String)}.
	 * @throws MyOwnException 
	 */
	@Test
	public void testSetStartTimeNotNull() throws MyOwnException {
		testJob.setStartTime("8:00AM");
		assertEquals("setStartTime() failed.", "8:00AM", testJob.getStartTime());
	}
	
	/**
	 * Test method for {@link model.Job#addToSlot(model.Volunteer, int, int)}.
	 * @throws MyOwnException 
	 */
	@Test (expected = MyOwnException.class)
	public void testAddVolunteerNullWithSlotNotZero() throws MyOwnException {
		Volunteer newVolunteer = null;
		int lightSlot = 1;
		testJob.addVolunteer(newVolunteer, lightSlot);
	}
	
	/**
	 * Test method for {@link model.Job#addToSlot(model.Volunteer, int, int)}.
	 * @throws MyOwnException 
	 */
	@Test (expected = MyOwnException.class)
	public void testAddVolunteerNotNullWithSlotZero() throws MyOwnException {
		Volunteer newVolunteer = new Volunteer("testFirst", "testLast", "testEmail", "testPasword");
		int lightSlot = 0;
		testJob.addVolunteer(newVolunteer, lightSlot);
	}
	/**
	 * Test method for {@link model.Job#addToSlot(model.Volunteer, int, int)}.
	 * @throws MyOwnException 
	 */
	@Test
	public void testAddVolunteerNotNullWithSlotNotZero() throws MyOwnException {
		Volunteer newVolunteer = new Volunteer("testFirst", "testLast", "testEmail", "testPasword");
		int lightSlot = 1;
		testJob.setJobSlot(3, 2, 1);
		testJob.addVolunteer(newVolunteer, lightSlot);
		assertEquals("addVolunteer() failed.", testJob.getTotalVolunteers(), 1);
		assertEquals("addVolunteer() failed.", testJob.getVolunteers().get(0).getEmail(), "testEmail");
	}
}
