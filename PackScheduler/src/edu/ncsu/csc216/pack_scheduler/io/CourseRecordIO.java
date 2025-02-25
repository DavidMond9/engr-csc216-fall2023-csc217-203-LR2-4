package edu.ncsu.csc216.pack_scheduler.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.FileInputStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Read Course records from text files.  Write a set of CourseRecords to a file.
 * 
 * @author Sam McDonald
 */
public class CourseRecordIO {

    /**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     */
    public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
    	Scanner fileReader = new Scanner(new FileInputStream(fileName));  //Create a file scanner to read the file
        SortedList<Course> courses = new SortedList<Course>(); //Create an empty array of Course objects
        while (fileReader.hasNextLine()) { //While we have more lines in the file
            try { //Attempt to do the following
                //Read the line, process it in readCourse, and get the object
                //If trying to construct a Course in readCourse() results in an exception, flow of control will transfer to the catch block, below
                Course course = readCourse(fileReader.nextLine());

                //Create a flag to see if the newly created Course is a duplicate of something already in the list  
                boolean duplicate = false;
                //Look at all the courses in our list
                for (int i = 0; i < courses.size(); i++) {
                    //Get the course at index i
                    Course current = courses.get(i);
                    //Check if the name and section are the same
                    if (course.getName().equals(current.getName()) &&
                            course.getSection().equals(current.getSection())) {
                        //It's a duplicate!
                        duplicate = true;
                        break; //We can break out of the loop, no need to continue searching
                    }
                }
                //If the course is NOT a duplicate
                if (!duplicate) {
                    courses.add(course); //Add to the ArrayList!
                } //Otherwise ignore
            } catch (IllegalArgumentException | InvalidTransitionException e) {
                //The line is invalid b/c we couldn't create a course, skip it!
            }
        }
        //Close the Scanner b/c we're responsible with our file handles
        fileReader.close();
        //Return the ArrayList with all the courses we read!
        return courses;
    }
    
    /**
     * Reads a line and returns a course object with information from line
     * @param nextLine input string with course info
     * @return a course object populated with info from nextLine
     * @throws InvalidTransitionException  if invalid format
     * @throws IllegalArgumentException if input string format is invalid
     */
    private static Course readCourse(String nextLine) throws InvalidTransitionException {
    	Scanner reader = new Scanner(nextLine);
		reader.useDelimiter(",");
		
    	try {
    		//read in tokens  name, title, section, credits, instructorId, and meetingDays and store in local variables
    		String name = reader.next();
    		String title = reader.next();
    		String section = reader.next();
    		int credits  = reader.nextInt();
    		String instructorId = reader.next();
    		int enrollmentCap = reader.nextInt();
    		String meetingDays = reader.next();
    		
    		if ("A".equals(meetingDays)) {
    			if (reader.hasNext()) {
    				reader.close();
    				throw new IllegalArgumentException("Illegal meetingDays");
    			} else {
    				reader.close();

    				return new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays);
    			}
    		} else {
    			int startTime = reader.nextInt();
    			int endTime = reader.nextInt();
    			if (reader.hasNext()) {
    				reader.close();
    				throw new IllegalArgumentException("Illegal number of arguments");
    			} else {
    				reader.close();
    				return new Course(name, title, section, credits, 

    						instructorId, enrollmentCap, meetingDays, startTime, endTime);
    			}
    		}
    	} catch (InputMismatchException e) {
    		reader.close();
    		throw new IllegalArgumentException("Input mismatch.");
    	} catch (NoSuchElementException e) {
    		reader.close();
    		throw new IllegalArgumentException("No such element.");
    	}
    }
    
	/**
     * Writes the given list of Courses to a specified file
     * 
     * @param fileName file to write schedule of Courses to
     * @param courses list of Courses to write
     * @throws IOException if cannot write to file
     */
	public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
		PrintStream fileWriter = new PrintStream(fileName);

		for (int i = 0; i < courses.size(); i++) {
		    fileWriter.println(courses.get(i).toString());
		}

		fileWriter.close();
		
	}

}