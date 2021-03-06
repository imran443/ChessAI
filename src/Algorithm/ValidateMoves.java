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
	
	
	ArrayList<String> whiteMoves = new ArrayList<String>();
	ArrayList<String> blackMoves = new ArrayList<String>();
	//Used for the king safety 
	ArrayList<String> whiteMovesKing = new ArrayList<>();
	ArrayList<String> blackMovesKing = new ArrayList<>();
	
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
		ArrayList<String> moves = new ArrayList<String>();
		//Gets the current chess board
		switch (chessBoard[sourceX][sourceY]) {
			case "P":
				moves = pawn.possibleMoves(sourceX, sourceY,Gui.WHITE,chessBoard);
				copyArrayList(moves, whiteMoves, sourceX, sourceY);
				copyArrayListKing(moves, whiteMovesKing);
				break;
			case "R":
				moves = rook.possibleMoves(sourceX, sourceY, Gui.WHITE, chessBoard);
				copyArrayList(moves, whiteMoves, sourceX, sourceY);
				copyArrayListKing(moves, whiteMovesKing);
				break;
			case "K":
				moves = knight.possibleMoves(sourceX, sourceY, Gui.WHITE, chessBoard);
				copyArrayList(moves, whiteMoves, sourceX, sourceY);
				copyArrayListKing(moves, whiteMovesKing);
				break;
			case "B":
				moves = bishop.possibleMoves(sourceX, sourceY, Gui.WHITE, chessBoard);
				copyArrayList(moves, whiteMoves, sourceX, sourceY);
				copyArrayListKing(moves, whiteMovesKing);
				break;
			case "Q":
				moves = queen.possibleMoves(sourceX, sourceY, Gui.WHITE, chessBoard);
				copyArrayList(moves, whiteMoves, sourceX, sourceY);
				copyArrayListKing(moves, whiteMovesKing);
				break;
			case "A":
				moves = king.possibleMoves(sourceX,sourceY, Gui.WHITE, chessBoard);
				copyArrayList(moves, whiteMoves, sourceX, sourceY);
				copyArrayListKing(moves, whiteMovesKing);
				break;
			case "p":
				moves = pawn.possibleMoves(sourceX, sourceY,Gui.BLACK,chessBoard);
				copyArrayList(moves, blackMoves, sourceX, sourceY);
				copyArrayListKing(moves, blackMovesKing);
				break;
			case "r":
				moves = rook.possibleMoves(sourceX, sourceY, Gui.BLACK, chessBoard);
				copyArrayList(moves, blackMoves, sourceX, sourceY);
				copyArrayListKing(moves, blackMovesKing);
				break;
			case "k":
				moves = knight.possibleMoves(sourceX, sourceY, Gui.BLACK, chessBoard);
				copyArrayList(moves, blackMoves, sourceX, sourceY);
				copyArrayListKing(moves, blackMovesKing);
				break;
			case "b":
				moves = bishop.possibleMoves(sourceX, sourceY, Gui.BLACK, chessBoard);
				copyArrayList(moves, blackMoves, sourceX, sourceY);
				copyArrayListKing(moves, blackMovesKing);
				break;
			case "q":
				moves = queen.possibleMoves(sourceX, sourceY, Gui.BLACK, chessBoard);
				copyArrayList(moves, blackMoves, sourceX, sourceY);
				copyArrayListKing(moves, blackMovesKing);
				break;
			case "a":
				moves = king.possibleMoves(sourceX,sourceY, Gui.BLACK, chessBoard);
				copyArrayList(moves, blackMoves, sourceX, sourceY);
				copyArrayListKing(moves, blackMovesKing);
				break;
			default:
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
	
	

	public void copyArrayList(ArrayList<String> list, ArrayList<String> tempList, int sourceX, int sourceY){
		ArrayList<String> newList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			String s = list.get(i);
			newList.add(s);
		}
		for (int i = 0; i < newList.size(); i++) {
			String temp = sourceX + " " + sourceY + " " + newList.get(i);
			tempList.add(temp);
		}
	}
	//Used for the king check mate func
	public void copyArrayListKing(ArrayList<String> list, ArrayList<String> tempList){
		ArrayList<String> newList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			String s = list.get(i);
			newList.add(s);
		}
		for (int i = 0; i < newList.size(); i++) {
			String temp = newList.get(i);
			tempList.add(temp);
		}
	}
	
	public ArrayList<String> possibleWhiteMoves(){
		return whiteMoves;
	}
	
	public ArrayList<String> possibleBlackMoves(){
		return blackMoves;
	}
	
	public ArrayList<String> possibleWhiteMovesKing(){
		return whiteMovesKing;
	}
	
	public ArrayList<String> possibleBlackMovesking(){
		return blackMovesKing;
	}
	
	
}
