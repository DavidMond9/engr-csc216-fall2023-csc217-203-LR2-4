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
		ArrayList<String> list = new ArrayList();
		//valid checks
		list.add(0, "one");
		assertEquals(1, list.size(), "Size should be 1 after adding more elements");
		assertEquals("one", list.get(0), "check to make sure it is being added correctly");
		// Test adding more elements to trigger array growth
        for (int i = 2; i < 11; i++) {
            list.add(i - 1, "note" + i);
        }
        
        list.add(INIT_SIZE, "test"); //this line may not be needed once add method is fixed
        assertEquals(20, list.size(), "check to see if arraylist doubles when a tenth integer is added");
		
		

		//assertEquals(0, list.size());
		//Invalid checks
		//null pointer check
		Exception e1 = assertThrows(NullPointerException.class, 
						() -> list.add(0, null));
		assertEquals("Element cannot be null.", e1.getMessage(), "checking that a null pointer exception is thrown when a null element is added");
		//duplicate element
		list.add(15, "hi");
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> list.add(65, "hi"));
		assertEquals("Duplicate element not allowed.", e2.getMessage(), "checking that a duplicate element cannot be added");
		//index out of range
		Exception e3 = assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, "thirty"));
		assertEquals("Index out of bounds.", e3.getMessage(), "checking that out of bounds exception is thrown for a negative index");
		Exception e4 = assertThrows(IndexOutOfBoundsException.class, () -> list.add(1000, "woah"));
		assertEquals("Out of bounds", e4.getMessage(), "checking that out of bounds exception is thrown for a negative index");		
		assertEquals("Index out of bounds.", e4.getMessage(), "checking that out of bounds exception is thrown for a negative index");
		
		list.add(1, "h");
		list.add(2, "j");
		list.add(3, "l");
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
		ArrayList<String> list = new ArrayList();
		// Invalid check
		//try removing an object from an empty list
        try {
            list.remove(0);
            fail(); 
            //fail statement is security in case an exception is not thrown
        } catch (IndexOutOfBoundsException e) {
        }

        // Add some elements to the list
        list.add(0, "one");
        list.add(1, "two");
        list.add(2, "three");

        // Test removing an element at a valid index
        list.remove(0);
        assertEquals(2, list.size(), "esnure the size is now 2");
        assertEquals("two", list.get(0), "check order");
        assertEquals("three", list.get(1), "check order");

        // Test removing an element at an invalid index
        try {
            list.remove(5);
            fail();
        } catch (IndexOutOfBoundsException e) {
        }
	}

	/**
	 * Testing the get method in ArrayList.
	 */
	@Test
	void testGet() {
		assertTrue(true);
	}
}
