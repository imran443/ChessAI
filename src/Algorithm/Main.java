package Algorithm;

import java.awt.BorderLayout;

import gui.Gui;
import gui.InitializeGame;

import javax.swing.JFrame;

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
