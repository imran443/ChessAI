package Algorithm;

import java.util.ArrayList;

import gui.Gui;

public class Bishop extends Piece {
	ArrayList<String> moves = new ArrayList<String>();
	
	//Used to stop searching when first enemy is found for bishop so it does not jump over it to cap others behind it
	boolean enemyTopRightSearch = true;
	boolean enemyTopLeftSearch = true;
	boolean enemyBottomRightSearch = true;
	boolean enemyBottomLeftSearch = true;
			
	
	@Override
	public ArrayList<String> possibleMoves(int sourceX, int sourceY, int pieceColor, String[][] chessBoard) {
		// The possible moves
		String pMove = "";
		// The new X coordinate for the piece
		int newX;
		int newY;
		
		boolean searchBottomRight = true;
		boolean searchBottomLeft = true;
		boolean searchTopRight = true;
		boolean searchTopLeft = true;
		
		for(int i = 1; i<8; i++){
			//Top right
			if(sourceX - i >= 0 && sourceY + i <= 7){
				if(checkPieceForBishop(sourceX - i, sourceY + i, pieceColor, chessBoard) == false && searchTopRight == true){
					if(enemyTopRightSearch == true){
						if(chessBoard[sourceX - i][sourceY + i].equals(" ") || checkPiece(sourceX-i, sourceY + i, pieceColor, chessBoard, 1)){
							newX = sourceX - i;
							newY = sourceY + i;
							pMove = newX + " " + newY;
							moves.add(pMove);
						}
					}
				}
				else{
					searchTopRight = false;
				}
			}
			//Top left 
			if(sourceX - i >= 0 && sourceY - i >=0){
				if(checkPieceForBishop(sourceX - i, sourceY - i, pieceColor, chessBoard) == false && searchTopLeft == true){
					if(enemyTopLeftSearch == true){
						if(chessBoard[sourceX - i][sourceY - i].equals(" ") || checkPiece(sourceX - i, sourceY - i, pieceColor, chessBoard, 2)){
							newX = sourceX - i;
							newY = sourceY - i;
							pMove = newX + " " + newY;
							moves.add(pMove);
						}
					}
				}else{
					searchTopLeft = false;
				}
			}
			//Bottom left
			if(sourceX + i <=7 && sourceY - i >= 0){
				if(checkPieceForBishop(sourceX + i, sourceY - i, pieceColor, chessBoard) == false && searchBottomLeft == true){
					if(enemyBottomLeftSearch == true){
						if(chessBoard[sourceX + i][sourceY - i].equals(" ") || checkPiece(sourceX + i, sourceY - i, pieceColor, chessBoard,3)){
							newX = sourceX + i;
							newY = sourceY - i;
							pMove = newX + " " + newY;
							moves.add(pMove);
						}
					}
				}
				else{
					searchBottomLeft = false;
				}
			}
			//Bottom Right
			if(sourceX + i <= 7 && sourceY + i <=7){
				//Stops the search if a same color piece is found in the bishops path
				if(checkPieceForBishop(sourceX + i, sourceY + i, pieceColor, chessBoard) == false && searchBottomRight == true){
					if(enemyBottomRightSearch == true){
						if(chessBoard[sourceX + i][sourceY + i].equals(" ") || checkPiece(sourceX + i, sourceY + i, pieceColor, chessBoard, 4)){
							newX = sourceX + i;
							newY = sourceY + i;
							pMove = newX + " " + newY;
							moves.add(pMove);
						}
					}
				}else{
					//Permanently stops the search 
					searchBottomRight = false;
				}
			}
		}
		//Resets the values for use next time 
		enemyTopRightSearch = true;
		enemyTopLeftSearch = true;
		enemyBottomRightSearch = true;
		enemyBottomLeftSearch = true;
		
		return moves;
	}
	
	
	//Method used to reduce redundant coding, will return true only if it finds the corresponding piece to capture based on color
	public boolean checkPiece(int sourceX, int sourceY,int pieceColor, String[][] chessBoard,int whichSearch){
		if(Character.isLowerCase(chessBoard[sourceX][sourceY].charAt(0)) && pieceColor == Gui.WHITE){
			//Stops the corresponding with respect to the number that represents it 
			stopEnemySearch(whichSearch);
			System.out.println("Enemy search set to false for WHITE");
			return true;
		}
		else if(Character.isUpperCase(chessBoard[sourceX][sourceY].charAt(0)) && pieceColor == Gui.BLACK){
			//Stops the corresponding with respect to the number that represents it 
			stopEnemySearch(whichSearch);
			System.out.println("Enemy search set to false for BLACK");
			return true;
		}
			return false;
	}
	
	//This method is to help determine if the same color piece is present as the bishop.
	public boolean checkPieceForBishop(int sourceX, int sourceY,int pieceColor, String[][] chessBoard){
		if(Character.isUpperCase(chessBoard[sourceX][sourceY].charAt(0)) && pieceColor == Gui.WHITE){
			return true;
		}
		else if(Character.isLowerCase(chessBoard[sourceX][sourceY].charAt(0)) && pieceColor == Gui.BLACK){
			return true;
		}
			return false;
	}
	//Used to tell which search to stop
	public void stopEnemySearch(int i){
		switch (i) {
			case 1:
				enemyTopRightSearch = false;
				break;
			case 2:
				enemyTopLeftSearch = false;
				break;
			case 3: 
				enemyBottomLeftSearch = false;
				break;
			case 4:
				enemyBottomRightSearch = false;
				break;
		default:
			break;
		}
	}
}
