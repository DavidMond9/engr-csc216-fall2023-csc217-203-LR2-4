package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests the CourseNameValidator for all possible states and transitions.
 * @author Audrey Fuelleman
 */
public class CourseNameValidatorTest {
	
	/** Create an instance of CourseNameValidator for testing. */
	private CourseNameValidator fsm = new CourseNameValidator();
	
	/**
	 * Test the FSM state and transitions for a course with one letter.
	 * @throws InvalidTransitionException if state transition is invalid.
	 */
	@Test
	public void testStateL() {
		
		try {
			assertTrue(fsm.isValid("E115"));
		} catch (InvalidTransitionException e) {
			
			// Do nothing
		}
		
	}
	
	/**
	 * Test the FSM state and transitions for a course with two letters.
	 * @throws InvalidTransitionException if state transition is invalid.
	 */
	@Test
	public void testStateLL(){
		
		try {
			assertTrue(fsm.isValid("CS100"));
		} catch (InvalidTransitionException e) {
			
			// Do nothing
		}
		
	}
	
	/**
	 * Test the FSM state and transitions for a course with three letters.
	 * @throws InvalidTransitionException if state transition is invalid.
	 */
	@Test
	public void testStateLLL(){
		
		try {
			assertTrue(fsm.isValid("CSC116"));
		} catch (InvalidTransitionException e) {
			
			// Do nothing
		}
		
	}
	
	/**
	 * Test the FSM state and transitions for a course with four letters.
	 * @throws InvalidTransitionException if state transition is invalid.
	 */
	@Test
	public void testStateLLLL(){
		
		try {
			assertTrue(fsm.isValid("HESF101"));
		} catch (InvalidTransitionException e) {
			
			// Do nothing
		}
		
	}
	
	/**
	 * Test the FSM state and transitions for a course with a suffix letter.
	 * @throws InvalidTransitionException if state transition is invalid.
	 */
	@Test
	public void testStateSuffix(){
		
		try {
			assertTrue(fsm.isValid("CSC116A"));
		} catch (InvalidTransitionException e) {
			
			// Do nothing
		}
		
	}
	
	/**
	 * Test the FSM state and transitions for a course with one digit.
	 */
	@Test
	public void testStateD(){
		
		Exception e = assertThrows(InvalidTransitionException.class,
				() -> fsm.isValid("CSC1A"));
		
		assertEquals("Invalid transition.", e.getMessage());
		
	}
	
	/**
	 * Test the FSM state and transitions for a course with two digits.
	 */
	@Test
	public void testStateDD(){
		
		Exception e = assertThrows(InvalidTransitionException.class,
				() -> fsm.isValid("CSC11A"));
		
		assertEquals("Invalid transition.", e.getMessage());
	}
	
	/**
	 * Test the FSM state and transitions for a course with more than three digits.
	 */
	@Test
	public void testStateTooManyDigits(){
		
		Exception e = assertThrows(InvalidTransitionException.class,
				() -> fsm.isValid("CSC1116"));
		
		assertEquals("Invalid transition.", e.getMessage());
	}
	
	/**
	 * Test the FSM state and transitions with an invalid character.
	 */
	@Test
	public void testInvalidChar() {
		
		Exception e = assertThrows(InvalidTransitionException.class,
				() -> fsm.isValid("CSC!116"));
		
		assertEquals("Invalid transition.", e.getMessage());
	}
	
	/**
	 * Test the FSM state and transitions with a course starting with a digit.
	 */
	@Test
	public void testStartWithDigit() {
		
		Exception e = assertThrows(InvalidTransitionException.class,
				() -> fsm.isValid("116CSC"));

		assertEquals("Invalid transition.", e.getMessage());
	}
	
	/**
	 * Test the FSM state and transitions with a course with more than four letters.
	 */
	@Test
	public void testTooManyLetters() {
		
		Exception e = assertThrows(InvalidTransitionException.class,
				() -> fsm.isValid("HESFA101"));

		assertEquals("Invalid transition.", e.getMessage());
	}
	
	/**
	 * Test the FSM state and transitions with a course that has a suffix letter followed
	 * by another letter or another digit.
	 */
	@Test
	public void testInvalidSuffix() {
		
		Exception e1 = assertThrows(InvalidTransitionException.class,
				() -> fsm.isValid("CSC116AB"));
		
		assertEquals("Invalid transition.", e1.getMessage());
		
		Exception e2 = assertThrows(InvalidTransitionException.class,
				() -> fsm.isValid("CSC116A1"));

		assertEquals("Invalid transition.", e2.getMessage());
	}

}
