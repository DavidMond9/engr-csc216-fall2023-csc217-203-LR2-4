/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;
import java.util.Objects;

/**
 * ArrayList class has many methods to add, remove, and more for handling an array better.
 * It also extends AbstractList and has a few fields such as its own list with type E and
 * its size. It has a constant as well which determines the maximum capacity of the list.
 * @param <E> Parameter of type E in the ArrayList.
 * @author David Mond
 */
public class ArrayList<E> extends AbstractList<E> {
	/**
	 * Maximum capacity of list.
	 */
	private static final int INIT_SIZE = 10;
	/**
	 * List of type E for ArrayList.
	 */
	private E[] list;
	/**
	 * Size of list.
	 */
	private int size;
	
	/**
	 * ArrayList constructor, creates a new list of type E and sets the size to 0.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		list = (E[]) new Object[INIT_SIZE];
		size = 0;
	}
	/**
	 * Adds an element at a specific index.
	 * @param idx index to add at.
	 * @param ele element being added.
	 */
	@SuppressWarnings("unchecked")
	public void add(int idx, E ele) {
		if(ele == null) {
			throw new NullPointerException("Element cannot be null.");
		}
		if(idx < 0 || idx > size()) {
			throw new IndexOutOfBoundsException("Index out of bounds.");
		}
		for(int i = 0; i < list.length; i++) {
			E temp = list[i];
			if (temp != null && temp.equals(ele)) {
				throw new IllegalArgumentException("Duplicate element not allowed.");
			}
		}
		if(size == list.length) {
			int newCapacity = list.length * 2;
			E[] newList = (E[]) new Object[newCapacity];
			for (int i = 0; i < size; i++) {
				newList[i] = list[i];
			}
			list = newList;
			//this.list = Arrays.copyOf(list, newCapacity);
			//this.size = this.size * 2;
			//return;
		}
		
//		if(idx < size) {
//			System.arraycopy(list, idx, list, idx + 1, size - idx);
//			
//		}
		this.list[idx] = ele;
		this.size += 1;
	}
	/**
	 * Removes an element at a specific index.
	 * @param idx index to remove at.
	 * @return E element being removed.
	 */
	public E remove(int idx) {
		if(idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Index out of bounds.");
		}
		
		E removedElement = (E)list[idx];
		
		if(idx < size - 1) {
			System.arraycopy(list, idx + 1, list, idx, size - idx - 1);
		}
		
		list[size - 1] = null;
        size--;
		return removedElement;
	}
	/**
	 * Sets an element at a specific index.
	 * @param idx index to set at
	 * @param ele element to set
	 * @return E the element after being set.
	 */
	public E set(int idx, E ele) {
		if(ele == null) {
			throw new NullPointerException("Element cannot be null.");
		}
		if(idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Index out of bounds.");
		}
		for(int i = 0; i < size; i++) {
			if(i != idx && Objects.equals(list[i], ele)) {
				throw new IllegalArgumentException("Duplicate element not allowed.");
			}
		}
		
		
		E temp = (E) list[idx];
		list[idx] = ele;
		
		return temp;
	}
	/**
	 * Gets the element at a specific index.
	 * @param idx index to get from
	 * @return E element to return.
	 */
	public E get(int idx) {
		if(idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Index out of bounds.");
		}
		return (E)list[idx];
	}
	/**
	 * Gets the size of list.
	 * @return int size of list.
	 */
	public int size() {
		return size;
	}

}