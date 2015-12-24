package gui;

import javax.swing.JFrame;

public class ChessGUI {
	public static void main(String[] args){
		JFrame frame = new JFrame("Chess");
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Gui gui_button = new Gui();
		frame.getContentPane().add(gui_button);;
		frame.setVisible(true);
	}
}
