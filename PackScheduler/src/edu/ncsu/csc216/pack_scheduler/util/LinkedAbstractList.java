/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Creates a Linked list of a generic object type.
 * @param <E> the generic object type.
 * @author Audrey Fuelleman
 */
public class LinkedAbstractList<E> extends AbstractList<E> {
	/** The ListNode at the front of the list. */
	private ListNode front;
	/** The size of the list. */
	private int size;
	/** The list's capacity. */
	private int capacity;
	
	/**
	 * Creates a linked link of a generic type.
	 * @param capacity the capacity of the list.
	 */
	public LinkedAbstractList(int capacity) {
		this.front = null;
		this.size = 0;
		if (capacity >= 0) {
			this.capacity = capacity;
		} else {
			throw new IllegalArgumentException("Invalid capacity.");
		}
		if (capacity < this.size) {
			throw new IllegalArgumentException("Invalid capacity.");
		}
	}
	
	/**
	 * Adds an element to the linked list.
	 * @param idx the index to add the element to.
	 * @param ele the element to add.
	 */
	@Override
	public void add(int idx, E ele) {
		if (this.size == this.capacity) {
			throw new IllegalArgumentException("List is at capacity.");
		}
		if (ele == null) {
			throw new NullPointerException("Element is null.");
		}
		for (int i = 0; i < size; i++) {
			if (get(i).equals(ele)) {
				throw new IllegalArgumentException("Element is already in list.");
			}
		}
		if (idx < 0 || idx > size()) {
			throw new IndexOutOfBoundsException();
		}
		
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/** Inner class of LinkedAbstractList that creates a ListNode for the linked list. */
	private class ListNode {
		/** The data in the node. */
		private E data;
		/** The next node in the list. */
		private ListNode next;
		
		/**
		 * Constructs a ListNode with data.
		 * @param data the node's data.
		 */
		ListNode(E data) {
			this.data = data;
		}
		
		/**
		 * Constructs a ListNode with data and a next node.
		 * @param data the node's data.
		 * @param next the next node in the list.
		 */
		ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}

}
