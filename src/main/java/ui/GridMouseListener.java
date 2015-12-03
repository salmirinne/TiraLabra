package ui;

import impl.Grid;
import impl.Node;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GridMouseListener extends MouseAdapter {
	
	private GridPanel panel;
	private Grid grid;
	private Node block = new Node(-1, -1);
	private boolean movingStart;
	private boolean movingEnd;
	
	public GridMouseListener(GridPanel panel, Grid grid) {
		this.panel = panel;
		this.grid = grid;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Node node = panel.getPosition(e);
		node.blocked = !node.blocked;
		panel.repaint();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		Node node = panel.getPosition(e);
		if (node != null) {
			if (!movingStart && !movingEnd) {
				if (!block.equals(node)) {
					block = node;
					node.blocked = !node.blocked;
				}
			} else if (movingStart) {
				grid.setStart(node.x, node.y);
			} else {
				grid.setEnd(node.x, node.y);
			}
		}
		panel.repaint();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		Node node = panel.getPosition(e);
		if (node != null) {
			if (node.equals(grid.getStart())) {
				movingStart = true;
			} else if (node.equals(grid.getEnd())) {
				movingEnd = true;
			} 
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		Node node = panel.getPosition(e);
		if (node != null) {
			if (movingStart) {
				movingStart = false;
			} else if (movingEnd) {
				movingEnd = false;
			}
		}
		panel.repaint();
	}

}
