package Algorithm;

import java.awt.BorderLayout;

import gui.Gui;

import javax.swing.JFrame;

public class Main {
	Gui gui_button;
	//Chess board from GUI
	String [][] chessBoard;
	
	public Main(){
		JFrame frame = new JFrame("Chess");
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Gui gui_button = new Gui();
		frame.getContentPane().add(gui_button.tools, BorderLayout.PAGE_START);
		frame.getContentPane().add(gui_button, BorderLayout.CENTER);
		frame.setVisible(true);
	}
	
	
	
	public static void main(String[] args) {
		new Main();
	}

}
