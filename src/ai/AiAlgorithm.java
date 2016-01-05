package ai;

import java.util.ArrayList;

import Algorithm.ValidateMoves;

public class AiAlgorithm {

	ValidateMoves possibleMoves = new ValidateMoves();
	String[][] board;
	ArrayList<String> list = new ArrayList<String>();
	
	public AiAlgorithm(String[][] chessBoard){
		this.board = chessBoard;
	}
	
	// doMove for getting evalutionBoard score
	
	public int alphabeta(int depth, int alpha, int beta, String[][] board, int player, String move){
		//Gets the moves loaded in the possibleWhiteMoves and possibleBlackMoves
		getAllMoves();
		
		if(depth == 0){
			int evaluateScore = evalutionBoard(board);
			// score + " " + move
			//return  move + ":"+ evaluateScore ;
			return evaluateScore;
		}
		
		if(player == 0){
			// black maxs
			list = possibleMoves.possibleBlackMoves();
			// for every possible move
			int newAlpha = Integer.MIN_VALUE;
			for(String s : list){
				System.out.println(s);
				// apply the move 
				makeMove(s);
				list.clear();
				// do the max( alphatbeta(depth-1, beta, alpha, board, 1-player)) for alpha and find the best value 
				// x1 y1 x2 y2
				print(board);
				String[][] newBoard = board;
				//String returnString = alphabeta(depth-1, beta, alpha, board, 1-player, s);
				//newValue = Math.max(newValue, Integer.valueOf(returnString.substring(8)));
				//if(returnString.length() >9){
				newAlpha = Math.max(newAlpha, alphabeta(depth-1, alpha, beta, newBoard, 1-player, s));
				alpha = Math.max(alpha, newAlpha);
				System.out.println(newAlpha + " : real: " + alpha);
				//}
					

				// revert back
				revert(s);
				// cut off points if beta is still less then alpha
				if(beta <= newAlpha){
					break; 
				}
			}
			//return move+":"+alpha;
			return newAlpha;
		}else{
			//white mini
			list = possibleMoves.possibleWhiteMoves();
			// for every possible move
			int newBeta = Integer.MAX_VALUE;
			for(String s : list){
				System.out.println(s);
				// apply the move
				makeMove(s);
				list.clear();
				// do the min( alphatbeta(depth-1, beta, alpha, board, 1-player)) for alpha and find the best value
				//String returnString = alphabeta(depth-1, beta, alpha, board, 1-player, s);
				print(board);
				String[][] newBoard = board;
//				if(returnString.length() > 9){
//					beta = Math.min(beta, Integer.valueOf(returnString.substring(8)));
//					System.out.println(beta);
//				}
				newBeta = Math.min(newBeta, alphabeta(depth-1,  alpha, beta, newBoard, 1-player, s));
				beta = Math.min(beta, newBeta);
				System.out.println(newBeta + " : real: " + beta);
				//System.out.println(alphabeta(depth-1,  alpha, beta, board, 1-player, s));
				// revert back
				revert(s);
				// cut off points if beta is still less then alpha
				if(newBeta <= alpha){
					break; 
				}
				
			}
			//return move+":"+beta;
			return newBeta;
		}
	}
	
	// going to send coordinates to move on the board x,y of prev and x,y of new coords with space between it
	// x1 y1 x2 y2
	// 1 1 2 2
	public void makeMove(String move){
		if(move.length() != 0 ){
			board[Character.getNumericValue(move.charAt(4))][Character.getNumericValue(move.charAt(6))] = board[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(2))];
			board[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(2))] = " ";
			print(board);
		}else{
			System.out.println("Stop a Douchbag");
		}
	}
		
	// put the board back into original position after finishing up the move
	public void revert(String move){
		if(move.length() != 0){
			board[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(2))] = board[Character.getNumericValue(move.charAt(4))][Character.getNumericValue(move.charAt(6))];
			board[Character.getNumericValue(move.charAt(4))][Character.getNumericValue(move.charAt(6))] = " ";
		}
	}
	
	//Used to load the list with all moves
	public void getAllMoves(){
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				ArrayList<String> tempList = possibleMoves.permittedMoves(i, j, board);
				tempList.clear();
			}
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
		return boardScore;
	}
	
	
	public void print(String[][] chessboard){
		for(int i=0; i<chessboard.length; i++){
			for(int j=0; j<chessboard[i].length; j++){
				System.out.print(chessboard[i][j]);
			}
			System.out.println();
		}
	}
}
