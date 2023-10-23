package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Defines the Course object
 * Receives all course information and validates information before creating a course object and has ability to get and set all fields.
 * Extends Activity class for title and meeting days/times implementation.
 * 
 * @author Sam McDonald
 */
public class Course extends Activity implements Comparable<Course> {
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Minimum course name length */
    private static final int MIN_LENGTH = 4;
    /** Maximum course name length */
    private static final int MAX_LENGTH = 8;
//    /** Minimum course letter count */
//    private static final int MIN_LETTER_COUNT = 1;
//    /** Maximum course letter count */
//    private static final int MAX_LETTER_COUNT = 4;
//    /** Course digit count */
//    private static final int DIGIT_COUNT = 3;
    /** Course section length */
    private static final int SECTION_LENGTH = 3;
    /** Course minimum credits */
    private static final int MIN_CREDITS = 1;
    /** Course maximum credits */
    private static final int MAX_CREDITS = 5;
    
    /**
	 * Constructs a Course object with values for all fields.
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays meeting days for Course as series of chars
	 * @param startTime start time for Course
	 * @param endTime end time for Course
     * @throws InvalidTransitionException if invalid format
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
	        int startTime, int endTime) throws InvalidTransitionException {
	    super(title, meetingDays, startTime, endTime);
		try {
			setName(name);
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException("Invalid course name.");
		}
	    setSection(section);
	    setCredits(credits);
	    setInstructorId(instructorId);
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId, and meetingDays for 
	 * courses that are arranged.
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays meeting days for Course as series of chars
	 * @throws InvalidTransitionException  if invalid format
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) throws InvalidTransitionException {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}
	
	/**
	 * Provides short version of information for GUI
	 * @return String[] containing name, section, title, and meeting string
	 */
	public String[] getShortDisplayArray() {
		return new String[] {name, section, getTitle(), getMeetingString()};
	}
	
	/**
	 * Provides long version of information for GUI
	 * @return String[] containing name, section, title, credits, instructor ID, meeting string, and empty string (not used in Course)
	 */
	public String[] getLongDisplayArray() {
		return new String[] {name, section, getTitle(), String.valueOf(credits), instructorId, getMeetingString(), ""};
	}
	
	/**
	 * Returns whether input activity is a duplicate.
	 * Course is a duplicate if it has the same name and section
	 * @param activity the activity to compare to
	 * @return true if it is a duplicate, false if not.
	 */
	public boolean isDuplicate(Activity activity) {
		if(activity instanceof Course) {
			Course c = (Course)activity;
			if(c.getName().equals(getName()) && c.getSection().equals(getSection())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the Course's name
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the Course's name.  If the name is null, has a length less than 5 or more than 8,
	 * does not contain a space between letter characters and number characters, has less than 1
	 * or more than 4 letter characters, and not exactly three trailing digit characters, an
	 * IllegalArgumentException is thrown.
	 * @param name the name to set
	 * @throws InvalidTransitionException if invalid format
	 * @throws IllegalArgumentException if the name parameter is invalid
	 */
	private void setName(String name) throws InvalidTransitionException {
	    //Throw exception if the name is null
	    if (name == null) {
	        throw new IllegalArgumentException("Invalid course name.");
	    }
	    
	    //Throw exception if the name is an empty string
	    //Throw exception if the name contains less than 4 character or greater than 8 characters
	    if (name.length() < MIN_LENGTH || name.length() > MAX_LENGTH) {
	    	throw new IllegalArgumentException("Invalid course name.");
		}
	    CourseNameValidator validator = new CourseNameValidator();
		if (validator.isValid(name)) {
			this.name = name;
		} else {
			throw new InvalidTransitionException();
		}
	    
//	    //Check for pattern of L[LLL] NNN
//	    int letterCount = 0;
//	    int digitCount = 0;
//	    boolean space = false;
//	    for (int i = 0; i < name.length(); i++) {
//	        if (!space) {
//	            if (Character.isLetter(name.charAt(i))) {
//	                letterCount++;
//	            } else if (name.charAt(i) == ' ') {
//	                space = true;
//	            } else {
//	                throw new IllegalArgumentException("Invalid course name.");
//	            }
//	        } else if (space) {
//	            if (Character.isDigit(name.charAt(i))) {
//	                digitCount++;
//	            } else {
//	                throw new IllegalArgumentException("Invalid course name.");
//	            }
//	        }
//	    }
	    
//	    //Check that the number of letters is correct
//	    if (letterCount < MIN_LETTER_COUNT || letterCount > MAX_LETTER_COUNT) {
//	        throw new IllegalArgumentException("Invalid course name.");
//	    }
//	    //Check that the number of digits is correct
//	    if (digitCount != DIGIT_COUNT) {
//	        throw new IllegalArgumentException("Invalid course name.");
//	    }
//	    this.name = name;
	}
	
	/**
	 * Returns the Course's section
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	
	/**
	 * Sets the Course's section
	 * @param section the section to set
	 * @throws IllegalArgumentException if input section format is invalid
	 */
	public void setSection(String section) {
		//Throw Exception if section is null or not correct length
		if(section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}
		//Throw Exception if section is not all digits
		for(int i = 0; i < section.length(); i++) {
			if(!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Invalid section.");
			}
		}
		this.section = section;
	}
	
	/**
	 * Returns the Course's credits
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}
	
	/**
	 * Sets the Course's credits
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if input credits format is invalid
	 */
	public void setCredits(int credits) {
		if(credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}
		this.credits = credits;
	}
	
	/**
	 * Returns the Course's instructorId
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}
	
	/**
	 * Sets the Course's instructorId
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if input instructorId format is invalid
	 */
	public void setInstructorId(String instructorId) {
		//Throw Exception if instructorId is null or empty
		if (instructorId == null || "".equals(instructorId)) { 
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		this.instructorId = instructorId;
	}
	
	/**
	 * Overrides setMeetingDaysAndTime to check validity of meeting days for Course.
	 * @param meetingDays the meeting days for the course
	 * @param startTime the start time for the course
	 * @param endTime the end time for the course
	 * @throws IllegalArgumentException if input meeting days, start time, or end time is invalid
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		//Tests fail if this check is not in this override...
		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		if ("A".equals(meetingDays)) {// Arranged
			if (startTime != 0 || endTime != 0) {
		     throw new IllegalArgumentException("Invalid meeting days and times.");
	      }
		  super.setMeetingDaysAndTime(meetingDays, 0, 0);
		  
		} else { //not arranged
			//counters for weekdays
			int m = 0;
			int t = 0;
			int w = 0;
			int h = 0;
			int f = 0;
			for (int i = 0; i < meetingDays.length(); i++) {
				switch(meetingDays.charAt(i)) {
					case 'M':
						m++;
						break;
					case 'T':
						t++;
						break;
					case 'W':
						w++;
						break;
					case 'H':
						h++;
						break;
					case 'F':
						f++;
						break;
					default:
						throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}
		
			if (m > 1 || t > 1 || w > 1 || h > 1 || f > 1) { // checks for duplicates
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		}
	}

	/**
	 * Returns a comma separated value String of Course object.  Meeting days and times are combined to one string.
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
	    if ("A".equals(getMeetingDays())) {
	        return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
	    }
	    return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime(); 
	}

	/**
	 * Returns hash code representation of Course object.  Relies on hashCode of Activity.
	 * @return integer hash code representation of Course object. 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/**
	 * Returns true or false depending on if all fields of input object are equal to Course object.
	 * @param obj the object to compare to
	 * @return true if all fields of object are equal, false if not.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * Compares this course object to another course object
	 * for order by course name and section
	 * 
	 * @param c the course to compare this course to
	 * @return compare a negative integer, zero, or a positive integer if this object is less than, equal to, or greater than the specified object
	 */
	@Override
	public int compareTo(Course c) {
		int compare = 0;
		int c1NameLength = this.getName().length();
		int c2NameLength = c.getName().length();
		int nameLength;
		
		if (c1NameLength < c2NameLength) {
			nameLength = c1NameLength;
		} else {
			nameLength = c2NameLength;
		}
		for (int i = 0; i < nameLength; i++) {
			if (this.getName().charAt(i) < c.getName().charAt(i)) {
				compare = -1;
				break;
			} else if (this.getName().charAt(i) == c.getName().charAt(i)) {
				if (i != nameLength - 1) {
					continue;
				} else {
					if (c1NameLength < c2NameLength) {
						compare = -1;
						break;
					} else if (c1NameLength > c2NameLength) {
						compare = 1;
						break;
					} else {
						break;		
					}	
				}
			} else {
				compare = 1;
				break;
			}
		}
		
		if (compare == 0) {
			for (int i = 0; i < 3; i++) {
				if (this.getSection().charAt(i) < c.getSection().charAt(i)) {
					compare = -1;
					break;
				} else if (this.getSection().charAt(i) == c.getSection().charAt(i)) {
					if (i != 2) {
						continue;
					}
				} else {
					compare = 1;
					break;	
				}	
			}	
		}
		return compare;
	}
}
