package edu.ncsu.csc216.pack_scheduler.directory;


import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests StudentDirectory.
 * @author Sarah Heckman
 * @author Ben Wojtkowiak
 */
public class StudentDirectoryTest {
	
	/** Valid course records */
	private final String validTestFile = "test-files/student_records.txt";
	/** Test first name */
	private static final String FIRST_NAME = "Stu";
	/** Test last name */
	private static final String LAST_NAME = "Dent";
	/** Test id */
	private static final String ID = "sdent";
	/** Test email */
	private static final String EMAIL = "sdent@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max credits */
	private static final int MAX_CREDITS = 15;
	
	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {		
		//Reset student_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_student_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "student_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests StudentDirectory().
	 */
	@Test
	public void testStudentDirectory() {
		//Test that the StudentDirectory is initialized to an empty list
		StudentDirectory sd = new StudentDirectory();
		assertFalse(sd.removeStudent("sesmith5"));
		assertEquals(0, sd.getStudentDirectory().length);
	}
	

	/**
	 * Tests StudentDirectory.testNewStudentDirectory().
	 */
	@Test
	public void testNewStudentDirectory() {
		//Test that if there are students in the directory, they 
		//are removed after calling newStudentDirectory().
		StudentDirectory sd = new StudentDirectory();
		
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
		
		sd.newStudentDirectory();
		assertEquals(0, sd.getStudentDirectory().length);
	}

	/**
	 * Tests StudentDirectory.loadStudentsFromFile().
	 */
	@Test
	public void testLoadStudentsFromFile() {
		StudentDirectory sd = new StudentDirectory();
				
		//Test valid file
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
		
		//Testing an Invalid file, try file, catch i
		try {
			sd.loadStudentsFromFile("invalid.txt");
			fail("IllegalArgumentException expected");
		} catch (IllegalArgumentException e){
			assertEquals("Unable to read file invalid.txt", e.getMessage());
		}
	}

	/**
	 * Tests StudentDirectory.addStudent().
	 */
	@Test
	public void testAddStudent() {
		StudentDirectory sd = new StudentDirectory();
		
		// Test valid Student
		sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		String [][] studentDirectory = sd.getStudentDirectory();
		assertEquals(1, studentDirectory.length);
		assertEquals(FIRST_NAME, studentDirectory[0][0]);
		assertEquals(LAST_NAME, studentDirectory[0][1]);
		assertEquals(ID, studentDirectory[0][2]);
		
		sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, 2);
		
		assertEquals(1, studentDirectory.length);
		assertEquals(FIRST_NAME, studentDirectory[0][0]);
		assertEquals(LAST_NAME, studentDirectory[0][1]);
		assertEquals(ID, studentDirectory[0][2]);
		
		
		sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);

		// Tests for duplicates
		assertFalse(sd.addStudent(FIRST_NAME, LAST_NAME, "sdent", EMAIL, PASSWORD, PASSWORD, MAX_CREDITS));
		
		// if password is null
		try {
			sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, null, PASSWORD, MAX_CREDITS);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
		
		// if repeat password is not matching password
		try {
			sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "Invalid", MAX_CREDITS);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Passwords do not match", e.getMessage());
		}
		
		// if repeat password is null
		try {
			sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, null, MAX_CREDITS);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
		
		// if password is empty 
		try {
			sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, "", PASSWORD, MAX_CREDITS);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
		
		// if repeat password is empty 
		try {
			sd.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "", MAX_CREDITS);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Invalid password", e.getMessage());
		}
		
	}

	/**
	 * Tests StudentDirectory.removeStudent().
	 */
	@Test
	public void testRemoveStudent() {
		StudentDirectory sd = new StudentDirectory();
				
		//Add students and remove
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
		assertTrue(sd.removeStudent("efrost"));
		String [][] studentDirectory = sd.getStudentDirectory();
		assertEquals(9, studentDirectory.length);
		assertEquals("Shannon", studentDirectory[3][0]);
		assertEquals("Hansen", studentDirectory[3][1]);
		assertEquals("shansen", studentDirectory[3][2]);
	}
	
	/**
	 * Tests StudentDirectory.removeStudent().
	 */
	@Test
	public void testRemovingNonExistingStudent() {
		StudentDirectory sd = new StudentDirectory();
		
		//Add students and remove
		sd.loadStudentsFromFile(validTestFile);
		assertEquals(10, sd.getStudentDirectory().length);
		assertFalse(sd.removeStudent("Non-Exist"));
		
		//Now check if file length stayed the same length
		assertEquals(10, sd.getStudentDirectory().length);
	}

	/**
	 * Tests StudentDirectory.saveStudentDirectory().
	 */
	@Test
	public void testSaveStudentDirectory() {
		StudentDirectory sd = new StudentDirectory();
		
		//Add a student
		sd.addStudent("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 15);
		assertEquals(1, sd.getStudentDirectory().length);
		sd.saveStudentDirectory("test-files/actual_student_records.txt");
		checkFiles("test-files/expected_student_records.txt", "test-files/actual_student_records.txt");

		// Tests to see if unable to write to file
		try {
			sd.saveStudentDirectory("/home/sesmith5/actual_student_records.txt");
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Unable to write to file /home/sesmith5/actual_student_records.txt", e.getMessage());
		}
	}
	
	
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
	
	/**
	 * Tests StudentDirectory.getStudentById().
	 */
	@Test
	public void testGetStudentById() {
		StudentDirectory sd = new StudentDirectory();
		Student s = new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", 15);
		Student s2 = new Student("James", "Long", "jlong", "jlong@ncsu.edu", "pw", 15);
		sd.addStudent("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", "pw", "pw", 15);
		sd.addStudent("James", "Long", "jlong", "jlong@ncsu.edu", "pw", "pw", 15);
		assertEquals(sd.getStudentById("zking").getFirstName(), s.getFirstName());
		assertEquals(sd.getStudentById("zking").getLastName(), s.getLastName());
		assertEquals(sd.getStudentById("zking").getEmail(), s.getEmail());
		assertEquals(sd.getStudentById("jlong").getFirstName(), s2.getFirstName());
		assertEquals(sd.getStudentById("jlong").getLastName(), s2.getLastName());
		assertEquals(sd.getStudentById("jlong").getEmail(), s2.getEmail());
		
		//ID doesn't exist
		try {
			sd.getStudentById("");
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Student not in directory.", e.getMessage());
		}
	}

}