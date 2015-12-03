package impl;

import java.util.Arrays;

/**
 * Pathfinding
 */
public abstract class PathFinding {
	
	protected static final float SQRT2 = (float)Math.sqrt(2);

	protected Grid grid;
	protected Heuristic heuristic;
	protected long time;
	protected int iterations;
	protected String algorithm;

	public PathFinding(Grid grid, String algorithm) {
		this.grid = grid;
		this.algorithm = algorithm;
		this.heuristic = Heuristic.EUCLIDEAN;
	}

	/**
	 * Asettaa A*-algoritmin k‰ytt‰m‰n heuristiikkafunktion
	 * 
	 * @param heuristic Heuristiikkafunktio
	 */
	public void setHeuristic(Heuristic heuristic) {
		this.heuristic = heuristic;
	}
	
	/**
	 * Asettaa parametriin heuristiikkafunktion antaman et‰isyyden maalisolmuun
	 * 
	 * @param node Asettaa parametriin heuristiikkafunktion antaman et‰isyyden maalisolmuun
	 */
	protected void computeHeuristic(Node node) {
		node.heuristic = heuristic.getHeuristic(node, grid.getEnd());
	}
	
	/**
	 * P‰ivitt‰‰ kuljetun et‰isyyden solmuun sen edelt‰j‰st‰ siten, ett‰ matka v‰li-ilmansuuntiin 
	 * on sqrt(2); muuten 1
	 * 
	 * @param node Mihin solmuun tullaan
	 * @param previous Mist‰ solmusta tultiin
	 */
	protected void updateCost(Node node, Node previous) {
		if (node.x - previous.x == 0 || node.y - previous.y == 0)
			node.cost = previous.cost + 1;
		else	
			node.cost = previous.cost + SQRT2;
	}
	
	/**
	 * Palauttaa polunetsint‰‰n kuluneen ajan
	 * 
	 * @return Polunetsint‰‰n kulunut aika
	 */
	public long getTime() {
		return time;
	}
	
	/**
	 * Etsii lyhimm‰n polun ruudukossa alkusolmusta maalisolmuun
	 */
	public abstract void findPath();
	
	/**
	 * Luo lyhimm‰n polun ruudukossa maalisolmusta alkusolmuun, jos sellainen on olemassa
	 */
	public Node[] createPath() {
		Node current = grid.getEnd();
		if (current.cost == 0) 
			return null;
		Node[] path = new Node[(int)current.cost + 1];
		int i = 0;
		path[i++] = current;
		while (current != null && current.previous != null && !current.equals(grid.getStart())) {
			Node previous = current.previous;
			int dx = (previous.x - current.x) / Math.max(Math.abs(previous.x - current.x), 1);
			int dy = (previous.y - current.y) / Math.max(Math.abs(previous.y - current.y), 1);
			while (!current.equals(previous)) {
				current = grid.getNode(current.x + dx, current.y + dy);
				path[i++] = current;
			}
		}
		return Arrays.copyOf(path, i);
	}
	
	public String debug() {
		boolean found = grid.getEnd().cost != 0;
		String result = "Algorithm=" + algorithm 
				  	    + "  Path found=" + found
					    + "  Iterations=" + iterations
					    + "  Time=" + time + "ms"
					    + "  Length=" + grid.getEnd().cost 
					    + "  Heuristic=" + heuristic
					    + "  Diagonal=" + grid.isDiagonalAllowed();
		return result;
	}
	
}
