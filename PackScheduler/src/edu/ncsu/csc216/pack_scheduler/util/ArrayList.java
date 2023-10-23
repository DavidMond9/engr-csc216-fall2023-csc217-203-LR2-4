/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Objects;

/**
 * 
 */
public class ArrayList<E> extends AbstractList<E> {
	private static final int INIT_SIZE = 10;
	private E[] list;
	private int size;
	
	@SuppressWarnings("unchecked")
	public ArrayList() {
		list = (E[]) new Object[INIT_SIZE];
		size = 0;
	}
	
	public void add(int idx, E ele) {
		if(ele == null) {
			throw new NullPointerException("Element cannot be null.");
		}
		if(idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Index out of bounds.");
		}
		for(int i = 0; i < size; i++) {
			if(Objects.equals(list[i], ele)) {
				throw new IllegalArgumentException("Duplicate element not allowed.");
			}
		}
		if(size == list.length) {
			int newCapacity = list.length * 2;
			list = Arrays.copyOf(list, newCapacity);
		}
		
		if(idx < size) {
			System.arraycopy(list, idx, list, idx + 1, size - idx);
			
		}
		list[idx] = ele;
		size += 1;
	}
	
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
	
	public E get(int idx) {
		if(idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Index out of bounds.");
		}
		return (E)list[idx];
	}
}
