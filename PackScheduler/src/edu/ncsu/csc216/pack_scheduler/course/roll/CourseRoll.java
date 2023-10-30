package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.user.Student;

public class CourseRoll {
	private LinkedAbstractList<Student> roll;
	private int enrollmentCap;
	private static final int MIN_ENROLLMENT = 10;
	private static final int MAX_ENROLLMENT = 250;
	
	
	public CourseRoll(int capacity) {
		roll = new LinkedAbstractList<Student>();
		setEnrollmentCap(capacity);
	}
	
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}
	
	public int getEnrollmentCap() {
		return enrollmentCap;
	}
	
	public void setEnrollmentCap(int cap) {
		if(cap < MIN_ENROLLMENT || cap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException("Invalid capacity.");
		}
		enrollmentCap = cap;
	}
	
	public void enroll(Student s) {
		if(canEnroll(s) == false) {
			throw new IllegalArgumentException("Cannot enroll student.");
		}
		
		try {
			roll.add(s);
		} catch(Exception e) {
			throw new IllegalArgumentException("Cannot enroll student.");
		}
	}
	
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
	
	public boolean canEnroll(Student s) {
		if(s == null || roll.size().equals(enrollmentCap)) {
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
