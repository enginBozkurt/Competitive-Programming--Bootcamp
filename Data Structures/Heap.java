package com.training;

import java.util.*;

public class Heap<E extends Comparable<E>> {
	  private ArrayList<E> list = new ArrayList<>();

	  /** Create a default heap */
	  public Heap() {
	  }

	  /** Create a heap from an array of objects */
	  public Heap(E[] objects) {
	    for (int i = 0; i < objects.length; i++)
	      add(objects[i]);
	  }
	  
	  
	  public void swap(int i, int j) {
		  E temp = list.get(i);
		  list.set(i, list.get(j));
		  list.set(j, temp);  
	  }

	  /** Add a new object into the heap */
	  // appends the object to the tree and then swaps the object with its parent 
	  // if the object is greater than its parent
	  public void add(E newObject) {
	    list.add(newObject); // Append to the heap
	    int currentIndex = list.size() - 1; // The index of the last node

	    while (currentIndex > 0) {
	      int parentIndex = (currentIndex - 1) / 2;
	      // Swap if the current object is greater than its parent
	      if (list.get(currentIndex).compareTo(
	          list.get(parentIndex)) > 0) {
	    	  
	    	  swap(currentIndex, parentIndex);
	      }
	      else
	        break; // the tree is a heap now

	      currentIndex = parentIndex;
	    }
	  }

	  /** Remove the root from the heap */
	  // moves the last object to the root position and swaps it with its larger
	  // child if it is less than the larger child
	  public E remove() {
	    if (list.size() == 0) return null;

	    E removedObject = list.get(0);
	    list.set(0, list.get(list.size() - 1));
	    list.remove(list.size() - 1);

	    int currentIndex = 0;
	    while (currentIndex < list.size()) {
	      int leftChildIndex = 2 * currentIndex + 1;
	      int rightChildIndex = 2 * currentIndex + 2;

	      // Find the maximum between two children
	      if (leftChildIndex >= list.size()) break; // The tree is a heap
	      int maxIndex = leftChildIndex;
	      if (rightChildIndex < list.size()) {
	        if (list.get(maxIndex).compareTo(
	            list.get(rightChildIndex)) < 0) {
	          maxIndex = rightChildIndex;
	        }
	      }

	      // Swap if the current node is less than the maximum
	      if (list.get(currentIndex).compareTo(
	          list.get(maxIndex)) < 0) {
	        
	    	swap(maxIndex, currentIndex);  
	    	
	        currentIndex = maxIndex;
	      }
	      else
	        break; // The tree is a heap
	    }

	    return removedObject;
	  }

	  /** Get the number of nodes in the tree */
	  public int getSize() {
	    return list.size();
	  }	  	  
}
