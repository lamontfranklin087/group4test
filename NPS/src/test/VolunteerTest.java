package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.User;
import model.Volunteer;

public class VolunteerTest {
	private final int NUM_OF_TEST_JOBS = 10;
	
	private User testUser;

	@Before
	public void setUp() throws Exception {
		testUser = new Volunteer("JUnitTestFirst", "JUnitTestLast",
								 "JUnitTestEmail", "JUnitTestPassword");
		for (int i = 0; i < NUM_OF_TEST_JOBS; i++) {
			
		}
	}

	/**
	 * Tests that the correct Simple Name is returned.
	 */
	@Test
	public void testGetSimpleName() {
		assertEquals("getSimpleName() Test failed!", "Volunteer", testUser.getSimpleName());
	}

	@Test
	public void testVolunteerStringStringStringString() {
		assertEquals("testVolunteer() FirstName Test failed!", "JUnitTestFirst", testUser.getFirstName());
		assertEquals("testVolunteer() LastName Test failed!", "JUnitTestLast", testUser.getLastName());
		assertEquals("testVolunteer() Email Test failed!", "JUnitTestEmail", testUser.getEmail());
		assertEquals("testVolunteer() Password Test failed!", "JUnitTestPassword", testUser.getPassword());
	}

	@Test
	public void testJobSignUp() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testviewMyJobs() {
		fail("Not yet implemented");
	}
}
