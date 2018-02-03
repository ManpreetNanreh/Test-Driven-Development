package tdd;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class Graph<T> implements GraphInterface<T> {
	//Size variables.
	private int maxSize;
	private int currentSize;
  
	//Graph Variables.
	ArrayList<Edge<T>> edges;
	ArrayList<T> nodes;
	boolean[] visited;
  
	/***
	* Constructor for the graph.
	* @param maxSize The capacity of the graph.
	*/
	public Graph(int maxSize){
		edges = new ArrayList<Edge<T>>();
		nodes = new ArrayList<T>();
		this.maxSize = maxSize;
		clearMarks();
	}
  
	@Override
	/***
	* Check size variables to determine amount of objects in the Graph.
	*/
	public boolean isEmpty() {
		return this.currentSize == 0;
	}

	@Override
	/***
	* Check size variables to determine amount of objects in the Graph.
	*/
	public boolean isFull() {
		return this.currentSize == maxSize;
	}

	@Override
	/***
	* Adds the vertex to the graph. Throws exceptions if the vertex already exists,
	* or if the graph is full.
	*/
	public void addVertex(T vertex) throws GraphIsFullException, VertexExistsException{
		if(this.currentSize == maxSize){
			throw new GraphIsFullException("Tried to add vertex to full graph.");
		}
		if(nodes.contains(vertex)){
			throw new VertexExistsException("Vertex already exists in graph.");
		}
  
		//If we get here, then all the requirements to create a new vertex have been met.
		nodes.add(vertex);
	}

	@Override
	/***
	* Create a new Edge object to add to the graph. Cannot add edge that leads to itself,
	* or if it already exists.
	*/
	public void addEdge(T fromVertex, T toVertex) {
		//Can't add vertex that leads to self.
		if(!fromVertex.equals(toVertex)){
			//Undirected graph
			Edge<T> candidateEdge = new Edge<T>(fromVertex, toVertex);
			//Can't add already existing edges.
			if(!edgeContained(candidateEdge)){   
				edges.add(candidateEdge);
			}
		}
	}

	/**This method checks if the edge we are adding already exists in 
	* the graph in either direction.
	* If it exists then return true otherwise return false.
	*/
	public boolean edgeContained(Edge<T> A){
		for(Edge<T> e: edges){
			if(e.getFrom().equals(A.getFrom()) && e.getTo().equals(A.getTo())){
				return true;
			}
			if(e.getTo().equals(A.getFrom()) && e.getFrom().equals(A.getTo())){
				return true;
			}
		}
		return false;
	}

	@Override
	/***
	* This method adds to a Queue all connected nodes to the given vertex.
	* Does not check if node is already visited.
	*/
	public Queue<T> getToVertices(T vertex) {
		LinkedList<T> neighbours = new LinkedList<T>();
		//Check every edge, and if it contains the 'from' vertex, then add the 'to'
		//to the return and vice versa.
		for(Edge<T> edge : edges){
			if(edge.getFrom().equals(vertex)){
				neighbours.add(edge.getTo());
			}
			if(edge.getTo().equals(vertex)){
				neighbours.add(edge.getFrom()); 
			}
		}

		return neighbours;
	}

	@Override
	/***
	* Must only be called within DFSVisit.
	* Clear the marks for all the vertices by setting them to false.
	*/
	public void clearMarks() {
		visited = new boolean[maxSize];
	}

	@Override
	/***
	* Must only be called within DFSVisit.
	* Set the mark for the current vertex to true.
	*/
	public void markVertex(T vertex) {
		visited[nodes.indexOf(vertex)] = true;
	}

	@Override
	/***
	* Must only be called within DFSVisit.
	* Check if a vertex is marked.
	*/
	public boolean isMarked(T vertex) {
		return visited[nodes.indexOf(vertex)];
	}

	public Set<T> DFSVisit(T startVertex){
	// Uses depth-first search algorithm to visit as much vertices as
	// possible starting from startVertex.
	// In the process, keeping track of all vertices reachable from startVertex
	// by adding them to a Set<T> variable.
	// Once done, returns the Set<T> that we build up.
	// This is precisely the connected component that contains the vertex startVertex.
    
		//To store return.
		TreeSet<T> result = new TreeSet<T>();

		clearMarks();
		//Required to traverse
		Stack<T> s = new Stack<T>();
		s.push(startVertex);

		while (!s.empty()){
			T node = s.pop();
			result.add(node);
			markVertex(node);
	    
			for(T neighbour : getToVertices(node)){
				if(!isMarked(neighbour)){
					markVertex(neighbour);
					s.push(neighbour);
				}
			}
		}
		return result;
	}
  
	/***
	* Detect all distinct connected components.
	* 
	* @return ArrayList of unique connected components.
	*/
	public ArrayList<Set<T>> connectedComponents(){
		ArrayList<Set<T>> components = new ArrayList<Set<T>>();

		for(T node : nodes){
        //Determine if node is already in a component
			boolean shouldSearch = true;
          
			for(Set<T> component : components){
				if(component.contains(node)){
					shouldSearch = false;
				}
			}
			if(shouldSearch){
				components.add(DFSVisit(node));
			}
		}
		return components;
	}
	
	/***
	* Class to store edges in Graph.
	*
	* @param <T> Graph type;
	*/
	class Edge<T>{
		private T from;
		private T to;
  
		public Edge(T from,T to){
			this.from = from;
			this.to = to;
		}
  
		public T getFrom(){
			return this.from;
		}
		
		public T getTo(){
			return this.to;
		}
	}
}
