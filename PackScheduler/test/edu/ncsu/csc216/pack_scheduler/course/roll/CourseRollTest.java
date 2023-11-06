package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


/**
 * Test class for CourseRoll class..
 * @author David Mond
 */
class CourseRollTest {
	/**
	 * Test student 1.
	 */
	private Student testStudent1 = new Student("Henry", "Ruggs", "hruggs", "hruggs@ncsu.edu", "pw");
	/**
	 * Test student 2.
	 */
	private Student testStudent2 = new Student("Sarah", "Flary", "sflary", "sflary@ncsu.edu", "pw");
	
	@Test
	void testEnroll() {
		//VALID TESTS
		
		CourseRoll roll = new CourseRoll(16);
		roll.enroll(testStudent1);
		assertEquals(roll.getOpenSeats(), 15);
		roll.enroll(testStudent2);
		assertEquals(roll.getOpenSeats(), 14);
		
		//INVALID TESTS
		
		//student is null
		try {
			roll.enroll(null);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Cannot enroll student.");
		}
		
		//duplicate student
		try {
			roll.enroll(testStudent1);
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Cannot enroll student.");
		}
	}
	
	@Test
	void testDrop() {
		//VALID TESTS
		
		CourseRoll roll = new CourseRoll(16);
		roll.enroll(testStudent1);
		roll.enroll(testStudent2);
		roll.drop(testStudent1);
		assertEquals(roll.getOpenSeats(), 15);
		roll.drop(testStudent2);
		assertEquals(roll.getOpenSeats(), 16);
		//INVALID TESTS
		
		//student is null
		try {
			roll.drop(null);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Cannot drop student.");
		}
		
	}
}
