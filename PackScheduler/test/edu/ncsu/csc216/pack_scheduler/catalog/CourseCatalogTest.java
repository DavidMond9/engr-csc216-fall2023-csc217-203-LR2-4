package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;


/**
 * Tests CourseCatalog
 * @author Ben Wojtkowiak
 */
public class CourseCatalogTest {
	
	
	/** Valid course records */
	private final String validTestFile = "test-files/actual_course_records.txt";
	
	/**
	 * Resets test file for use in other tests.
	 * @throws IOException e if cannot reset file
	 */
	@Before
	public void setUp() throws Exception {		
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_course_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "student_record.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests to see if Student directory is initialized with empty list
	 */
	@Test
	public void testCourseCatalog() {
		CourseCatalog catalog1 = new CourseCatalog();
		assertEquals(0, catalog1.getCourseCatalog().length);
	}

	/**
	 * checks if new course catalog is empty
	 * @throws InvalidTransitionException if invalid
	 * @throws IllegalArgumentException if invalid
	 */
	@Test
	public void testNewCourseCatalog() throws IllegalArgumentException, InvalidTransitionException {
	  CourseCatalog catalog = new CourseCatalog();
	  catalog.loadCoursesFromFile("test-files/student_record.txt");
	  catalog.newCourseCatalog();
	  assertEquals(0, catalog.getCourseCatalog().length);
	}
	
	/**
	 * Tests CourseCatalog.addCourseToCatalog(). tests valid course, and adding
	 * same course twice.
	 * @throws InvalidTransitionException if invalid
	 */
	@Test
	public void testAddCourseToCatalog() {
		CourseCatalog catalog1 = new CourseCatalog();
		
		catalog1.addCourseToCatalog("CSC116", "Intro to Programming - Java", "003", 3, "tbdimitr", "TH", 1120, 1310);
		String [][] courseCatalog = catalog1.getCourseCatalog();
		assertEquals(1, courseCatalog.length);
		assertEquals("CSC116", courseCatalog[0][0]);
		assertEquals("003", courseCatalog[0][1]);
		assertEquals("Intro to Programming - Java", courseCatalog[0][2]);
		//assertFalse(catalog1.addCourseToCatalog("CSC 116", "Intro to Programming - Java", "003", 3, "spbalik", "MW", 1250, 1440));
	}
	
	/**
	 * tests getting a course from catalog
	 * @throws InvalidTransitionException if invalid
	 * @throws IllegalArgumentException  if invalid
	 */
	@Test
	public void testGetCourseFromCatalog() throws IllegalArgumentException, InvalidTransitionException {
	  CourseCatalog catalog = new CourseCatalog();
	  catalog.loadCoursesFromFile("test-files/student_record.txt");
	  Course c1 = catalog.getCourseFromCatalog("CSC216", "001");
	  assertNotNull(c1);
	  assertEquals("CSC216", c1.getName());
	  assertEquals("001", c1.getSection());
	  Course c2 = catalog.getCourseFromCatalog("CSC999", "999");
	  assertNull(c2);
	}
	
	
	/**
	 * tests loading the courses from chosen file
	 * @throws IllegalArgumentException if invalid
	 * @throws InvalidTransitionException if invalid
	 */
	@Test
	public void testLoadCoursesFromFile() throws IllegalArgumentException, InvalidTransitionException {
		CourseCatalog catalog1 = new CourseCatalog();
	
		catalog1.loadCoursesFromFile(validTestFile);
		assertEquals(3, catalog1.getCourseCatalog().length);
		String [][] studentDirectory = catalog1.getCourseCatalog();
		assertEquals("CSC116", studentDirectory[0][0]);
		assertEquals("CSC216", studentDirectory[1][0]);
		assertEquals("CSC216", studentDirectory[2][0]);
		
		try {
			catalog1.loadCoursesFromFile("invalid");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to read file invalid", e.getMessage());
		}
	}
	
	
	
	
	/**
	 * tests removing a course from the catalog
	 * @throws InvalidTransitionException if invalid
	 * @throws IllegalArgumentException if invalid 
	 */
	@Test
	public void testRemoveCourseFromCatalog() throws IllegalArgumentException, InvalidTransitionException {
		CourseCatalog catalog1 = new CourseCatalog();
		catalog1.loadCoursesFromFile(validTestFile);
		catalog1.removeCourseFromCatalog("CSC116", "003");
		String [][] courseCatalog = catalog1.getCourseCatalog();
		assertEquals(2, courseCatalog.length);
		assertEquals("CSC216", courseCatalog[0][0]);
		assertEquals("001", courseCatalog[0][1]);
		
		assertFalse(catalog1.removeCourseFromCatalog("CSC116", "003"));
	}
	
	/**
	 * tests saving the course to file
	 * @throws InvalidTransitionException if invalid
	 * @throws IllegalArgumentException  if invalid
	 */
	@Test
	public void testSaveCourseCatalog() throws IllegalArgumentException, InvalidTransitionException {
		CourseCatalog catalog = new CourseCatalog();
		
		catalog.loadCoursesFromFile(validTestFile);
		assertEquals(3, catalog.getCourseCatalog().length);
		catalog.saveCourseCatalog("test-files/actual_course_records.txt");
		checkFiles("test-files/expected_course_records.txt", "test-files/actual_course_records.txt");
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected file
	 * @param actFile actual file
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner scanner1 = new Scanner(new FileInputStream(expFile));
			Scanner scanner2 = new Scanner(new FileInputStream(actFile));
			while (scanner1.hasNextLine()) {
				assertEquals(scanner1.nextLine(), scanner2.nextLine());
			}
			
			scanner1.close();
			scanner2.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}