package pieces;

import java.util.ArrayList;

import Algorithm.Piece;
import gui.Gui;

public class King extends Piece {
	ArrayList<String> moves = new ArrayList<String>();

	@Override
	public ArrayList<String> possibleMoves(int sourceX, int sourceY, int pieceColor, String[][] chessBoard) {
		// The possible moves
		String pMove = "";
		// The new X coordinate for the piece
		int newX;
		int newY;

		// Stop from checking out of bounds
		if (sourceX - 1 >= 0) {
			// Top
			if (chessBoard[sourceX - 1][sourceY].equals(" ") || checkPiece(sourceX-1, sourceY, pieceColor, chessBoard)) {
				newX = sourceX - 1;
				newY = sourceY;
				pMove = newX + " " + newY;
				moves.add(pMove);
			}
			if(sourceY - 1 >= 0){
			// Top left
				if (chessBoard[sourceX - 1][sourceY - 1].equals(" ") || checkPiece(sourceX-1, sourceY-1, pieceColor, chessBoard)) {
					newX = sourceX - 1;
					newY = sourceY - 1;
					pMove = newX + " " + newY;
					moves.add(pMove);
				}
			}
			if(sourceY + 1 <= 7){
			// Top right
				if (chessBoard[sourceX - 1][sourceY + 1].equals(" ") || checkPiece(sourceX - 1, sourceY + 1, pieceColor, chessBoard)) {
					newX = sourceX - 1;
					newY = sourceY + 1;
					pMove = newX + " " + newY;
					moves.add(pMove);
				}
			}
		}
		// Stop from checking out of bounds
		if (sourceX + 1 <= 7) {
			// Below
			if (chessBoard[sourceX + 1][sourceY].equals(" ") || checkPiece(sourceX + 1, sourceY, pieceColor, chessBoard)) {
				newX = sourceX + 1;
				newY = sourceY;
				pMove = newX + " " + newY;
				moves.add(pMove);
			}
			// Bottom right
			if(sourceY + 1 <= 7){
				if (chessBoard[sourceX + 1][sourceY + 1].equals(" ") || checkPiece(sourceX + 1, sourceY +1, pieceColor, chessBoard)) {
					newX = sourceX + 1;
					newY = sourceY + 1;
					pMove = newX + " " + newY;
					moves.add(pMove);
				}
			}
			if(sourceY - 1 >= 0){
			// Bottom left
				if (chessBoard[sourceX + 1][sourceY - 1].equals(" ") || checkPiece(sourceX + 1, sourceY-1, pieceColor, chessBoard)) {
					newX = sourceX + 1;
					newY = sourceY - 1;
					pMove = newX + " " + newY;
					moves.add(pMove);
				}
			}
		}
		// Left check
		if (sourceY - 1 >= 0) {
			if (chessBoard[sourceX][sourceY - 1].equals(" ") || checkPiece(sourceX, sourceY - 1, pieceColor, chessBoard) ) {
				newX = sourceX;
				newY = sourceY - 1;
				pMove = newX + " " + newY;
				moves.add(pMove);
			}
		}
		// Right check
		if (sourceY + 1 <= 7) {
			if (chessBoard[sourceX][sourceY + 1].equals(" ") || checkPiece(sourceX, sourceY + 1, pieceColor, chessBoard)) {
				newX = sourceX;
				newY = sourceY + 1;
				pMove = newX + " " + newY;
				moves.add(pMove);
			}
		}
		return moves;
	}
	//Method used to reduce redundant coding, will return true only if it finds the corresponding piece to capture
	public boolean checkPiece(int sourceX, int sourceY,int pieceColor, String[][] chessBoard){
		if(Character.isLowerCase(chessBoard[sourceX][sourceY].charAt(0)) && pieceColor == Gui.WHITE){
			return true;
		}else if(Character.isUpperCase(chessBoard[sourceX][sourceY].charAt(0)) && pieceColor == Gui.BLACK){
			return true;
		}
		return false;
	}
	
	//Prevents the king from moving into a spot for potential check
	public boolean kingInCheck() {
		
		return false;
	}

}
