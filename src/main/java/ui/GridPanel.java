package ui;

import impl.Grid;
import impl.Node;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

/**
 * Piirtoalusta polunetsinnän havainnoinnille
 */
public class GridPanel extends JPanel {
	
	private static final int NODE_SIZE = 8;
	
	private Grid grid;
	private Node[] path;
	private boolean showEvaluated;
	
	public GridPanel(Grid grid) {
		this.grid = grid;
		setPreferredSize(new Dimension(NODE_SIZE * Grid.WIDTH, NODE_SIZE * Grid.HEIGHT));
	}
	
	protected Node getPosition(MouseEvent e) {
		int x = e.getX() / NODE_SIZE;
		int y = e.getY() / NODE_SIZE;		
		return grid.getNode(x, y);
	}
	
	protected void setPath(Node[] path) {
		this.path = path;
	}
	
	protected void showEvaluated(boolean showEvaluated) {
		this.showEvaluated = showEvaluated;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(new Color(35, 35, 35));
		g.fillRect(0, 0, NODE_SIZE * Grid.WIDTH, NODE_SIZE * Grid.HEIGHT);
		
		if (path != null) {
			g.setColor(Color.BLUE);
			for (Node node : path) {
				g.fillRect(node.x * NODE_SIZE + 1, node.y * NODE_SIZE + 1, NODE_SIZE - 1, NODE_SIZE - 1);
			}
		}
		
		g.setColor(Color.BLACK);
		for (int x = 0; x < Grid.WIDTH; x++) {
			for (int y = 0; y < Grid.HEIGHT; y++) {
				Node node = grid.getNode(x, y);
				g.setColor(Color.BLACK);
				g.drawRect(node.x * NODE_SIZE, node.y * NODE_SIZE, NODE_SIZE, NODE_SIZE);
				if (node.opened && showEvaluated) {
					g.setColor(Color.MAGENTA);
					g.fillRect(node.x * NODE_SIZE + 1, node.y * NODE_SIZE + 1, NODE_SIZE - 1, NODE_SIZE - 1);
				}
				if (node.blocked) {
					g.setColor(Color.WHITE);
					g.fillRect(node.x * NODE_SIZE + 1, node.y * NODE_SIZE + 1, NODE_SIZE - 1, NODE_SIZE - 1);
				}
			}
		}
		
		Node start = grid.getStart();
		g.setColor(Color.GREEN);
		g.fillRect(start.x * NODE_SIZE + 1, start.y * NODE_SIZE + 1, NODE_SIZE - 1, NODE_SIZE - 1);
		
		Node end = grid.getEnd();
		g.setColor(Color.RED);
		g.fillRect(end.x * NODE_SIZE + 1, end.y * NODE_SIZE + 1, NODE_SIZE - 1, NODE_SIZE - 1);
	}

}
