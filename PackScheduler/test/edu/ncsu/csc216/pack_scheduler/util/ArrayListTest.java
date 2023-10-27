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
	 * Testing the constructor in ArrayList.
	 */
	@Test
	void testArrayList() {
		ArrayList<Integer> list = new ArrayList();
		assertEquals(0, list.size(), "Size should be 0 after construction" );
		}

	/**
	 * Testing the add method in ArrayList.
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
		
		

		assertEquals(0, list.size());
		//Invalid checks
		//null pointer check
		Exception e1 = assertThrows(NullPointerException.class, 
						() -> list.add(0, null));
		assertEquals("Element cannot be null.", e1.getMessage(), "checking that a null pointer exception is thrown when a null element is added");
		//duplicate element
		list.add(0, 66);
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> list.add(1, 66));
		assertEquals("Duplicate element not allowed.", e2.getMessage(), "checking that a duplicate element cannot be added");
		//index out of range
		Exception e3 = assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 33));
		assertEquals("Index out of bounds.", e3.getMessage(), "checking that out of bounds exception is thrown for a negative index");
		Exception e4 = assertThrows(IndexOutOfBoundsException.class, () -> list.add(1000, 33333));
		assertEquals("Out of bounds", e4.getMessage(), "checking that out of bounds exception is thrown for a negative index");		
		assertEquals("Index out of bounds.", e4.getMessage(), "checking that out of bounds exception is thrown for a negative index");
		
		list.add(1, 15);
		list.add(2, 20);
		list.add(3, 25);
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


	/**
	 * Testing the remove method in ArrayList.
	 */
	@Test
	void testRemove() {
		assertTrue(true);

	}

	/**
	 * Testing the get method in ArrayList.
	 */
	@Test
	void testGet() {
		assertTrue(true);
	}
}
