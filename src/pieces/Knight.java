package pieces;

import java.util.ArrayList;

import Algorithm.Piece;
import gui.Gui;

public class Knight extends Piece{
	
	
	@Override
	public ArrayList<String> possibleMoves(int sourceX, int sourceY, int pieceColor, String[][] chessBoard) {
		ArrayList<String> moves = new ArrayList<String>();
		//The possible moves 
		String pMove="";
		//The new X coordinate for the piece
		int newX;
		int newY;
		
		//Keeps the search in bounds
		if(sourceX+2 <= 7){
			// 2 down and 1 right
			if(sourceY+1 <= 7){
				if(chessBoard[sourceX+2][sourceY+1].equals(" ") || checkPiece(sourceX+2, sourceY+1, pieceColor, chessBoard)){
					newX = sourceX+2;
					newY = sourceY+1;
					pMove = newX + " " + newY;
					moves.add(pMove);
				}
			}
			
			if(sourceY-1 >= 0){
				if(chessBoard[sourceX+2][sourceY-1].equals(" ") || checkPiece(sourceX+2, sourceY-1, pieceColor, chessBoard)){
					newX = sourceX+2;
					newY = sourceY-1;
					pMove = newX + " " + newY;
					moves.add(pMove);
				}
			}
		}
		
		if(sourceX-2 >= 0 ){
			// 2 up and 1 right
			if(sourceY+1 <= 7){
				if(chessBoard[sourceX-2][sourceY+1].equals(" ") || checkPiece(sourceX-2, sourceY+1, pieceColor, chessBoard)){
					newX = sourceX-2;
					newY = sourceY+1;
					pMove = newX + " " + newY;
					moves.add(pMove);
				}
			}
			
			
			if(sourceY-1 >= 0){
				//2 up and 1 left
				if(chessBoard[sourceX-2][sourceY-1].equals(" ") || checkPiece(sourceX-2, sourceY-1, pieceColor, chessBoard)){
					newX = sourceX-2;
					newY = sourceY-1;
					pMove = newX + " " + newY;
					moves.add(pMove);
				}
			}
			
		}
		
		if(sourceY+2 <= 7){
			// 2 right and 1 up
			if(sourceX+1 <= 7){
				if(chessBoard[sourceX+1][sourceY+2].equals(" ") || checkPiece(sourceX+1, sourceY+2, pieceColor, chessBoard)){
					newX = sourceX+1;
					newY = sourceY+2;
					pMove = newX + " " + newY;
					moves.add(pMove);
				}
			}
			
			// 2 right and 1 down
			if(sourceX-1 >= 0){
				if(chessBoard[sourceX-1][sourceY+2].equals(" ") || checkPiece(sourceX-1, sourceY+2, pieceColor, chessBoard)){
					newX = sourceX-1;
					newY = sourceY+2;
					pMove = newX + " " + newY;
					moves.add(pMove);
				}
			}
			
		}
		
		if(sourceY-2 >= 0){
			// 1 down and 2 left
			if(sourceX+1 <= 7){
				if(chessBoard[sourceX+1][sourceY-2].equals(" ") || checkPiece(sourceX+1, sourceY-2, pieceColor, chessBoard)){
					newX = sourceX+1;
					newY = sourceY-2;
					pMove = newX + " " + newY;
					moves.add(pMove);
				}
			}
			
			if(sourceX-1 >= 0){
				//1 up and 2 left
				if(chessBoard[sourceX-1][sourceY-2].equals(" ") || checkPiece(sourceX-1, sourceY-2, pieceColor, chessBoard)){
					newX = sourceX-1;
					newY = sourceY-2;
					pMove = newX + " " + newY;
					moves.add(pMove);
				}
			}
			
		
		}
		return moves;
	}	
	
	public boolean checkPiece(int sourceX, int sourceY,int pieceColor, String[][] chessBoard){
		if(Character.isLowerCase(chessBoard[sourceX][sourceY].charAt(0)) && pieceColor == Gui.WHITE){
			return true;
		}else if(Character.isUpperCase(chessBoard[sourceX][sourceY].charAt(0)) && pieceColor == Gui.BLACK){
			return true;
		}
		return false;
	}
}
