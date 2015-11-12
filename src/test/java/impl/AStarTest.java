package impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AStarTest {
	
	private Grid grid;
	private AStar astar;
	
	@Before
	public void setUp() {
		this.grid = new Grid();
		this.grid.setStart(25, 25);
		this.grid.setEnd(50, 50);
		this.astar = new AStar(grid);
	}
	
	@Test
	public void testEuclideanHeuristic() {
		astar.setHeuristic(Heuristic.EUCLIDEAN);
		Node start = grid.getStart();
		astar.computeHeuristic(start);
		assertEquals(35.35, start.heuristic, 0.1);
	}
	
	@Test
	public void testManhattanHeuristic() {
		astar.setHeuristic(Heuristic.MANHATTAN);
		Node start = grid.getStart();
		astar.computeHeuristic(start);
		assertEquals(50.0, start.heuristic, 0.1);
	}

}
