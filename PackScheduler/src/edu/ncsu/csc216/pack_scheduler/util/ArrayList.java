/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * 
 */
public class ArrayList<E> extends AbstractList<E> {
	private static final int INIT_SIZE = 10;
	private E list;
	private int size;
	
	public ArrayList() {
		//create array object
		
		//cast to E
		
		//set size to 0
		size = 0;
	}
	
	public void add(int idx, E list) {
		if(size == INIT_SIZE) {
			
		}
		for(int i = 0; i < list.length; i++) {
			if(i == idx) {
				
			}
		}
	}
	
	public E remove(int idx) {
		return null;
	}
	
	public E set(int idx, E list) {
		return null;
	}
	
	public E get(int idx, E list) {
		return null;
	}
}
