package Algorithm;

import java.util.ArrayList;

public class Pawn extends Piece{
	ArrayList<String> moves = new ArrayList<String>();
	
	public void possibleMoves(int sourceX, int sourceY, int PieceColor, String[][] chessBoard){
		//The possible moves 
		String pMove="";
		//The new X coordinate for the piece
		int newX;
		
		//tells you if pawn is in original placement, For white pieces
		if(chessBoard[sourceX+1][sourceY].equals(" ")){
			newX = sourceX + 1;
			pMove = newX + " "+ sourceY;
			moves.add(pMove);
		}
		//If the two spots in front of it are empty and it is in the original position it can jump two spots
		if(chessBoard[sourceX+2][sourceY].equals(" ") && chessBoard[sourceX+1][sourceY].equals(" ") && sourceX==1){
			newX = sourceX + 2;
			pMove = newX + " " + sourceY;
			moves.add(pMove);
		}
	}
	
	public boolean isValid(){
		return false;
		
	}
}
