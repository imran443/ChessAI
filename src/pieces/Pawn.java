package pieces;

import java.util.ArrayList;

import Algorithm.Piece;
import gui.Gui;

public class Pawn extends Piece{
	ArrayList<String> moves = new ArrayList<String>();
	//Checks all possible moves for the pawn
	public ArrayList<String> possibleMoves(int sourceX, int sourceY, int pieceColor, String[][] chessBoard){
		//The possible moves 
		String pMove="";
		//The new X coordinate for the piece
		int newX;
		int newY;
		
		if(pieceColor == Gui.BLACK){
			if(chessBoard[sourceX+1][sourceY].equals(" ")){
				newX = sourceX + 1;
				pMove = newX + " " + sourceY;
				moves.add(pMove);
			}
			
			//If the two spots in front of it are empty and it is in the original position it can jump two spots
			if(sourceX == 1)
				if(chessBoard[sourceX+2][sourceY].equals(" ") && chessBoard[sourceX+1][sourceY].equals(" ")){
					newX = sourceX + 2;
					pMove = newX + " " + sourceY;
					moves.add(pMove);
				}
			//Can capture a White piece if itself is black piece in the diagonal
			if(sourceY+1 < 8)
				if(Character.isUpperCase(chessBoard[sourceX+1][sourceY+1].charAt(0)) && pieceColor == Gui.BLACK){
					newX = sourceX + 1;
					newY = sourceY + 1;
					pMove = newX + " " + newY;
					moves.add(pMove);
				}
			if(sourceY-1 >= 0)
				if(Character.isUpperCase(chessBoard[sourceX+1][sourceY-1].charAt(0)) && pieceColor == Gui.BLACK){
					newX = sourceX + 1;
					newY = sourceY - 1;
					pMove = newX + " " + newY;
					moves.add(pMove);
				}
			}
		else if(pieceColor == Gui.WHITE){
			if(chessBoard[sourceX-1][sourceY].equals(" ")){
				newX = sourceX - 1;
				pMove = newX + " " + sourceY;
				moves.add(pMove);
			}
			//If the two spots in front of it are empty and it is in the original position it can jump two spots
			if(sourceX==6)
				if(chessBoard[sourceX - 2][sourceY].equals(" ") && chessBoard[sourceX - 1][sourceY].equals(" ")){
					newX = sourceX - 2;
					pMove = newX + " " + sourceY;
					moves.add(pMove);
				}
			//Can capture a white piece if itself is black piece in the diagonal
			if(sourceY-1 >= 0){
				if(Character.isLowerCase(chessBoard[sourceX - 1][sourceY - 1].charAt(0)) && pieceColor == Gui.WHITE){
					newX = sourceX - 1;
					newY = sourceY - 1;
					pMove = newX + " " + newY;
					moves.add(pMove);
				}
			}
			if(sourceY+1 <8){
				if(Character.isLowerCase(chessBoard[sourceX - 1][sourceY + 1].charAt(0)) && pieceColor == Gui.WHITE){
					newX = sourceX - 1;
					newY = sourceY + 1;
					pMove = newX + " " + newY;
					moves.add(pMove);
				}
			}
		}
		return moves;
	}
}
