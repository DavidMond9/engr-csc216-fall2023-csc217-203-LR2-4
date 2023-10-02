package edu.ncsu.csc216.pack_scheduler.user;

import java.util.Objects;

/**
 * Class to create a Student object
 * 
 * @author Audrey Fuelleman
 */
public class Student extends User implements Comparable<Student> {
	
	/** Absolute maximum number of credits a student can take */
	public static final int MAX_CREDITS = 18;
	/** Student's maximum number of credits */
	private int maxCredits = MAX_CREDITS;
	
	/**
	 * Constructs a student object with fields for first name, last name, id, email, hash password, and max credits
	 * 
	 * @param firstName the student's first name
	 * @param lastName the student's last name
	 * @param id the student's id
	 * @param email the student's email
	 * @param hashPW the student's password in hash code
	 * @param maxCredits the student's max number of credits
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) {
		
		super();
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(hashPW);
		setMaxCredits(maxCredits);
		
	}
	
	/**
	 * Constructs a student object with fields for first name, last name, id, email, and hash password
	 * 
	 * @param firstName the student's first name
	 * @param lastName the student's last name
	 * @param id the student's id
	 * @param email the student's email
	 * @param hashPW the student's password in hash code
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW) {
		this(firstName, lastName, id, email, hashPW, MAX_CREDITS);
	}
	
	/**
	 * Returns a student's maximum number of credits
	 * 
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets a student's maximum number of credits
	 * 
	 * @param maxCredits the maxCredits to set
	 * @throws IllegalArgumentException if maxCredits is not between 3 and 18, inclusive
	 */
	public void setMaxCredits(int maxCredits) {
		
		if (maxCredits < 3 || maxCredits > MAX_CREDITS) {
			
			throw new IllegalArgumentException("Invalid max credits");
			
		}
		this.maxCredits = maxCredits;
		
	}




	/**
	 * Returns a comma separated value String of all Student fields
	 * 
	 * @return String student fields
	 */
	@Override
	public String toString() {
		
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + "," + maxCredits;
		
	}

	/**
	 * Compares this student object to another student object
	 * for order by last name, first name, and then unity id
	 * 
	 * @param s the student to compare this student to
	 * @return compare a negative integer, zero, or a positive integer if this object is less than, equal to, or greater than the specified object
	 */
	@Override
	public int compareTo(Student s) {
		// Initialize return value
		int compare = 0;
		
		// Get length of Students' last names, first names, and unity ids
		int s1LastNameLength = this.getLastName().length();
		int s2LastNameLength = s.getLastName().length();
		int lastNameLength;
		int s1FirstNameLength = this.getFirstName().length();
		int s2FirstNameLength = s.getFirstName().length();
		int firstNameLength;
		int s1IdLength = this.getId().length();
		int s2IdLength = s.getId().length();
		int idLength;
		
		//	Use the last name of shorter length for the loop
		if (s1LastNameLength < s2LastNameLength) {
			
			lastNameLength = s1LastNameLength;
			
		} else {
			
			lastNameLength = s2LastNameLength;
			
		}
		
		// Compare characters in Students' last names
		for (int i = 0; i < lastNameLength; i++) {
			
			// Check if the character at index i of this Student's last name is
			// less than the character at index i of the other Student's last name
			if (this.getLastName().charAt(i) < s.getLastName().charAt(i)) {
				
				// This student is less than the other student in order
				compare = -1;
				break;
				
			} else if (this.getLastName().charAt(i) == s.getLastName().charAt(i)) {
				
				// If not on the last iteration of the loop
				if (i != lastNameLength - 1) {
					
					// Continue comparing last names if the current characters are equal
					continue;
					
				// Otherwise check if one last name is longer than the other
				} else {
					
					if (s1LastNameLength < s2LastNameLength) {
						
						// This student is less than the other student in order
						compare = -1;
						break;
						
					} else if (s1LastNameLength > s2LastNameLength) {
						
						// This student is greater than the other student in order
						compare = 1;
						break;
						
					} else {
						
						// Last names are the same, move on to checking first names
						break;
						
					}
					
				}
	
			} else {
				
				// The other student is less than this student in order
				compare = 1;
				break;
				
			}
			
		}
		
		// If last names are equal, compare first names
		if (compare == 0) {
			
			//	Use the first name of shorter length for the loop
			if (s1FirstNameLength < s2FirstNameLength) {
				
				firstNameLength = s1FirstNameLength;
				
			} else {
				
				firstNameLength = s2FirstNameLength;
				
			}
			
			// Compare characters in Students' first names
			for (int i = 0; i < firstNameLength; i++) {
				
				// Check if the character at index i of this Student's first name is
				// less than the character at index i of the other Student's first name
				if (this.getFirstName().charAt(i) < s.getFirstName().charAt(i)) {
					
					// This student is less than the other student in order
					compare = -1;
					break;
					
				} else if (this.getFirstName().charAt(i) == s.getFirstName().charAt(i)) {
					
					// If not on the last iteration of the loop
					if (i != firstNameLength - 1) {
						
						// Continue comparing first names if the current characters are equal
						continue;
						
					// Otherwise check if one first name is longer than the other
					} else {
						
						if (s1FirstNameLength < s2FirstNameLength) {
							
							// This student is less than the other student in order
							compare = -1;
							break;
							
						} else if (s1FirstNameLength > s2FirstNameLength) {
							
							// This student is greater than the other student in order
							compare = 1;
							break;
							
						} else {
							
							// first names are the same, move on to checking unity ids
							break;
							
						}
						
					}
		
				} else {
					
					// The other student is less than this student in order
					compare = 1;
					break;
					
				}
				
			}
			
		}
		
		// If last names and first names are equals, compare unity ids
		if (compare == 0) {
			
			// Use the id of shorter length for the loop
			if (s1IdLength < s2IdLength) {
						
				idLength = s1IdLength;
						
			} else {
						
				idLength = s2IdLength;
						
			}
					
			// Compare characters in Students' ids
			for (int i = 0; i < idLength; i++) {
						
				// Check if the character at index i of this Student's id is
				// less than the character at index i of the other Student's id
				if (this.getId().charAt(i) < s.getId().charAt(i)) {
							
					// This student is less than the other student in order
					compare = -1;
					break;
							
				} else if (this.getId().charAt(i) == s.getId().charAt(i)) {
							
					// If not on the last iteration of the loop
					if (i != idLength - 1) {
								
						// Continue comparing ids if the current characters are equal
						continue;
								
					// Otherwise check if one id is longer than the other
					} else {
								
						if (s1IdLength < s2IdLength) {
									
							// This student is less than the other student in order
							compare = -1;
							break;
									
						} else if (s1IdLength > s2IdLength) {
									
							// This student is greater than the other student in order
							compare = 1;
							break;
									
						} else {
									
							// unity ids are the same, Students are equal
							break;
									
						}
								
					}
				
				} else {
							
					// The other student is less than this student in order
					compare = 1;
					break;
							
				}
						
			}
					
		}

		// Return negative integer if this student is less than the other student
		// Return zero if this student is equal to the other student
		// Return positive integer if this student is greater than the other student
		return compare;
	}

	
}
