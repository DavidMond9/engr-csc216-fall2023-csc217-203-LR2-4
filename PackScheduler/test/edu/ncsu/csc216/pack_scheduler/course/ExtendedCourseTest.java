package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Tests the extended functionality of Course from 
 * Guided Project 2.
 * @author Sarah Heckman
 */
public class ExtendedCourseTest {
	
	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Software Development Fundamentals";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 3;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course meeting days */
	private static final String MEETING_DAYS = "MW";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	/** enrollment for course */
	private static final int CAP = 100;

	/**
	 * Tests Course.getShortDisplayArray().
	 * @throws InvalidTransitionException if invalid foramt
	 */
	@Test
	public void testGetShortDisplayArray() throws InvalidTransitionException {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(NAME, c.getName());
		assertEquals(TITLE, c.getTitle());
		assertEquals(SECTION, c.getSection());
		assertEquals(CREDITS, c.getCredits());
		assertEquals(INSTRUCTOR_ID, c.getInstructorId());
		assertEquals(CAP, c.getCourseRoll().getEnrollmentCap());
		assertEquals(MEETING_DAYS, c.getMeetingDays());
		assertEquals(START_TIME, c.getStartTime());
		assertEquals(END_TIME, c.getEndTime());
		
		String [] actualShortDisplay = c.getShortDisplayArray();
		assertEquals(5, actualShortDisplay.length);
		assertEquals(NAME, actualShortDisplay[0]);
		assertEquals(SECTION, actualShortDisplay[1]);
		assertEquals(TITLE, actualShortDisplay[2]);
		assertEquals("MW 1:30PM-2:45PM", actualShortDisplay[3]);
		assertEquals(String.valueOf(c.getCourseRoll().getOpenSeats()), actualShortDisplay[4]);
	}

	/**
	 * Tests Course.getLongDisplayArray().
	 * @throws InvalidTransitionException if invalid format
	 */
	@Test
	public void testGetLongDisplayArray() throws InvalidTransitionException {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, CAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(NAME, c.getName());
		assertEquals(TITLE, c.getTitle());
		assertEquals(SECTION, c.getSection());
		assertEquals(CREDITS, c.getCredits());
		assertEquals(INSTRUCTOR_ID, c.getInstructorId());
		assertEquals(MEETING_DAYS, c.getMeetingDays());
		assertEquals(START_TIME, c.getStartTime());
		assertEquals(END_TIME, c.getEndTime());
		assertEquals(CAP, c.getCourseRoll().getEnrollmentCap());
		
		String [] actualLongDisplay = c.getLongDisplayArray();
		assertEquals(NAME, actualLongDisplay[0]);
		assertEquals(SECTION, actualLongDisplay[1]);
		assertEquals(TITLE, actualLongDisplay[2]);
		assertEquals("" + CREDITS, actualLongDisplay[3]);
		assertEquals(INSTRUCTOR_ID, actualLongDisplay[4]);
		assertEquals("MW 1:30PM-2:45PM", actualLongDisplay[5]);
		assertEquals("", actualLongDisplay[6]);
	}

}

