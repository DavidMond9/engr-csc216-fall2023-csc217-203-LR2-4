package edu.ncsu.csc216.pack_scheduler.user;

<<<<<<< HEAD
/**
 * User class represents a user who has a first name, last name,
 * id, email, and hashPW.
 */
=======
>>>>>>> branch 'main' of https://github.ncsu.edu/engr-csc216-fall2023/csc217-203-LR2-4.git
public abstract class User {

	/** Student first name */
	private String firstName;
	/** Student last name */
	private String lastName;
	/** Student id */
	private String id;
	/** Student email */
	private String email;
	/** Student password in hash code */
	private String hashPW;


	
	/**
	 * Constructor to create a User in the User class.
	 * @param firstName Represents the user's first name.
	 * @param lastName Represents the user's last name.
	 * @param id Represents the user's id.
	 * @param email Represents the user's email.
	 * @param hashPW Represents the user's hash password.
	 */
	public User(String firstName, String lastName, String id, String email, String hashPW) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.email = email;
		this.hashPW = hashPW;
	}
	
	/**
	 * Returns the student's email
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the student's email
	 * 
	 * @param email the email to set
	 * @throws IllegalArgumentException if email is null or empty or an invalid format
	 */
	public void setEmail(String email) {
		if (email == null || "".equals(email)) {
			
			throw new IllegalArgumentException("Invalid email");
			
		} 
		
		if (!email.contains("@") || !email.contains(".")) {
			
			throw new IllegalArgumentException("Invalid email");
			
		} 
		
		if (email.lastIndexOf(".") < email.indexOf("@")) {
			
			throw new IllegalArgumentException("Invalid email");
			
		}
		
		this.email = email;
	}

	/**
	 * Returns the hash password
	 * 
	 * @return the hashPW
	 */
	public String getPassword() {
		return hashPW;
	}

	/**
	 * Sets the student's password in hash code
	 * 
	 * @param hashPW the hashPW to set
	 * @throws IllegalArgumentException if hashPW is null or empty
	 */
	public void setPassword(String hashPW) {
		if (hashPW == null || "".equals(hashPW)) {
			
			throw new IllegalArgumentException("Invalid password");
			
		} 
		this.hashPW = hashPW;
	}

	/**
	 * Returns the student's first name
	 * 
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the student's first name
	 * 
	 * @param firstName the firstName to set
	 * @throws IllegalArgumentException if firstName is null or empty
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || "".equals(firstName)) {
			
			throw new IllegalArgumentException("Invalid first name");
			
		} 
		
		this.firstName = firstName;
	}

	/**
	 * Returns the student's last name
	 * 
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the student's last name
	 * 
	 * @param lastName the lastName to set
	 * @throws IllegalArgumentException if lastName is null or empty
	 */
	public void setLastName(String lastName) {
		if (lastName == null || "".equals(lastName)) {
			
			throw new IllegalArgumentException("Invalid last name");
			
		} 
		this.lastName = lastName;
	}

	/**
	 * Returns the student's id
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the student's id
	 * 
	 * @param id the id to set
	 * @throws IllegalArgumentException if id is null or empty
	 */
	protected void setId(String id) {
		if (id == null || "".equals(id)) {
			
			throw new IllegalArgumentException("Invalid id");
			
		} 
		
		this.id = id;
	}

	/**
	 * the hashcode method encrpyts the password
	 * @return the encoded integer value
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((hashPW == null) ? 0 : hashPW.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	/**
	 * this creates a method to compare to objects and see if they are the same
	 * @param obj the object this instance of a user is being compared to
	 * @return true if the two objects are not the same, false if they are identical
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (hashPW == null) {
			if (other.hashPW != null)
				return false;
		} else if (!hashPW.equals(other.hashPW))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
	
	

}