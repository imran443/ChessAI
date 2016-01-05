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
	
	public String alphabeta(int depth, int alpha, int beta, int player, String move){
	
		if(depth == 0){
			int evaluateScore = evalutionBoard(board);
			// score + " " + move
			//return  move + ":"+ evaluateScore ;
			System.out.println("depth= 0 move");
			System.out.println(move + ":" + evaluateScore);
			return move + ":" +evaluateScore;
		}
		//Black
		if(player == 0){
			//Gets the moves loaded in the possibleWhiteMoves and possibleBlackMoves
			getAllMoves();

			list = possibleMoves.possibleBlackMoves();
			// for every possible move
			int value = Integer.MIN_VALUE;
			
			for (int i = 0; i <list.size(); i++) {
				//Get the item from the list 
				String s = list.get(i);
				System.out.println("black moves");
				System.out.println(s);
				// apply the move 
				
				String movePlusPiece = makeMove(s);
				

				//Holds move for revert when recursion back up
				String temp = s;
				list.clear();
				// do the max( alphatbeta(depth-1, beta, alpha, board, 1-player)) for alpha and find the best value 
				// x1 y1 x2 y2
				print(board);
				String returnValue = alphabeta(depth-1, alpha, beta, 1-player, s);
				int newValue = Integer.valueOf(returnValue.substring(8));
				value = Math.max(value, newValue);
				alpha = Math.max(alpha,value);
				if(depth == 2){
					move = returnValue.substring(0, 7);
				}
				// revert back
				
				if(movePlusPiece.length() > 7){
					revertCapturePiece(movePlusPiece);
				}
				System.out.println();
				System.out.println("B revert board: ");
				print(board);
				// cut off points if beta is still less then alpha
				if(beta <= alpha){
					break; 
				}
			}
			System.out.println("black move");
			System.out.println(move + ":" + value);
			return move + ":" +value;
		}else{
			//White 
			list = possibleMoves.possibleWhiteMoves();
			if(list.isEmpty()){
				//Gets the moves loaded in the possibleWhiteMoves and possibleBlackMoves
				getAllMoves();
			}
			
			//Value at minimizer node in the beginning
			int value = Integer.MAX_VALUE;
			for (int i = 0; i <list.size(); i++) {
				String s = list.get(i);
				
				System.out.println("white moves");
				System.out.println(s);
				// apply the move
				String movePlusPiece = makeMove(s);
				String temp = s;
				
				list.clear();
				// do the min( alphatbeta(depth-1, beta, alpha, board, 1-player)) for alpha and find the best value
				System.out.println();
				print(board);
				String returnValue = alphabeta(depth-1,  alpha, beta, 1-player, s);
				// 1 2 3 4:value
				int newValue = Integer.valueOf(returnValue.substring(8));
				value = Math.min(value, newValue);
				beta = Math.min(beta, value);
				// revert back
				if(depth == 2){
					move = returnValue.substring(0, 7);
				}
				
				if(movePlusPiece.length() > 7){
					revertCapturePiece(movePlusPiece);
				}
				System.out.println();
				System.out.println("W revert board: ");
				print(board);
				// cut off points if beta is still less then alpha
				if(beta <= alpha){
					break; 
				}
				if(list.isEmpty()){
					//Gets the moves loaded in the possibleWhiteMoves and possibleBlackMoves
					getAllMoves();
					list = possibleMoves.possibleWhiteMoves(); 
				}
				
			}
			System.out.println("white move: ");
			System.out.println(move + ":" + value);
			return move+":"+value;
		}
	}
	
	// going to send coordinates to move on the board x,y of prev and x,y of new coords with space between it
	// x1 y1 x2 y2
	// 1 1 2 2
	public String makeMove(String move){
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
		movePlusPiece = move +":" + piece;
		return movePlusPiece;
	}
		
	// put the board back into original position after finishing up the move
	public void revert(String move){
		if(move.length() != 0){
			board[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(2))] = board[Character.getNumericValue(move.charAt(4))][Character.getNumericValue(move.charAt(6))];
			board[Character.getNumericValue(move.charAt(4))][Character.getNumericValue(move.charAt(6))] = " ";
		}
	}
	
	public void revertCapturePiece(String move){
		if(move.length() != 0){
			board[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(2))] = board[Character.getNumericValue(move.charAt(4))][Character.getNumericValue(move.charAt(6))];
			board[Character.getNumericValue(move.charAt(4))][Character.getNumericValue(move.charAt(6))] = move.substring(8);
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
