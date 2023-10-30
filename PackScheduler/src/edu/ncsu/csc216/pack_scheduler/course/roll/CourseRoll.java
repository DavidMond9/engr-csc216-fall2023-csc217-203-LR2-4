package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;

/**
 * CourseRoll class. A course roll connects with a course and holds a list of students in a roll.
 * Has multiple fields including a roll of students with the type LinkedAbstractList.
 * Has an enrollment capacity and also a max and min capacity for this field.
 * Has many methods that can add, remove students from the roll and more.
 * @author David Mond
 */
public class CourseRoll {
	/**
	 * Field for list of students for the roll.
	 */
	private LinkedAbstractList<Student> roll;
	/**
	 * Field for the enrollment cap.
	 */
	private int enrollmentCap;
	/**
	 * Field for the minimum capacity.
	 */
	private static final int MIN_ENROLLMENT = 10;
	/**
	 * Field for the maximum capacity.
	 */
	private static final int MAX_ENROLLMENT = 250;
	
	/**
	 * CourseRoll constructor creates a course roll. 
	 * @param capacity the capacity to set
	 */
	public CourseRoll(int capacity) {
		setEnrollmentCap(capacity);
		roll = new LinkedAbstractList<Student>(enrollmentCap);
	}
	
	/**
	 * Gets the open seats from the capacity - current roll size.
	 * @return an integer of how many open seats there are.
	 */
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}
	/**
	 * Gets the enrollment capacity.
	 * @return an integer of the capacity.
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
	}
	/**
	 * Sets the enrollment capacity.
	 * @param cap to set.
	 */
	public void setEnrollmentCap(int cap) {
		if(cap < MIN_ENROLLMENT || cap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException("Invalid capacity.");
		}
		enrollmentCap = cap;
	}
	/**
	 * Adds a student to the roll.
	 * @param s the student to add.
	 */
	public void enroll(Student s) {
		if(!canEnroll(s)) {
			throw new IllegalArgumentException("Cannot enroll student.");
		}
		
		try {
			roll.add(s);
		} catch(Exception e) {
			throw new IllegalArgumentException("Cannot enroll student.");
		}
		
	}
	/**
	 * Removes a student from the roll.
	 * @param s the student to remove.
	 */
	public void drop(Student s) {
		if(s == null) {
			throw new IllegalArgumentException("Cannot drop student.");
		}
		
		try {
			roll.remove(s);
		} catch(Exception e) {
			throw new IllegalArgumentException("Cannot drop student.");
		}
		
	}
	/**
	 * Checks to see if a student can be enrolled to the current roll.
	 * @param s student to enroll.
	 * @return boolean to see if it can be enrolled, true, or can't be enrolled, false.
	 */
	public boolean canEnroll(Student s) {
		if(s == null || roll.size() == enrollmentCap) {
			return false;
		}
		for(int i = 0; i < roll.size(); i++) {
			if(roll.get(i) == s) {
				return false;
			}
		}
		return true;
	}
}
