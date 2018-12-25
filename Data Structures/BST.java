package com.tree;


public class BST <K extends Comparable<K>, V>{
	
	 private TreeNode<K, V> root;             // root of BST
	
	// *** constructor ***
	public BST() { root = null; } 
	
	/* BST insertion
	The public insert method uses an auxiliary recursive "helper" method to do the actual insertion.
	The node containing the new value is always inserted as a leaf in the BST.
	The public insert method returns void, but the helper method returns a BSTnode. 
	It does this to handle the case when the node passed to it is null. 
	In general, the helper method is passed a pointer to a possibly empty tree. 
	Its responsibility is to add the indicated key to that tree and return a pointer to the root of the modified tree. 
	If the original tree is empty, the result is a one-node tree. Otherwise, the result is a pointer to the same node 
	that was passed as an argument.
	
	if key is in tree ==> reset value
	if key is not in tree ==> add new node
	*/
	
	public void put(K key, V val) {
		root = put(root, key, val);
	}
	
	private TreeNode<K, V> put(TreeNode<K, V> x, K key, V val) {
		if(x == null) return new TreeNode<K, V>(key, val, null, null);
		
		int cmp = key.compareTo(x.key);
		
		if (cmp < 0)
			x.left = put(x.left, key, val);
		else if (cmp > 0)
			x.right = put(x.right, key, val);
		else
			x.val = val;
		
		return x;
	}
	
	// BST search
	// Return value corresponding to given key, or null if no such key.
	public V get(K key){ 
		return get(root, key); 
	}
	
	private V get(TreeNode<K, V> x, K key) { // Return value associated with key in the subtree rooted at x;
	// return null if key not present in subtree rooted at x.
	if (x == null) return null;
	
	int cmp = key.compareTo(x.key);
	
	if (cmp < 0) return get(x.left, key);
	else if (cmp > 0) return get(x.right, key);
	else return x.val;
	}
	
	// delete min operation
	// go left until reaching null left link and return that node's right link
	public void deleteMin() {
		root = deleteMin(root);
	}
	
	private TreeNode<K, V> deleteMin(TreeNode<K, V> x) {
		
		if(x.left == null) return x.right;
		
		x.left = deleteMin(x.left);
		return x;	
	}
	
	
	// return minimum key
	public K min() {
		return min(root).key;
	}
	
	private TreeNode<K, V> min(TreeNode<K, V> x) {
		
		if(x.left == null) return x;
		
		return min(x.left);
	}
	
	//deleting the specified key
	public void delete(K key) {
		root = delete(root, key);
	}
	
	private TreeNode<K, V> delete(TreeNode<K, V> x, K key) {
		
		// setting parent link to null if no children 
		if(x == null) return null;  
		
		int cmp = key.compareTo(x.key);
		
		//search for the key
		if(cmp < 0) x.left = delete(x.left, key);              
		else if(cmp > 0) x.right = delete(x.right, key);
		
		else {
	    // for the node with 1 child return that link to the child node
			if (x.right == null) return x.left;
			if (x.left == null) return x.right;
			
		
		
		TreeNode<K, V> t  = x;      //t containing the key to be deleted
		
		// node with two children: Get the inorder successor (smallest 
        // in the right subtree) and Put x in t's spot.
		x = min(t.right);
		
		//Delete the inorder successor 
		x.right = deleteMin(t.right);
		
		x.left = t.left;
		
		}
		return x;
	}
	
	
	//inorder traversal
	public void traverseInorder() {
		traverseInorder(root);
	}
	
	private void traverseInorder(TreeNode<K, V> node) {
		if (node != null) {
			traverseInorder(node.left);
			System.out.println(node.key);
			traverseInorder(node.right);	
		}		
	}
}