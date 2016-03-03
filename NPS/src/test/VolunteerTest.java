package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import model.User;
import model.Volunteer;

public class VolunteerTest {
	User testUser;

	@Before
	public void setUp() throws Exception {
		testUser = new Volunteer("TestFirst", "TestLast", "TestEmail", "TestPassword");
	}

	/**
	 * Tests that the correct Simple Name is returned.
	 */
	@Test
	public void testGetSimpleName() {
		assertEquals("getSimpleName() Test failed!", "Volunteer", testUser.getSimpleName());
	}

	@Test
	public void testVolunteer() {
		fail("Not yet implemented");
	}

	@Test
	public void testVolunteerStringStringStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewMyJobs() {
		fail("Not yet implemented");
	}

	@Test
	public void testMatchVolunteer() {
		fail("Not yet implemented");
	}

	@Test
	public void testJobSignUp() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewSumAllJobs() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewJobDetails() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindJob() {
		fail("Not yet implemented");
	}

}
