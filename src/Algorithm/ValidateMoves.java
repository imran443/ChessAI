package Algorithm;

import java.util.ArrayList;

import gui.Gui;

public class ValidateMoves {
	Gui gui;
	//Chess board from GUI
	String [][] chessBoard;
	ArrayList<String> moves;
	Pawn pawn;
	
	public ValidateMoves(){
		gui = new Gui();
	}
	
	//Used to check what piece is grabbed by mouse
	public ArrayList<String> permittedMoves(int sourceX, int sourceY){
		//Gets the current chess board
		chessBoard = gui.returnChessboard();
		switch (chessBoard[sourceX][sourceX]) {
			case "P":
				moves = pawn.possibleMoves(sourceX, sourceY,Gui.WHITE,chessBoard);
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
			//Return array list with all moves  
			return moves;
			
		}
		
		//Gets the move list
		public ArrayList<String> getMoveList(){
			return moves;
		}
}
