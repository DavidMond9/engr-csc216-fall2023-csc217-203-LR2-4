package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * this tests the linked abstract list class
 * @author Duncan
 */
class LinkedAbstractListTest {

	
	/**
	 * tests the constructor
	 */
	@Test
	void testLinkedAbstractList() {
		int capacity = 5;
		LinkedAbstractList<String> list = new LinkedAbstractList(capacity);
		assertNull(list.get(0));
		assertEquals(0, list.size(), "ensure size is 0 when first created");
		
		
		//invalid capacity
		try {
			int invalid = -2;
			LinkedAbstractList<String> test = new LinkedAbstractList(invalid);
			fail("invalid list constructed");
		} catch (IllegalArgumentException e) {
			//nothing should occur after exception is caught	
		}
		
//		list.add(0, "test");
//		Exception e = assertThrows(IllegalArgumentException.class, () -> list = LinkedAbstractList(0));
//		assertEquals("Invalid capacity", e.getMessage(), "checking for constructor when cpacity < size");
//		
		
	}

	/**
	 * tests the add method
	 */
	@Test
	void testAdd() {
		
		int capacity = 5;
		LinkedAbstractList<String> list = new LinkedAbstractList(capacity);
		
		//valid checks
		list.add(0, "zero");
		assertEquals(1, list.size(), "Size should be 1 after adding more elements");				
		assertEquals("zero", list.get(0), "check to make sure it is being added correctly");
		
		//add until size is equal to capacity
		list.add(1, "one");
		list.add(2, "two");
		list.add(3, "three");
		list.add(4, "four");
		Exception e3 = assertThrows(IllegalArgumentException.class, () -> list.add(5, "five"));
		assertEquals("List is at capacity.", e3.getMessage());

		list.remove(3);
		//invalid checks
		//null check
		try {
			list.add(3, null);
			fail("Null should not pass");
		} catch (NullPointerException e) {
			// nothing should happen after the exception is thrown
		}
		//check for duplicate element
		list.remove(3);
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> list.add(1, "one"));
		assertEquals("Element is already in list.", e1.getMessage(), "duplicate element check");
		//index out of bounds
		try {
			list.add(-1, "element");
			fail("invalid index number");
		} catch (IndexOutOfBoundsException e) {	
			//do nothing
		}
		
		try {
			list.add(6, "element");
			fail("invalid index number");
		} catch (IndexOutOfBoundsException e) {
			//do nothing
		}
		
		
		LinkedAbstractList<String> list2 = new LinkedAbstractList<String>(5);
		
		list2.add(0, "h");
		list2.add(1, "j");
		list2.add(2, "l");
		assertEquals("h", list2.get(0));
		assertEquals("j", list2.get(1));
		assertEquals("l", list2.get(2));
		// h -> h -> j -> l
		
		// Add to front
		list2.add(0, "front");
		assertEquals("front", list2.get(0));
		assertEquals("h", list2.get(1));
		assertEquals("j", list2.get(2));
		assertEquals("l", list2.get(3));
		
		// Add to middle
		list2.add(1, "middle");
		assertEquals("front", list2.get(0));
		assertEquals("middle", list2.get(1));
		assertEquals("h", list2.get(2));
		assertEquals("j", list2.get(3));
		assertEquals("l", list2.get(4));
		
		// Add to back
		list2.add(5, "back");
		assertEquals("front", list2.get(0));
		assertEquals("middle", list2.get(1));
		assertEquals("h", list2.get(2));
		assertEquals("j", list2.get(3));
		assertEquals("l", list2.get(4));
		assertEquals("back", list2.get(5));
	}

	/** 
	 * tests the get method
	 */
	@Test
	void testGet() {
		fail("Not yet implemented");
		//edit for push
	}
	
	/**
	 * tests the set method
	 */
	@Test
	void testSet() {
		fail("Not yet implemented");
		//s
	}
	
	/**
	 * tests the remove method
	 */
	@Test
	void testRemove() {
		fail("Not yet implemented");
	}
	
	
}
