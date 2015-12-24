package gui;

import java.awt.*;

import javax.swing.*;

public class Gui extends JPanel {
	
	
	JButton[][] board = new JButton[8][8];
	
	
	public Gui(){
		setBackground(Color.CYAN);
		setLayout(new GridLayout(8, 8));
		
		
		for(int i=0; i<board.length; i++){
			for(int j=0; j<board[i].length; j++){
				if((i%2 == 0 && j % 2 == 0) || (i%2 == 1 && j%2==1)){
					JButton button = new JButton();
					button.setBackground(Color.WHITE);
					add(button);
					board[i][j] = button;
				}else{
					JButton button = new JButton();
					button.setBackground(Color.BLACK);
					add(button);
					board[i][j] = button;
				}
			}
		}
		
	}
	
	
	
	
}
