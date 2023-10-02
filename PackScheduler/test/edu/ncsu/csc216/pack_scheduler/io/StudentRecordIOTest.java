package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Tests the StudentRecordIO object
 * @author Sam McDonald
 */

class StudentRecordIOTest extends StudentRecordIO {

	/** Valid student's toString information */
	private String validStudent0 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";

	/** Valid student's toString information */
	private String validStudent1 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";

	/** Valid student's toString information */
	private String validStudent2 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";

	/** Valid student's toString information */
	private String validStudent3 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";

	/** Valid student's toString information */
	private String validStudent4 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";

	/** Valid student's toString information */
	private String validStudent5 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";

	/** Valid student's toString information */
	private String validStudent6 = "Lane,Berg,lberg,sociis@non.org,pw,14";

	/** Valid student's toString information */
	private String validStudent7 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";

	/** Valid student's toString information */
	private String validStudent8 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";

	/** Valid student's toString information */
	private String validStudent9 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";

	/** Array containing all valid student toString information */
	private String [] validStudents = {validStudent0, validStudent1, validStudent2, validStudent3, validStudent4, validStudent5,
	        validStudent6, validStudent7, validStudent8, validStudent9};

	/** Hashed password */
	private String hashPW;
	
	/** Hashing algorithm used */
	private static final String HASH_ALGORITHM = "SHA-256";

	@BeforeEach
	public void setUp() {
	    try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = Base64.getEncoder().encodeToString(digest.digest());
	        
	        for (int i = 0; i < validStudents.length; i++) {
	            validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}
	
	/**
	 * Tests the readStudentRecords method, testing for correct file reading functionality.
	 */
	@Test
	void testReadStudentRecords() {
		SortedList<Student> valid = new SortedList<Student>();
		for(int i = 0; i < validStudents.length; i++) {
			valid.add(new Student(validStudents[i].split(",")[0], validStudents[i].split(",")[1], 
				validStudents[i].split(",")[2], validStudents[i].split(",")[3], 
				validStudents[i].split(",")[4], Integer.valueOf(validStudents[i].split(",")[5])));
		}
		
		try {
			SortedList<Student> students = StudentRecordIO.readStudentRecords("test-files/student_records.txt");
			
			assertEquals(students.size(), validStudents.length);
			for (int i = 0; i < students.size(); i++) {
				assertEquals(students.get(i).toString(), valid.get(i).toString());
			}
		}
		catch (FileNotFoundException e) {
			fail("Unable to find file");
		}
		
		try {
			SortedList<Student> students = StudentRecordIO.readStudentRecords("test-files/student_records_no_credit.txt");
			assertEquals(students.size(), 1);
			assertEquals(students.get(0), new Student(validStudents[0].split(",")[0], validStudents[0].split(",")[1], 
					validStudents[0].split(",")[2], validStudents[0].split(",")[3], 
					validStudents[0].split(",")[4]));
		}
		catch (FileNotFoundException e) {
			fail("Unable to find file");
		}
		
		try {
			SortedList<Student> students = StudentRecordIO.readStudentRecords("test-files/invalid_student_records.txt");
			assertEquals(students.size(), 0);			
		}
		catch (FileNotFoundException e) {
			fail("Unable to find file");
		}
		assertThrows(FileNotFoundException.class, () -> StudentRecordIO.readStudentRecords("fakefile123"));
	}

	/**
	 * Tests the writeStudentRecords method, testing for correct file writing functionality.
	 */
	@Test
	void testWriteStudentRecords() {
		SortedList<Student> students = new SortedList<Student>();
		for (int i = 0; i < validStudents.length; i++) {
			students.add(new Student(validStudents[i].split(",")[0], validStudents[i].split(",")[1], 
					validStudents[i].split(",")[2], validStudents[i].split(",")[3], 
					validStudents[i].split(",")[4], Integer.parseInt(validStudents[i].split(",")[5])));
		}
		
		try {
			StudentRecordIO.writeStudentRecords("actual_student_records.txt", students);
			checkFiles("test-files/expected_full_student_records.txt", "actual_student_records.txt");
		} catch (IOException e) {
			fail("Error writing to files.");
		}
	}
	
	/**
	 * Tests the situation where program does not have permission to access files.
	 */
	@Test
	public void testWriteStudentRecordsNoPermissions() {
		SortedList<Student> students = new SortedList<Student>();
		students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
		
		Exception exception = assertThrows(IOException.class, 
				() -> StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students));
		assertEquals("/home/sesmith5/actual_student_records.txt (No such file or directory)", exception.getMessage());
	}
	
	/**
	* Helper method to compare two files for the same contents
	* @param expFile expected output
	* @param actFile actual output
	*/
	private void checkFiles(String expFile, String actFile) {
	try (Scanner expScanner = new Scanner(new FileInputStream(expFile));
		 Scanner actScanner = new Scanner(new FileInputStream(actFile));) {
		
		while (expScanner.hasNextLine()  && actScanner.hasNextLine()) {
			String exp = expScanner.nextLine();
			String act = actScanner.nextLine();
			assertEquals(exp, act, "Expected: " + exp + " Actual: " + act); 
			//The third argument helps with debugging!
		}
		if (expScanner.hasNextLine()) {
			fail("The expected results expect another line " + expScanner.nextLine());
		}
		if (actScanner.hasNextLine()) {
			fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
		}
		
		expScanner.close();
		actScanner.close();
	} catch (IOException e) {
		fail("Error reading files.");
	}
	}

}



