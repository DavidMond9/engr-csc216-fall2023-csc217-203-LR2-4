package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Checking whether
 * a Course's Name is valid.
 * @author David Mond
 */
public class CourseNameValidator {
	
	/**
	 * Field to keep track of the amount of letters.
	 */
	private int letterCount = 0;
	/**
	 * Field to keep track of the amount of digits.
	 */
	private int digitCount = 0;
	/**
	 * Field to keep track of the current state.
	 */
	private State currentState;
	/**
	 * Field to keep track if end is valid.
	 */
	private boolean validEndState;
	
	/**
	 * Constructor for CourseNameValidator.
	 */
	public CourseNameValidator() {
		
	}
	/**
	 * Checks to see if the name is valid.
	 * @param name Name input.
	 * @return Boolean that represents if the name is valid or not.
	 * @throws InvalidTransitionException throws an exception if invalid input.
	 */
	public boolean isValid(String name) throws InvalidTransitionException {
		for(int i = 0; i < name.length(); i++) {
			
			if(Character.isLetter(name.charAt(i))) {
				currentState.onLetter();
			}
			else if(Character.isDigit(name.charAt(i))) {
				currentState.onDigit();
			}	
			else {
				currentState.onOther();
			}
		}
		return false;
	}
	
	/**
	 * Abstract class state that represents the state that the FSM is in.
	 */
	public abstract class State {
		/**
		 * Constructor for State.
		 */
		public State() {
			
		}
		/**
		 * Checks to see if char is a letter.
		 * @return boolean which represents if letter or not.
		 */
		public abstract boolean onLetter();
		/**
		 * Checks to see if char is a digit.
		 * @return boolean which represents if digit or not.
		 */
		public abstract boolean onDigit();
		/**
		 * If not digit or letter than onOther.
		 * @throws InvalidTransitionException Throws exception for invalid input.
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
		
	}
	
	/**
	 * InitialState class, first state, extends state.
	 */
	public class InitialState extends State {
		/**
		 * Constructor for InitialState.
		 */
		private InitialState() {
			
		}
		/**
		 * Checks to see if char is a letter.
		 * @return boolean which represents if letter or not.
		 */
		public boolean onLetter() {
			return false;
		}
		/**
		 * Checks to see if char is a digit.
		 * @return boolean which represents if digit or not.
		 */
		public boolean onDigit() {
			return false;
		}
	}
	/**
	 * LetterState class, second state, extends state.
	 */
	public class LetterState extends State {
		/**
		 * Constant for max prefix letters.
		 */
		private static final int MAX_PREFIX_LETTERS = 4;
		/**
		 * Constructor for LetterState.
		 */
		private LetterState() {
			
		}
		/**
		 * Checks to see if char is a letter.
		 * @return boolean which represents if letter or not.
		 */
		public boolean onLetter() {
			return false;
		}
		/**
		 * Checks to see if char is a digit.
		 * @return boolean which represents if digit or not.
		 */
		public boolean onDigit() {
			return false;
		}
	}
	/**
	 * DigitState class, third state, extends state.
	 */
	public class DigitState extends State {
		/**
		 * Constant for Course Number Length.
		 */
		private static final int COURSE_NUMBER_LENGTH = 8;
		/**
		 * Constructor for DigitState.
		 */
		private DigitState() {
			
		}
		/**
		 * Checks to see if char is a letter.
		 * @return boolean which represents if letter or not.
		 */
		public boolean onLetter() {
			return false;
		}
		/**
		 * Checks to see if char is a digit.
		 * @return boolean which represents if digit or not.
		 */
		public boolean onDigit() {
			return false;
		}
	}
	/**
	 * SuffixState class, fourth state, extends state.
	 */
	public class SuffixState extends State {
		/**
		 * Constructor for SuffixState.
		 */
		private SuffixState() {
			
		}
		/**
		 * Checks to see if char is a letter.
		 * @return boolean which represents if letter or not.
		 */
		public boolean onLetter() {
			return false;
		}
		/**
		 * Checks to see if char is a digit.
		 * @return boolean which represents if digit or not.
		 */
		public boolean onDigit() {
			return false;
		}
	}
}
