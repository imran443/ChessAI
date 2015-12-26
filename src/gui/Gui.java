package gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Gui extends JPanel {
	//Lower case letter are White and Upper case are Black
	public static String chessBoard[][]={
		        {"r","k","b","q","a","b","k","r"},
		        {"p","p","p","p","p","p","p","p"},
		        {" "," "," "," "," "," "," "," "},
		        {" "," "," "," "," "," "," "," "},
		        {" "," "," "," "," "," "," "," "},
		        {" "," "," "," "," "," "," "," "},
		        {"P","P","P","P","P","P","P","P"},
		        {"R","K","B","Q","A","B","K","R"}};
	JButton[][] board = new JButton[8][8];

	public JToolBar tools = new JToolBar();
	int[][] buttonGrid = new int[8][8];
	
	public Gui(){
		// menu bar
		setBorder(new EmptyBorder(15,15,15,15));
		tools.setFloatable(false);
		tools.add(new JButton("New Game"));
		tools.addSeparator();
		tools.add(new JButton("Resign"));
		tools.addSeparator();
		tools.add(new JButton("Reset"));
		
		// background
		setBackground(Color.GRAY);
		setSize(600,600);
		
		setLayout(new GridLayout(8, 8));
								
		//chess board
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
	
	//Gets the chess board
		public String[][] returnChessboard(){
			return chessBoard;
		}
	
	
	
	
	
	
}
