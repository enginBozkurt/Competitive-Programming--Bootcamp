package com.tree;

public class BSTDemo {
	
	public static void main(String[] args) {
		
		BST<Integer, String> tree = new BST<Integer, String>();
		
		tree.put(4, "Engin");
		tree.put(2, "James");
		tree.put(1, "Madison");
		tree.put(3, "Rebecca");
		tree.put(6, "Fiona");
		tree.put(5, "Shrek");
		tree.put(7, "Kevin");
		
		
		System.out.println(tree.get(7));
		
		tree.traverseInorder();
		
		tree.deleteMin();
		
		System.out.println("----------------------");
		System.out.println("After deleting the minimum key");
		tree.traverseInorder();
		
		tree.delete(6);
		System.out.println("----------------------");
		System.out.println("After deleting the key 6");
		tree.traverseInorder();
		
	}
}