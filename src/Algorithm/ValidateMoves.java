package Algorithm;

import java.util.ArrayList;

import gui.Gui;

public class ValidateMoves {
	//Lower case letter are Black and Upper case are White
	public static String chessBoard[][]={
			       {"r","k","b","q","a","b","k","r"},
			       {"p","p","p","p","p","p","p","p"},
			       {" "," "," "," "," "," "," "," "},
			       {" "," "," "," "," "," "," "," "},
			       {" "," "," "," "," "," "," "," "},
			       {" "," "," "," "," "," "," "," "},
			       {"P","P","P","P","P","P","P","P"},
			       {"R","K","B","Q","A","B","K","R"}};
	ArrayList<String> moves;
	Pawn pawn;
	
	public ValidateMoves(){
		
	}
	
	//Used to check what piece is grabbed by mouse
	public ArrayList<String> permittedMoves(int sourceX, int sourceY){
		//Gets the current chess board
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
	//Checks if the move is allowed
	public boolean isValid(int newX, int newY, ArrayList<String> moves){
		String currentPos = newX + " " + newY;
		//If the Array List is empty then no possible moves have been calculated
		if(moves.contains(currentPos)){
			return true;
		}
		return false;
	}
}
