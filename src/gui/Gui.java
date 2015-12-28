package gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

import Algorithm.ValidateMoves;

public class Gui extends JPanel implements ActionListener{
	
	// board of buttons
	JButton[][] board = new JButton[8][8];
	
	// menubar
	public JToolBar tools = new JToolBar();
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

	JButton new_game = new JButton("New Game");
	JButton save = new JButton("Save");
	JButton reset = new JButton("Reset");
	private Image[][] chessPieceImages = new Image[2][6];
	public static final int BLACK = 0, WHITE = 1;
	public static final int QUEEN = 0, KING = 1, ROOK = 2, KNIGHT = 3, BISHOP = 4, PAWN = 5;
	public static final int[] STARTING_ROW = { ROOK, KNIGHT, BISHOP, KING, QUEEN, BISHOP, KNIGHT, ROOK};

	int[][] buttonGrid = new int[8][8];
	

	public Gui(){
		createImages();
		// menu bar
		setBorder(new EmptyBorder(15,15,15,15));
		tools.setFloatable(false);
		tools.add(new_game);
		tools.addSeparator();
		tools.add(save);
		tools.addSeparator();
		tools.add(reset);
		
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
	 	board[1][i].setIcon((new ImageIcon(chessPieceImages[BLACK][PAWN])));
    }
	// places black actual pieces
	 for(int i=0; i<8; i++){
		 board[0][i].setIcon((new ImageIcon(chessPieceImages[BLACK][STARTING_ROW[i]])));
	 }
		 
	 // places the white pawn pieces
	 for (int i = 0; i < 8; i++) {
		 board[6][i].setIcon((new ImageIcon(chessPieceImages[WHITE][PAWN])));
     }
	 // places white actual pieces
	 for(int i=0; i<8; i++){
		 board[7][i].setIcon((new ImageIcon(chessPieceImages[WHITE][STARTING_ROW[i]])));
	 }
		
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

	//Gets the chess board
	public String[][] returnChessboard(){
		return chessBoard;
	}
	//Used to update the internal representation of chess board
	public void storeSource (int sourceX,int sourceY){
		//Used to store chess board value
		String temp="";
		temp = chessBoard[sourceX][sourceY];
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	//Used in the class below
	JButton firstClick = null;
	ImageIcon mc = null;
	ArrayList<String> list = new ArrayList<>();
	ValidateMoves moves = new ValidateMoves();
	
	public class ChessListener implements ActionListener {
		//Remembers its own position
		int row, column;
		
		public ChessListener(int i, int j){
			this.row = i;
			this.column = j;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton clickButton = (JButton)e.getSource();
			if(firstClick == null){
				firstClick = clickButton;
				mc = (ImageIcon) firstClick.getIcon();
				
				//list = moves.permittedMoves(row, column);
			}else{
				if(clickButton.getIcon() == null){
					clickButton.setIcon(mc);
					firstClick.setIcon(null);
					firstClick = null;
				}			
			}
			System.out.println("index in the array: " + row + " : " + column);
		}

	}
	
}
