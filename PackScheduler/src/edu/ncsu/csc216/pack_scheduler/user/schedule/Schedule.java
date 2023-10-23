/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * An ArrayList of Courses that contains no duplicates or
 * courses with conflicting days and times.
 * 
 * @author Audrey Fuelleman
 */
public class Schedule {
	/** The current instance of Schedule */
	private ArrayList<Course> schedule;
	/** Field for the schedule's title */
	private String title;
	/** Default title for the schedule */
	private static final String DEFAULT_TITLE = "My Schedule";
	
	/** 
	 * Constructor for Schedule. 
	 */
	public Schedule() {
		this.schedule = new ArrayList<Course>();
		this.title = DEFAULT_TITLE;
	}
	
	/**
	 * Adds a course to the schedule and returns true if successful.
	 * Checks if the given course is a duplicate or conflicts with
	 * an existing course in the schedule.
	 * @param c the course to add to the schedule.
	 * @return true if the course is successfully added to the schedule.
	 * @throws IllegalArgumentException if the course is a duplicate or conflicts
	 * with an existing course.
	 * @throws NullPointerException if the given course is null.
	 */
	public boolean addCourseToSchedule(Course c) throws NullPointerException {
		for (Course course:schedule) {
			// Check if the Course is a duplicate to another in the schedule
			if (c.isDuplicate(course)) {
				throw new IllegalArgumentException("You are already enrolled in " + course + ".");	
			}
			// Check if course conflicts with an existing course in the schedule
			try {
				c.checkConflict(course);
			} catch (ConflictException e){
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
		}
		
		schedule.add(c);
		return true;
		
	}
	
	/**
	 * Remove the given course from the schedule if it
	 * exists in the schedule.
	 * @param c the course to remove from the schedule.
	 * @return isRemoved true/false if the course was successfully removed
	 * from the schedule.
	 */
	public boolean removeCourseFromSchedule(Course c) {
		boolean isRemoved = false;
		for (Course course:schedule) {
			if (course.equals(c)) {
				schedule.remove(c);
				isRemoved = true;
			}
		}
		return isRemoved;
	}
	
	/**
	 * Resets the schedule to an empty ArrayList
	 * and resets the schedule's title to the default title.
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Course>();
		this.title = DEFAULT_TITLE;
	}
	
	/**
	 * Returns a 2D array of the name, section, title, and meeting string for
	 * the courses in the current Schedule.
	 * @return scheduleArray the 2D array of the name, section, title, and meeting
	 * string for the courses in the schedule.
	 */
	public String[][] getScheduledCourses() {
		// Number of columns for Course name, section, title, and meeting string
		int cols = 4;
		// Number of rows for the number of Courses in schedule
		int rows = schedule.size();
		String[][] scheduleArray = new String[rows][cols];
		for (int i = 0; i < schedule.size(); i++) {
			scheduleArray[i] = schedule.get(i).getShortDisplayArray();
		}
		
		return scheduleArray;
	}
	
	/**
	 * Sets the title for the current Schedule.
	 * @param title the title to set for the current Schedule.
	 * @throws IllegalArgumentException if the title parameter is null.
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
	}
	
	/**
	 * Returns the title of the current Schedule.
	 * @return title the title of the current instance of Schedule.
	 */
	public String getTitle() {
		return this.title;
	}
}
