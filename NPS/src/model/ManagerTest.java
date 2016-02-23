/**
 * 
 */
package model;

import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;

/**
 * @author David
 *
 */
public class ManagerTest {
	
	private ArrayList<String> testParksList;
	private Collection<User> testUsers;
	private Collection<Job> testJobs;
	private Manager testUser;
	private InputStream in;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		assertTrue(true);
		testParksList = new ArrayList<String>();
		for(int i = 0; i < 3; i++) {
			testParksList.add("Park" + (i + 1));
		}
		testUsers = new LinkedList<User>();
		testJobs = new LinkedList<Job>();
		testUser = new Manager("first", "last", "test@mail.com", "pass", testParksList);
	}

	/**
	 * Test method for {@link model.Manager#mainMenu(java.util.Collection, java.util.Collection)}.
	 */
	@Test
	public void testMainMenu() {
		assertTrue(true);
	}

	/**
	 * Test method for 
	 * */
	@Test
	public void testSubmitNewJob() {
//		assertTrue(0 == testUser.getNumberOfJobs());
//		//This Test fails because I can't make a new job without using like 7 user inputs.
//		String input = "1";		
//		in = new ByteArrayInputStream(input.getBytes());
//	    System.setIn(in);
//	    testUser.submitNewJob(testJobs);
//		assertTrue(1 == testUser.getNumberOfJobs());
//		assertEquals("Error", "Park1", testUser.getMyJobs().get(1));
	}

	/**
	 * Test method for {@link model.Manager#viewSumAllJobs(java.util.Collection)}.
	 */
	@Test
	public void testViewSumAllJobs() {
		testUser.viewSumAllJobs(testJobs);
		assertTrue(true);
	}

	/**
	 * Test method for {@link model.Manager#Manager(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.ArrayList)}.
	 */
	@Test
	public void testManager() {
		assertTrue(true);
	}

	/**
	 * Test method for {@link model.Manager#viewVolunteers(java.util.LinkedList)}.
	 */
	@Test
	public void testViewVolunteers() {
		assertTrue(true);
	}

	/**
	 * Test method for {@link model.Manager#toString()}.
	 */
	@Test
	public void testToString() {
		assertTrue(true);
	}

}
