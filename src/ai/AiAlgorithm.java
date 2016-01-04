package ai;

import java.util.ArrayList;

import Algorithm.ValidateMoves;

public class AiAlgorithm {

	ValidateMoves possibleMoves = new ValidateMoves();
	String[][] board;
	ArrayList<String> list = new ArrayList<String>();
	
	public AiAlgorithm(){
		
	}
	
	// going to send coordinates to move on the board x,y of perv and x,y of new coords with space between it
	// x1,y1 x2,y2
	// 1,1 2,2
	public void makeMove(String move){
		if(move.length() != 0){
			board[Character.getNumericValue(move.charAt(4))][Character.getNumericValue(move.charAt(6))] = board[Character.getNumericValue(move.charAt(1))][Character.getNumericValue(move.charAt(3))];
			board[Character.getNumericValue(move.charAt(4))][Character.getNumericValue(move.charAt(6))] = " ";
		}
	}
	
	// put the board back into original position after finishing up the move
	public void revert(String move){
		if(move.length() != 0){
			board[Character.getNumericValue(move.charAt(1))][Character.getNumericValue(move.charAt(3))] = board[Character.getNumericValue(move.charAt(4))][Character.getNumericValue(move.charAt(6))];
			board[Character.getNumericValue(move.charAt(1))][Character.getNumericValue(move.charAt(3))] = " ";
		}
	}
	
	
	
	public void chessBoard(String[][] chessBoard){
		board = chessBoard;
	}
	
	// domove for getting evalutionBoard score
	
	public int alphatbeta(int depth, int beta, int alpha, String[][] board, int player){
		
		if(depth == 0){
			int evluateScore = evalutionBoard(board);
			return evluateScore;
		}
		
		if(player == 0){
			// black maxs
			list = possibleMoves.possibleWhiteMoves();
			
			// for every possible move
			for(String s : list){
				// apply the move 
				makeMove(s);
				// do the max( alphatbeta(depth-1, beta, alpha, board, 1-player)) for alpha and find the best value 
				alpha = Math.max(alpha, alphatbeta(depth-1, beta, alpha, board, player*2-1));
				// revert back
				revert(s);
				// cut off points if beta is still less then alpha
				if(beta <= alpha){
					break; 
				}
			}
			return alpha;
		}else{
			//white mini
			list = possibleMoves.possibleBlackMoves();
			// for every possible move
			for(String s : list){
				// apply the move 
				makeMove(s);
				// do the max( alphatbeta(depth-1, beta, alpha, board, 1-player)) for alpha and find the best value 
				beta = Math.min(beta, alphatbeta(depth-1, beta, alpha, board, player*2-1));
				// revert back
				revert(s);
				// cut off points if beta is still less then alpha
				if(beta <= alpha){
					break; 
				}
				
			}
			return beta;
		}
	}
	
	
	
	public int evalutionBoard(String[][] board){
		int whiteScore = 0;
		int blackScore = 0;
		int boardScore = 0;
		
		for(int i=0; i<board.length; i++){
			for(int j=0; j<board.length; j++){
				if(Character.isLowerCase(board[i][j].charAt(0))){ // case when piece is black
					if(board[i][j] == "p"){
						blackScore += 1;
					}else if(board[i][j] == "b" || board[i][j] == "k"){
						blackScore += 3;
					}else if(board[i][j] == "r"){
						blackScore += 5;
					}else if(board[i][j] == "q"){
						blackScore += 9;
					}else if(board[i][j] == "a"){
						blackScore += 10000;
					}
				}else if(Character.isUpperCase(board[i][j].charAt(0))){ // cases when the piece is white
					if(board[i][j] == "P"){
						whiteScore += 1;
					}else if(board[i][j] == "B" || board[i][j] == "K"){
						whiteScore += 3;
					}else if(board[i][j] == "R"){
						whiteScore += 5;
					}else if(board[i][j] == "Q"){
						whiteScore += 9;
					}else if(board[i][j] == "A"){
						whiteScore += 10000;
					}
				
				}
			}
		}
		
		boardScore = blackScore - whiteScore; // white minimizes, black maximize
		return 0;
	}
	
}
