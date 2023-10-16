/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * this class creates a checked exception that can be called for any invalid transitions
 * @author Duncan
 */
public class InvalidTransitionException extends Exception {

	/** ID used for serialization*/
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor with a parameter that is used to call the default constructor
	 * @param message the message to be thrown when the checked exception is found
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}
	
	/**
	 * parameterless constructor that calls the parameterized constructor with an author specified default message
	 */
	public InvalidTransitionException() {
		this("Invalid FSM Transition.");
	}
}
