package Algorithm;

import java.util.ArrayList;

import javax.swing.text.MaskFormatter;

import gui.Gui;

public class KingSafety {
	// The move of the enemy piece
	ArrayList<String> pieceMove = new ArrayList<String>();
	ValidateMoves validateMoves = new ValidateMoves();
	PieceSafety pieceSafety = new PieceSafety();

	private boolean enemyTop = true;
	private boolean enemyDown = true;
	private boolean enemyLeft = true;
	private boolean enemyRight = true;
	private boolean enemyTopRightSearch = true;
	private boolean enemyTopLeftSearch = true;
	private boolean enemyBottomLeftSearch = true;
	private boolean enemyBottomRightSearch = true;

	int sourceX;
	int sourceY;

	public boolean kingInCheck(String[][] chessBoard, int pieceColor) {
		pieceMove.clear();
		String kingCoords;
		boolean searchTop = true;
		boolean searchBottom = true;
		boolean searchRight = true;
		boolean searchLeft = true;
		boolean searchBottomRight = true;
		boolean searchBottomLeft = true;
		boolean searchTopRight = true;
		boolean searchTopLeft = true;

		// Gets the kings coordinates based on its color
		kingCoords = returnKingCoords(pieceColor, chessBoard);

		for (int i = 1; i < 8; i++) {
			// UP
			if (sourceX - i >= 0) {
				if (checkForSamePiece(sourceX - i, sourceY, pieceColor, chessBoard) == false && searchTop == true) {
					if (enemyTop == true) {
						if (checkPiece(sourceX - i, sourceY, pieceColor, chessBoard, 1) == true) {
							pieceMove = validateMoves.permittedMoves(sourceX - i, sourceY, chessBoard);
							// If the enemy piece is in the same row, column or
							// diagonal
							// see if it can attack king if yes return true
							if (pieceMove.contains(kingCoords)) {
								pieceMove.clear();
								setAllEnemySearchTrue();
								return true;
							}
						}
					}
				} else {
					searchTop = false;
				}
			}

			// down
			if (sourceX + i <= 7) {
				if (checkForSamePiece(sourceX + i, sourceY, pieceColor, chessBoard) == false && searchBottom == true) {
					if (enemyDown == true) {
						if (checkPiece(sourceX + i, sourceY, pieceColor, chessBoard, 2) == true) {
							pieceMove = validateMoves.permittedMoves(sourceX + i, sourceY, chessBoard);
							if (pieceMove.contains(kingCoords)) {
								pieceMove.clear();
								setAllEnemySearchTrue();
								return true;
							}
						}
					}
				} else {
					searchBottom = false;
				}
			}

			// LEFT
			if (sourceY - i >= 0) {
				if (checkForSamePiece(sourceX, sourceY - i, pieceColor, chessBoard) == false && searchLeft == true) {
					if (enemyLeft == true) {
						if (checkPiece(sourceX, sourceY - i, pieceColor, chessBoard, 3) == true) {
							pieceMove = validateMoves.permittedMoves(sourceX, sourceY - i, chessBoard);
							if (pieceMove.contains(kingCoords)) {
								pieceMove.clear();
								setAllEnemySearchTrue();
								return true;
							}
						}
					}
				} else {
					searchLeft = false;
				}
			}

			// RIGHT
			if (sourceY + i <= 7) {
				if (checkForSamePiece(sourceX, sourceY + i, pieceColor, chessBoard) == false && searchRight == true) {
					if (enemyRight == true) {
						if (checkPiece(sourceX, sourceY + i, pieceColor, chessBoard, 4) == true) {
							pieceMove = validateMoves.permittedMoves(sourceX, sourceY + i, chessBoard);
							if (pieceMove.contains(kingCoords)) {
								pieceMove.clear();
								setAllEnemySearchTrue();
								return true;
							}
						}
					}
				} else {
					searchRight = false;
				}
			}

			// Top right
			if (sourceX - i >= 0 && sourceY + i <= 7) {
				if (checkForSamePiece(sourceX - i, sourceY + i, pieceColor, chessBoard) == false
						&& searchTopRight == true) {
					if (enemyTopRightSearch == true) {
						if (checkPiece(sourceX - i, sourceY + i, pieceColor, chessBoard, 5)) {
							pieceMove = validateMoves.permittedMoves(sourceX - i, sourceY + i, chessBoard);
							if (pieceMove.contains(kingCoords)) {
								pieceMove.clear();
								setAllEnemySearchTrue();
								return true;
							}
						}
					}
				} else {
					searchTopRight = false;
				}
			}
			// Top left
			if (sourceX - i >= 0 && sourceY - i >= 0) {
				if (checkForSamePiece(sourceX - i, sourceY - i, pieceColor, chessBoard) == false
						&& searchTopLeft == true) {
					if (enemyTopLeftSearch == true) {
						if (checkPiece(sourceX - i, sourceY - i, pieceColor, chessBoard, 6)) {
							pieceMove = validateMoves.permittedMoves(sourceX - i, sourceY - i, chessBoard);
							if (pieceMove.contains(kingCoords)) {
								pieceMove.clear();
								setAllEnemySearchTrue();
								return true;
							}
						}
					}
				} else {
					searchTopLeft = false;
				}
			}
			// Bottom left
			if (sourceX + i <= 7 && sourceY - i >= 0) {
				if (checkForSamePiece(sourceX + i, sourceY - i, pieceColor, chessBoard) == false
						&& searchBottomLeft == true) {
					if (enemyBottomLeftSearch == true) {
						if (checkPiece(sourceX + i, sourceY - i, pieceColor, chessBoard, 7)) {
							pieceMove = validateMoves.permittedMoves(sourceX + i, sourceY - i, chessBoard);
							if (pieceMove.contains(kingCoords)) {
								pieceMove.clear();
								setAllEnemySearchTrue();
								return true;
							}
						}
					}
				} else {
					searchBottomLeft = false;
				}
			}
			// Bottom Right
			if (sourceX + i <= 7 && sourceY + i <= 7) {
				// Stops the search if a same color piece is found in the
				// bishops path
				if (checkForSamePiece(sourceX + i, sourceY + i, pieceColor, chessBoard) == false
						&& searchBottomRight == true) {
					if (enemyBottomRightSearch == true) {
						if (checkPiece(sourceX + i, sourceY + i, pieceColor, chessBoard, 8)) {
							pieceMove = validateMoves.permittedMoves(sourceX + i, sourceY + i, chessBoard);
							if (pieceMove.contains(kingCoords)) {
								pieceMove.clear();
								setAllEnemySearchTrue();
								return true;
							}
						}
					}
				} else {
					// Permanently stops the search
					searchBottomRight = false;
				}
			}

		}

		// Keeps the search in bounds
		if (sourceX + 2 <= 7) {
			// 2 down and 1 right
			if (sourceY + 1 <= 7) {
				if (checkPiece(sourceX + 2, sourceY + 1, pieceColor, chessBoard, 0)) {
					pieceMove = validateMoves.permittedMoves(sourceX + 2, sourceY + 1, chessBoard);
					if (pieceMove.contains(kingCoords)) {
						pieceMove.clear();
						setAllEnemySearchTrue();
						return true;
					}
				}
			}

			// 2 down and 1 left
			if (sourceY - 1 >= 0) {
				if (checkPiece(sourceX + 2, sourceY - 1, pieceColor, chessBoard, 0)) {
					pieceMove = validateMoves.permittedMoves(sourceX + 2, sourceY - 1, chessBoard);
					if (pieceMove.contains(kingCoords)) {
						pieceMove.clear();
						setAllEnemySearchTrue();
						return true;
					}
				}
			}
		}

		if (sourceX - 2 >= 0) {
			// 2 up and 1 right
			if (sourceY + 1 <= 7) {
				if (checkPiece(sourceX - 2, sourceY + 1, pieceColor, chessBoard, 0)) {
					pieceMove = validateMoves.permittedMoves(sourceX - 2, sourceY + 1, chessBoard);
					if (pieceMove.contains(kingCoords)) {
						pieceMove.clear();
						setAllEnemySearchTrue();
						return true;
					}
				}

				if (sourceY - 1 >= 0) {
					// 2 up and 1 left
					if (checkPiece(sourceX - 2, sourceY - 1, pieceColor, chessBoard, 0)) {
						pieceMove = validateMoves.permittedMoves(sourceX - 2, sourceY - 1, chessBoard);
						if (pieceMove.contains(kingCoords)) {
							pieceMove.clear();
							setAllEnemySearchTrue();
							return true;
						}
					}
				}

			}
		}

		if (sourceY + 2 <= 7) {
			// 2 right and 1 down
			if (sourceX + 1 <= 7) {
				if (checkPiece(sourceX + 1, sourceY + 2, pieceColor, chessBoard, 0)) {
					pieceMove = validateMoves.permittedMoves(sourceX + 1, sourceY + 2, chessBoard);
					if (pieceMove.contains(kingCoords)) {
						pieceMove.clear();
						setAllEnemySearchTrue();
						return true;
					}
				}
			}

			// 2 right and 1 up
			if (sourceX - 1 >= 0) {
				if (checkPiece(sourceX - 1, sourceY + 2, pieceColor, chessBoard, 0)) {
					pieceMove = validateMoves.permittedMoves(sourceX - 1, sourceY + 2, chessBoard);
					if (pieceMove.contains(kingCoords)) {
						pieceMove.clear();
						setAllEnemySearchTrue();
						return true;
					}
				}
			}

		}

		if (sourceY - 2 >= 0) {
			// 1 down and 2 left
			if (sourceX + 1 <= 7) {
				if (checkPiece(sourceX + 1, sourceY - 2, pieceColor, chessBoard, 0)) {
					pieceMove = validateMoves.permittedMoves(sourceX + 1, sourceY - 2, chessBoard);
					if (pieceMove.contains(kingCoords)) {
						pieceMove.clear();
						setAllEnemySearchTrue();
						return true;
					}
				}
			}

			if (sourceX - 1 >= 0) {
				// 1 up and 2 left
				if (checkPiece(sourceX - 1, sourceY - 2, pieceColor, chessBoard, 0)) {
					pieceMove = validateMoves.permittedMoves(sourceX - 1, sourceY - 2, chessBoard);
					if (pieceMove.contains(kingCoords)) {
						pieceMove.clear();
						setAllEnemySearchTrue();
						return true;
					}
				}
			}

		}

		// Resets the values for use next time
		setAllEnemySearchTrue();

		return false;
	}

	// Sets all of the enemy searches to true
	public void setAllEnemySearchTrue() {
		// Resets the values for use next time
		enemyTop = true;
		enemyDown = true;
		enemyRight = true;
		enemyLeft = true;
		enemyTopRightSearch = true;
		enemyTopLeftSearch = true;
		enemyBottomRightSearch = true;
		enemyBottomLeftSearch = true;
	}

	// Finds the king by searching through the chessBoard
	public String findKing(String[][] chessBoard, String king) {
		String kingCoords = "";
		for (int i = 0; i < chessBoard.length; i++) {
			for (int j = 0; j < chessBoard.length; j++) {
				if (chessBoard[i][j].equals(king)) {
					kingCoords = i + " " + j;
				}
			}
		}
		return kingCoords;
	}

	// Used to check for enemy piece
	public boolean checkPiece(int sourceX, int sourceY, int pieceColor, String[][] chessBoard, int whichSearch) {
		if (Character.isLowerCase(chessBoard[sourceX][sourceY].charAt(0)) && pieceColor == Gui.WHITE) {
			// Stops the corresponding with respect to the number that
			// represents it
			stopEnemySearch(whichSearch);
			return true;
		} else if (Character.isUpperCase(chessBoard[sourceX][sourceY].charAt(0)) && pieceColor == Gui.BLACK) {
			stopEnemySearch(whichSearch);
			return true;
		}
		return false;
	}

	// This method is to help determine if the same color piece is present as
	// the king.
	public boolean checkForSamePiece(int sourceX, int sourceY, int pieceColor, String[][] chessBoard) {
		if (Character.isUpperCase(chessBoard[sourceX][sourceY].charAt(0)) && pieceColor == Gui.WHITE) {
			return true;
		} else if (Character.isLowerCase(chessBoard[sourceX][sourceY].charAt(0)) && pieceColor == Gui.BLACK) {
			return true;
		}
		return false;
	}

	// Used to tell which search to stop
	public void stopEnemySearch(int i) {
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

	// Function used to find a out if in check mate
	public boolean kingCheckMate(String[][] chessBoard, int pieceColor) {
		ArrayList<String> moves = new ArrayList<>();
		String kingCoords = returnKingCoords(pieceColor, chessBoard);
		String newKingX = kingCoords.substring(0, 1);
		String newKingY = kingCoords.substring(2);
		int kingX = Integer.parseInt(newKingX);
		int kingY = Integer.parseInt(newKingY);
		
		int spaceCheckCount = 0;
		String newX;
		String newY;
		
		// sourceX and Y get updated after returnKingCoords is done
		moves = validateMoves.permittedMoves(sourceX, sourceY, chessBoard);
		//If no moves then it mean it is surrounded by its own pieces so not in check mate
		if(!moves.isEmpty()){
			//Checks all the directions around the king
			for(int i = 0; i < moves.size(); i++){
				String possibleMoves = moves.get(i);
				newX = possibleMoves.substring(0, 1);
				newY = possibleMoves.substring(2);
				int x = Integer.parseInt(newX);
				int y = Integer.parseInt(newY);
				//Checks around for check mate if is in check
				if(pieceSafety.pieceInDanger(chessBoard, pieceColor, x, y) == false){
					spaceCheckCount++;
				}else{
					if(pieceSafety.pieceInDanger(chessBoard, pieceColor, pieceSafety.getAttackingPieceX(), pieceSafety.getAttackingPieceY()) == true){
						spaceCheckCount++;
					}
					//Gets all the moves of the pieces on the board to see if any can block this check
					getAllMoves(pieceColor, chessBoard);
					ArrayList<String> temp = new ArrayList<>();
					ArrayList<String> enemyMoves = new ArrayList<>();
					
					//Sets the proper move list
					if(pieceColor == Gui.WHITE){
						temp = validateMoves.whiteMovesKing;
						temp.removeAll(moves);
					}else {
						temp = validateMoves.blackMovesKing;
						temp.removeAll(moves);
					}
					//Gets the possible paths of the enemy piece that is attacking to see if they can be blocked by piece
					enemyMoves = validateMoves.permittedMoves(pieceSafety.attackingPieceX, pieceSafety.attackingPieceY, chessBoard);
					//Checks the temp list which has all possible moves of the current board
					for (int j = 0; j < enemyMoves.size(); j++) {
						if(temp.contains(enemyMoves.get(i))){
							spaceCheckCount++;
							return false;
						}
					}
					//Clean the list as they are globals in the other class
					temp.clear();
				}
			}
		}
		if(kingInCheck(chessBoard, pieceColor)){
			if(spaceCheckCount!=0){
				return false;
			}else {
				System.out.println("King is in check mate!!!");
				return true;
			}
		}else{
			return false;
		}
		
	}

	// Used to find a certain kings coordinates based on color
	public String returnKingCoords(int pieceColor, String[][] chessBoard) {
		String whiteKing = "A";
		String blackKing = "a";
		String kingCoords;
		String newX;
		String newY;

		// To determine what king we care about through the search
		if (pieceColor == Gui.WHITE) {
			kingCoords = findKing(chessBoard, whiteKing);
			newX = kingCoords.substring(0, 1);
			newY = kingCoords.substring(2);
			sourceX = Integer.parseInt(newX);
			sourceY = Integer.parseInt(newY);
		} else {
			kingCoords = findKing(chessBoard, blackKing);
			newX = kingCoords.substring(0, 1);
			newY = kingCoords.substring(2);
			sourceX = Integer.parseInt(newX);
			sourceY = Integer.parseInt(newY);
		}
		return kingCoords;
	}
	
	//Used to load the list with all moves
	public void getAllMoves(int pieceColor, String[][] board){
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if(pieceColor == Gui.BLACK){
					if(Character.isLowerCase(board[i][j].charAt(0))){
						ArrayList<String> tempList = validateMoves.permittedMoves(i, j, board);
						tempList.clear();
					}
				}else{
					if(Character.isUpperCase(board[i][j].charAt(0))){
						ArrayList<String> tempList = validateMoves.permittedMoves(i, j, board);
						tempList.clear();
					}
				}
			}
		}
	}
	
	
}
