package Algorithm;

import gui.InitializeGame;



public class Main {

	InitializeGame initGame;
	
	
	public Main(){
		Initialize();
	}
	
	
	public void Initialize(){
		initGame = new InitializeGame();
	}

	public static void main(String[] args) {
		new Main();
	}

}
