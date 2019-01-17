package com.graph;

import java.util.LinkedList;
import java.util.Stack;


public class GraphKosarajuSharirSCC {
	
	private boolean[] visited;  // reached vertices
	
	// Array  of LinkedLists for Adjacency List Representation 
	private LinkedList<Integer>[] adj;
	
	
	private int V;   // No. of vertices 
	
	public GraphKosarajuSharirSCC(int V) {
		this.V = V;
		
		
		// Create array of linkedlists
		adj = (LinkedList<Integer>[]) new LinkedList[this.V]; 
		
		for (int v =0; v < V; v++)
			adj[v] = new LinkedList<Integer>();			
	}
	
	
	
	// Function to add an edge into the graph 
	void addEdge(int v, int w) {
		adj[v].add(w);
	}
	
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
	
	// A recursive function to print DFS starting from v 
    void DFSUtil(int v, boolean visited[]) {
    	// Mark the current node as visited and print it 
        visited[v] = true; 
        System.out.print(v + " "); 
        
        for(int w: adj[v]) 
        	if (!visited[w]) { 
        		DFSUtil(w, visited);
        	}
    }
	
	//reverse graph
	
	public GraphKosarajuSharirSCC reverseGraph() {
		
		GraphKosarajuSharirSCC g = new  GraphKosarajuSharirSCC(V);
		
		 for(int v = 0; v < V; v++) 
			 for (int w : adj[v]) 
	                g.addEdge(w, v);
	 
		 return g;
	}
	
	//The function to do DFS traversal. It uses recursive DFSUtil() 
    void DFS() {
    	
    	// Mark all the vertices as not visited(set as  false by default in java) 
    	boolean visited[] = new boolean[V];
    	
    	// Call the recursive helper function to print DFS traversal 
        // starting from all vertices one by one 
    	for (int i=0; i<V; i++) { 
    		 if (visited[i] == false)
    			 DFSUtil(i, visited);
    	}
    }
    
    
    // Reverse postorder : Put the vertex on a stack after the recursive calls based on DFS
    void fillOrder(int v, boolean visited[], Stack stack)  {
    	
    	// Mark the current node as visited and print it 
        visited[v] = true;
        
        for(int w: adj[v]) 
        	if (!visited[w])  
        		fillOrder(w, visited, stack);
        
      // All vertices reachable from v are processed by now, 
      // push v to Stack 
      stack.push(new Integer(v));
    }
    
    
    // The main function that finds and prints all strongly 
    // connected components
    
    void printSCCs() {
    	Stack stack = new Stack(); 
    	
    	// Mark all the vertices as not visited (For first DFS) 
        boolean visited[] = new boolean[V]; 
        for(int i = 0; i < V; i++) 
            visited[i] = false; 
        
        // Fill vertices in stack according to their finishing times
        // (topological order (reverse post order))
        for (int i = 0; i < V; i++) 
            if (visited[i] == false) 
                fillOrder(i, visited, stack); 
        
        // Create a reversed graph 
        GraphKosarajuSharirSCC gr = reverseGraph();
        
        // Mark all the vertices as not visited (For second DFS) 
        for (int i = 0; i < V; i++) 
            visited[i] = false; 
        
        // Now process all vertices in order defined by Stack 
        while (stack.empty() == false) { 
            // Pop a vertex from stack 
            int v = (int)stack.pop(); 
  
            // Print Strongly connected component of the popped vertex 
            if (visited[v] == false) { 
                gr.DFSUtil(v, visited); 
                System.out.println(); 
            } 
        } 
    }
    
    
	
	 // Driver method 
    public static void main(String args[]) {
    	
    	
        // Create a graph given in the above diagram 
    	GraphKosarajuSharirSCC g = new GraphKosarajuSharirSCC(5); 
    	g.addEdge(1, 0); 
        g.addEdge(0, 2); 
        g.addEdge(2, 1); 
        g.addEdge(0, 3); 
        g.addEdge(3, 4);   
        
        System.out.println("Following are strongly connected components "+ 
                "in given graph "); 
        
        g.printSCCs();
    } 
	
}