/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the Schedule class for construction, getters/setters,
 * and add/remove Course functionality.
 * 
 * @author Audrey Fuelleman
 */
public class ScheduleTest {
	
	/**
	 * Test the construction of a Schedule object.
	 */
	@Test
	public void testSchedule() {
		Schedule s = new Schedule();
		// Check default size and title
		assertEquals(0, s.getScheduledCourses().length);
		assertEquals("My Schedule", s.getTitle());
	}
	
	/**
	 * Test adding valid and invalids courses to the schedule.
	 */
	@Test
	public void testAddCourseToSchedule() {
		Schedule s = new Schedule();

		Course c1 = new Course("CSC116", "title1", "001", 3, "instructorid", 15,"MW", 1400, 1500);
		Course c2 = new Course("CSC216", "title2", "001", 3, "instructorid", 15, "A");
		// Add course to empty schedule
		assertTrue(s.addCourseToSchedule(c1));
		assertEquals(1, s.getScheduledCourses().length);
		// Add another course unique course
		assertTrue(s.addCourseToSchedule(c2));
		assertEquals(2, s.getScheduledCourses().length);
		// Add a duplicate course
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> s.addCourseToSchedule(c1));
		assertEquals("You are already enrolled in CSC116", e1.getMessage());
		assertEquals(2, s.getScheduledCourses().length);
		// Add a course with conflict

		Course c3 = new Course("CSC230", "title3", "001", 3, "instructorid", 15, "MW", 1400, 1500);
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.addCourseToSchedule(c3));
		assertEquals("The course cannot be added due to a conflict.", e2.getMessage());
		assertEquals(2, s.getScheduledCourses().length);
		
	}
	
	/**
	 * Test removing a course from the schedule.
	 */
	@Test
	public void testRemoveCourseFromSchedule() {
		Schedule s = new Schedule();

		Course c1 = new Course("CSC116", "title1", "001", 3, "instructorid", 15, "MW", 1400, 1500);
		Course c2 = new Course("CSC216", "title2", "001", 3, "instructorid", 15, "A");
		Course c3 = new Course("CSC230", "title3", "001", 3, "instructorid", 15, "MW", 1400, 1500);
		s.addCourseToSchedule(c1);
		s.addCourseToSchedule(c2);
		assertEquals(2, s.getScheduledCourses().length);
		// Successfully remove a course
		assertTrue(s.removeCourseFromSchedule(c1));
		assertEquals(1, s.getScheduledCourses().length);
		// Try removing a course that doesn't exist
		assertFalse(s.removeCourseFromSchedule(c3));
		assertEquals(1, s.getScheduledCourses().length);
	}
	
	/**
	 * Test resetting the schedule.
	 */
	@Test
	public void testResetSchedule() {
		Schedule s = new Schedule();

		Course c1 = new Course("CSC116", "title1", "001", 3, "instructorid", 15, "MW", 1400, 1500);
		Course c2 = new Course("CSC216", "title2", "001", 3, "instructorid", 15, "A");
		s.addCourseToSchedule(c1);
		s.addCourseToSchedule(c2);
		s.setTitle("New Title");
		assertEquals(2, s.getScheduledCourses().length);
		assertEquals("New Title", s.getTitle());
		// Clear the schedule
		s.resetSchedule();
		assertEquals("My Schedule", s.getTitle());
		assertEquals(0, s.getScheduledCourses().length);
		
	}
	
	/**
	 * Test getting the scheduled courses as a 2D array.
	 */
	@Test
	public void testGetScheduledCourses() {
		Schedule s = new Schedule();

		Course c1 = new Course("CSC116", "title1", "001", 3, "instructorid", 15, "MW", 1400, 1500);
		Course c2 = new Course("CSC216", "title2", "001", 3, "instructorid", 15, "A");
		s.addCourseToSchedule(c1);
		s.addCourseToSchedule(c2);
		assertEquals(2, s.getScheduledCourses().length);
		// Get the scheduled courses as an array
		String[][] courses = s.getScheduledCourses();
		// Check number of courses
		assertEquals(2, courses.length);
		// Check number of fields
		assertEquals(5, courses[0].length);
		// Check each course
		assertEquals("CSC116", courses[0][0]);
		assertEquals("CSC216", courses[1][0]);
		assertEquals(c1.getMeetingString(), courses[0][3]);
		assertEquals(c2.getMeetingString(), courses[1][3]);
		
	}
	
	/**
	 * Test setting the title with invalid title.
	 */
	@Test
	public void testSetTitleInvalid() {
		Schedule s = new Schedule();
		assertThrows(IllegalArgumentException.class,
				() -> s.setTitle(null));

	}
	
	
	
	
}
