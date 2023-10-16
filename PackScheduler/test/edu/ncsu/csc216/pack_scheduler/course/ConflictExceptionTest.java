/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests for ConflictException
 * @author - Sam McDonald
 */
class ConflictExceptionTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.InvalidTransitionException.course.ConflictException#ConflictException()}.
	 */
	@Test
	public void testConflictException() {
	    InvalidTransitionException ce = new InvalidTransitionException();
	    assertEquals("Schedule conflict.", ce.getMessage());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.InvalidTransitionException.course.ConflictException#ConflictException(java.lang.String)}.
	 */
	@Test
	public void testConflictExceptionString() {
	    InvalidTransitionException ce = new InvalidTransitionException("Custom exception message");
	    assertEquals("Custom exception message", ce.getMessage());
	}

}
