package Algorithm;

import java.util.ArrayList;

import gui.Gui;

public class Pawn extends Piece{
	ArrayList<String> moves = new ArrayList<String>();
	
	public ArrayList<String> possibleMoves(int sourceX, int sourceY, int pieceColor, String[][] chessBoard){
		//The possible moves 
		String pMove="";
		//The new X coordinate for the piece
		int newX;
		int newY;
		
		if(pieceColor == Gui.WHITE){
			if(chessBoard[sourceX+1][sourceY].equals(" ")){
				newX = sourceX + 1;
				pMove = newX + " " + sourceY;
				moves.add(pMove);
			}
			//If the two spots in front of it are empty and it is in the original position it can jump two spots
			if(chessBoard[sourceX+2][sourceY].equals(" ") && chessBoard[sourceX+1][sourceY].equals(" ") && sourceX==1){
				newX = sourceX + 2;
				pMove = newX + " " + sourceY;
				moves.add(pMove);
			}
			//Can capture a black piece if itself is white piece in the diagonal
			if(chessBoard[sourceX+1][sourceY+1].equals("p") && pieceColor == Gui.WHITE){
				newX = sourceX + 1;
				newY = sourceY + 1;
				pMove = newX + " " + newY;
				moves.add(pMove);
			}
			if(chessBoard[sourceX+1][sourceY-1].equals("p") && pieceColor == Gui.WHITE){
				newX = sourceX + 1;
				newY = sourceY - 1;
				pMove = newX + " " + newY;
				moves.add(pMove);
			}
		}
		if(pieceColor == Gui.BLACK){
			if(chessBoard[sourceX-1][sourceY].equals(" ")){
				newX = sourceX - 1;
				pMove = newX + " " + sourceY;
				moves.add(pMove);
			}
			//If the two spots in front of it are empty and it is in the original position it can jump two spots
			if(chessBoard[sourceX - 2][sourceY].equals(" ") && chessBoard[sourceX - 1][sourceY].equals(" ") && sourceX==6){
				newX = sourceX - 2;
				pMove = newX + " " + sourceY;
				moves.add(pMove);
			}
			//Can capture a white piece if itself is black piece in the diagonal
			if(chessBoard[sourceX - 1][sourceY - 1].equals("P") && pieceColor == Gui.BLACK){
				newX = sourceX - 1;
				newY = sourceY - 1;
				pMove = newX + " " + newY;
				moves.add(pMove);
			}
			if(chessBoard[sourceX - 1][sourceY + 1].equals("P") && pieceColor == Gui.BLACK){
				newX = sourceX - 1;
				newY = sourceY + 1;
				pMove = newX + " " + newY;
				moves.add(pMove);
			}
		}
		return moves;
	}
	
	public boolean isValid(int sourceX, int sourceY, ArrayList<String> moves){
		String currentPos =""+ sourceX + " " + sourceY;
		//If the Array List is empty then no possible moves have been calculated
		if(moves.isEmpty()){
			return true;
		}else if(moves.contains(currentPos)){
			return true;
		}
		
		return false;
		
	}
}
