package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

public class Gui extends JPanel implements MouseListener, MouseMotionListener{
	
	// board of buttons
	JButton[][] board = new JButton[8][8];
	// menubar
	JToolBar tools = new JToolBar();
	
	// Button for menu bar
	JButton new_game = new JButton("New Game");
	JButton save = new JButton("Save");
	JButton reset = new JButton("Reset");
	
	public Gui(){
		// menu bar
		setBorder(new EmptyBorder(15,15,15,15));
		tools.setFloatable(false);
		tools.add(new_game);
		tools.addSeparator();
		tools.add(save);
		tools.addSeparator();
		tools.add(reset);
		
		// background
		setBackground(Color.GRAY);
		setSize(600,600);
		
		setLayout(new GridLayout(8, 8));
		
		// chess board
		for(int i=0; i<board.length; i++){
			for(int j=0; j<board[i].length; j++){
				// alternate button have black and white background
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
		
		
		// places pawns on chess board
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
}
