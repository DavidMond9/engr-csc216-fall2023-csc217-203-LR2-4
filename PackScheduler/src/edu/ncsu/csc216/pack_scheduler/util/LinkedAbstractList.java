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
		if (capacity < this.size()) {
			throw new IllegalArgumentException("Invalid capacity.");
		}
	}
	
	/**
	 * Adds an element to the linked list.
	 * @param idx the index to add the element to.
	 * @param ele the element to add.
	 * @throws IllegalArgumentException if the list is at capacity.
	 * @throws NullPointerException if the element to add is null.
	 * @throws IllegalArgumentException if the element is a duplicate of another in the list.
	 * @throws IndexOutOfBoundsException if there is not an element at the given index in the list.
	 */
	@Override
	public void add(int idx, E ele) {
		if (size() == this.capacity) {
			throw new IllegalArgumentException("List is at capacity.");
		}
		if (ele == null) {
			throw new NullPointerException("Element is null.");
		}
		for (int i = 0; i < size(); i++) {
			if (get(i).equals(ele)) {
				throw new IllegalArgumentException("Element is already in list.");
			}
		}
		if (idx < 0 || idx > size()) {
			throw new IndexOutOfBoundsException();
		}
		
		// Add to an empty list
		if (this.front == null) {
			this.front = new ListNode(ele);
		}
		// Add to the front of the list
		if (idx == 0) {
			ListNode newFront = new ListNode(ele);
			newFront.next = this.front;
			this.front = newFront;
		// Add to the middle/end of the list
		} else {
			ListNode current = this.front;
			for (int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
			current.next = new ListNode(ele, current);
		}
		
	}
	
	/**
	 * Removes an element from the list at the given index.
	 * @param idx the index of the element to remove.
	 * @return ele the element that is remove.
	 */
	public E remove(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Index is out of bounds.");
		}
		E ele = null;
		// Remove from the front of list
		if (idx == 0) {
			ele = front.data;
			front = front.next;
		// Remove from elsewhere in the list
		} else {
			ListNode current = front;
			for (int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
			ele = current.next.data;
			current.next = current.next.next;
		}
		size--;
		return ele;
	}
	
	/**
	 * 
	 */
	public E set(int idx, E ele) {
		if (ele == null) {
			throw new NullPointerException("Element is null.");
		}
		for (int i = 0; i < size(); i++) {
			if (get(i).equals(ele)) {
				throw new IllegalArgumentException("Element is already in list.");
			}
		}
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Index is out of bounds.");
		}
		
		ListNode current = front;
		for (int i = 0; i < idx - 1; i++) {
			current = current.next;
		}
		current = new ListNode(ele, current);
		return ele;
		
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public E get(int idx) {
		ListNode current = front;
		for (int i = 0; i < idx - 1; i++) {
			current = current.next;
		}
		return current.data;
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
