package gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

import Algorithm.KingSafety;
import Algorithm.ValidateMoves;
import ai.AiAlgorithm;

public class Gui extends JPanel implements ActionListener{
	//Lower case letter are Black and Upper case are White
	public static String chessBoard[][]={
				       {"r","k","b","q","a","b","k","r"},
				       {"p","p","p","p","p","p","p","p"},
				       {" "," "," "," "," "," "," "," "},
				       {" "," "," "," "," "," "," "," "},
				       {" "," "," "," "," "," "," "," "},
				       {" "," "," "," "," "," "," "," "},
				       {"P","P","P","P","P","P","P","P"},
				       {"R","K","B","Q","A","B","K","R"}};
	//This is a secondary copy of the board used for checking if the king is in check
	
	String[][] chessBoardCopy = new String[8][8];
	String[][] aiChessBoard = new String[8][8];
	// board of buttons
	JButton[][] board = new JButton[8][8];

	public JToolBar tools;
	
	JButton new_game = new JButton("New Game");
	JButton save = new JButton("Save");
	JButton reset = new JButton("Reset");
	JButton reset2 = new JButton("Reset");
	JLabel white_turn= new JLabel("WHITE's Turn");
	JLabel black_turn = new JLabel("BLACK's Turn");
	private Image[][] chessPieceImages = new Image[2][6];
	
	public static final int BLACK = 0, WHITE = 1;
	
	
	public static final int QUEEN = 0, KING = 1, ROOK = 2, KNIGHT = 3, BISHOP = 4, PAWN = 5;
	public static final int[] STARTING_ROW = { ROOK, KNIGHT, BISHOP, KING, QUEEN, BISHOP, KNIGHT, ROOK};
	
	int ply;
	int turnCount = 0;
	String playerId;
	int[][] buttonGrid = new int[8][8];
	//Used to validate moves
	ValidateMoves moves = new ValidateMoves();
	
	// checks for who turn it is 
	boolean computerPlayer = false;
	boolean humanPlayer = true;
	
	// AI
	AiAlgorithm ai ;
	
	//Used to see if the king is safe and not in check or checkmate
	KingSafety kingSafety = new KingSafety();


	public Gui(String Ply, String Player){
		this.ply = Integer.parseInt(Ply);
		this.playerId = Player;
		
		
		tools = new JToolBar();
	
		white_turn.setBackground(Color.GREEN);
		black_turn.setBackground(Color.YELLOW);
		
		white_turn.setOpaque(true);
		black_turn.setOpaque(true);
		createImages();
		// menu bar
		setBorder(new EmptyBorder(15,15,15,15));
		tools.setFloatable(false);
		tools.add(new_game);
		tools.addSeparator();
		tools.add(save);
		tools.addSeparator();
		tools.add(reset);
		tools.addSeparator();
		tools.add(white_turn);
		
		
		// background
		setBackground(Color.GRAY);
		setSize(600,600);
		
		setLayout(new GridLayout(8, 8));

		for(int i=0; i<board.length; i++){
			for(int j=0; j<board[i].length; j++){
				// alternate button have black and white background
				if((i%2 == 0 && j % 2 == 0) || (i%2 == 1 && j%2==1)){
					JButton button = new JButton();
					button.setBackground(Color.WHITE);
					button.addActionListener(new ChessListener(i, j));
					
					add(button);
					board[i][j] = button;
				}else{
					JButton button = new JButton();
					button.setBackground(Color.BLACK);
					button.addActionListener(new ChessListener(i, j));
					
					add(button);
					board[i][j] = button;
				}
			}
		}
		// places black pawn pieces
		 for (int i = 0; i < 8; i++) {
			 ImageIcon icon = new ImageIcon(chessPieceImages[BLACK][PAWN]);
			 // labels the piece to black
			 icon.setDescription("" + BLACK);
		 	board[1][i].setIcon(icon);
	    }
		// places black actual pieces
		 for(int i=0; i<8; i++){
			 ImageIcon icon = new ImageIcon(chessPieceImages[BLACK][STARTING_ROW[i]]);
			 icon.setDescription("" + BLACK);
			 board[0][i].setIcon(icon);
		 }
			 
		 // places the white pawn pieces
		 for (int i = 0; i < 8; i++) {
			 ImageIcon icon = new ImageIcon(chessPieceImages[WHITE][PAWN]);
			 icon.setDescription("" + WHITE);
			 board[6][i].setIcon(icon);
	     }
		 // places white actual pieces
		 for(int i=0; i<8; i++){
			 ImageIcon icon = new ImageIcon(chessPieceImages[WHITE][STARTING_ROW[i]]);
			 icon.setDescription("" + WHITE);
			 board[7][i].setIcon(icon);
		 }
		 
		copyAiArray();
		ai = new AiAlgorithm(aiChessBoard, ply);

	}
	
	private final void createImages() {
        try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);
            for (int ii = 0; ii < 2; ii++) {
                for (int jj = 0; jj < 6; jj++) {
                    chessPieceImages[ii][jj] = bi.getSubimage(
                            jj * 64, ii * 64, 64, 64);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

	public void print(String[][] chessboard){
		for(int i=0; i<chessboard.length; i++){
			for(int j=0; j<chessboard[i].length; j++){
				System.out.print(chessboard[i][j]);
			}
			System.out.println();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {}
	
	// stores sourceX, sourceY and the piece at that position
	String[] sourceArray = new String[3];
	
	// the clicked x,y and piece on the board
	public void storeSource(int sourceX, int sourceY){
		String row = "" + sourceX;
		String column = "" + sourceY;
		String temp = chessBoard[sourceX][sourceY];
		
		sourceArray[0] = row;
		sourceArray[1] = column;
		sourceArray[2] = temp;
	}
	
	// updates the chess board array according GUI moves
	public void UpdateChessBoard(int newX, int newY, String[][] chessBoard){
		// updates the position of the piece on the chess board
		chessBoard[newX][newY] = sourceArray[2];
		chessBoard[Integer.parseInt(sourceArray[0])][Integer.parseInt(sourceArray[1])] = " ";
		// resets it for the next move
		sourceArray[0] = "";
		sourceArray[1] = "";
		sourceArray[2] = "";
	}
	// updates the chess board copy array according GUI moves
	public void updateChessBoardCopy(int newX, int newY, String[][] chessBoard){
		// updates the position of the piece on the chess board
		chessBoard[newX][newY] = sourceArray[2];
		chessBoard[Integer.parseInt(sourceArray[0])][Integer.parseInt(sourceArray[1])] = " ";
	}
	
	//Copies chessBoard array to chessBoardCopy 
	public void copyArray(){
		for(int i = 0; i<chessBoard.length;i++){
			for (int j = 0; j < chessBoard.length; j++) {
				chessBoardCopy[i][j] = chessBoard[i][j];
			}
		}
	}
	
	public void copyAiArray(){
		for(int i = 0; i<chessBoard.length;i++){
			for (int j = 0; j < chessBoard.length; j++) {
				aiChessBoard[i][j] = chessBoard[i][j];
			}
		}
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
	
	
	
	//This is a nested class
	JButton firstClick = null;
	ImageIcon mc = null;
	ArrayList<String> list = new ArrayList<>();
	
	public class ChessListener implements ActionListener {
		//Remembers its own position
		int row, column;
		
		public ChessListener(int i, int j){
			this.row = i;
			this.column = j;
		}
		
		
		public boolean placingPiece(JButton clickButton){
			int pieceColor = 0;
			copyArray();
			//Decides what color to send into the kingSafety, based on opposite color
			if(Integer.parseInt(mc.getDescription()) == Gui.WHITE){
				pieceColor = Gui.WHITE;
			}else {
				pieceColor = Gui.BLACK;
			}
			//Do the move for the copy chessBoard 
			updateChessBoardCopy(row, column, chessBoardCopy);
			if(pieceColor == WHITE){
				kingSafety.kingCheckMate(chessBoardCopy, BLACK);
			}else {
				kingSafety.kingCheckMate(chessBoardCopy, WHITE);
			}
			//Check on the copy chess board if king is in check, if it is then don't do move on real chessBoard
			if(kingSafety.kingInCheck(chessBoardCopy, pieceColor)==false){
				// updates the icon on the board
				clickButton.setIcon(mc);
				// sets the icon of first click position to null
				firstClick.setIcon(null);
				firstClick = null;
				list.clear();
				
				// this method is used for updating chessBoard
				UpdateChessBoard(row, column, chessBoard);
				copyAiArray();
				print(chessBoard);
				//Return if board is updated successfully and piece has been placed.
				return true;
			}else{
				print(chessBoard);
				//Failed to make move 
				return false;
			}
		}
		
		// this method selects the piece user wants to place or AI wants to place
		public boolean selectingPiece(JButton clickButton){
			
			if(firstClick == null){
				firstClick = clickButton;
				mc = (ImageIcon) firstClick.getIcon();
				// this method is used for updating chessBoard
				storeSource(row, column);
				//Checks for the possible moves and returns them in a array list
				list = moves.permittedMoves(row, column,chessBoard);
				// checks if some other piece is selected 
			}else if(firstClick != null && clickButton.getIcon() != null){
				// capture a piece if valid position
				if(moves.isValid(row, column, list)==true){
					// checks whose turn it is
					// computer players turn
					if(mc.getDescription().equals(String.valueOf(1)) && humanPlayer == true){
						//If placing piece is done then end turn and return true, else false
						if(placingPiece(clickButton)){
							humanPlayer = false;
							return true;
						}else {
							System.out.println("In Check");
							return false;
						}
						// human player turn
					}else if(mc.getDescription().equals(String.valueOf(0)) && computerPlayer == true){
						if(placingPiece(clickButton)){
							computerPlayer = false;
							return true;
						}else{
							System.out.println("In Check");
							return false;
						}
					}
					
				}else{
					// if something else is selected, deselected from the original piece
					// if nothing is selected the original piece is selected
					list.clear();
					firstClick = clickButton;
					mc = (ImageIcon) firstClick.getIcon();
					// this method is used for updating chessBoard
					storeSource(row, column);
					list = moves.permittedMoves(row, column,chessBoard);
				}
				

			}
			else{
				//Checks in the array list if the move that is being made is valid 
				if(moves.isValid(row, column, list)==true){
					if(clickButton.getIcon() == null){
						// checks whose turn it is
						// if computer player turns
						if(mc.getDescription().equals(String.valueOf(1)) && humanPlayer == true){
							if(placingPiece(clickButton)){
								humanPlayer = false;
								return true;
							}else {
								System.out.println("In Check");
								return false;
							}
							// human players turn
						}else if(mc.getDescription().equals(String.valueOf(0)) && computerPlayer == true){
							if(placingPiece(clickButton)){
								computerPlayer = false;
								return true;
							}else{
								System.out.println("In Check");
								return false;
							}
						}
						
					}
				}
			}
			return false;
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton clickButton = (JButton)e.getSource();
			// turn systems, white always goes first. 

			//Human player is white and goes first
			if(humanPlayer == true){

				if(selectingPiece(clickButton)){
					computerPlayer = true;
					//Puts up the count when turn is finished
					turnCount++;
					tools.remove(white_turn);
					tools.add(black_turn);
					
					tools.revalidate();
					tools.repaint();
					

					System.out.println(ai.alphabeta(ply, Integer.MIN_VALUE, Integer.MAX_VALUE, BLACK, "", copyBoard(chessBoard)));
					System.out.println("Ai chess board");
					print(aiChessBoard);

				}
				
				//Black
			}else if(computerPlayer==true){
		
				if(selectingPiece(clickButton)){
					humanPlayer = true;
					//Puts up the count when turn is finished
					turnCount++;
					tools.remove(black_turn);
					tools.add(white_turn);
					
					tools.revalidate();
					tools.repaint();
				}
				
			}
			
			System.out.println("index in the array: " + row + " : " + column);
		}

	}
	
	
}
