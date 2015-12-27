package Algorithm;

import java.awt.BorderLayout;

import gui.Gui;
import javax.swing.JFrame;

public class Main {
	Gui gui;
	
	public Main(){
		JFrame frame = new JFrame("Chess");
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui = new Gui();
		frame.getContentPane().add(gui.tools, BorderLayout.PAGE_START);
		frame.getContentPane().add(gui, BorderLayout.CENTER);
		frame.setVisible(true);		
	}

	public static void main(String[] args) {
		new Main();
	}

}
