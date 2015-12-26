package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MyActionListener implements ActionListener {
	
	JButton clickButton = null;
	int row, column;
	JButton[][] board;
	ImageIcon mc;
	public MyActionListener(int i, int j, JButton[][] board){
		this.row = i;
		this.column = j;
		this.board = board;
	}
	
	
	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(clickButton == null){
			clickButton = (JButton)e.getSource();
			mc = (ImageIcon) clickButton.getIcon();
			if(mc != null){
				System.out.println("hello");
			}
		}else{
			JButton secondButton = (JButton)e.getSource();
			if(mc != null){
				secondButton.setText("PAWN HERE");
			}
			
			
			clickButton = null;
		}
		System.out.println("index in the array: " + row + " : " + column);
	}

}
