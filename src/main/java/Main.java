import impl.AStar;
import impl.Grid;

import java.awt.EventQueue;

import ui.GUI;


public class Main {
	
	public static void main(String[] args) {
		
		Grid grid = new Grid();
		grid.setStart(40, 40);
		grid.setEnd(60, 60);
		
		for (int i = 5; i < 95; i++) {
			grid.setBlocked(i, 50, true);
			grid.setBlocked(50, i, true);
		}
		
		AStar astar = new AStar(grid);
		
		EventQueue.invokeLater(() -> new GUI(grid, astar));
		
	}

}
