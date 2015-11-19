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
		astar = new AStar(grid);
		astar.setHeuristic(Heuristic.EUCLIDEAN);
	}
	
	@Test
	public void testEuclideanHeuristic() {
		grid.setStart(25, 25);
		grid.setEnd(50, 50);
		astar.setHeuristic(Heuristic.EUCLIDEAN);
		Node start = grid.getStart();
		astar.computeHeuristic(start);
		assertEquals(35.35, start.heuristic, 0.1);
	}
	
	@Test
	public void testManhattanHeuristic() {
		grid.setStart(25, 25);
		grid.setEnd(50, 50);
		astar.setHeuristic(Heuristic.MANHATTAN);
		Node start = grid.getStart();
		astar.computeHeuristic(start);
		assertEquals(50.0, start.heuristic, 0.1);
	}
	
	/*
	 * Tällä hetkellä vain tulosteen tarkastelua
	 */
	@Test
	public void testPathFinding() {
		/* . . . . . . .  
		 * . . . . . . . 
		 * . . S @ @ @ .
		 * . . . @ E . .
		 * . . . @ @ @ .
		 * . . . . . . .
		 */
		grid.setStart(2, 2);
		grid.setEnd(3, 4);
		grid.setBlocked(2, 3, true);
		grid.setBlocked(2, 4, true);
		grid.setBlocked(2, 5, true);
		grid.setBlocked(3, 3, true);
		grid.setBlocked(4, 3, true);
		grid.setBlocked(4, 4, true);
		grid.setBlocked(4, 5, true);
		astar.findPath();
		
	}
	
}
