package Algorithm;

import java.util.ArrayList;

import gui.Gui;

public abstract class Piece {
	abstract public ArrayList<String> possibleMoves(int sourceX, int sourceY, int pieceColor, String[][] chessBoard);
	
	public boolean checkPiece(int sourceX, int sourceY,int pieceColor, String[][] chessBoard){
		if(Character.isLowerCase(chessBoard[sourceX][sourceY].charAt(0)) && pieceColor == Gui.WHITE){
			return true;
		}else if(Character.isUpperCase(chessBoard[sourceX][sourceY].charAt(0)) && pieceColor == Gui.BLACK){
			return true;
		}
		return false;
	}
}
