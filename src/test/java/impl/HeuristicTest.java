package impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HeuristicTest {
	
	@Test
	public void testEuclideanHeuristic() {
		Node node1 = new Node(0, 0);
		Node node2 = new Node(10, 10);
		float heuristic = Heuristic.EUCLIDEAN.getHeuristic(node1, node2);
		assertEquals(14.14, heuristic, 0.1);
	}
	
	@Test
	public void testManhattanHeuristic() {
		Node node1 = new Node(0, 0);
		Node node2 = new Node(10, 10);
		float heuristic = Heuristic.MANHATTAN.getHeuristic(node1, node2);
		assertEquals(20.0, heuristic, 0.1);
	}
	
	@Test
	public void testChebyshevHeuristic() {
		Node node1 = new Node(0, 0);
		Node node2 = new Node(10, 10);
		float heuristic = Heuristic.CHEBYSHEV.getHeuristic(node1, node2);
		assertEquals(10.0, heuristic, 0.1);
	}
	
	@Test
	public void testDijkstraHeuristic() {
		Node node1 = new Node(0, 0);
		Node node2 = new Node(10, 10);
		float heuristic = Heuristic.DIJKSTRA.getHeuristic(node1, node2);
		assertEquals(0.0, heuristic, 0.1);
	}

}
