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
	ArrayList<String> whiteMoves = new ArrayList<String>();
	ArrayList<String> blackMoves = new ArrayList<String>();
	
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
				whiteMoves.addAll(pawn.possibleMoves(sourceX, sourceY, Gui.WHITE, chessBoard));
				//Adds the source of the piece to the possible move to make it easy for the AI
				for (int i = 0; i < moves.size(); i++) {
					String temp = sourceX + " " + sourceY + " " + moves.get(i);
					whiteMoves.set(i, temp);
				}
				break;
			case "R":
				moves = rook.possibleMoves(sourceX, sourceY, Gui.WHITE, chessBoard);
				whiteMoves.addAll(rook.possibleMoves(sourceX, sourceY, Gui.WHITE, chessBoard));
				//Adds the source of the piece to the possible move to make it easy for the AI
				for (int i = 0; i < moves.size(); i++) {
					String temp = sourceX + " " + sourceY + " " + moves.get(i);
					whiteMoves.set(i, temp);
				}
				break;
			case "K":
				moves = knight.possibleMoves(sourceX, sourceY, Gui.WHITE, chessBoard);
				whiteMoves.addAll(knight.possibleMoves(sourceX, sourceY, Gui.WHITE, chessBoard));
				//Adds the source of the piece to the possible move to make it easy for the AI
				for (int i = 0; i < moves.size(); i++) {
					String temp = sourceX + " " + sourceY + " " + moves.get(i);
					whiteMoves.set(i, temp);
				}
				break;
			case "B":
				moves = bishop.possibleMoves(sourceX, sourceY, Gui.WHITE, chessBoard);
				whiteMoves.addAll(bishop.possibleMoves(sourceX, sourceY, Gui.WHITE, chessBoard));
				//Adds the source of the piece to the possible move to make it easy for the AI
				for (int i = 0; i < moves.size(); i++) {
					String temp = sourceX + " " + sourceY + " " + moves.get(i);
					whiteMoves.set(i, temp);
				}
				break;
			case "Q":
				moves = queen.possibleMoves(sourceX, sourceY, Gui.WHITE, chessBoard);
				whiteMoves.addAll(queen.possibleMoves(sourceX, sourceY, Gui.WHITE, chessBoard));
				//Adds the source of the piece to the possible move to make it easy for the AI
				for (int i = 0; i < moves.size(); i++) {
					String temp = sourceX + " " + sourceY + " " + moves.get(i);
					whiteMoves.set(i, temp);
				}
				break;
			case "A":
				moves = king.possibleMoves(sourceX,sourceY, Gui.WHITE, chessBoard);
				whiteMoves.addAll(king.possibleMoves(sourceX, sourceY, Gui.WHITE, chessBoard));
				//Adds the source of the piece to the possible move to make it easy for the AI
				for (int i = 0; i < moves.size(); i++) {
					String temp = sourceX + " " + sourceY + " " + moves.get(i);
					whiteMoves.set(i, temp);
				}
				break;
			case "p":
				moves = pawn.possibleMoves(sourceX, sourceY,Gui.BLACK,chessBoard);
				blackMoves.addAll(pawn.possibleMoves(sourceX, sourceY, Gui.BLACK, chessBoard));
				//Adds the source of the piece to the possible move to make it easy for the AI
				for (int i = 0; i < moves.size(); i++) {
					String temp = sourceX + " " + sourceY + " " + moves.get(i);
					blackMoves.set(i, temp);
				}
				break;
			case "r":
				moves = rook.possibleMoves(sourceX, sourceY, Gui.BLACK, chessBoard);
				blackMoves.addAll(rook.possibleMoves(sourceX, sourceY, Gui.BLACK, chessBoard));
				//Adds the source of the piece to the possible move to make it easy for the AI
				for (int i = 0; i < moves.size(); i++) {
					String temp = sourceX + " " + sourceY + " " + moves.get(i);
					blackMoves.set(i, temp);
				}
				break;
			case "k":
				moves = knight.possibleMoves(sourceX, sourceY, Gui.BLACK, chessBoard);
				blackMoves.addAll(knight.possibleMoves(sourceX, sourceY, Gui.BLACK, chessBoard));
				//Adds the source of the piece to the possible move to make it easy for the AI
				for (int i = 0; i < moves.size(); i++) {
					String temp = sourceX + " " + sourceY + " " + moves.get(i);
					blackMoves.set(i, temp);
				}
				break;
			case "b":
				moves = bishop.possibleMoves(sourceX, sourceY, Gui.BLACK, chessBoard);
				blackMoves.addAll(bishop.possibleMoves(sourceX, sourceY, Gui.BLACK, chessBoard));
				//Adds the source of the piece to the possible move to make it easy for the AI
				for (int i = 0; i < moves.size(); i++) {
					String temp = sourceX + " " + sourceY + " " + moves.get(i);
					blackMoves.set(i, temp);
				}
				break;
			case "q":
				moves = queen.possibleMoves(sourceX, sourceY, Gui.BLACK, chessBoard);
				blackMoves.addAll(queen.possibleMoves(sourceX, sourceY, Gui.BLACK, chessBoard));
				//Adds the source of the piece to the possible move to make it easy for the AI
				for (int i = 0; i < moves.size(); i++) {
					String temp = sourceX + " " + sourceY + " " + moves.get(i);
					blackMoves.set(i, temp);
				}
				break;
			case "a":
				moves = king.possibleMoves(sourceX,sourceY, Gui.BLACK, chessBoard);
				blackMoves.addAll(king.possibleMoves(sourceX, sourceY, Gui.BLACK, chessBoard));
				//Adds the source of the piece to the possible move to make it easy for the AI
				for (int i = 0; i < moves.size(); i++) {
					String temp = sourceX + " " + sourceY + " " + moves.get(i);
					blackMoves.set(i, temp);
				}
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
	
	public void copyArrayList(){
		
	}
	
	public ArrayList<String> possibleWhiteMoves(){
		return whiteMoves;
	}
	
	public ArrayList<String> possibleBlackMoves(){
		return blackMoves;
	}
	
	
}
