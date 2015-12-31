package Algorithm;

import java.util.ArrayList;

import gui.Gui;

public class Rook extends Piece{

	ArrayList<String> moves = new ArrayList<String>();
	
	//Used to stop searching when first enemy is found for bishop so it does not jump over it to cap others behind it
	boolean enemyTop;
	boolean enemyDown;
	boolean enemyRight;
	boolean enemyLeft;
	
	@Override
	public ArrayList<String> possibleMoves(int sourceX, int sourceY, int pieceColor, String[][] chessBoard) {
		
		String pMove = "";
		// The new X coordinate for the piece
		int newX;
		int newY;
		
		boolean searchTop = true;
		boolean searchBottom = true;
		boolean searchRight = true;
		boolean searchLeft = true;
		
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
			
		}
		
		//Resets the values for use next time 
		enemyTop = true;
		enemyDown = true;
		enemyRight = true;
		enemyLeft = true;
		
		return moves;
	}
	//Checks to see if enemy is found for the corresponding piece
	public boolean checkPiece(int sourceX, int sourceY,int pieceColor, String[][] chessBoard,int whichSearch){
		if(Character.isLowerCase(chessBoard[sourceX][sourceY].charAt(0)) && pieceColor == Gui.WHITE){
			//Stops the corresponding with respect to the number that represents it 
			stopEnemySearch(whichSearch);
			System.out.println("Enemy search set to false for WHITE");
			return true;
		}
		else if(Character.isUpperCase(chessBoard[sourceX][sourceY].charAt(0)) && pieceColor == Gui.BLACK){
			stopEnemySearch(whichSearch);
			System.out.println("Enemy search set to false for BLACK");
			return true;
		}
			return false;
	}
	
	//This method is to help determine if the same color piece is present as the rook.
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
		default:
			break;
		}
	}
}
