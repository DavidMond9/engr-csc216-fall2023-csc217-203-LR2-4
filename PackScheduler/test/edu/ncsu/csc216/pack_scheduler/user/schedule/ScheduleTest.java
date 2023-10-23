/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
		Course c1 = new Course("CSC116", "title1", "section", 3, "instructorid", "MW");
		Course c2 = new Course("CSC216", "title2", "section", 3, "instructorid", "TH");
		// Add course to empty schedule
		assertTrue(s.addCourseToSchedule(c1));
		// Add another course unique course
		assertTrue(s.addCourseToSchedule(c2));
		// Add a duplicate course
		assertThrows(IllegalArgumentException.class,
				() -> s.addCourseToSchedule(c1));
	}
	
	
}
