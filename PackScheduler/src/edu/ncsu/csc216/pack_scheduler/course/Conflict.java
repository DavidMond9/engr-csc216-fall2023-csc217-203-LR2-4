/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Conflict interface for checking if activities have conflicting times.
 * @author - Sam McDonald
 */
public interface Conflict {
	/**
	 * Checks if the input activity's start and end times conflicts with current activity on any of the meeting days.
	 * @param possibleConflictingActivity input activity to check for conflict.
	 * @throws InvalidTransitionException Exception thrown when conflict is found.
	 */
	void checkConflict(Activity possibleConflictingActivity) throws InvalidTransitionException;	
}