package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Superclass for all activities.  
 * Contains information on title, meeting days and times.
 * Extended by both Course and Event for shared functionality with title and meeting days/times
 * Contains parameters for title of activity and meeting days/times, with methods to set and get each.
 * Can also check for conflicts in days/times between activities
 * 
 * @author Sam McDonald
 */
public abstract class Activity implements Conflict {

	/** Activity's title. */
	private String title;
	/** Activity's meeting days */
	private String meetingDays;
	/** Activity's starting time */
	private int startTime;
	/** Activity's ending time */
	private int endTime;
	/** Maximum hour for time */
	private static final int UPPER_HOUR = 23;
	/** Maximum minute for time */
	private static final int UPPER_MINUTE = 59;
	
	/**
	 * Constructor for Activity superclass.  Sets title, meeting days, and meeting times.
	 * @param title title of activity
	 * @param meetingDays days which activity meets
	 * @param startTime start time of activity
	 * @param endTime end time of activity
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}
	
	/**
	 * Provides short version of information for GUI
	 * @return String[] containing short information
	 */
	public abstract String[] getShortDisplayArray();
	
	/**
	 * Provides long version of information for GUI
	 * @return String[] containing long information
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * Returns whether input activity is a duplicate.
	 * @param activity the activity to compare to
	 * @return true if it is a duplicate, false if not.
	 */
	public abstract boolean isDuplicate(Activity activity);
	
	/**
	 * Returns the Activity's title
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Activity's title
	 * @param title the title to set
	 * @throws IllegalArgumentException if title is invalid
	 */
	public void setTitle(String title) {
		//Throw Exception if title is null or empty
		if (title == null || "".equals(title)) { 
			throw new IllegalArgumentException("Invalid title.");
		}
		this.title = title;
	}

	/**
	 * Returns the Activity's meeting days
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Activity's start time
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Activity's end time
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the Activity's meeting days, start time, and end time
	 * @param meetingDays the meetingDays to set
	 * @param startTime the startTime to set
	 * @param endTime the endTime to set
	 * @throws IllegalArgumentException if meeting days and/or times are invalid
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		//null and empty check was originally in this method but tests failed due to overrides being called first.  Moved to overridden methods.
		//break apart startTime and endTime into hours and minutes
		int startHour = startTime / 100;
		int startMin = startTime % 100;
		int endHour = endTime / 100;
		int endMin = endTime % 100;
		
		//check time for validity
		if (startHour < 0 || startHour > UPPER_HOUR) {// not between 0 and 23, inclusive
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		if (startMin < 0 || startMin > UPPER_MINUTE) { // not between 0 and 59, inclusive
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		if (endHour < 0 || endHour > UPPER_HOUR) { // not between 0 and 23, inclusive
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		  
		if (endMin < 0 || endMin > UPPER_MINUTE) {// not between 0 and 59, inclusive
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		if (startTime > endTime) {// check if start is later than end
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		//everything is valid and works together
		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Returns a string with the meeting date and time information.
	 * @return String representation of meeting date and time
	 */
	public String getMeetingString() {
		if ("A".equals(this.meetingDays)) {
			return "Arranged";
		} else {
			//format example: MW 1:30PM-2:45PM
			return this.meetingDays + " " + getTimeString(startTime) + "-" + getTimeString(endTime);
		}
	}

	/**
	 * Input time in military format and receive a string in standard format.
	 * @param time time in military format
	 * @return String representation of meeting date and time
	 */
	private String getTimeString(int time) {
		if (time < 1200) {
			return String.valueOf(time / 100) + ":" + String.format("%02d", time % 100) + "AM";
		} else if (time < 1300) {
			return String.valueOf(time / 100) + ":" + String.format("%02d", time % 100) + "PM";
		} else {
			return String.valueOf((time - 1200) / 100) + ":" + String.format("%02d", time % 100) + "PM";
		}
	}

	/**
	 * Checks if the input activity's start and end times conflicts with current activity on any of the meeting days.
	 * Conflict is defined as an overlap of at least one day and time, even by the same minute.
	 * @param possibleConflictingActivity input activity to check for conflict.
	 * @throws ConflictException Exception thrown when conflict is found.
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		if(!"A".equals(possibleConflictingActivity.getMeetingDays()) || !"A".equals(getMeetingDays())) {
			for(int i = 0; i < possibleConflictingActivity.getMeetingDays().length(); i++) {
				if (getMeetingDays().contains(Character.toString(possibleConflictingActivity.getMeetingDays().charAt(i)))) {
					if(getStartTime() < possibleConflictingActivity.getStartTime()) {
						if(getEndTime() >= possibleConflictingActivity.getStartTime()) {
							throw new ConflictException();
						}
					}
					else if (getStartTime() <= possibleConflictingActivity.getEndTime()) {
						throw new ConflictException();
					}
				}
			}
		}
	}

	/**
	 * Returns hash code representation of Activity object.
	 * @return integer hash code representation of Activity object. 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Returns true or false depending on if all fields of input object are equal to Activity object.
	 * @param obj the object to compare to
	 * @return true if all fields of object are equal, false if not.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}