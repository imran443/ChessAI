package Algorithm;

import java.util.ArrayList;

public abstract class Piece {
	abstract public ArrayList<String> possibleMoves(int sourceX, int sourceY, int pieceColor, String[][] chessBoard);
	abstract public boolean isValid(int sourceX, int sourceY, ArrayList<String> moves);
	
	
}
