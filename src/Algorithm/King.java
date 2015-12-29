package Algorithm;

import java.util.ArrayList;

import gui.Gui;

public class King extends Piece {
	ArrayList<String> moves = new ArrayList<String>();
	
	@Override
	public ArrayList<String> possibleMoves(int sourceX, int sourceY, int pieceColor, String[][] chessBoard) {
		//The possible moves 
		String pMove="";
		//The new X coordinate for the piece
		int newX;
		int newY;
		if(pieceColor == Gui.BLACK){
			//Stop from checking out of bounds 
			if(sourceY-1>0){
				if(chessBoard[sourceX][sourceY-1].equals(" ")){
					newX = sourceX;
					newY = sourceY-1;
					pMove = newX + " " + newY;
					moves.add(pMove);
				}
				if(chessBoard[sourceX-1][sourceY-1].equals(" ")){
				
				}
				if(chessBoard[sourceX+1][sourceY-1].equals(" ")){
					
				}
			}
		}
		
		return null;
	}
	
	public boolean kingInCheck(){
		return false;
		
	}

}
