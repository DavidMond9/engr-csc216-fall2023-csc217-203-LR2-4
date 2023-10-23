/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * test class for array list class
 * @author Duncan Munro
 */
class ArrayListTest {

	/**
	 * integer size for the array
	 */
	private static final int INIT_SIZE = 10;
	
	/** value for size of the array */
	private int size;
	
	/**
	 * this will test the array list constructor
	 */
	@Test
	void testArrayList() {
		ArrayList<Integer> list = new ArrayList();
		//Size should be 0 after construction
		assertEquals(0, list.size());
		}

	/**
	 * this will test the array list add method
	 */
	@Test
	void testAdd() {
		ArrayList<Integer> list = new ArrayList();
		//Invalid checks
		//null pointer check
		Exception e1 = assertThrows(NullPointerException.class, 
						() -> list.add(4, null));
		assertEquals("Element cannot be null", e1.getMessage(), "checking that a null pointer exception is thrown when a null element is added");
		//duplicate element
		list.add(1, 66);
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> list.add(3, 66));
		assertEquals("Duplicate element", e2.getMessage(), "checking that a duplicate element cannot be added");
		//index out of range
		Exception e3 = assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 33));
		assertEquals("Out of bounds", e3.getMessage(), "checking that out of bounds exception is thrown for a negative index");
		Exception e4 = assertThrows(IndexOutOfBoundsException.class, () -> list.add(1000, 33333));
		assertEquals("Out of bounds", e4.getMessage(), "checking that out of bounds exception is thrown for a negative index");

				
				
	}
	
	
	
}
