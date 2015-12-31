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
		
		boolean searchTop = true;
		boolean searchBottom = true;
		boolean searchRight = true;
		boolean searchLeft = true;
		boolean searchBottomRight = true;
		boolean searchBottomLeft = true;
		boolean searchTopRight = true;
		boolean searchTopLeft = true;
		
		// Vertical
		for(int i=1; i<=7; i++){
			// UP
			if(sourceX-i >= 0){
				if(checkForSamePiece(sourceX-i, sourceY, pieceColor, chessBoard) == false && searchTop == true){
					if(chessBoard[sourceX-i][sourceY].equals(" ") || checkPiece(sourceX-i, sourceY, pieceColor, chessBoard)==true){
						newX = sourceX-i;
						newY = sourceY;
						pMove = newX + " " + newY;
						moves.add(pMove);
					}
				}else{
					searchTop = false;
				}
			}

			// down
			if(sourceX+i <= 7){
				if(checkForSamePiece(sourceX+i, sourceY, pieceColor, chessBoard) == false && searchBottom == true){
					if(chessBoard[sourceX+i][sourceY].equals(" ") || checkPiece(sourceX+i, sourceY, pieceColor, chessBoard) == true){
						newX = sourceX+i;
						newY = sourceY;
						pMove = newX + " " + newY;
						moves.add(pMove);
					}
				}else{
					searchBottom = false;
				}
			}
			
			// LEFT
			if(sourceY-i >= 0){
				if(checkForSamePiece(sourceX, sourceY-i, pieceColor, chessBoard) == false && searchLeft == true){
					if(chessBoard[sourceX][sourceY-i].equals(" ") || checkPiece(sourceX, sourceY-i, pieceColor, chessBoard) == true){
						newX = sourceX;
						newY = sourceY-i;
						pMove = newX + " " + newY;
						moves.add(pMove);
					}
				}else{
					searchLeft = false;
				}
			}
			
			//RIGHT
			if(sourceY+i <= 7){
				if(checkForSamePiece(sourceX, sourceY+i, pieceColor, chessBoard) == false && searchLeft == true){
					if(chessBoard[sourceX][sourceY+i].equals(" ") || checkPiece(sourceX, sourceY+i, pieceColor, chessBoard) == true){
						newX = sourceX;
						newY = sourceY+i;
						pMove = newX + " " + newY;
						moves.add(pMove);
					}
				}else{
					searchLeft = false;
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
	
	//This method is to help determine if the same color piece is present as the bishop.
	public boolean checkForSamePiece(int sourceX, int sourceY,int pieceColor, String[][] chessBoard){
		if(Character.isUpperCase(chessBoard[sourceX][sourceY].charAt(0)) && pieceColor == Gui.WHITE){
			return true;
		}
		else if(Character.isLowerCase(chessBoard[sourceX][sourceY].charAt(0)) && pieceColor == Gui.BLACK){
			return true;
		}
			return false;
	}

}
