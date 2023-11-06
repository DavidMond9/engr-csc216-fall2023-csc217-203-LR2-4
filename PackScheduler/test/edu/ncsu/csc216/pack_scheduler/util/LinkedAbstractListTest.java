package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * this tests the linked abstract list class
 * @author Duncan
 * @author Audrey Fuelleman
 */
class LinkedAbstractListTest {

	
	/**
	 * tests the constructor
	 */
	@Test
	void testLinkedAbstractList() {
		int capacity = 5;
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(capacity);
		assertEquals(0, list.size(), "ensure size is 0 when first created");
		
		
		// Invalid capacity
		assertThrows(IllegalArgumentException.class,
				() -> new LinkedAbstractList<String>(-2));
		
//		list.add(0, "test");
//		Exception e = assertThrows(IllegalArgumentException.class, () -> new);
//		assertEquals("Invalid capacity", e.getMessage(), "checking for constructor when capacity < size");
		
		
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
			
		LinkedAbstractList<String> list2 = new LinkedAbstractList<String>(6);
		
		list2.add(0, "h");
		list2.add(1, "j");
		list2.add(2, "l");
		assertEquals("h", list2.get(0));
		assertEquals("j", list2.get(1));
		assertEquals("l", list2.get(2));
		
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
	 * Tests the get method.
	 */
	@Test
	void testGet() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(5);
		Exception e1 = assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(0));
		assertEquals(e1.getMessage(), "Index is out of bounds.");
		
		Exception e2 = assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(-1));
		assertEquals(e2.getMessage(), "Index is out of bounds.");
		
		list.add(0, "element1");
		assertEquals("element1", list.get(0));
		list.add(1, "element2");
		assertEquals("element2", list.get(1));
		list.add(2, "element3");
		assertEquals("element3", list.get(2));
	}
	
	/**
	 * tests the set method
	 */
	@Test
	void testSet() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(5);
		
		Exception e1 = assertThrows(IndexOutOfBoundsException.class,
				() -> list.set(-1, "element"));
		assertEquals(e1.getMessage(), "Index is out of bounds.");
		
		list.add(0, "element");
		Exception e2 = assertThrows(IndexOutOfBoundsException.class,
				() -> list.set(1, "element2"));
		assertEquals(e2.getMessage(), "Index is out of bounds.");
		
		Exception e3 = assertThrows(NullPointerException.class,
				() -> list.set(0, null));
		assertEquals(e3.getMessage(), "Element is null.");
		
		list.add(1, "element2");
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> list.set(1, "element"));
		assertEquals(e4.getMessage(), "Element is already in list.");	
		
		assertEquals("element", list.get(0));
		assertEquals("element2", list.get(1));
		list.set(1, "another");
		assertEquals("element", list.get(0));
		assertEquals("another", list.get(1));
		list.add(2, "three");
		assertEquals("three", list.get(2));
		list.set(2, "different");
		assertEquals("element", list.get(0));
		assertEquals("another", list.get(1));
		assertEquals("different", list.get(2));
		
	}
	
	/**
	 * Tests the remove method.
	 */
	@Test
	void testRemove() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(5);
		Exception e1 = assertThrows(IndexOutOfBoundsException.class,
				() -> list.remove(-1));
		assertEquals(e1.getMessage(), "Index is out of bounds.");
		Exception e2 = assertThrows(IndexOutOfBoundsException.class,
				() -> list.remove(1));
		assertEquals(e2.getMessage(), "Index is out of bounds.");
		
		list.add(0, "first");
		list.add(1, "second");
		list.add(2, "third");
		assertEquals("first", list.get(0));
		list.remove(0);
		assertEquals("second", list.get(0));
		
		list.add(0, "first");
		assertEquals("first", list.get(0));
		list.remove(1);
		assertEquals("first", list.get(0));
		assertEquals("third", list.get(1));
		
		
	}
	
	
}
