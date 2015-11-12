package ui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class GUI {

	private JFrame frame;
	private JPanel panel;
	
	public void initialise() {
		frame = new JFrame();
		frame.setTitle("A* + Jump Point Search");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(700, 700));
		frame.add(panel);
		
        frame.pack();
        frame.setVisible(true);
	}
	
}
