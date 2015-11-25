package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import impl.AStar;
import impl.Grid;
import impl.Heuristic;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class GUI {

	private JFrame frame;
	private JPanel panel;
	private JPanel setup;
	private Grid grid;
	private AStar astar;
	
	public GUI(Grid grid, AStar astar) {
		this.grid = grid;
		this.astar = astar;
		build();
	}

	private void build() {
		frame = new JFrame();
		frame.setTitle("A* + Jump Point Search");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		setup = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		JLabel result = new JLabel("Length: " + grid.getEnd().cost);
		JComboBox<Heuristic> heuristics = new JComboBox<>(Heuristic.values());
		JCheckBox diagonal = new JCheckBox("Diagonal", true);
		
		JButton start = new JButton("Start");
		setup.add(start);
		start.addActionListener(ae -> {
			grid.reset(false);
			grid.allowDiagonal(diagonal.isSelected());
			astar.setHeuristic(Heuristic.values()[heuristics.getSelectedIndex()]);
			astar.findPath();
			result.setText("Length: " 
						    + (grid.getEnd().cost != 0 ? grid.getEnd().cost : "\u221E")
						    + "   Time: " + astar.getTime() + "ms");
			panel.repaint();
		});
		
		JButton reset = new JButton("Reset");
		reset.addActionListener(ae -> {
			grid.reset(false);
			panel.repaint();
		});
		setup.add(reset);
		
		JButton clear = new JButton("Clear");
		clear.addActionListener(ae -> {
			grid.reset(true);
			panel.repaint();
		});
		setup.add(clear);
		
		JButton random = new JButton("Random");
		random.addActionListener(ae -> {
			grid.randomise();
			panel.repaint();
		});
		setup.add(random);
		
		setup.add(heuristics);
		setup.add(diagonal);
		setup.add(result);
		
		frame.add(setup, BorderLayout.NORTH);
		
		panel = new GridPanel(grid);
		frame.add(panel);
		
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	
}
