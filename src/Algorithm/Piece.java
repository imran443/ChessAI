package Algorithm;

import java.util.ArrayList;

public abstract class Piece {
	abstract ArrayList<String> possibleMoves(int sourceX, int sourceY, int pieceColor, String[][] chessBoard);
}
