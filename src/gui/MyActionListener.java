package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyActionListener implements ActionListener {

	int row, column;
	
	public MyActionListener(int i, int j){
		this.row = i;
		this.column = j;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("index in the array: " + row + " : " + column);
	}

}
