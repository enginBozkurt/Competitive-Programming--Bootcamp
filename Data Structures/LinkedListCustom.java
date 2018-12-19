package com.camp;

public class LinkedListCustom<T> {
	Node head; // first node
	
	public LinkedListCustom() {
		head = null;  // empty list
	}
	
	// creating a new node
	public void add(Object value) {
		Node newNode= new Node(value, null);
		// if I have not any existing node, add the new node
		if(head == null) {
			head= newNode;  //new node is the head
		}
		else {
			// make new node referring to the head
			// link the new node with the head
			newNode.next= head;
			// make the new node head
			// head points to the new node
			head= newNode;
		}
	}	
	
	// deleting the first element
	public void delete() {
		head= head.next;
	}
	
	// displaying nodes
	public void display() { 
		Node n= head; // first node is head
		
		while(n != null) {
			System.out.println((T) n.value);
			n= n.next;
		}
				
	}	
}
