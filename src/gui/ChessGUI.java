package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class ChessGUI {

	public static void main(String[] args){
		JFrame frame = new JFrame("Chess");
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Gui gui_button = new Gui();
		frame.getContentPane().add(gui_button.tools, BorderLayout.PAGE_START);
		frame.getContentPane().add(gui_button, BorderLayout.CENTER);
		
		frame.setVisible(true);
	}


}
