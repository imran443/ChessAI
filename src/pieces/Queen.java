package pieces;

import java.util.ArrayList;

import Algorithm.Piece;
import gui.Gui;

public class Queen extends Piece{

	
	
	//Used to stop searching when first enemy is found for Queen so it does not jump over it to cap others behind it
	boolean enemyTop = true;
	boolean enemyDown = true;
	boolean enemyRight = true;
	boolean enemyLeft = true;
	boolean enemyTopRightSearch = true;
	boolean enemyTopLeftSearch = true;
	boolean enemyBottomRightSearch = true;
	boolean enemyBottomLeftSearch = true;
	
	@Override
	public ArrayList<String> possibleMoves(int sourceX, int sourceY, int pieceColor, String[][] chessBoard) {
		ArrayList<String> moves = new ArrayList<String>();
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
					if(enemyTop == true){
						if(chessBoard[sourceX-i][sourceY].equals(" ") || checkPiece(sourceX-i, sourceY, pieceColor, chessBoard, 1)==true){
							newX = sourceX-i;
							newY = sourceY;
							pMove = newX + " " + newY;
							moves.add(pMove);
						}
					}
				}else{
					searchTop = false;
				}
			}

			// down
			if(sourceX+i <= 7){
				if(checkForSamePiece(sourceX+i, sourceY, pieceColor, chessBoard) == false && searchBottom == true){
					if(enemyDown == true){
						if(chessBoard[sourceX+i][sourceY].equals(" ") || checkPiece(sourceX+i, sourceY, pieceColor, chessBoard, 2) == true){
							newX = sourceX+i;
							newY = sourceY;
							pMove = newX + " " + newY;
							moves.add(pMove);
						}
					}
				}else{
					searchBottom = false;
				}
			}
			
			// LEFT
			if(sourceY-i >= 0){
				if(checkForSamePiece(sourceX, sourceY-i, pieceColor, chessBoard) == false && searchLeft == true){
					if(enemyLeft == true){
						if(chessBoard[sourceX][sourceY-i].equals(" ") || checkPiece(sourceX, sourceY-i, pieceColor, chessBoard, 3) == true){
							newX = sourceX;
							newY = sourceY-i;
							pMove = newX + " " + newY;
							moves.add(pMove);
						}
					}
				}else{
					searchLeft = false;
				}
			}
			
			//RIGHT
			if(sourceY+i <= 7){
				if(checkForSamePiece(sourceX, sourceY+i, pieceColor, chessBoard) == false && searchRight == true){
					if(enemyRight == true){
						if(chessBoard[sourceX][sourceY+i].equals(" ") || checkPiece(sourceX, sourceY+i, pieceColor, chessBoard, 4) == true){
							newX = sourceX;
							newY = sourceY+i;
							pMove = newX + " " + newY;
							moves.add(pMove);
						}
					}
				}else{
					searchRight = false;
				}
			}
			
			
			//Top right
			if(sourceX - i >= 0 && sourceY + i <= 7){
				if(checkForSamePiece(sourceX - i, sourceY + i, pieceColor, chessBoard) == false && searchTopRight == true){
					if(enemyTopRightSearch == true){
						if(chessBoard[sourceX - i][sourceY + i].equals(" ") || checkPiece(sourceX-i, sourceY + i, pieceColor, chessBoard, 5)){
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
				if(checkForSamePiece(sourceX - i, sourceY - i, pieceColor, chessBoard) == false && searchTopLeft == true){
					if(enemyTopLeftSearch == true){
						if(chessBoard[sourceX - i][sourceY - i].equals(" ") || checkPiece(sourceX - i, sourceY - i, pieceColor, chessBoard, 6)){
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
				if(checkForSamePiece(sourceX + i, sourceY - i, pieceColor, chessBoard) == false && searchBottomLeft == true){
					if(enemyBottomLeftSearch == true){
						if(chessBoard[sourceX + i][sourceY - i].equals(" ") || checkPiece(sourceX + i, sourceY - i, pieceColor, chessBoard,7)){
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
				//Stops the search if a same color piece is found in the Queens path
				if(checkForSamePiece(sourceX + i, sourceY + i, pieceColor, chessBoard) == false && searchBottomRight == true){
					if(enemyBottomRightSearch == true){
						if(chessBoard[sourceX + i][sourceY + i].equals(" ") || checkPiece(sourceX + i, sourceY + i, pieceColor, chessBoard, 8)){
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
		enemyTop = true;
		enemyDown = true;
		enemyRight = true;
		enemyLeft = true;
		enemyTopRightSearch = true;
		enemyTopLeftSearch = true;
		enemyBottomRightSearch = true;
		enemyBottomLeftSearch = true;
		
		return moves;
	}
	
	public boolean checkPiece(int sourceX, int sourceY,int pieceColor, String[][] chessBoard,int whichSearch){
		if(Character.isLowerCase(chessBoard[sourceX][sourceY].charAt(0)) && pieceColor == Gui.WHITE){
			//Stops the corresponding with respect to the number that represents it 
			stopEnemySearch(whichSearch);
			return true;
		}
		else if(Character.isUpperCase(chessBoard[sourceX][sourceY].charAt(0)) && pieceColor == Gui.BLACK){
			stopEnemySearch(whichSearch);
			return true;
		}
			return false;
	}
	
	//This method is to help determine if the same color piece is present as the Queen.
	public boolean checkForSamePiece(int sourceX, int sourceY,int pieceColor, String[][] chessBoard){
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
				enemyTop = false;
				break;
			case 2:
				enemyDown = false;
				break;
			case 3: 
				enemyLeft = false;
				break;
			case 4:
				enemyRight = false;
				break;
			case 5: 
				enemyTopRightSearch = false;
				break;
			case 6:
				enemyTopLeftSearch = false;
				break;
			case 7:
				enemyBottomLeftSearch = false;
				break;
			case 8:
				enemyBottomRightSearch = false;
				break;
		default:
			break;
		}
	}
}
