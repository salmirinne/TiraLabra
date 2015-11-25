package ui;

import impl.Grid;
import impl.Node;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GridPanel extends JPanel {
	
	private static final int NODE_SIZE = 7;
	
	private Grid grid;
	
	public GridPanel(Grid grid) {
		this.grid = grid;
		setPreferredSize(new Dimension(700, 700));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(new Color(35, 35, 35));
		g.fillRect(0, 0, 700, 700);
		
		Node[][] nodes = grid.getGrid();
		g.setColor(Color.BLACK);
		for (int x = 0; x < nodes.length; x++) {
			for (int y = 0; y < nodes[0].length; y++) {
				Node node = nodes[x][y];
				g.drawRect(node.x * NODE_SIZE, node.y * NODE_SIZE, NODE_SIZE, NODE_SIZE);
				if (node.blocked) {
					g.setColor(Color.WHITE);
					g.fillRect(node.x * NODE_SIZE + 1, node.y * NODE_SIZE + 1, NODE_SIZE - 1, NODE_SIZE - 1);
					g.setColor(Color.BLACK);
				}
			}
		}
		
		Node start = grid.getStart();
		g.setColor(Color.GREEN);
		g.fillRect(start.x * NODE_SIZE + 1, start.y * NODE_SIZE + 1, NODE_SIZE - 1, NODE_SIZE - 1);
		
		g.setColor(Color.BLUE);
		Node end = grid.getEnd();
		Node current = end;
		while (current != null && !current.equals(grid.getStart())) {
			g.fillRect(current.x * NODE_SIZE + 1, current.y * NODE_SIZE + 1, NODE_SIZE - 1, NODE_SIZE - 1);
			current = current.previous;
		}
		
		g.setColor(Color.RED);
		g.fillRect(end.x * NODE_SIZE + 1, end.y * NODE_SIZE + 1, NODE_SIZE - 1, NODE_SIZE - 1);
	}

}
