package tdd;

import java.util.Queue;

public interface GraphInterface<T>{
	boolean isEmpty();
	// Return true if this graph is empty; otherwise, return false.

	boolean isFull();
	// Return true if this graph is full; otherwise, return false.

	void addVertex(T vertex) throws GraphIsFullException, VertexExistsException;
	// Preconditions:  Vertex is not already in this graph.
	//                 Vertex is not null.
	// Throws GraphIsFullException if the graph is full.
	// Otherwise adds the vertex to this graph.

	void addEdge(T fromVertex, T toVertex);
	// Add an edge with the specified weight from fromVertex to toVertex.

	Queue<T> getToVertices(T vertex);
	// Return a queue of the vertices that are adjacent from vertex.

	void clearMarks();
	// Sets marks for all vertices to false.

	void markVertex(T vertex);
	// Sets mark for vertex to true.

 	boolean isMarked(T vertex);
	// Return true if the vertex is marked; otherwise, return false.
}
