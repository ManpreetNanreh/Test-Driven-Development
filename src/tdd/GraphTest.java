package tdd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.junit.Test;

public class GraphTest {
	
	// Reflection tests
	@Test(timeout=100)
	//@Description(description = "The addVertex() method")
	public void testReflectionMethodAddVertex() {
		try {
			Class<?> c = Graph.class;
			Method m = c.getMethod("addVertex", new Class<?>[]{Object.class});
			Type t = m.getReturnType();
			assertTrue("The return type of addVertex() should be void", 
					t.equals(void.class));
		} catch(NoSuchMethodException e) {
			fail("The addVertex() method is either private or could not be found");
		}
	}

	@Test(timeout=100)
	//@Description(description = "The addEdge() method")
	public void testReflectionMethodAddEdge() {
		try {
			Class<?> c = Graph.class;
			Method m = c.getMethod("addEdge", new Class<?>[]{Object.class,Object.class});
			Type t = m.getReturnType();
			assertTrue("The return type of addEdge() should be void", 
					t.equals(void.class));
		} catch(NoSuchMethodException e) {
			fail("The addEdge() method is either private or could not be found");
		}
	}

	@Test(timeout=100)
	//@Description(description = "The getToVertices() method")
	public void testReflectionGetToVertices() {
		try {
			Class<?> c = Graph.class;
			Method m = c.getMethod("getToVertices", new Class<?>[]{Object.class});
			Type t = m.getReturnType();
			assertTrue("The return type of getToVertices() should be Queue<T>", 
					t.equals(Queue.class));
		} catch(NoSuchMethodException e) {
			fail("The getToVertices() method is either private or could not be found");
		}
	}

	@Test(timeout=100)
	//@Description(description = "The markVertex() method")
	public void testReflectionMarkVertex() {
		try {
			Class<?> c = Graph.class;
			Method m = c.getMethod("markVertex", new Class<?>[]{Object.class});
			Type t = m.getReturnType();
			assertTrue("The return type of markVertex() should be void", 
					t.equals(void.class));
		} catch(NoSuchMethodException e) {
			fail("The markVertex() method is either private or could not be found");
		}
	}

	@Test(timeout=100)
	//@Description(description = "The isMarked() method")
	public void testReflectionIsMarked() {
		try {
			Class<?> c = Graph.class;
			Method m = c.getMethod("isMarked", new Class<?>[]{Object.class});
			Type t = m.getReturnType();
			assertTrue("The return type of isMarked() should be boolean", 
					t.equals(boolean.class));
		} catch(NoSuchMethodException e) {
			fail("The isMarked() method is either private or could not be found");
		}
	}


	@Test(timeout=100)
	//@Description(description = "The DFSVisit() method")
	public void testReflectionConnectedComponents() {
		try {
			Class<?> c = Graph.class;
			Method m = c.getMethod("connectedComponents", new Class<?>[]{});
			Type t = m.getReturnType();
			assertTrue("The return type of connectedComponents() should be ArrayList<Set<T>>", 
					t.equals(ArrayList.class));
		} catch(NoSuchMethodException e) {
			fail("The connectedComponents() method is either private or could not be found");
		}
	}


	@Test(timeout=100)
	//@Description(description = "Functionality tests:0")
	public void testAddVertex0() {
		Graph<String> empty = new Graph<String>(0);
		try{
			empty.addVertex("test");
		}
		catch (GraphIsFullException e)
		{
			return;
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			fail("Exception that graph is full was not thrown!");
		}
		fail("Exception that graph is full was not thrown");
	}


	@Test(timeout=100)
	//@Description(description = "Functionality tests:1")
	public void testAddVertex1() {
		Graph<String> empty = new Graph<String>(3);
		try{
			empty.addVertex("test");
			empty.addVertex("test");
		}
		catch (VertexExistsException e)
		{
			return;
		} catch (GraphIsFullException e) {
			// TODO Auto-generated catch block
			fail("Exception that vertex already exists was not thrown!");
		}
		fail("Exception that vertex already exists was not thrown");
	}

	@Test(timeout=100)
	//@Description(description = "Functionality tests:2")
	public void testGetToVertices1() {
		Graph<String> empty = new Graph<String>(6);
		try{
			empty.addVertex("test1");
			empty.addVertex("test2");
			empty.addVertex("test3");
			empty.addVertex("test7");
			empty.addVertex("test5");
			empty.addVertex("test6");
			empty.addEdge("test3", "test1");
			empty.addEdge("test7", "test1");
			empty.addEdge("test6", "test1");
			empty.addEdge("test6", "test5");
			empty.addEdge("test6", "test2");
			Queue<String> x = empty.getToVertices("test3");

			Queue<String> expected = new LinkedList<String>();
			expected.add("test1");
			assertEquals(x, expected);
		}
		catch (GraphIsFullException e)
		{
			fail("Exception that graph is full was thrown");
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			fail("Exception that vertex already exists was not thrown");
		}


	}

	@Test(timeout=100)
	//@Description(description = "Functionality tests:3")
	public void testGetToVertices2() {
		Graph<String> empty = new Graph<String>(6);
		try{
			empty.addVertex("test1");
			empty.addVertex("test2");
			empty.addVertex("test3");
			empty.addVertex("test7");
			empty.addVertex("test5");
			empty.addVertex("test6");
			empty.addEdge("test3", "test1");
			empty.addEdge("test7", "test1");
			empty.addEdge("test6", "test1");
			empty.addEdge("test6", "test7");
			empty.addEdge("test6", "test5");
			empty.addEdge("test6", "test2");
			Queue<String> x = empty.getToVertices("test6");

			Queue<String> expected = new LinkedList<String>();
			expected.add("test1");
			expected.add("test2");
			expected.add("test7");
			expected.add("test5");
			assertTrue(equalQueues(x,expected));
		}
		catch (GraphIsFullException e)
		{
			fail("Exception that graph is full was thrown");
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			fail("Exception that vertex already exists was not thrown");
		}	
	}

	@Test(timeout=100)
	//@Description(description = "Functionality tests:4")
	public void testMarkVertex1() {
		Graph<String> empty = new Graph<String>(6);
		try{
			empty.addVertex("test1");
			empty.addVertex("test2");
			empty.addVertex("test3");
			empty.addVertex("test7");
			empty.addVertex("test5");
			empty.addVertex("test6");
			empty.addEdge("test3", "test1");
			empty.addEdge("test7", "test1");
			empty.addEdge("test6", "test1");
			empty.addEdge("test6", "test7");
			empty.addEdge("test6", "test5");
			empty.addEdge("test6", "test2");
			empty.markVertex("test6");
			boolean value = empty.isMarked("test6");

			assertEquals("Vertex is not Marked",value,true);
		}
		catch (GraphIsFullException e)
		{
			fail("Exception that graph is full was thrown");
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			fail("Exception that vertex already exists was not thrown");
		}	
	}

	@Test(timeout=100)
	//@Description(description = "Functionality tests:5")
	public void testIsMarked1() {
		Graph<String> empty = new Graph<String>(6);
		try{
			empty.addVertex("test1");
			empty.addVertex("test2");
			empty.addVertex("test3");
			empty.addVertex("test7");
			empty.addVertex("test5");
			empty.addVertex("test6");
			empty.addEdge("test3", "test1");
			empty.addEdge("test7", "test1");
			empty.addEdge("test6", "test1");
			empty.addEdge("test6", "test7");
			empty.addEdge("test6", "test5");
			empty.addEdge("test6", "test2");
			boolean value = empty.isMarked("test6");
			assertEquals("Vertex is Marked",value,false);
		}
		catch (GraphIsFullException e)
		{
			fail("Exception that graph is full was thrown");
		} catch (VertexExistsException e) {
			fail("Exception that vertex already exists was not thrown");
		}	
	}

	@Test(timeout=100)
	//@Description(description = "Functionality tests:6")
	public void testConnectedComponents1() {
		Graph<String> empty = new Graph<String>(7);
		try{
			empty.addVertex("A");
			empty.addVertex("B");
			empty.addVertex("C");
			empty.addVertex("D");
			empty.addVertex("E");
			empty.addVertex("F");
			empty.addVertex("G");
			empty.addEdge("A", "B");
			empty.addEdge("B", "C");
			empty.addEdge("B", "D");
			empty.addEdge("A", "D");
			empty.addEdge("A", "E");
			empty.addEdge("E", "F");
			empty.addEdge("F", "G");
			ArrayList<Set<String>> value = empty.connectedComponents();
			
			ArrayList<Set<String>> expected = new ArrayList<Set<String>>();
			Set<String> set1 = new HashSet<String>();
			set1.add("A");
			set1.add("B");
			set1.add("C");
			set1.add("D");
			set1.add("E");
			set1.add("F");
			set1.add("G");
			expected.add(set1);
			assertTrue(equalArrayLists(value,expected));
		}
		catch (GraphIsFullException e)
		{
			fail("Exception that graph is full was thrown");
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			fail("Exception that vertex already exists was not thrown");
		}	

	}
	
	@Test(timeout=100)
	//@Description(description = "Functionality tests:7")
	public void testConnectedComponents2() {
		Graph<String> empty = new Graph<String>(10);
		try{
			empty.addVertex("A");
			empty.addVertex("B");
			empty.addVertex("C");
			empty.addVertex("D");
			empty.addVertex("E");
			empty.addVertex("F");
			empty.addVertex("G");
			empty.addVertex("H");
			empty.addVertex("I");
			empty.addVertex("J");
			empty.addEdge("A", "B");
			empty.addEdge("B", "C");
			empty.addEdge("B", "D");
			empty.addEdge("A", "D");
			empty.addEdge("A", "E");
			empty.addEdge("E", "F");
			empty.addEdge("F", "G");
			empty.addEdge("I", "J");
			ArrayList<Set<String>> value = empty.connectedComponents();
			
			ArrayList<Set<String>> expected = new ArrayList<Set<String>>();
			Set<String> set1 = new HashSet<String>();
			Set<String> set2 = new HashSet<String>();
			Set<String> set3 = new HashSet<String>();
			set1.add("A");
			set1.add("B");
			set1.add("C");
			set1.add("D");
			set1.add("E");
			set1.add("F");
			set1.add("G");
			set2.add("H");
			set3.add("I");
			set3.add("J");
			expected.add(set1);
			expected.add(set2);
			expected.add(set3);
			assertTrue(equalArrayLists(value,expected));
		}
		catch (GraphIsFullException e)
		{
			fail("Exception that graph is full was thrown");
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			fail("Exception that vertex already exists was not thrown");
		}	

	}
	
	@Test(timeout=100)
	//@Description(description = "Functionality tests:8")
	public void testConnectedComponents3() {
		Graph<String> empty = new Graph<String>(12);
		try{
			empty.addVertex("A");
			empty.addVertex("B");
			empty.addVertex("C");
			empty.addVertex("D");
			empty.addVertex("E");
			empty.addVertex("F");
			empty.addVertex("G");
			empty.addVertex("H");
			empty.addVertex("I");
			empty.addVertex("J");
			empty.addVertex("K");
			empty.addVertex("L");
			empty.addEdge("A", "B");
			empty.addEdge("B", "C");
			empty.addEdge("C", "D");
			empty.addEdge("A", "C");
			empty.addEdge("G", "E");
			empty.addEdge("E", "F");
			empty.addEdge("F", "G");
			empty.addEdge("E", "H");
			empty.addEdge("I", "H");
			empty.addEdge("J", "K");
			ArrayList<Set<String>> value = empty.connectedComponents();
			
			ArrayList<Set<String>> expected = new ArrayList<Set<String>>();
			Set<String> set1 = new HashSet<String>();
			Set<String> set2 = new HashSet<String>();
			Set<String> set3 = new HashSet<String>();
			Set<String> set4 = new HashSet<String>();
			set1.add("A");
			set1.add("B");
			set1.add("C");
			set1.add("D");
			set2.add("E");
			set2.add("F");
			set2.add("G");
			set2.add("H");
			set2.add("I");
			set3.add("J");
			set3.add("K");
			set4.add("L");
			expected.add(set1);
			expected.add(set2);
			expected.add(set3);
			expected.add(set4);
			assertTrue(equalArrayLists(value,expected));
		}
		catch (GraphIsFullException e)
		{
			fail("Exception that graph is full was thrown");
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			fail("Exception that vertex already exists was not thrown");
		}	

	}
	
	@Test(timeout=100)
	//@Description(description = "Functionality tests:9")
	public void testConnectedComponents4() {
		Graph<String> empty = new Graph<String>(9);
		try{
			empty.addVertex("A");
			empty.addVertex("B");
			empty.addVertex("C");
			empty.addVertex("D");
			empty.addVertex("E");
			empty.addVertex("F");
			empty.addVertex("G");
			empty.addVertex("H");
			empty.addVertex("I");
			empty.addEdge("A", "B");
			empty.addEdge("B", "C");
			empty.addEdge("C", "D");
			empty.addEdge("A", "C");
			empty.addEdge("G", "E");
			empty.addEdge("E", "F");
			empty.addEdge("F", "G");
			empty.addEdge("E", "H");
			empty.addEdge("I", "H");
			ArrayList<Set<String>> value = empty.connectedComponents();
			
			ArrayList<Set<String>> expected = new ArrayList<Set<String>>();
			Set<String> set1 = new HashSet<String>();
			Set<String> set2 = new HashSet<String>();
			set1.add("A");
			set1.add("B");
			set1.add("C");
			set1.add("D");
			set2.add("E");
			set2.add("F");
			set2.add("G");
			set2.add("H");
			set2.add("I");
			expected.add(set1);
			expected.add(set2);
			assertTrue(equalArrayLists(value,expected));
		}
		catch (GraphIsFullException e)
		{
			fail("Exception that graph is full was thrown");
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			fail("Exception that vertex already exists was not thrown");
		}	

	}
	
	@Test(timeout=100)	
	//@Description(description = "Functionality tests:10")
	public void testConnectedComponents5() {
		Graph<String> empty = new Graph<String>(9);
		try{
			empty.addVertex("E");
			empty.addVertex("F");
			empty.addVertex("G");
			empty.addVertex("H");
			empty.addVertex("I");
			empty.addVertex("J");
			empty.addVertex("K");
			empty.addEdge("G", "E");
			empty.addEdge("E", "F");
			empty.addEdge("F", "G");
			empty.addEdge("E", "H");
			empty.addEdge("I", "H");
			empty.addEdge("J", "K");
			ArrayList<Set<String>> value = empty.connectedComponents();
			
			ArrayList<Set<String>> expected = new ArrayList<Set<String>>();
			Set<String> set1 = new HashSet<String>();
			Set<String> set2 = new HashSet<String>();
			set2.add("E");
			set2.add("F");
			set2.add("G");
			set2.add("H");
			set2.add("I");
			set1.add("J");
			set1.add("K");
			expected.add(set1);
			expected.add(set2);
			assertTrue(equalArrayLists(value,expected));
		}
		catch (GraphIsFullException e)
		{
			fail("Exception that graph is full was thrown");
		} catch (VertexExistsException e) {
			// TODO Auto-generated catch block
			fail("Exception that vertex already exists was not thrown");
		}	

	}


	static boolean equalQueues(Queue<String> Q1, Queue<String> Q2){
		if(Q1.size() != Q2.size()) { return false; }
		for (String e : Q1)
		{
			if (!Q2.contains(e))
			{
				return false;
			}
		}
		return true;
	}
	
	static boolean equalArrayLists(ArrayList<Set<String>> A1, ArrayList<Set<String>> A2){
		if(A1.size() != A2.size()) { return false; }
		for (Set<String> e : A1)
		{
			if (!A2.contains(e))
			{
				return false;
			}
		}
		return true;
	}
}

