package impl;

import java.util.Arrays;
import java.util.Random;

/**
 * Ruudukko, jossa polunetsintä suoritetaan
 */
public class Grid {
	
	/**
	 * Ruudukon leveys
	 */
	public static final int WIDTH = 100;
	
	/**
	 * Ruudukon korkeus
	 */
	public static final int HEIGHT = 100;
	
	private Node[][] grid;
	private Node start;
	private Node end;
	
	/**
	 * Sallitaanko siirtyminen väli-ilmansuuntiin
	 */
	private boolean allowDiagonal;
	
	public Grid() {
		grid = new Node[WIDTH][HEIGHT];
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[0].length; y++) {
				grid[x][y] = new Node(x, y);
			}
		}
	}

	public void setStart(int x, int y) {
		start = grid[x][y];
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
	
	public Node getNode(int x, int y) {
		return inBounds(x, y) ? grid[x][y] : null;
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
	 * Onko mahdollista siirtyä parametreina annettuun solmuun
	 * 
	 * @return true, jos solmuun on mahdollista siirtyä
	 */
	public boolean isWalkable(int x, int y) {
		Node node = getNode(x, y);
		return node != null ? !node.blocked : false;
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
				Node node = grid[x][y];
				node.cost = 0;
				node.heuristic = 0;
				if (clearBlocks) 
					node.blocked = false;
				node.opened = false;
				node.closed = false;
				node.previous = null;
			}
		}
	}
	
	/**
	 * Lisää max. 1000 uutta estettä ruudukkoon
	 */
	public void randomise() {
		Random random = new Random();
		for (int i = 0; i < 1000; i++) {
			setBlocked(random.nextInt(WIDTH), random.nextInt(HEIGHT), true);
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
		int i = 0;
		Node[] neighbours = new Node[8];
		if (allowDiagonal) {
			for (int x = -1; x <= 1; x++) {
				for (int y = -1; y <= 1; y++) {
					if (!isWalkable(node.x + x, node.y + y))
						continue;
					if (x != 0 && y != 0 && !validDiagonal(node.x, node.y, x, y))
						continue;
					if (x == 0 && y == 0)
						continue;
					neighbours[i++] = grid[node.x + x][node.y + y];
				}
			}
			return Arrays.copyOf(neighbours, i);
		}
		if (isWalkable(node.x, node.y - 1)) {
			neighbours[i++] = grid[node.x][node.y - 1];
		}
		if (isWalkable(node.x + 1, node.y)) {
			neighbours[i++] = grid[node.x + 1][node.y];
		}
		if (isWalkable(node.x, node.y + 1)) {
			neighbours[i++] = grid[node.x][node.y + 1];
		}
		if (isWalkable(node.x - 1, node.y)) {
			neighbours[i++] = grid[node.x - 1][node.y];
		}
		return Arrays.copyOf(neighbours, i);
	}
	
	private boolean inBounds(int x, int y) {
		return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;
	}
	
	private boolean validDiagonal(int x, int y, int dx, int dy) {
		return isWalkable(x + dx, y) || isWalkable(x, y + dy);
	}
	
}
