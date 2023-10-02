package edu.ncsu.csc216.pack_scheduler.users;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;
//import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.User;


/**
 * Tests the Student object. 
 * @author Sarah Heckman
 * @author Audrey Fuelleman
 */
public class StudentTest {
	
	/** Test Student's first name. */
	private String firstName = "first";
	/** Test Student's last name */
	private String lastName = "last";
	/** Test Student's id */
	private String id = "flast";
	/** Test Student's email */
	private String email = "first_last@ncsu.edu";
	/** Invalid Student emails */
	private String[] invalidEmails = {null, "", "emailncsu.edu", "email@ncsuedu", "wolf.pack@ncsuedu"};
	/** Test Student's hashed password */
	private String hashPW;
	/** Test Student's max credits */
	private int maxCredits = 15;
	/** Tests values for maxCredits boundaries */
	private int[] maxCreditsBoundaryVals = {3, 2, 18, 19};
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	//This is a block of code that is executed when the StudentTest object is
	//created by JUnit.  Since we only need to generate the hashed version
	//of the plaintext password once, we want to create it as the StudentTest object is
	//constructed.  By automating the hash of the plaintext password, we are
	//not tied to a specific hash implementation.  We can change the algorithm
	//easily.
	{
		try {
			String plaintextPW = "password";
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(plaintextPW.getBytes());
			this.hashPW = new String(digest.digest());
		} catch (NoSuchAlgorithmException e) {
			fail("An unexpected NoSuchAlgorithmException was thrown.");
		}
	}
	
	/**
	 * Test getEmail() method.
	 */
	@Test
	public void testGetEmail() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		assertEquals(email, s1.getEmail());
	}
	
	/**
	 * Test setEmail() method.
	 */
	@Test
	public void testSetEmail() {

		// Test valid email
		assertDoesNotThrow(
				() -> new Student(firstName, lastName, id, email, hashPW, maxCredits),
				"Should not throw exception.");
		
		// Test invalid emails
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, invalidEmails[0], hashPW, maxCredits));
		assertEquals("Invalid email", e1.getMessage());
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, invalidEmails[1], hashPW, maxCredits));
		assertEquals("Invalid email", e2.getMessage());
		
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, invalidEmails[2], hashPW, maxCredits));
		assertEquals("Invalid email", e3.getMessage());
		
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, invalidEmails[3], hashPW, maxCredits));
		assertEquals("Invalid email", e4.getMessage());
		
		Exception e5 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, invalidEmails[4], hashPW, maxCredits));
		assertEquals("Invalid email", e5.getMessage());
	}
	
	/**
	 * Test getPassword() method.
	 */
	@Test
	public void testGetPassword() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		assertEquals(hashPW, s1.getPassword());
	}
	
	/**
	 * Test setPassword() method.
	 */
	@Test
	public void testSetPassword() {

		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, null, maxCredits));
		assertEquals("Invalid password", e1.getMessage());
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, "", maxCredits));
		assertEquals("Invalid password", e2.getMessage());

	}
	
	/**
	 * Test getMaxCredits() method.
	 */
	@Test
	public void testGetMaxCredits() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW, maxCredits);
		assertEquals(maxCredits, s1.getMaxCredits());
	
	}
	
	/**
	 * Test setMaxCredits() method.
	 */
	@Test
	public void testSetMaxCredits() {

		// Test lower bound for maxCredits field
		Student s1 = new Student(firstName, lastName, id, email, hashPW, maxCreditsBoundaryVals[0]);
		assertEquals(maxCreditsBoundaryVals[0], s1.getMaxCredits());
		
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, hashPW, maxCreditsBoundaryVals[1]));
		assertEquals("Invalid max credits", e1.getMessage());

		// Test upper bound for maxCredits field
		Student s2 = new Student(firstName, lastName, id, email, hashPW, maxCreditsBoundaryVals[2]);
		assertEquals(maxCreditsBoundaryVals[2], s2.getMaxCredits());
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, hashPW, maxCreditsBoundaryVals[3]));
		assertEquals("Invalid max credits", e2.getMessage());
	}
	
	/**
	 * Test getFirstName() method.
	 */
	@Test
	public void testGetFirstName() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		assertEquals(firstName, s1.getFirstName());
	}
	
	/**
	 * Test setFirstName() method.
	 */
	@Test
	public void testSetFirstName() {

		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(null, lastName, id, email, hashPW, maxCredits));
		assertEquals("Invalid first name", e1.getMessage());
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student("", lastName, id, email, hashPW, maxCredits));
		assertEquals("Invalid first name", e2.getMessage());

	}
	
	/**
	 * Test getLastName() method.
	 */
	@Test
	public void testGetLastName() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		assertEquals(lastName, s1.getLastName());
	}
	
	/**
	 * Test setLastName() method.
	 */
	@Test
	public void testSetLastName() {

		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, null, id, email, hashPW, maxCredits));
		assertEquals("Invalid last name", e1.getMessage());
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, "", id, email, hashPW, maxCredits));
		assertEquals("Invalid last name", e2.getMessage());

	}
	
	/**
	 * Test getId() method.
	 */
	@Test
	public void testGetId() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		assertEquals(id, s1.getId());
	}
	
	/**
	 * Test setId() method.
	 */
	@Test
	public void testSetId() {

		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, null, email, hashPW, maxCredits));
		assertEquals("Invalid id", e1.getMessage());
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, "", email, hashPW, maxCredits));
		assertEquals("Invalid id", e2.getMessage());

	}
	
	/**
	 * Test setEquals() method.
	 */
	@Test
	public void testEquals() {
		// Create test students
		Student s1 = new Student(firstName, lastName, id, email, hashPW, maxCredits);
		Student s2 = new Student(firstName, lastName, id, email, hashPW, maxCredits);
		Student s3 = new Student("Different", lastName, id, email, hashPW, maxCredits);
		Student s4 = new Student(firstName, "Different", id, email, hashPW, maxCredits);
		Student s5 = new Student(firstName, lastName, "Different", email, hashPW, maxCredits);
		Student s6 = new Student(firstName, lastName, id, "different@ncsu.edu", hashPW, maxCredits);
		Student s7 = new Student(firstName, lastName, id, email, "Different", maxCredits);
		Student s8 = new Student(firstName, lastName, id, email, hashPW, 10);
		
		assertEquals(this, this);
		
		// Test equality in both directions
		assertTrue(s1.equals(s2));
		assertTrue(s2.equals(s1));
		
		// Test a null student
		Student nullStudent = null;
		assertFalse(s1.equals(nullStudent));
		
		// Test for non-Student object
		String notStudent = "student";
		assertFalse(s1.equals(notStudent));
		
		// Test for each of the fields
		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));
		
	}
	
	/**
	 * Tests that hashCode works correctly.
	 */
	@Test
	public void testHashCode() {
		// Create test students
		Student s1 = new Student(firstName, lastName, id, email, hashPW, maxCredits);
		Student s2 = new Student(firstName, lastName, id, email, hashPW, maxCredits);
		Student s3 = new Student("Different", lastName, id, email, hashPW, maxCredits);
		Student s4 = new Student(firstName, "Different", id, email, hashPW, maxCredits);
		Student s5 = new Student(firstName, lastName, "Different", email, hashPW, maxCredits);
		Student s6 = new Student(firstName, lastName, id, "different@ncsu.edu", hashPW, maxCredits);
		Student s7 = new Student(firstName, lastName, id, email, "Different", maxCredits);
		Student s8 = new Student(firstName, lastName, id, email, hashPW, 10);


		// Test for the same hash code for the same values
		assertEquals(s1.hashCode(), s2.hashCode());

		// Test for each of the fields
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
		assertNotEquals(s1.hashCode(), s8.hashCode());

	}
	
	/**
	 * Test toString() method.
	 */
	@Test
	public void testToString() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		assertEquals("first,last,flast,first_last@ncsu.edu," + hashPW + ",18", s1.toString());
	}

	/**
	 * Test compareTo() method with Student objects for order by
	 * last name, first name, and then unity id
	 */
	@Test
	public void testCompareTo() {
		Student s1 = new Student("Abbey", "John", "ajohn", email, hashPW);
		Student s2 = new Student("Abby", "John", "ajohn", email, hashPW);
		Student s3 = new Student("Abby", "Joe", "ajoe", email, hashPW);
		Student s4 = new Student("Abby", "Johnson", "ajohnson", email, hashPW);
		Student s5 = new Student("Roger", "Rabbit", "rrabbit", email, hashPW);
		Student s6 = new Student("Rogerette", "Rabbit", "rrabbit", email, hashPW);
		Student s7 = new Student("John", "Smith", "jsmith", email, hashPW);
		Student s8 = new Student("John", "Smith", "jsmith2", email, hashPW);
		Student s9 = new Student("John", "Smith", "jsmitty", email, hashPW);
		Student s10 = new Student("Demetrius", "Austin", "daustin", "daustin@ncsu.edu", "XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg=");
		Student s11 = new Student("Dylan", "Austin", "dausti4", "dausti4@ncsu.edu", "XohImNooBHFR0OVvjcYpJ3NgPQ1qq73WKhHvch0VQtg=");
		
		assertTrue(s10.compareTo(s11) < 0);
		assertTrue(s1.compareTo(s1) == 0 && s1.equals(s1));
		assertTrue(s1.compareTo(s2) < 0);
		assertTrue(s2.compareTo(s1) > 0);
		assertTrue(s3.compareTo(s2) < 0);
		assertTrue(s2.compareTo(s3) > 0);
		assertTrue(s2.compareTo(s4) < 0);
		assertTrue(s4.compareTo(s2) > 0);
		assertTrue(s5.compareTo(s6) < 0);
		assertTrue(s6.compareTo(s5) > 0);
		assertTrue(s7.compareTo(s8) < 0);
		assertTrue(s8.compareTo(s7) > 0);
		assertTrue(s7.compareTo(s9) < 0);
		assertTrue(s9.compareTo(s7) > 0);
		

		assertThrows(NullPointerException.class, () -> s1.compareTo(null));
		
	}
	
}