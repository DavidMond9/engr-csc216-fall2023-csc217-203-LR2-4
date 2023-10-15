package edu.ncsu.csc216.pack_scheduler.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the RegistrationManager class.
 * @author Audrey Fuelleman
 * @author David Mond
 * @author Duncan Munro
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
	 */
	@Test
	public void testGetCourseCatalog() {
		assertEquals(0, manager.getCourseCatalog().getCourseCatalog().length);
		manager.getCourseCatalog().addCourseToCatalog("CSC 116", "Intro to Programming - Java", "003", 3, "spbalik", "MW", 1250, 1440);
		assertEquals("CSC 116", manager.getCourseCatalog().getCourseFromCatalog("CSC 116", "003").getName());
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
	 */
	@Test
	public void testLogin() {		
		Properties prop = new Properties();

		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);

			String firstName = prop.getProperty("first");
			String lastName = prop.getProperty("last");
			String id = prop.getProperty("id");
			String email = prop.getProperty("email");
			String pw = prop.getProperty("pw");
			
			manager.getStudentDirectory().addStudent(firstName, lastName, id, email, pw, pw, 15);
			assertTrue(manager.login(id, pw));
			assertFalse(manager.login(id, pw + "different"));
			

		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot load properties file.");
		}

	}

	@Test
	public void testLogout() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCurrentUser() {
		fail("Not yet implemented");
	}

}