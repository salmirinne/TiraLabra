package impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	
	/**
	 * Sallitaanko siirtyminen väli-ilmansuuntiin
	 */
	private boolean allowDiagonal;
	
	public Grid() {
		grid = new Node[GRID_SIZE][GRID_SIZE];
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[0].length; y++) {
				grid[x][y] = new Node(x, y);
			}
		}
	}
	
	public Node[][] getGrid() {
		return grid;
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
	
	/**
	 * Asetetaan este annettuihin koordinaatteihin
	 * 
	 * @param blocked true, jos solmu on este
	 */
	public void setBlocked(int x, int y, boolean blocked) {
		if (!grid[x][y].equals(start) && !grid[x][y].equals(end)) {
			grid[x][y].blocked = blocked;
		}
	}
	
	/**
	 * Sallitaanko siirtyminen väli-ilmansuuntiin
	 * 
	 * @param allowDiagonal Sallitaanko siirtyminen väli-ilmansuuntiin
	 */
	public void allowDiagonal(boolean allowDiagonal) {
		this.allowDiagonal = allowDiagonal;
	}
	
	public boolean isDiagonalAllowed() {
		return allowDiagonal;
	}
	
	/**
	 * Palauttaa ruudukon solmut alkuarvoihin
	 * 
	 * @param clearBlocks true, jos halutaan poistaa esteet ruudukosta
	 */
	public void reset(boolean clearBlocks) {
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[0].length; y++) {
				grid[x][y].cost = 0;
				grid[x][y].heuristic = 0;
				if (clearBlocks) 
					grid[x][y].blocked = false;
				grid[x][y].opened = false;
				grid[x][y].closed = false;
				grid[x][y].previous = null;
			}
		}
	}
	
	/**
	 * Lisää max. 1000 uutta estettä ruudukkoon
	 */
	public void randomise() {
		Random random = new Random();
		for (int i = 0; i < 1000; i++) {
			setBlocked(random.nextInt(GRID_SIZE), random.nextInt(GRID_SIZE), true);
		}
	}
	
	/**
	 * Palauttaa annetun solmun naapurit ruudukossa joihin on mahdollista siirtyä
	 * 
	 * @param node Annettu solmu
	 * 
	 * @return Annetun solmun naapurit ruudukossa joihin on mahdollista siirtyä
	 */
	public Node[] getNeighbours(Node node) {
		if (allowDiagonal) {
			List<Node> neighbours = new ArrayList<>(8);
			for (int x = -1; x <= 1; x++) {
				for (int y = -1; y <= 1; y++) {
					if (!validNeighbour(node.x + x, node.y + y))
						continue;
					if (x == 0 && y == 0)
						continue;
					neighbours.add(grid[node.x + x][node.y + y]);
				}
			}
			return neighbours.toArray(new Node[0]);
		}
		List<Node> neighbours = new ArrayList<>(4);
		if (validNeighbour(node.x, node.y - 1)) {
			neighbours.add(grid[node.x][node.y - 1]);
		}
		if (validNeighbour(node.x + 1, node.y)) {
			neighbours.add(grid[node.x + 1][node.y]);
		}
		if (validNeighbour(node.x, node.y + 1)) {
			neighbours.add(grid[node.x][node.y + 1]);
		}
		if (validNeighbour(node.x - 1, node.y)) {
			neighbours.add(grid[node.x - 1][node.y]);
		}
		return neighbours.toArray(new Node[0]);
	}
	
	private boolean validNeighbour(int x, int y) {
		return x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE && !grid[x][y].blocked;
	}
	
}
