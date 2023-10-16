package edu.ncsu.csc216.pack_scheduler.course.validator;

public class CourseNameValidator {
	
	private int letterCount = 0;
	private int digitCount = 0;
	
	public boolean isValid(String a) {
		return false;
	}
	
	public abstract class State {
		
		public abstract boolean onLetter();
		public abstract boolean onDigit();
		public void onOther() {
			throw new IllegalArgumentException("Course name can only contain letters and digits.");
		}
		
	}
	
	public class InitialState {
		public boolean onLetter() {
			return false;
		}
		public boolean onDigit() {
			return false;
		}
	}
	public class LetterState {
		public boolean onLetter() {
			return false;
		}
		public boolean onDigit() {
			return false;
		}
	}
	public class DigitState {
		public boolean onLetter() {
			return false;
		}
		public boolean onDigit() {
			return false;
		}
	}
	public class SuffixState {
		public boolean onLetter() {
			return false;
		}
		public boolean onDigit() {
			return false;
		}
	}
}
