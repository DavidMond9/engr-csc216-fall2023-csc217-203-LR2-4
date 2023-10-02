package edu.ncsu.csc216.pack_scheduler.course;

/**
 * ConflictException is a custom checked error that is thrown when a schedule conflict is found
 * The error can either be given a custom error message, or will throw "Schedule conflict." as a default
 * @author - Sam McDonald
 */
public class ConflictException extends Exception {
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for exception with default message "Schedule conflict."
	 */
	public ConflictException() { 
		super("Schedule conflict.");
	}
	/**
	 * Constructor for exception with custom message
	 * @param e custom message to use with exception
	 */
	public ConflictException(String e) { 
		super(e);
	}
	
}
