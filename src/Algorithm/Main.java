package Algorithm;

import java.awt.BorderLayout;
import gui.Gui;
import javax.swing.JFrame;

public class Main {
	Gui gui;
	//Chess board from GUI
	String [][] chessBoard;
	Pawn pawn;
	
	public Main(){
		JFrame frame = new JFrame("Chess");
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui = new Gui();
		frame.getContentPane().add(gui.tools, BorderLayout.PAGE_START);
		frame.getContentPane().add(gui, BorderLayout.CENTER);
		frame.setVisible(true);		
	}
	
	//Used to check what piece is grabbed by mouse
	public String permittedMoves(int a, int b){
		//Gets the current chess board
		chessBoard = gui.returnChessboard();
		switch (chessBoard[a][b]) {
		case "P":
			
			break;
		case "R":
			break;
		case "K":
			break;
		case "B":
			break;
		case "Q":
			break;
		case "A":
			break;
		case "p":
			break;
		case "r":
			break;
		case "k":
			break;
		case "b":
			break;
		case "q":
			break;
		case "a":
			break;
		default:
			System.out.println("Not a real piece");
			break;
		}
		return null;
		
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		new Main();
	}

}
