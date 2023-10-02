package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;

/**
 * the registration manager class is capable of ensuring users are met with their class needs
 * @author Duncan
 */
public class RegistrationManager {

	/**Instance of a registration manager	 */
	private static RegistrationManager instance;	
	/** The course catalog object used within the class*/
	private CourseCatalog courseCatalog;
	/** the student directory object used in this class*/
	private StudentDirectory studentDirectory;
	/** The single instance of the registrar user*/
	private User registrar;
	/** the current user in the class*/
	private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/** the file being used for the class*/
	private static final String PROP_FILE = "registrar.properties";

	/**
	 * the constructor for the registration manager class
	 */
	private RegistrationManager() {
		RegistrationManager manager = RegistrationManager.getInstance();
		createRegistrar();
	}

	/**
	 * creates a registrar from the file being taken in as a field
	 */
	private void createRegistrar() {
		Properties prop = new Properties();

		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);

			String hashPW = hashPW(prop.getProperty("pw"));

			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"),
					prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}

	/**
	 * this method encrypts the student's password
	 * @param pw the password to be encrypted
	 * @return the encrypted password
	 * @throws IllegalArgumentException if the hashing algorithm does not exist
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return Base64.getEncoder().encodeToString(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

	/**
	 * returns the current instance of the registrar manager
	 * @return instance field of the registrar manager
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}

	/**
	 * gets the current course catalog
	 * @return the course catalog
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}

	/**
	 * gets the current student directory 
	 * @return the student directory
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	/**
	 * the method to allow a user to login using their password
	 * @param id id of the user logging in
	 * @param password password of the user logging in
	 * @return true if the user enters the correct password, false if they enter the wrong password
	 */
	public boolean login(String id, String password) {
		Student s = studentDirectory.getStudentById(id);

		String localHashPW = hashPW(password);
		if (s.getPassword().equals(localHashPW)) {
			currentUser = s;
			return true;
		}

		if (registrar.getId().equals(id)) {

			if (registrar.getPassword().equals(localHashPW)) {
				currentUser = registrar;
				return true;
			}
		}

		return false;
	}

	/**
	 * allows the user to log out
	 */
	public void logout() {
		currentUser = registrar;
	}

	/**
	 * gets the current user
	 * @return the current user
	 */ 
	public User getCurrentUser() {
		// TODO implement method
		return null;
	}

	/**
	 * clears the data in the course catalog and student directory
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
	}

	/**
	 * the inner registrar class that will implement a singleton pattern
	 * @author Aubrey
	 */
	private static class Registrar extends User {
		/**
		 * Create a registrar user.
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}
}