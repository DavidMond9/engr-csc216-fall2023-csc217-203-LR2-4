package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Checking whether a Course's Name is valid.
 * 
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
	private String currentState = "I";
	private InitialState stateInitial;
	private LetterState stateLetter;
	private DigitState stateDigit;
	private SuffixState stateSuffix;
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
	 * 
	 * @param name Name input.
	 * @return Boolean that represents if the name is valid or not.
	 * @throws InvalidTransitionException throws an exception if invalid input.
	 */
	public boolean isValid(String name) throws InvalidTransitionException {
		for (int i = 0; i < name.length(); i++) {
			if ("I".equals(currentState)) {
				if (Character.isLetter(name.charAt(i))) {
					stateInitial.onLetter();
				} else if (Character.isDigit(name.charAt(i))) {
					stateInitial.onDigit();
				} else {
					stateInitial.onOther();
				}
			} else if ("L".equals(currentState)) {
				if (Character.isLetter(name.charAt(i))) {
					stateLetter.onLetter();
				} else if (Character.isDigit(name.charAt(i))) {
					stateLetter.onDigit();
				} else {
					stateLetter.onOther();
				}
			} else if ("D".equals(currentState)) {
				if (Character.isLetter(name.charAt(i))) {
					stateDigit.onLetter();
				} else if (Character.isDigit(name.charAt(i))) {
					stateDigit.onDigit();
				} else {
					stateDigit.onOther();
				}
			} else if ("S".equals(currentState)) {
				if (Character.isLetter(name.charAt(i))) {
					stateSuffix.onLetter();
				} else if (Character.isDigit(name.charAt(i))) {
					stateSuffix.onDigit();
				} else {
					State.onOther();
				}
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
		 * 
		 * @return boolean which represents if letter or not.
		 * @throws InvalidTransitionException
		 */
		public abstract void onLetter() throws InvalidTransitionException;

		/**
		 * Checks to see if char is a digit.
		 * 
		 * @return boolean which represents if digit or not.
		 * @throws InvalidTransitionException
		 */
		public abstract void onDigit() throws InvalidTransitionException;

		/**
		 * If not digit or letter than onOther.
		 * 
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
		 */
		public void onLetter() {
			currentState = "L";
			letterCount += 1;
		}

		/**
		 * Checks to see if char is a digit.
		 * 
		 * @throws InvalidTransitionException
		 */
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");
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
		 * if the letter count is equal to 4 it transitions to the NumberState
		 * @throws InvalidTransitionException if letter count is equal to 0 or greater than 5
		 */
		@Override
		public void onLetter() throws InvalidTransitionException{
			letterCount++;
			if (letterCount == 0 || letterCount > MAX_PREFIX_LETTERS) {
				throw new InvalidTransitionException("Invalid FSM transition.");
			}
			if (letterCount == MAX_PREFIX_LETTERS) {
				currentState = "N";
			}
			currentState = "D";
		}

		/**
		 * Checks to see if char is a digit.
		 */
		@Override
		public void onDigit() throws InvalidTransitionException{
			digitCount++;
			if (letterCount == 0) {
				throw new InvalidTransitionException("Invalid FSM transition.");
		}
			currentState = "N";
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
		 * @throws InvalidTransitionException 
		 */
		public void onLetter() throws InvalidTransitionException {
			if(digitCount == 3) {
				currentState = "S";
			}
			else {
				throw new InvalidTransitionException("Invalid transition.");
			}
		}
		/**
		 * Checks to see if char is a digit.
		 * @throws InvalidTransitionException 
		 */
		public void onDigit() throws InvalidTransitionException {
			if(digitCount == 3) {
				throw new InvalidTransitionException("Invalid transition.");
			}
			digitCount += 1;
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
		 */
		public void onLetter() {
			
		}
		/**
		 * Checks to see if char is a digit.
		 */
		public void onDigit() {
			
		}
	}
}
