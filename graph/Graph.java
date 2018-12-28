package com.graph;

import java.util.*;


public class Graph {
	
	private final int V;   // number of vertices
	// Array  of lists for Adjacency List Representation 
	private LinkedList<Integer>[] adjList;
	
	// constructor
	public Graph (int V) {
		this.V = V;
		// Create array of linkedlists
		adjList = (LinkedList<Integer>[]) new LinkedList[this.V]; 
		
		for (int v=0; v < V; v++)
			adjList[v] = new LinkedList<Integer>();	
	}
	
	//Function to add an edge into the graph (raph is undirected)
	public void addEdge(int v, int w) {
		adjList[v].add(w); // Add w to v’s list.
		adjList[w].add(v); // Add v to w’s list.
	}
	
	 // A function used by DFS 
	void DFSUtil(int v, boolean visited[]) {
		
		// Mark the current node as visited and print it 
		visited[v] = true;
		System.out.print(v+" "); 
		
		// Recur for all the vertices adjacent to this vertex 
		Iterator<Integer> i = adjList[v].listIterator();
		
		while (i.hasNext()) 
        { 
            int n = i.next(); 
            if (!visited[n]) 
                DFSUtil(n, visited); 
        } 			
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
    
    
    // A function used by BFS
    void BFSUtil(int v, boolean visited[]) {
    	
    	Queue<Integer> queue = new LinkedList<Integer>();
    	
    	 // Put vertex onto a FIFO queue and mark as visited
    	queue.add(v);
    	visited[v] = true;
    	
    	while(!queue.isEmpty()) {
    		
    		//remove the least recently added vertex 
    		int u = queue.poll();
    		System.out.print(u+" "); 
    		
    	   // Get all adjacent vertices of the dequeued vertex s 
           // If a adjacent has not been visited, then mark it 
           // visited and enqueue it 
    		Iterator<Integer> i = adjList[u].listIterator(); 
    		
    		while (i.hasNext()) {
    			 int n = i.next(); 
    			 
    			 if (!visited[n]) {
    				 visited[n] = true;
    				 queue.add(n);
    			 }
    		}
    	}	
    }
    
  //The function to do BFS traversal. It uses recursive BFSUtil() 
    void BFS() {
    	
    	// Mark all the vertices as not visited(set as  false by default in java) 
    	boolean visited[] = new boolean[V];
    	
    	// Call the recursive helper function to print BFS traversal 
        // starting from all vertices one by one 
    	for (int i=0; i<V; i++) {
    		if (visited[i] == false)
    			BFSUtil(i, visited);
    	}	
    }
    
    // find connected components 
    void connectedComponents() { 
    	// Mark all the vertices as not visited 
        boolean[] visited = new boolean[V]; 
        
        for(int v = 0; v < V; ++v) { 
        	if(!visited[v]) {
        		// print all reachable vertices from v
        		 DFSUtil(v,visited);
        		 System.out.println();    		
        	}
        }
    }
    
    public static void main(String args[]) {
    	 Graph g = new Graph(4); 
    	  
         g.addEdge(0, 1); 
         g.addEdge(0, 2); 
         g.addEdge(1, 2); 
         g.addEdge(2, 0); 
         g.addEdge(2, 3); 
        
         
         System.out.println("Following is Depth First Traversal"); 
         
         g.DFS();
         
         System.out.println("");
         
         System.out.println("Following is Breadth First Traversal");
         
         g.BFS();   
         
         
         Graph gNew = new Graph(8);
         
         gNew.addEdge(1, 0);  
         gNew.addEdge(2, 3);  
         gNew.addEdge(3, 4);
         gNew.addEdge(4, 5);
         gNew.addEdge(6, 7);
         
         System.out.println("");
         System.out.println("Connected Components");
         
         gNew.connectedComponents();

    }
}