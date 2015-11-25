package impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AStarTest {
	
	private Grid grid;
	private AStar astar;
	
	@Before
	public void setUp() {
		grid = new Grid();
		grid.allowDiagonal(true);
		grid.setStart(40, 40);
		grid.setEnd(60, 60);
		
		for (int i = 5; i < 95; i++) {
			grid.setBlocked(i, 50, true);
			grid.setBlocked(50, i, true);
		}
		
		astar = new AStar(grid);
	}
	
	@Test
	public void testPathFindingWithEuclidean() {
		astar.findPath();
		assertEquals(143.9, grid.getEnd().cost, 0.1);
	}
	
	@Test
	public void testPathFindingWithEuclideanNoDiagonal() {
		grid.allowDiagonal(false);
		astar.findPath();
		assertEquals(182.0, grid.getEnd().cost, 0.1);
	}
	
	@Test
	public void testPathFindingWithManhattan() {
		astar.setHeuristic(Heuristic.MANHATTAN);
		astar.findPath();
		assertEquals(143.9, grid.getEnd().cost, 0.1);
	}
	
	@Test
	public void testPathFindingWithManhattanNoDiagonal() {
		grid.allowDiagonal(false);
		astar.setHeuristic(Heuristic.MANHATTAN);
		astar.findPath();
		assertEquals(182.0, grid.getEnd().cost, 0.1);
	}
	
	@Test
	public void testPathFindingWithChebyshev() {
		astar.setHeuristic(Heuristic.CHEBYSHEV);
		astar.findPath();
		assertEquals(143.9, grid.getEnd().cost, 0.1);
	}
	
	@Test
	public void testPathFindingWithChebyshevNoDiagonal() {
		grid.allowDiagonal(false);
		astar.setHeuristic(Heuristic.CHEBYSHEV);
		astar.findPath();
		assertEquals(182.0, grid.getEnd().cost, 0.1);
	}
	
	@Test
	public void testPathFindingWithDijkstra() {
		astar.setHeuristic(Heuristic.DIJKSTRA);
		astar.findPath();
		assertEquals(143.9, grid.getEnd().cost, 0.1);
	}
	
	@Test
	public void testPathFindingWithDijkstraNoDiagonal() {
		grid.allowDiagonal(false);
		astar.setHeuristic(Heuristic.DIJKSTRA);
		astar.findPath();
		assertEquals(182.0, grid.getEnd().cost, 0.1);
	}
	
}
