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
	private static final int INT_SIZE = 10;
	
	/** array to hold a list of objects */
	private ArrayList E[];
	
	/** value for size of the array */
	private int size;
	
	/**
	 * this will test the array list constructor
	 */
	@Test
	void testArrayList() {
		ArrayList<Integer> list = new ArrayList();
		assertEquals("Size should be 0 after construction", 0, list.getSize());
		}

}
