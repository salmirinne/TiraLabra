package impl;

import java.util.ArrayList;
import java.util.List;

/**
 * Ruudukko, jossa polunetsintä suoritetaan
 */
public class Grid {
	
	/**
	 * Ruudukon sivun pituus
	 */
	private static final int GRID_SIZE = 100;
	
	private Node[][] grid;
	private Node start;
	private Node end;
	
	public Grid() {
		grid = new Node[GRID_SIZE][GRID_SIZE];
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[x].length; y++) {
				grid[x][y] = new Node(x, y);
			}
		}
	}
	
	public void setStart(int x, int y) {
		start = this.grid[x][y];
	}
	
	public Node getStart() {
		return start;
	}
	
	public void setEnd(int x, int y) {
		end = grid[x][y];
	}
	
	public Node getEnd() {
		return end;
	}
	
	public void setBlocked(int x, int y, boolean blocked) {
		grid[x][y].blocked = blocked;
	}
	
	/**
	 * 
	 * 
	 * @param node Annettu solmu
	 * @return Annetun solmun naapurit ruudukossa joihin on mahdollista siirtyä
	 */
	public Node[] getNeighbours(Node node) {
		List<Node> neighbours = new ArrayList<>(8);
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				if (!inBounds(node.x + x, node.y + y))
					continue;
				if (x == 0 && y == 0)
					continue;
				if (!grid[node.x + x][node.y + y].blocked) {
					neighbours.add(grid[node.x + x][node.y + y]);
				}
			}
		}
		return neighbours.toArray(new Node[0]);
	}
	
	private boolean inBounds(int x, int y) {
		return x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE;
	}
	
}
