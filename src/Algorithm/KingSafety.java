package Algorithm;

import java.util.ArrayList;

import gui.Gui;

public class KingSafety {
	
	public boolean kingInCheck(String[][] chessBoard, ArrayList<String> moves, int pieceColor){
		if(pieceColor == Gui.WHITE){
			
		}
		return false;
	}
	public String findKing(String[][] chessBoard, String king){
		String kingCoords = "";
		for(int i = 0; i <chessBoard.length; i++){ 
			for (int j = 0; j < chessBoard.length; j++) {
				if(chessBoard[i][j].equals(king)){
					kingCoords = i + " " + j;
				}
			}
		}
		return kingCoords;
		
	}
}
