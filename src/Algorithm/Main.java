package Algorithm;

import gui.Gui;

import javax.swing.JFrame;

public class Main {
	Gui gui_button;
	String [][] chessBoard;
	
	public Main(){
		JFrame frame = new JFrame("Chess");
		chessBoard = new String[8][8];
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui_button = new Gui();
		frame.getContentPane().add(gui_button);;
		frame.setVisible(true);
		
	}
	
	
	public static void main(String[] args) {
		new Main();
	}

}
