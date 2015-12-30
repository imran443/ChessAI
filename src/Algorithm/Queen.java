package Algorithm;

import java.util.ArrayList;

import gui.Gui;

public class Queen extends Piece{

	ArrayList<String> moves = new ArrayList<String>();
	
	@Override
	public ArrayList<String> possibleMoves(int sourceX, int sourceY, int pieceColor, String[][] chessBoard) {
		// TODO Auto-generated method stub
		
		String pMove = "";
		// The new X coordinate for the piece
		int newX;
		int newY;
		
		
		
		
		
		
		
		
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
