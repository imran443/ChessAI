package Algorithm;

import java.util.ArrayList;

import gui.Gui;

public class Knight extends Piece{
	ArrayList<String> moves = new ArrayList<String>();
	
	@Override
	public ArrayList<String> possibleMoves(int sourceX, int sourceY, int pieceColor, String[][] chessBoard) {
		// TODO Auto-generated method stub
		//The possible moves 
		String pMove="";
		//The new X coordinate for the piece
		int newX;
		int newY;
		
		// HEAD AND BACK
		if(sourceX+2 <= 7){
			// 2 head and 1 right
			if(sourceY+1 <= 7){
				if(chessBoard[sourceX+2][sourceY+1].equals(" ") || checkPiece(sourceX+2, sourceY+1, pieceColor, chessBoard)){
					newX = sourceX+2;
					newY = sourceY+1;
					pMove = newX + " " + newY;
					moves.add(pMove);
				}
			}
			
			
			// 2 head and 1 left
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
			// 2 back and 1 right and left
			if(sourceY+1 <= 7){
				if(chessBoard[sourceX-2][sourceY+1].equals(" ") || checkPiece(sourceX-2, sourceY+1, pieceColor, chessBoard)){
					newX = sourceX-2;
					newY = sourceY+1;
					pMove = newX + " " + newY;
					moves.add(pMove);
				}
			}
			
			
			if(sourceY-1 >= 0){
				if(chessBoard[sourceX-2][sourceY-1].equals(" ") || checkPiece(sourceX-2, sourceY-1, pieceColor, chessBoard)){
					newX = sourceX-2;
					newY = sourceY-1;
					pMove = newX + " " + newY;
					moves.add(pMove);
				}
			}
			
		}
		
		//RIGHT AND LEFT
		if(sourceY+2 <= 7){
			// 2 rights and 1 head
			if(sourceX+1 <= 7){
				if(chessBoard[sourceX+1][sourceY+2].equals(" ") || checkPiece(sourceX+1, sourceY+2, pieceColor, chessBoard)){
					newX = sourceX+1;
					newY = sourceY+2;
					pMove = newX + " " + newY;
					moves.add(pMove);
				}
			}
			
			// 2 rights and 1 back
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
			// lefts
			if(sourceX+1 <= 7){
				if(chessBoard[sourceX+1][sourceY-2].equals(" ") || checkPiece(sourceX+1, sourceY-2, pieceColor, chessBoard)){
					newX = sourceX+1;
					newY = sourceY-2;
					pMove = newX + " " + newY;
					moves.add(pMove);
				}
			}
			
			if(sourceX-1 >= 0){
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
