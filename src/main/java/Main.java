import impl.AStar;
import impl.Grid;
import impl.JumpPointSearch;
import impl.PathFinding;

import java.awt.EventQueue;

import ui.GUI;


public class Main {
	
	public static void main(String[] args) {
		
		Grid grid = new Grid();
		grid.setStart(10, 10);
		grid.setEnd(90, 90);
		
		for (int i = 5; i < 95; i++) {
			grid.setBlocked(i, 50, true);
			grid.setBlocked(50, i, true);
		}
		
		PathFinding astar = new AStar(grid);
		PathFinding jps = new JumpPointSearch(grid);
		
		EventQueue.invokeLater(() -> new GUI(grid, astar, jps));
		
	}

}
