package impl;

/**
 * A* algoritmin toteutus
 */
public class AStar {

	private Grid grid;
	private Heuristic heuristic;

	public AStar(Grid grid) {
		this.grid = grid;
	}

	/**
	 * Asettaa A*-algoritmin käyttämän heuristiikkafunktion
	 * 
	 * @param heuristic Heuristiikkafunktio
	 */
	public void setHeuristic(Heuristic heuristic) {
		this.heuristic = heuristic;
	}
	
	/**
	 * Asettaa parametriin heuristiikkafunktion antaman etäisyyden maalisolmuun
	 * 
	 * @param node Asettaa parametriin heuristiikkafunktion antaman etäisyyden maalisolmuun
	 */
	public void computeHeuristic(Node node) {
		node.heuristic = heuristic.getHeuristic(node, grid.getEnd());
	}
	
}
