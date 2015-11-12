package impl;

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
		this.grid = new Node[GRID_SIZE][GRID_SIZE];
		for (int x = 0; x < this.grid.length; x++) {
			for (int y = 0; y < this.grid[x].length; y++) {
				this.grid[x][y] = new Node(x, y);
			}
		}
	}
	
	public void setStart(int x, int y) {
		this.start = this.grid[x][y];
	}
	
	public Node getStart() {
		return this.start;
	}
	
	public void setEnd(int x, int y) {
		this.end = this.grid[x][y];
	}
	
	public Node getEnd() {
		return this.end;
	}
	
}
