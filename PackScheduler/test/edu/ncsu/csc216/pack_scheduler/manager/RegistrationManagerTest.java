package edu.ncsu.csc216.pack_scheduler.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Tests the RegistrationManager class.
 * @author Audrey Fuelleman
 */
public class RegistrationManagerTest {
	
	/** The instance of the registration manager */
	private RegistrationManager manager;
	
	/** The properties file storing student information */
	private static final String PROP_FILE = "registrar.properties";
	
	/**
	 * Sets up the RegistrationManager and clears the data.
	 * @throws Exception if error
	 */
	@BeforeEach
	public void setUp() throws Exception {
		manager = RegistrationManager.getInstance();
		manager.clearData();
	}

	/**
	 * Tests the getCourseCatalog method.
	 * @throws InvalidTransitionException if invalid
	 */
	@Test
	public void testGetCourseCatalog() throws InvalidTransitionException {
		assertEquals(0, manager.getCourseCatalog().getCourseCatalog().length);

		manager.getCourseCatalog().addCourseToCatalog("CSC116", "Intro to Programming - Java", "003", 3, "spbalik", 15, "MW", 1250, 1440);
		assertEquals("CSC116", manager.getCourseCatalog().getCourseFromCatalog("CSC116", "003").getName());
	}

	/**
	 * Tests the getStudentDirectory method.
	 */
	@Test
	public void testGetStudentDirectory() {
		assertEquals(0, manager.getStudentDirectory().getStudentDirectory().length);
		manager.getStudentDirectory().addStudent("John", "Doe", "jdoe", "jdoe@ncsu.edu", "password123", "password123", 15);
		assertEquals("John", manager.getStudentDirectory().getStudentById("jdoe").getFirstName());
	}

	/**
	 * Tests the login method with a valid and invalid password.
	 * @throws Exception if there is an error
	 */
	@Test
	public void testLogin() throws Exception {		
		Properties prop = new Properties();

		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);

			String id = prop.getProperty("id");
			String pw = prop.getProperty("pw");

			assertTrue(manager.login(id, pw));
			assertFalse(manager.login(id, pw + "different"));
			assertEquals(id, manager.getCurrentUser().getId());
			
			manager.logout();
			manager.getStudentDirectory().addStudent("Jill", "Smith", "jsmith", "jsmith@ncsu.edu", "pw", "pw", 0);
			
			assertTrue(manager.login("jsmith", "pw"));
			assertFalse(manager.login("jsmith", "notpw"));
			assertEquals(manager.getStudentDirectory().getStudentById("jsmith"), manager.getCurrentUser());

		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot load properties file.");
		}

	}

	/**
	 * Tests the logout method.
	 */
	@Test
	public void testLogout() {
			manager.logout();
			assertEquals(null, manager.getCurrentUser());
	}

	/**
	 * Tests the getCurrentUser method before current user is set.
	 */
	@Test
	public void testGetCurrentUser() {
		assertEquals(null, manager.getCurrentUser());
	}

}