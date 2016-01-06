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
	
	public String alphabeta(int depth, int alpha, int beta, int player, String move, String[][] board){
		Integer originalValue ;
		String originalMove = null;
		int a = alpha;
		int b = beta;
		
		
		if(depth == 0){
			int evaluateScore = evalutionBoard(board);
			
			System.out.println("depth= 0 move");

			return move + ":" +evaluateScore;
		}
		//Black
		if(player == 0){
			ValidateMoves vm = new ValidateMoves();
			getAllMovesAi(player, board, vm);
			
			originalValue = Integer.MIN_VALUE;
			
			ArrayList<String> Blist = new ArrayList<String>();
			
			//Gets the moves loaded in the possibleWhiteMoves and possibleBlackMoves

			Blist = vm.possibleBlackMoves();
				
			Collections.sort(Blist);

			for (int i = 0; i <Blist.size(); i++) {
				if(originalValue > b){
					if(originalMove.substring(0, 2).equals(Blist.get(i).substring(0, 2)))
					continue;
				}
				//Get the item from the list
				
				String s = Blist.get(i);
				System.out.println("black moves");
				System.out.println(s);
				// apply the move 
				String[][] copyb = copyBoard(board);
				String movePlusPiece = makeMoveAI(s, copyb);
				
				// do the max(alphatbeta(depth-1, beta, alpha, board, 1-player)) for alpha and find the best value 
				// x1 y1 x2 y2
				String returnValue = alphabeta(depth-1, a, b, 1-player,s, copyb);
				int newValue = Integer.valueOf(returnValue.substring(8));
				
				if(newValue > originalValue){
					originalValue = newValue;
					originalMove = s;
				}
			
				
				if(originalValue > a){
					a = originalValue;
				}
		
				
				
				// cut off points if beta is still less then alpha
//				
				
//				if(Blist.isEmpty()){
//					getAllMoves(player); 	
//					Blist = possibleMoves.possibleBlackMoves();
//				}
				
			}
			// r is for return value
			System.out.println("BLACK R move: ");
			System.out.println(move + ":" + a);
			return originalMove + ":" +a;
		}else{
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
				
				if(originalValue < b){
					b = originalValue;
				}

				
				
				
	
//				if(Wlist.isEmpty()){
//					getAllMoves(player);
//					Wlist = possibleMoves.possibleWhiteMoves();
//				}
				
				
				
			}
			
			System.out.println("WHITE R move: ");
			System.out.println(move + ":" + b);
			return originalMove+":"+b;
		}
	}
	
	// going to send coordinates to move on the board x,y of prev and x,y of new coords with space between it
	// x1 y1 x2 y2
	// 1 1 2 2
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
		if(piece == 0){
			movePlusPiece = move +":" + " ";
		}else{
			movePlusPiece = move +":" + piece;
		}
		return movePlusPiece;
	}
	
	
	
	public String[][] copyBoard(String[][] board){
		String[][] newBoard = new String[8][8];
		for(int i = 0; i< newBoard.length;i++){
			for (int j = 0; j < newBoard.length; j++) {
				newBoard[i][j] = board[i][j];
			}
		}
		return newBoard;
	}
	
	// if move is actually vaild
	
		
	// put the board back into original position after finishing up the move
	public void revertCapturePiece(String move){
		if(move.length() != 0){
			board[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(2))] = board[Character.getNumericValue(move.charAt(4))][Character.getNumericValue(move.charAt(6))];
			board[Character.getNumericValue(move.charAt(4))][Character.getNumericValue(move.charAt(6))] = move.substring(8);
		}
	}
	
	
	//Used to load the list with all moves
	public void getAllMoves(int pieceColor){
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if(pieceColor == Gui.BLACK){
					if(Character.isLowerCase(board[i][j].charAt(0))){
						ArrayList<String> tempList = possibleMoves.permittedMoves(i, j, board);
					}
				}else{
					if(Character.isUpperCase(board[i][j].charAt(0))){
						ArrayList<String> tempList = possibleMoves.permittedMoves(i, j, board);
					}
				}
			}
		}
	}
	
	public void getAllMovesAi(int pieceColor, String[][] board, ValidateMoves possibleMoves){
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if(pieceColor == Gui.BLACK){
					if(Character.isLowerCase(board[i][j].charAt(0))){
						ArrayList<String> tempList = possibleMoves.permittedMoves(i, j, board);
					}
				}else{
					if(Character.isUpperCase(board[i][j].charAt(0))){
						ArrayList<String> tempList = possibleMoves.permittedMoves(i, j, board);
					}
				}
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
	
	
	public void print(String[][] chessboard){
		for(int i=0; i<chessboard.length; i++){
			for(int j=0; j<chessboard[i].length; j++){
				System.out.print(chessboard[i][j]);
			}
			System.out.println();
		}
	}
}
