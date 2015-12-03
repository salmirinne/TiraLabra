package impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class JumpPointSearchTest {
	
	private Grid grid;
	private JumpPointSearch jps;
	
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
		
		jps = new JumpPointSearch(grid);
	}
	
	@Test
	public void testPathFindingWithEuclidean() {
		jps.findPath();
		assertEquals(143.9, grid.getEnd().cost, 0.1);
	}
	
	@Test
	public void testPathFindingWithChebyshev() {
		jps.setHeuristic(Heuristic.CHEBYSHEV);
		jps.findPath();
		assertEquals(143.9, grid.getEnd().cost, 0.1);
	}
	
	@Test
	public void testPathFindingWithDijkstra() {
		jps.setHeuristic(Heuristic.DIJKSTRA);
		jps.findPath();
		assertEquals(143.9, grid.getEnd().cost, 0.1);
	}
	
	@Test
	public void testAStarAndJPS() {
		AStar astar = new AStar(grid);
		for (int i = 0; i < 20; i++) {
			grid.randomise();
			grid.randomise();
			grid.randomise();
			astar.findPath();
			float length = grid.getEnd().cost;
			grid.reset(false);
			jps.findPath();
			assertEquals(length, grid.getEnd().cost, 1.0);
			grid.reset(true);
		}
	}
	
}
