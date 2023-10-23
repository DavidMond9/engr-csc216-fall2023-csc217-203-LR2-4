package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * This class implements a catalog function for courses, allowing for courses to be added and removed
 * from a sorted list.  Additionally includes the ability to save and load course catalogs from a file.
 * 
 * @author - Sam McDonald
 */

public class CourseCatalog {

	/**
	 * Variable catalog that contains all courses in a sorted list.
	 */
	private SortedList<Course> catalog;
	
	/**
	 * Constructs a course catalog object.
	 */
	public CourseCatalog() {
		newCourseCatalog();
	}
	
	/**
	 * Erases any data currently stored in catalog
	 */
	public void newCourseCatalog() {
		catalog = new SortedList<Course>();
	}
	
	/**
	 * Loads courses into the catalog from the provided file.
	 * @param fileName file to load courses from
	 * @throws IllegalArgumentException thrown if file is unable to be read/found
	 */
	public void loadCoursesFromFile(String fileName) throws IllegalArgumentException {
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	
	/**
	 * Creates a course with the given data and adds it to the catalog
	 * @param name name of the course
	 * @param title title of the course
	 * @param section section of the course
	 * @param credits credits of the course
	 * @param instructorId instructor ID of the course
	 * @param meetingDays meeting days of the course
	 * @param startTime start time of the course
	 * @param endTime end time of the course
	 * @return boolean, true if successfully added, false if not
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId, String meetingDays, int startTime, int endTime) {
		
			
		Course c;
		try {
			c = new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime);
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException("Invalid transition.");
		}
		for(int j = 0; j < catalog.size(); j++) {
			if(catalog.get(j).isDuplicate(c)) {
				//throw new IllegalArgumentException("Course already in system.");
				return false;
			}
		}
		catalog.add(c);
		return true;
	}
	
	/**
	 * Searches the catalog and if found, removes a course from the catalog
	 * @param name name of the course to be removed
	 * @param section section of the course to be removed
	 * @return boolean, true if course was removed, false if course was not found in catalog
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		for(int i = 0; i < catalog.size(); i++) {
			if(name.equals(catalog.get(i).getName()) || section.equals(catalog.get(i).getSection())) {
				catalog.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Searches the catalog and if found in catalog, returns the course.
	 * @param name name of the course to be returned
	 * @param section section of the course to be returned
	 * @return Course, the course specified if found, null if not found
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for(int i = 0; i < catalog.size(); i++) {
			if(name.equals(catalog.get(i).getName()) && section.equals(catalog.get(i).getSection())) {
				return catalog.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Returns the entire course catalog as a string matrix
	 * @return String[][], contains, name, section, title, and meeting information of the courses
	 */
	public String[][] getCourseCatalog() {
		String[][] strCatalog = new String[catalog.size()][4];
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			strCatalog[i] = c.getShortDisplayArray();
		}
		return strCatalog;
	}
	
	/**
	 * Saves the course catalog to the given file
	 * @param outputFile the file to save the catalog to
	 * @throws IllegalArgumentException thrown if the file specified cannot be saved to
	 */
	public void saveCourseCatalog(String outputFile) {
		try {
			CourseRecordIO.writeCourseRecords(outputFile, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
		
	}
}
