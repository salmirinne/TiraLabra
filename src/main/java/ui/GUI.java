package ui;

import impl.Grid;
import impl.Heuristic;
import impl.PathFinding;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

public class GUI {

	private JFrame frame;
	private Grid grid;
	private PathFinding astar;
	private PathFinding jps;
	private PathFinding pathFinding;
	
	public GUI(Grid grid, PathFinding astar, PathFinding jps) {
		this.grid = grid;
		this.astar = astar;
		this.jps = jps;
		build();
	}

	private void build() {
		frame = new JFrame();
		frame.setTitle("A* + Jump Point Search");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		JPanel setup = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		JPanel debug = new JPanel();
		JLabel result = new JLabel(astar.debug());
		debug.add(result);
		
		GridPanel panel = new GridPanel(grid);
		MouseAdapter listener = new GridMouseListener(panel, grid);
		panel.addMouseListener(listener);
		panel.addMouseMotionListener(listener);
		
		JComboBox<Heuristic> heuristics = new JComboBox<>(Heuristic.values());
		
		ButtonGroup algorithms = new ButtonGroup();
		JRadioButton astarButton = new JRadioButton("A*", true);
		algorithms.add(astarButton);
		JRadioButton jpsButton = new JRadioButton("JPS");
		algorithms.add(jpsButton);
		
		JCheckBox diagonal = new JCheckBox("Diagonal", true);
		JCheckBox evaluated = new JCheckBox("Show evaluated");
		
		JButton start = new JButton("Start");
		setup.add(start);
		start.addActionListener(ae -> {
			grid.reset(false);
			grid.allowDiagonal(diagonal.isSelected());
			pathFinding = astarButton.isSelected() ? astar : jps ;
			pathFinding.setHeuristic(Heuristic.values()[heuristics.getSelectedIndex()]);
			pathFinding.findPath();
			panel.setPath(pathFinding.createPath());
			result.setText(pathFinding.debug());
			debug.repaint();
			panel.repaint();
		});
		
		JButton reset = new JButton("Reset");
		reset.addActionListener(ae -> {
			grid.reset(false);
			panel.setPath(null);
			panel.repaint();
		});
		setup.add(reset);
		
		JButton clear = new JButton("Clear");
		clear.addActionListener(ae -> {
			grid.reset(true);
			panel.setPath(null);
			panel.repaint();
		});
		setup.add(clear);
		
		JButton random = new JButton("Random");
		random.addActionListener(ae -> {
			grid.randomise();
			panel.repaint();
		});
		setup.add(random);
		
		evaluated.addActionListener(ae -> {
			panel.showEvaluated(evaluated.isSelected());
			panel.repaint();
		});
		
		setup.add(astarButton);
		setup.add(jpsButton);
		setup.add(heuristics);
		setup.add(diagonal);
		setup.add(evaluated);
		
		frame.add(setup, BorderLayout.PAGE_START);
		frame.add(debug, BorderLayout.LINE_START);
		frame.add(panel, BorderLayout.PAGE_END);
		
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	
}
