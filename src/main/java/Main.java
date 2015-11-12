import java.awt.EventQueue;

import ui.GUI;


public class Main {
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(() -> {
			// ei n‰yt‰ toistaiseksi juuri mit‰‰n
			new GUI().initialise();
		});
		
	}

}
