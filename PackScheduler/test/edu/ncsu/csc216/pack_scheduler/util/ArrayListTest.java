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
		assertEquals("Size should be 0 after construction", 0, list.getSize());
		}

	/**
	 * this will test the array list add method
	 */
	@Test
	void testAdd() {
		ArrayList<Integer> list = new ArrayList();
		//valid checks
		list.add(0, 1);
		assertEquals(11, list.size(), "Size should be 1 after adding more elements");
		assertEquals(Integer.valueOf(1), list.get(0), "check to make sure it is being added correctly");
		// Test adding more elements to trigger array growth
        for (int i = 2; i < 11; i++) {
            list.add(i - 1, i);
        }
        assertEquals(20, list.size(), "check to see if arraylist doubles when a tenth integer is added");
		
		
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
	
	/**
	 * this will test the set method
	 */
	@Test void testSet() {
	ArrayList<Integer> list = new ArrayList<>();
    
    // Add some elements to the list
    for (int i = 0; i < 5; i++) {
        list.add(i, i + 1);
    }
    
    // Test setting elements
    assertEquals(Integer.valueOf(1), list.set(0, 6), "Original element should be returned");
    assertEquals(Integer.valueOf(6), list.get(0), "New element should be at index 0");
	
    
    //invalid checks
    
	}

}
