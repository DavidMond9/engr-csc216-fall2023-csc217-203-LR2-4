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
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", "A");
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 1330, 1445);
	    
	    assertDoesNotThrow(() -> a1.checkConflict(a2));
	    assertDoesNotThrow(() -> a2.checkConflict(a1));

	    Activity a3 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
	    Activity a4 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 1330, 1445);
	    
	    assertDoesNotThrow(() -> a3.checkConflict(a4));
	    assertDoesNotThrow(() -> a4.checkConflict(a3));
	    
	    Activity a5 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1230, 1320);
	    Activity a6 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1330, 1445);
	    
	    assertDoesNotThrow(() -> a5.checkConflict(a6));
	    assertDoesNotThrow(() -> a6.checkConflict(a5));
	    
	    Activity a7 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", "A");
	    Activity a8 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", "A");
	    
	    assertDoesNotThrow(() -> a7.checkConflict(a8));
	    assertDoesNotThrow(() -> a8.checkConflict(a7));
	}
	
	/**
	 * Tests checkConflict method with conflicting activities.
	 * @throws InvalidTransitionException if invalid format
	 */
	@Test
	public void testCheckConflictWithConflict() throws InvalidTransitionException {
	    Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1330, 1445);
		
	    Exception e1 = assertThrows(InvalidTransitionException.class, () -> a1.checkConflict(a2));
	    assertEquals("Schedule conflict.", e1.getMessage());
		
	    Exception e2 = assertThrows(InvalidTransitionException.class, () -> a2.checkConflict(a1));
	    assertEquals("Schedule conflict.", e2.getMessage());
	    
	    Activity a3 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1230, 1330);
	    Activity a4 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", "W", 1330, 1445);
		
	    Exception e3 = assertThrows(InvalidTransitionException.class, () -> a3.checkConflict(a4));
	    assertEquals("Schedule conflict.", e3.getMessage());
		
	    Exception e4 = assertThrows(InvalidTransitionException.class, () -> a4.checkConflict(a3));
	    assertEquals("Schedule conflict.", e4.getMessage());
	    
	    Activity a5 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1445, 1550);
	    Activity a6 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1330, 1445);
		
	    Exception e5 = assertThrows(InvalidTransitionException.class, () -> a5.checkConflict(a6));
	    assertEquals("Schedule conflict.", e5.getMessage());
		
	    Exception e6 = assertThrows(InvalidTransitionException.class, () -> a6.checkConflict(a5));
	    assertEquals("Schedule conflict.", e6.getMessage());
	    
	    Activity a7 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1230, 1445);
	    Activity a8 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1330, 1445);
		
	    Exception e7 = assertThrows(InvalidTransitionException.class, () -> a7.checkConflict(a8));
	    assertEquals("Schedule conflict.", e7.getMessage());
		
	    Exception e8 = assertThrows(InvalidTransitionException.class, () -> a8.checkConflict(a7));
	    assertEquals("Schedule conflict.", e8.getMessage());
	}

}
