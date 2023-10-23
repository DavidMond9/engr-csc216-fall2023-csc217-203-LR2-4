package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Testing the class CourseNameValidatorFSM which is a Finite State Machine for checking whether
 * a Course's Name is valid.
 * 
 * @author Audrey Fuelleman
 * 
 */
public class CourseNameValidatorFSMTest {

	/** Create an instance of CourseNameValidatorFSM for testing. */
	private CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();
	
	/**
	 * Test the FSM state and transitions for a course with one letter.
	 */
	@Test
	public void testStateL() {
		
		try {
			assertTrue(fsm.isValid("E115"));
		} catch (InvalidTransitionException e) {
			
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Test the FSM state and transitions for a course with two letters.
	 */
	@Test
	public void testStateLL(){
		
		try {
			assertTrue(fsm.isValid("CS100"));
		} catch (InvalidTransitionException e) {
			
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Test the FSM state and transitions for a course with three letters.
	 */
	@Test
	public void testStateLLL(){
		
		try {
			assertTrue(fsm.isValid("CSC116"));
		} catch (InvalidTransitionException e) {
			
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Test the FSM state and transitions for a course with four letters.
	 */
	@Test
	public void testStateLLLL(){
		
		try {
			assertTrue(fsm.isValid("HESF101"));
		} catch (InvalidTransitionException e) {
			
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Test the FSM state and transitions for a course with a suffix letter.
	 */
	@Test
	public void testStateSuffix(){
		
		try {
			assertTrue(fsm.isValid("CSC116A"));
		} catch (InvalidTransitionException e) {
			
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Test the FSM state and transitions for a course with one digit.
	 */
	@Test
	public void testStateD(){
		
		Exception e = assertThrows(InvalidTransitionException.class,
				() -> fsm.isValid("CSC1A"));
		
		assertEquals("Course name must have 3 digits.", e.getMessage());
		
	}
	
	/**
	 * Test the FSM state and transitions for a course with two digits.
	 */
	@Test
	public void testStateDD(){
		
		Exception e = assertThrows(InvalidTransitionException.class,
				() -> fsm.isValid("CSC11A"));
		
		assertEquals("Course name must have 3 digits.", e.getMessage());
	}
	
	/**
	 * Test the FSM state and transitions for a course with more than three digits.
	 */
	@Test
	public void testStateTooManyDigits(){
		
		Exception e = assertThrows(InvalidTransitionException.class,
				() -> fsm.isValid("CSC1116"));
		
		assertEquals("Course name can only have 3 digits.", e.getMessage());
	}
	
	/**
	 * Test the FSM state and transitions with an invalid character.
	 */
	@Test
	public void testInvalidChar() {
		
		Exception e = assertThrows(InvalidTransitionException.class,
				() -> fsm.isValid("CSC!116"));
		
		assertEquals("Course name can only contain letters and digits.", e.getMessage());
	}
	
	/**
	 * Test the FSM state and transitions with a course starting with a digit.
	 */
	@Test
	public void testStartWithDigit() {
		
		Exception e = assertThrows(InvalidTransitionException.class,
				() -> fsm.isValid("116CSC"));

		assertEquals("Course name must start with a letter.", e.getMessage());
	}
	
	/**
	 * Test the FSM state and transitions with a course with more than four letters.
	 */
	@Test
	public void testTooManyLetters() {
		
		Exception e = assertThrows(InvalidTransitionException.class,
				() -> fsm.isValid("HESFA101"));

		assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
	}
	
	/**
	 * Test the FSM state and transitions with a course that has a suffix letter followed
	 * by another letter or another digit.
	 */
	@Test
	public void testInvalidSuffix() {
		
		Exception e1 = assertThrows(InvalidTransitionException.class,
				() -> fsm.isValid("CSC116AB"));
		
		assertEquals("Course name can only have a 1 letter suffix.", e1.getMessage());
		
		Exception e2 = assertThrows(InvalidTransitionException.class,
				() -> fsm.isValid("CSC116A1"));

		assertEquals("Course name cannot contain digits after the suffix.", e2.getMessage());
	}

}