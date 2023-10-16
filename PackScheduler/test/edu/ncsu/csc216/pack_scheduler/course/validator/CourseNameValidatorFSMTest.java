package edu.ncsu.csc216.pack_scheduler.course.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;

/**
 * Testing the class CourseNameValidatorFSM which is a Finite State Machine for checking whether
 * a Course's Name is valid.
 * 
 * @author Audrey Fuelleman
 * 
 */
public class CourseNameValidatorFSMTest {

	private CourseNameValidatorFSM fsm = new CourseNameValidatorFSM();
	
	/**
	 * Test the FSM state and transitions for a course with one letter.
	 * @throws InvalidTransitionException 
	 */
	@Test
	public void testStateL() {
		
		try {
			fsm.isValid("E115");
		} catch (InvalidTransitionException e) {
			
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testStateLL(){
		
		
		
	}
	
	@Test
	public void testStateLLL(){
		
		
		
	}
	
	@Test
	public void testStateLLLL(){
		
		
		
	}
	
	@Test
	public void testStateSuffix(){
		
		
		
	}
	
	@Test
	public void testStateD(){
		
		
		
	}

}