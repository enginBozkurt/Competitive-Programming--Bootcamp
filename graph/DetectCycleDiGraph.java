package com.graph;


import java.util.LinkedList;
import java.util.Stack;

/* 
 Use DFS(Depth-First Search) to detect the back edge
	Do the DFS from each vertex
	For DFS from each vertex, keep track of visiting vertices in a recursion stack (array).
	If you encounter a vertex which already present in recursion stack then we have found a cycle.
	Use visited[] for DFS to keep track of already visited vertices.
	
How different is recursion stack[] from visitied [].

	Visited[] is used to keep track of already visited vertices during the DFS is never gets
	Recursion stack[] is used from keep track of visiting vertices during DFS from particular 
	vertex and gets reset once cycle is not found from that vertex and will try DFS from other vertices.
 * */

public class DetectCycleDiGraph {
	private int V; //number of vertices
	private LinkedList<Integer>[] adj; //adjacency list
	 
	//constructor method
	public DetectCycleDiGraph(int v) {
		this.V = v;
		adj = new LinkedList[V];
		for (int i=0; i < V; i++)
			adj[i] = new LinkedList<Integer>();	
	}
	
	// Function to add an edge into the graph 
	void addEdge(int v, int w) {
		adj[v].add(w);
	}
	
	//Detect Cycle in a Directed Graph
	private boolean isCyclicUtil(int v, boolean[] visited,
										boolean[] recStack) {
		
		//mark the current node visited and add it to the recStack
		visited[v] = true;
		recStack[v] = true;
		
		for(int w: adj[v]) {
			
			//if not visited
			if (!visited[w]) {

				 isCyclicUtil(w, visited,recStack);
			}
			
			else if (recStack[w]) {	
				return true;
			}					 
		}
		
		 //if reached here means cycle has not found in DFS from this vertex
        //reset
		recStack[v] = false;
		
        return false;		
	}
	
	 private boolean isCyclic()  { 

        boolean[] visited = new boolean[V]; 
        boolean[] recStack = new boolean[V]; 
          
        // do DFS from each node 
        // Call the recursive helper function to 
        // detect cycle in different DFS trees 
        for (int i = 0; i < V; i++) 
            if (isCyclicUtil(i, visited, recStack)) 
                return true; 
  
        
        return false; 
	 }
	 
	// Driver code 
	public static void main(String[] args) {
		
		DetectCycleDiGraph graph = new DetectCycleDiGraph(6); 
		graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 2);
        
          
        if(graph.isCyclic()) {
            System.out.println("Graph contains cycle"); 
        	
        }
        else
            System.out.println("Graph doesn't "
                                    + "contain cycle"); 
	    } 	
}
