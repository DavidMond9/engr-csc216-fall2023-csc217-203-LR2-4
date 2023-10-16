/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * this class tests the invalid transition exception class
 * @author Duncan
 */
public class InvalidTransitionExceptionTest {

	/**
	 * Test method for the parameterized constructor
	 * making sure that the constructor holds the saved default error message
	 */
	@Test
	public void testInvalidTransitionExceptionString() {
	    InvalidTransitionException ce = new InvalidTransitionException("Custom exception message");
	    assertEquals("Custom exception message", ce.getMessage());
	}

	/**
	 * Testing to make sure the parameterless constructor is inheriting the conflict message from the parameterized constructor
	 */
	@Test
	void testInvalidTransitionException() {
		InvalidTransitionException noParameter = new InvalidTransitionException();
		assertEquals("Invalid FSM Transition.", noParameter.getMessage());
	}
}
