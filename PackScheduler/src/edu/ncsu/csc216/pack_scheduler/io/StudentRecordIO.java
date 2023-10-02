package edu.ncsu.csc216.pack_scheduler.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * This class provides static methods that read from and write to a
 * file. The first public class in this method reads the given file and returns 
 * an array of students, while the second public method writes an array of 
 * students to the given file.
 * 
 * @author Ben Wojtowiak, Sam McDonald
 */
public class StudentRecordIO {

	/**
	 * This method reads Student records from the given file. Records
	 * are returned in an array containing Student objects.
	 * @param fileName student records file
	 * @return SortedList students the list of students to be read from the file
	 * @throws FileNotFoundException if specified file cannot be found
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		
			Scanner fileReader = new Scanner(new FileInputStream(fileName));  
			SortedList<Student> students = new SortedList<Student>();
		    while (fileReader.hasNextLine()) {
		    	try { 
		        	fileReader.useDelimiter(",");
		        	String[] line = fileReader.nextLine().split(",");
		        	
		            if(line.length < 6) {
		            	
		            	students.add(new Student(line[0], line[1], line[2], line[3], line[4]));
		            }
		            
		            else {
		            	
			            students.add(new Student(line[0], line[1], line[2], line[3], line[4], Integer.parseInt(line[5])));	
		            
		            }
		    	} catch (IllegalArgumentException e) {
		    		// If a file has any invalid input, clear the SortedList and stop reading file
		    		students.clear();
		    		break;
		    	}
		    }
		    
		    fileReader.close();
		    return students;
	}

	
	/**
	 * Writes the Student info to the file given in the parameter,
	 * one Student record at a time.
	 * @param fileName student records file
	 * @param studentDirectory sorted list of students
	 * @throws IOException Error writing to file
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		try {
			PrintStream fileWriter = new PrintStream(fileName);
				
			for (int i = 0; i < studentDirectory.size(); i++){
				fileWriter.println(studentDirectory.get(i).toString());
			}
			
			fileWriter.close();
		} catch (IOException e) {
			throw new IOException(fileName + " (No such file or directory)");
		}
	}

}