package Algorithm;

import java.util.ArrayList;

import gui.Gui;

public class PieceSafety {
	// The move of the enemy piece
	ArrayList<String> pieceMove = new ArrayList<String>();
	ValidateMoves validateMoves = new ValidateMoves();
	int attackingPieceX;
	int attackingPieceY;
	
	private boolean enemyTop = true;
	private boolean enemyDown = true;
	private boolean enemyLeft = true;
	private boolean enemyRight = true;
	private boolean enemyTopRightSearch = true;
	private boolean enemyTopLeftSearch = true;
	private boolean enemyBottomLeftSearch = true;
	private boolean enemyBottomRightSearch = true;
	
	

	public boolean pieceInDanger(String[][] chessBoard, int pieceColor, int sourceX,int sourceY) {
		String pieceCoords=sourceX + " " + sourceY;
		boolean searchTop = true;
		boolean searchBottom = true;
		boolean searchRight = true;
		boolean searchLeft = true;
		boolean searchBottomRight = true;
		boolean searchBottomLeft = true;
		boolean searchTopRight = true;
		boolean searchTopLeft = true;

		for (int i = 1; i < 8; i++) {
			// UP
			if (sourceX - i >= 0) {
				if (checkForSamePiece(sourceX - i, sourceY, pieceColor, chessBoard) == false && searchTop == true) {
					if (enemyTop == true) {
						if (checkPiece(sourceX - i, sourceY, pieceColor, chessBoard, 1) == true) {
							pieceMove = validateMoves.permittedMoves(sourceX - i, sourceY, chessBoard);
							// If the enemy piece is in the same row, column or
							// diagonal
							// see if it can attack piece if yes return true
							
							if (pieceMove.contains(pieceCoords)) {
								//Gets the position of the attacking piece 
								attackingPieceX = sourceX - i;
								attackingPieceY = sourceY;
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
							if (pieceMove.contains(pieceCoords)) {
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
							if (pieceMove.contains(pieceCoords)) {
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
							if (pieceMove.contains(pieceCoords)) {
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
							if (pieceMove.contains(pieceCoords)) {
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
							if (pieceMove.contains(pieceCoords)) {
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
							if (pieceMove.contains(pieceCoords)) {
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
							if (pieceMove.contains(pieceCoords)) {
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
					if (pieceMove.contains(pieceCoords)) {
						setAllEnemySearchTrue();
						return true;
					}
				}
			}

			// 2 down and 1 left
			if (sourceY - 1 >= 0) {
				if (checkPiece(sourceX + 2, sourceY - 1, pieceColor, chessBoard, 0)) {
					pieceMove = validateMoves.permittedMoves(sourceX + 2, sourceY - 1, chessBoard);
					if (pieceMove.contains(pieceCoords)) {
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
					if (pieceMove.contains(pieceCoords)) {
						setAllEnemySearchTrue();
						return true;
					}
				}

				if (sourceY - 1 >= 0) {
					// 2 up and 1 left
					if (checkPiece(sourceX - 2, sourceY - 1, pieceColor, chessBoard, 0)) {
						pieceMove = validateMoves.permittedMoves(sourceX - 2, sourceY - 1, chessBoard);
						if (pieceMove.contains(pieceCoords)) {
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
					if (pieceMove.contains(pieceCoords)) {
						setAllEnemySearchTrue();
						return true;
					}
				}
			}

			// 2 right and 1 up
			if (sourceX - 1 >= 0) {
				if (checkPiece(sourceX - 1, sourceY + 2, pieceColor, chessBoard, 0)) {
					pieceMove = validateMoves.permittedMoves(sourceX - 1, sourceY + 2, chessBoard);
					if (pieceMove.contains(pieceCoords)) {
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
					if (pieceMove.contains(pieceCoords)) {
						setAllEnemySearchTrue();
						return true;
					}
				}
			}

			if (sourceX - 1 >= 0) {
				// 1 up and 2 left
				if (checkPiece(sourceX - 1, sourceY - 2, pieceColor, chessBoard, 0)) {
					pieceMove = validateMoves.permittedMoves(sourceX - 1, sourceY - 2, chessBoard);
					if (pieceMove.contains(pieceCoords)) {
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


//Used to check for enemy piece
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
	// the piece.
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
	//Getter methods to get the positions in the other class (King Safety)
	public int getAttackingPieceX(){
		return attackingPieceX;
	}
	
	public int getAttackingPieceY(){
		return attackingPieceY;
	}
}
