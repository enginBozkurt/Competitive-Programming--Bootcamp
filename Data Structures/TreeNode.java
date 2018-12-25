package com.tree;

public class TreeNode<K extends Comparable<K>, V> {
	
	public K key;          					// sorted by key
	public V val;   	  					// associated data
	public TreeNode<K, V> left, right;    // left and right subtrees
	
	
	public TreeNode(K key, V val, TreeNode<K, V> left,
							TreeNode<K,V> right) {
		this.key = key;
		this.val = val;
		this.left = left;
		this.right= right;
		
	}
	
	
	public V getValue() { return val; }
    public void setValue(V newV) { val = newV; }
}