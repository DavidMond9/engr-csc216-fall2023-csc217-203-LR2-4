package edu.ncsu.csc217.collections.list;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.Test;


/**
 * Test class for SortedList
 * @author Audrey Fuelleman
 * @author Ben Wojtkowiak
 * @author Sam McDonald
 */
public class SortedListTest {

	/**
	 * Test that the SortedList constructor starts at length zero
	 * and more than 10 elements can be successfully added to the list
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		
		// Test that the list grows by adding at least 11 elements
		//Remember the list's initial capacity is 10
		for (int i = 0; i < 11; i++) {
			
			list.add("new string " + i);
			
		}
		
		assertEquals(11, list.size());
		
	}
	
	/**
	 * Test that the add method correctly adds items to the list, checking edge cases and duplicates
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		list.add("apple");
		assertEquals(2, list.size());
		assertEquals("apple", list.get(0));
		
		list.add("aztec");
		assertEquals(3, list.size());
		assertEquals("aztec", list.get(1));
		
		list.add("zebra");
		assertEquals(4, list.size());
		assertEquals("zebra", list.get(3));
		
		assertThrows(NullPointerException.class, () -> list.add(null));

		assertThrows(IllegalArgumentException.class, () -> list.add("aztec"));
	}
	
	/**
	 * Tests get method for SortList
	 * Checks if the method throws an IndexOutOfBoundsException when
	 * the index is out of range or the list is empty. It also checks 
	 * if it returns the correct element when the index is valid.
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		// Test getting an element from an empty list
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));

		
		// Add some elements to the list
		for (int i = 0; i < 11; i++) {
			
			list.add("new string " + i);
			
		}
		assertEquals("new string 4", list.get(5));
		
		// Test getting an element at an index < 0
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
		
		
		// Test getting an element at size
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(list.size()));

		
	}
	
	/**
	 * Tests that the remove method for SortList can successfully remove an element
	 * at any index of a list. Tests that method throws an exception if the index does not exist.
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		// Test removing from an empty list
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
		// Add some elements to the list - at least 4
		for (int i = 0; i < 6; i++) {
			
			list.add("string " + i);
			
		}
		// Test removing an element at an index < 0
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
		// Test removing an element at size
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(list.size()));
		// Test removing a middle element
		assertEquals("string 3", list.remove(3));
		// Test removing the last element
		assertEquals("string 5", list.remove(list.size() - 1));
		// Test removing the first element
		assertEquals("string 0", list.remove(0));
		// Test removing the last element
		assertEquals("string 4", list.remove(list.size() - 1));
	}
	
	/**
	 * test the indexOf method through adding items to list and verifying the correct
	 * index is returned.
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		// Test indexOf on an empty list
		assertEquals(-1, list.indexOf("apple"));
		// Add some elements
		for (int i = 0; i < 6; i++) {
			
			list.add("string " + i);
			
		}
		// Test various calls to indexOf for elements in the list
		//and not in the list
		assertEquals(4, list.indexOf("string 4"));
		assertEquals(0, list.indexOf("string 0"));
		assertEquals(5, list.indexOf("string 5"));
		assertEquals(-1, list.indexOf("not in list"));
		
		// Test checking the index of null
		assertThrows(NullPointerException.class, () -> list.indexOf(null));
	}
	
	/**
	 * Tests the clear method for SortList
	 * Checks if the method removes all elements from the list
	 * and sets its size to zero.
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		// Add some elements
		for (int i = 0; i < 11; i++) {
			
			list.add("new string " + i);
			
		}
		
		// Clear the list
		list.clear();
		
		// Test that the list is empty
		assertEquals(0, list.size());
	}

	/**
	 * Tests that the a SortedList starts empty
	 * and is no longer empty after adding an element
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		/// Test that the list starts empty
		assertEquals(0, list.size());		
		// Add at least one element
		list.add("string");	
		// Check that the list is no longer empty
		assertEquals(1, list.size());
	}

	/**
	 * Tests contains method through adding elements and verifying correct boolean returns.
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		// Test the empty list case
		assertFalse(list.contains("string 0"));
		// Add some elements
		for (int i = 0; i < 11; i++) {
			
			list.add("string " + i);
			
		}
		// Test some true and false cases
		assertTrue(list.contains("string 5"));
		assertFalse(list.contains("string 12"));
		assertFalse(list.contains("wow so cool"));
		assertTrue(list.contains("string 0"));
		assertTrue(list.contains("string 10"));
		assertFalse(list.contains(null));
	}
	
	/**
	 * Checks to see if equals() Compares the specified object 
	 * with this list for equality. 
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//TODO Make two lists the same and one list different
		for (int i = 0; i < 11; i++) {
			
			list1.add("new string " + i);
			list2.add("new string " + i);
			list3.add("wow " + i);
			
		}
		
		//TODO Test for equality and non-equality
		assertEquals(list1, list2);
		assertNotEquals(list1, list3);
	}
	
	/**
	 * Tests the Hash Code method of SortedList
	 * Checks if method returns the same has code for
	 * two lists that have the same elements.
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//TODO Make two lists the same and one list different.
		for (int i = 0; i < 11; i++) {
					
			list1.add("new string " + i);
			list2.add("new string " + i);
			list3.add("wow " + i);
					
		}
				
		//TODO Test for equality and non-equality
		assertEquals(list1.hashCode(), list2.hashCode());
		assertNotEquals(list1.hashCode(), list3.hashCode());
	}

}