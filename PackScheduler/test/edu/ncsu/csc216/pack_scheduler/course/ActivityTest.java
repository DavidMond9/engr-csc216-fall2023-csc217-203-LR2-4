/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Tests functionality of Activity class that is not covered with other tests.
 * Specifically tests checkConflict method
 * 
 * @author - Sam McDonald
 */
class ActivityTest {

	/**
	 * Tests checkConflict method with non-conflicting activities.
	 * @throws InvalidTransitionException if invalid format
	 */
	@Test
	public void testCheckConflict() throws InvalidTransitionException {
<<<<<<< HEAD
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "A");
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "TH", 1330, 1445);
=======
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 15, "A");
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 15, "TH", 1330, 1445);
>>>>>>> branch 'main' of git@github.ncsu.edu:engr-csc216-fall2023/csc217-203-LR2-4.git
	    
	    assertDoesNotThrow(() -> a1.checkConflict(a2));
	    assertDoesNotThrow(() -> a2.checkConflict(a1));

<<<<<<< HEAD
	    Activity a3 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
	    Activity a4 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "TH", 1330, 1445);
=======
	    Activity a3 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 15, "MW", 1330, 1445);
	    Activity a4 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 15, "TH", 1330, 1445);
>>>>>>> branch 'main' of git@github.ncsu.edu:engr-csc216-fall2023/csc217-203-LR2-4.git
	    
	    assertDoesNotThrow(() -> a3.checkConflict(a4));
	    assertDoesNotThrow(() -> a4.checkConflict(a3));
	    
<<<<<<< HEAD
	    Activity a5 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1230, 1320);
	    Activity a6 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10,"M", 1330, 1445);
=======
	    Activity a5 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 15, "MW", 1230, 1320);
	    Activity a6 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 15, "M", 1330, 1445);
>>>>>>> branch 'main' of git@github.ncsu.edu:engr-csc216-fall2023/csc217-203-LR2-4.git
	    
	    assertDoesNotThrow(() -> a5.checkConflict(a6));
	    assertDoesNotThrow(() -> a6.checkConflict(a5));
	    
<<<<<<< HEAD
	    Activity a7 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "A");
	    Activity a8 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "A");
=======
	    Activity a7 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 15, "A");
	    Activity a8 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 15, "A");
>>>>>>> branch 'main' of git@github.ncsu.edu:engr-csc216-fall2023/csc217-203-LR2-4.git
	    
	    assertDoesNotThrow(() -> a7.checkConflict(a8));
	    assertDoesNotThrow(() -> a8.checkConflict(a7));
	}
	
	/**
	 * Tests checkConflict method with conflicting activities.
	 * @throws InvalidTransitionException if invalid format
	 */
	@Test
	public void testCheckConflictWithConflict() throws InvalidTransitionException {
<<<<<<< HEAD
	    Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "M", 1330, 1445);
=======
	    Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 15, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 15, "M", 1330, 1445);
>>>>>>> branch 'main' of git@github.ncsu.edu:engr-csc216-fall2023/csc217-203-LR2-4.git
		
	    Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
	    assertEquals("Schedule conflict.", e1.getMessage());
		
	    Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
	    assertEquals("Schedule conflict.", e2.getMessage());
	    
<<<<<<< HEAD
	    Activity a3 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1230, 1330);
	    Activity a4 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "W", 1330, 1445);
=======
	    Activity a3 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 15, "MW", 1230, 1330);
	    Activity a4 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 15, "W", 1330, 1445);
>>>>>>> branch 'main' of git@github.ncsu.edu:engr-csc216-fall2023/csc217-203-LR2-4.git
		
	    Exception e3 = assertThrows(ConflictException.class, () -> a3.checkConflict(a4));
	    assertEquals("Schedule conflict.", e3.getMessage());
		
	    Exception e4 = assertThrows(ConflictException.class, () -> a4.checkConflict(a3));
	    assertEquals("Schedule conflict.", e4.getMessage());
	    
<<<<<<< HEAD
	    Activity a5 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1445, 1550);
	    Activity a6 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "M", 1330, 1445);
=======
	    Activity a5 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 15, "MW", 1445, 1550);
	    Activity a6 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 15, "M", 1330, 1445);
>>>>>>> branch 'main' of git@github.ncsu.edu:engr-csc216-fall2023/csc217-203-LR2-4.git
		
	    Exception e5 = assertThrows(ConflictException.class, () -> a5.checkConflict(a6));
	    assertEquals("Schedule conflict.", e5.getMessage());
		
	    Exception e6 = assertThrows(ConflictException.class, () -> a6.checkConflict(a5));
	    assertEquals("Schedule conflict.", e6.getMessage());
	    
<<<<<<< HEAD
	    Activity a7 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1230, 1445);
	    Activity a8 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "M", 1330, 1445);
=======
	    Activity a7 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 15, "MW", 1230, 1445);
	    Activity a8 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 15, "M", 1330, 1445);
>>>>>>> branch 'main' of git@github.ncsu.edu:engr-csc216-fall2023/csc217-203-LR2-4.git
		
	    Exception e7 = assertThrows(ConflictException.class, () -> a7.checkConflict(a8));
	    assertEquals("Schedule conflict.", e7.getMessage());
		
	    Exception e8 = assertThrows(ConflictException.class, () -> a8.checkConflict(a7));
	    assertEquals("Schedule conflict.", e8.getMessage());
	}

}
