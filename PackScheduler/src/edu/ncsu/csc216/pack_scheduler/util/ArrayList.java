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
		if (ele == null) {
			throw new NullPointerException("Cannot add a null object");
		}
		for (int i = 0; i < this.size; i++) {
			if (list[i].equals(ele)) {
				throw new IllegalArgumentException("Duplicate element");
			}
		}
		if (idx < 0 || idx > this.size) {
			throw new IndexOutOfBoundsException("Out of bounds index");
		}
		if(idx == size) {
			//growArray();
			E[] newList = (E[]) new Object[list.length * 2]; 
			for (int j = 0; j < newList.length; j++) {
				list[j] = newList.get(j); //need to add get method
		}
		for (int i = 0; i > idx; i--) {
			list[i] = list[i - 1];
		}
		list[idx] = ele;
	}
		
		
		if(size == INIT_SIZE) {
			
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
		
		for(int i = 0; i < list.length; i++) {
			if(i == idx) {
				if(idx == size - 1) {
					E ele = list.remove(i);
				}
				else {
					E ele = list.remove(i);
					
				}
			}
		}
		return ele;
	}
	
	public E set(int idx, E list) {
		
		if()
		
		if(idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Index out of bounds.");
		}
		return null;
	}
	
	public E get(int idx, E list) {
		if(idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException("Index out of bounds.");
		}
		return null;
	}
}
