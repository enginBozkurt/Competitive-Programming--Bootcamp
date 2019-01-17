package com.graph;

import java.io.*; 
import java.util.*; 

public class DiGraph {
	private int V; //number of vertices
	private LinkedList<Integer>[] adj; //adjacency list
	
	//constructor method
	public DiGraph(int v) {
		this.V = v;
		adj = new LinkedList[V];
		for (int i=0; i < V; i++)
			adj[i] = new LinkedList<Integer>();	
	}
	
	// Function to add an edge into the graph 
	void addEdge(int v, int w) {
		adj[v].add(w);
	}
	
	// A recursive function used by topologicalSort 
	void topologicalSortUtil(int v, boolean visited[],
							Stack stack) {
		
		// Mark the current vertex as visited. 
		visited[v] = true;
		
		// Recur for all the vertices adjacent to this vertex 
		Iterator<Integer> it = adj[v].iterator();
		
		while(it.hasNext()) {
			int n = it.next();
			if(!visited[n])
				topologicalSortUtil(n, visited, stack); 
		}
		
		// Push current vertex to stack which stores result 
		stack.push(new Integer(v));
	}
	
	// The function to do Topological Sort. It uses 
    // recursive topologicalSortUtil() 
	void topologicalSort() {
		
		Stack stack = new Stack();
		
		// Mark all the vertices as not visited to false
		boolean visited[] = new boolean[V];
		
		 for (int i = 0; i < V; i++) 
			 visited[i] = false;
		 
		// Call the recursive helper function to store 
	    // Topological Sort starting from all vertices one by one 
		 
		 for (int i = 0; i < V; i++) 
			 if(visited[i] == false)
				 topologicalSortUtil(i, visited, stack); 
	     
		// Print contents of stack 
		 while (!stack.empty()) {
			 System.out.print(stack.pop() + " ");
		 }

	}
	
	
	// drive for test
	public static void main(String args[]) { 
		
		// Create a graph given in the above diagram 
        DiGraph g = new DiGraph(6); 
        g.addEdge(5, 2); 
        g.addEdge(5, 0); 
        g.addEdge(4, 0); 
        g.addEdge(4, 1); 
        g.addEdge(2, 3); 
        g.addEdge(3, 1); 
  
        System.out.println("Following is a Topological " + 
                           "sort of the given graph"); 
        g.topologicalSort();
	}
}
