/**
 *  ********************INCREASE THE RECURSION STACK FOR PLY 4 or higher please and thank you.************************
 */
package ai;

import java.util.ArrayList;
import java.util.Collections;

import Algorithm.ValidateMoves;
import gui.Gui;

public class AiAlgorithm {

	ValidateMoves possibleMoves = new ValidateMoves();
	String[][] board;
	
	// Compared for minimax algo
	
	String secondMove = null;
	
	int globalDepth;
	
	public AiAlgorithm(String[][] chessBoard, int ply){
		this.board = chessBoard;
		this.globalDepth = ply;
	}
	
	// doMove for getting evalutionBoard score
	/**
	 * This method does the alpha beta pruning.
	 * @param depth - depth of the tree
	 * @param alpha - alpha 
	 * @param beta - beta
	 * @param player - Max player
	 * @param move - move thats been made
	 * @param board - fake board to perform moves on it
 	 * @return - returns a move to perform on actual board
	 */
	public String alphabeta(int depth, int alpha, int beta, int player, String move, String[][] board){
		// to store value at each node
		Integer originalValue ;
		String originalMove = null;
		// a is alpha 
		int a = alpha;
		// b is beta
		int b = beta;
		
		
		// if depth = 0 exit the tree.
		if(depth == 0){
			int evaluateScore = evalutionBoard(board);
			return move + ":" +evaluateScore;
		}
		//Maximing player is Black
		if(player == 0){
			ValidateMoves vm = new ValidateMoves();
			getAllMovesAi(player, board, vm);
			
			originalValue = Integer.MIN_VALUE;
			
			ArrayList<String> Blist = new ArrayList<String>();
			
			//Gets the moves loaded in the possibleWhiteMoves and possibleBlackMoves
			Blist = vm.possibleBlackMoves();
			
			// sort the list to perform better 
			Collections.sort(Blist);

			// iterate over all the black possible moves in the list
			for (int i = 0; i <Blist.size(); i++) {
				if(originalValue > b){
					// if the value happens again skip that step
					if(originalMove.substring(0, 2).equals(Blist.get(i).substring(0, 2)))
					continue;
				}
				//Get the item from the list
				String s = Blist.get(i);
				System.out.println("black moves");
				System.out.println(s);
				// apply the move 
				String[][] copyb = copyBoard(board);
				@SuppressWarnings("unused")
				String movePlusPiece = makeMoveAI(s, copyb);
				
				
				// x1 y1 x2 y2:value <---returns the string in that format x1 y1 is where the piece is and x2 y2 is where is going to move and value is evaluate score assign to the board 
				String returnValue = alphabeta(depth-1, a, b, 1-player,s, copyb);
				// saves the score of the board
				int newValue = Integer.valueOf(returnValue.substring(8));
				
				// updates the value if newValue is greater then original one
				if(newValue > originalValue){
					originalValue = newValue;
					originalMove = s;
				}
			
				// change the alpha
				if(originalValue > a){
					a = originalValue;
				}
		
				
			}
			
			// r is for return value
			System.out.println("BLACK R move: ");
			System.out.println(move + ":" + a);
			// returns moves and alpha value with it
			return originalMove + ":" +a;
		}else{
			// miniming players is white
			ValidateMoves vm = new ValidateMoves();
			getAllMovesAi(player, board, vm);
			
			originalValue = Integer.MAX_VALUE;
			
			ArrayList<String> Wlist = new ArrayList<String>();
			//Gets the moves loaded in the possibleWhiteMoves and possibleBlackMoves
			Wlist = vm.possibleWhiteMoves();
			
			Collections.sort(Wlist);
			
			for (int i = 0; i <Wlist.size(); i++) {
				if(originalValue < a){
					if(originalMove.substring(0, 2).equals(Wlist.get(i).substring(0, 2)))
					continue;
				}
				String s = Wlist.get(i);
				
				System.out.println("white moves");
				System.out.println(s);
				// apply the move
				String[][] copyb = copyBoard(board);
				@SuppressWarnings("unused")
				String movePlusPiece = makeMoveAI(s, copyb);
				//Wlist.clear();
				// do the min( alphatbeta(depth-1, beta, alpha, board, 1-player)) for alpha and find the best value
				System.out.println();
				
				String returnValue = alphabeta(depth-1,  a, b, 1-player, s, copyb);
				// 1 2 3 4:value
				int newValue = Integer.valueOf(returnValue.substring(8));

			
				if(newValue < originalValue){
					originalValue = newValue;
					originalMove = s;
				}
				// change the beta value if b is less then original value
				if(originalValue < b){
					b = originalValue;
				}				
				
			}
			
			System.out.println("WHITE R move: ");
			System.out.println(move + ":" + b);
			return originalMove+":"+b;
		}
	}
	
	// going to send coordinates to move on the board x,y of prev and x,y of new coords with space between it
	// x1 y1 x2 y2
	// 1 1 2 2
	/**
	 * Takes the board and makes the move
	 * @param move - move to make on the board
	 * @param board - copy of the original board
	 * @return - moves that was made and piece if it was capture or not
	 */
	public String makeMoveAI(String move, String[][] board){
		char piece = 0;
		String movePlusPiece = "";
		if(move.length() != 0 ){
			
			if(board[Character.getNumericValue(move.charAt(4))][Character.getNumericValue(move.charAt(6))] == " "){
				board[Character.getNumericValue(move.charAt(4))][Character.getNumericValue(move.charAt(6))] = board[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(2))];
				board[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(2))] = " ";
			}else{
				if(Character.isLowerCase(board[Character.getNumericValue(move.charAt(4))][Character.getNumericValue(move.charAt(6))].charAt(0)) || 
						Character.isUpperCase(board[Character.getNumericValue(move.charAt(4))][Character.getNumericValue(move.charAt(6))].charAt(0))
						){
					piece = board[Character.getNumericValue(move.charAt(4))][Character.getNumericValue(move.charAt(6))].charAt(0);
				}
				board[Character.getNumericValue(move.charAt(4))][Character.getNumericValue(move.charAt(6))] = board[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(2))];
				board[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(2))] = " ";
			}
		}
		if(piece == 0){
			movePlusPiece = move +":" + " ";
		}else{
			movePlusPiece = move +":" + piece;
		}
		return movePlusPiece;
	}
	
	
	/**
	 * Deeps copys board 
	 * @param board - board to copy
	 * @return - new board to return
	 */
	public String[][] copyBoard(String[][] board){
		String[][] newBoard = new String[8][8];
		for(int i = 0; i< newBoard.length;i++){
			for (int j = 0; j < newBoard.length; j++) {
				newBoard[i][j] = board[i][j];
			}
		}
		return newBoard;
	}
	
	/**
	 * Gets all the moves for black and white pieces on the board
	 * @param pieceColor - color to get piece for
	 * @param board - board to get the moves from
	 * @param possibleMoves - validates the moves on the board
	 */
	public void getAllMovesAi(int pieceColor, String[][] board, ValidateMoves possibleMoves){
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if(pieceColor == Gui.BLACK){
					if(Character.isLowerCase(board[i][j].charAt(0))){
						@SuppressWarnings("unused")
						ArrayList<String> tempList = possibleMoves.permittedMoves(i, j, board);
					}
				}else{
					if(Character.isUpperCase(board[i][j].charAt(0))){
						@SuppressWarnings("unused")
						ArrayList<String> tempList = possibleMoves.permittedMoves(i, j, board);
					}
				}
			}
		}
	}
	
	/**
	 * Evaluates the board. This is not strong function but it does good job at ply 3
	 * @param board - board to perform evalution on 
	 * @return - score of the board. Black is maximing and white is minizing
	 */
	public int evalutionBoard(String[][] board){
		int whiteScore = 0;
		int blackScore = 0;
		int boardScore = 0;
		
		for(int i=0; i<board.length; i++){
			for(int j=0; j<board.length; j++){
				if(Character.isLowerCase(board[i][j].charAt(0))){ // case when piece is black
					if(board[i][j].equals("p")){
						blackScore += 1;
					}else if(board[i][j].equals("b") || board[i][j].equals("k")){
						blackScore += 3;
					}else if(board[i][j].equals("r")){
						blackScore += 5;
					}else if(board[i][j].equals("q")){
						blackScore += 9;
					}else if(board[i][j].equals("a")){
						blackScore += 10000;
					}
				}else if(Character.isUpperCase(board[i][j].charAt(0))){ // cases when the piece is white
					if(board[i][j].equals("P")){
						whiteScore += 1;
					}else if(board[i][j].equals("B") || board[i][j].equals("K")){
						whiteScore += 3;
					}else if(board[i][j].equals("R")){
						whiteScore += 5;
					}else if(board[i][j].equals("Q")){
						whiteScore += 9;
					}else if(board[i][j].equals("A")){
						whiteScore += 10000;
					}
				
				}
			}
		}
		
		boardScore = blackScore - whiteScore; // white minimizes, black maximize
		return boardScore;
	}
	
	
	/*
	 * prints the board. For testing purpose
	 */
	public void print(String[][] chessboard){
		for(int i=0; i<chessboard.length; i++){
			for(int j=0; j<chessboard[i].length; j++){
				System.out.print(chessboard[i][j]);
			}
			System.out.println();
		}
	}
}
