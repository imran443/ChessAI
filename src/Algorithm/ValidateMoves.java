package Algorithm;

import java.util.ArrayList;

import gui.Gui;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;

public class ValidateMoves {
	
	ArrayList<String> moves = new ArrayList<String>();
	Pawn pawn;

	King king;
	Bishop bishop;
	Knight knight;
	Queen queen;
	Rook rook;
	
	public ValidateMoves(){
		pawn = new Pawn();
		king = new King();
		bishop = new Bishop();
		knight = new Knight();
		queen = new Queen();
		rook = new Rook();
	}
	
	//Used to check what piece is grabbed by mouse
	public ArrayList<String> permittedMoves(int sourceX, int sourceY, String[][] chessBoard){
		//Gets the current chess board
		switch (chessBoard[sourceX][sourceY]) {
			case "P":
				moves = pawn.possibleMoves(sourceX, sourceY,Gui.WHITE,chessBoard);
				break;
			case "R":
				moves = rook.possibleMoves(sourceX, sourceY, Gui.WHITE, chessBoard);
				break;
			case "K":
				moves = knight.possibleMoves(sourceX, sourceY, Gui.WHITE, chessBoard);
				break;
			case "B":
				moves = bishop.possibleMoves(sourceX, sourceY, Gui.WHITE, chessBoard);
				break;
			case "Q":
				moves = queen.possibleMoves(sourceX, sourceY, Gui.WHITE, chessBoard);
				break;
			case "A":
				moves = king.possibleMoves(sourceX,sourceY, Gui.WHITE, chessBoard);
				break;
			case "p":
				moves = pawn.possibleMoves(sourceX, sourceY,Gui.BLACK,chessBoard);
				break;
			case "r":
				moves = rook.possibleMoves(sourceX, sourceY, Gui.BLACK, chessBoard);
				break;
			case "k":
				moves = knight.possibleMoves(sourceX, sourceY, Gui.BLACK, chessBoard);
				break;
			case "b":
				moves = bishop.possibleMoves(sourceX, sourceY, Gui.BLACK, chessBoard);
				break;
			case "q":
				moves = queen.possibleMoves(sourceX, sourceY, Gui.BLACK, chessBoard);
				break;
			case "a":
				moves = king.possibleMoves(sourceX,sourceY, Gui.BLACK, chessBoard);
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
			moves.clear();
			return true;
		}
		return false;
	}
}
