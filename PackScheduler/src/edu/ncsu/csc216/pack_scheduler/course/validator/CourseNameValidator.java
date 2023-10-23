package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Checking whether a Course's Name is valid.
 * 
 * @author David Mond
 * @author Audrey Fuelleman
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
	/** The initial state for a character in the Course's name */
	private InitialState stateInitial = new InitialState();
	/** The letter state for a character in the Course's name */
	private LetterState stateLetter = new LetterState();
	/** The digit state for a character in the Course's name */
	private DigitState stateDigit = new DigitState();
	/** The suffix state for a character in the Course's name */
	private SuffixState stateSuffix = new SuffixState();
	/**
	 * Field to keep track if end is valid.
	 */
	private boolean validEndState = false;

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
					stateSuffix.onOther();
				}
			}
		}
		return validEndState;
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
		 * @throws InvalidTransitionException throws exception for the next character being invalid.
		 */
		public abstract void onLetter() throws InvalidTransitionException;

		/**
		 * Checks to see if char is a digit.
		 * @throws InvalidTransitionException throws exception for the next character being invalid.
		 */
		public abstract void onDigit() throws InvalidTransitionException;

		/**
		 * If not digit or letter than onOther.
		 * 
		 * @throws InvalidTransitionException throws exception for the next character being invalid.
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Invalid transition.");
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
		 * @throws InvalidTransitionException throws exception for the next character being invalid.
		 */
		public void onDigit() throws InvalidTransitionException {
			currentState = "D";
			//throw new InvalidTransitionException("Invalid transition.");
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
				onOther(); //throw new InvalidTransitionException("Invalid FSM transition.");
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
			if (letterCount == 0) {
				onOther(); //throw new InvalidTransitionException("Invalid FSM transition.");
			}
			digitCount++;
			currentState = "D";
		}
	}
	/**
	 * DigitState class, third state, extends state.
	 */
	public class DigitState extends State {
		/**
		 * Constructor for DigitState.
		 */
		private DigitState() {
			
		}
		/**
		 * Checks to see if char is a letter.
		 * @throws InvalidTransitionException throws exception for the next character being invalid.
		 */
		public void onLetter() throws InvalidTransitionException {
			if(digitCount == 3) {
				currentState = "S";
				validEndState = true;
			}
			else {
				onOther(); //throw new InvalidTransitionException("Invalid transition.");
			}
		}
		/**
		 * Checks to see if char is a digit.
		 * @throws InvalidTransitionException throws exception for the next character being invalid.
		 */
		public void onDigit() throws InvalidTransitionException {
			if(digitCount == 3 || letterCount == 0) {
				onOther(); //throw new InvalidTransitionException("Invalid transition.");
			}
			digitCount += 1;
			if (digitCount == 3) {
				validEndState = true;
			}
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
		 *@throws InvalidTransitionException throws exception if there is another character.
		 */
		public void onLetter() throws InvalidTransitionException {
			onOther();
		}
		/**
		 * Checks to see if char is a digit.
		 * @throws InvalidTransitionException throws exception if there is another character.
		 */
		public void onDigit() throws InvalidTransitionException {
			onOther();
		}
	}
}
	
