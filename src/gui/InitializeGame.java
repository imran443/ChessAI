package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class InitializeGame {
	
	// gui class
	Gui gui;

	// frame
	JFrame frame = new JFrame("Initializing");
	// panel
	JPanel panel = new JPanel(new BorderLayout(2,2));
	// labels panel
	JPanel labels = new JPanel(new GridLayout(0,1,1,1));
	
	// fields panel
	JPanel fields = new JPanel(new GridLayout(0,1,1,1));
	
	// button panel
	JPanel buttonHolds = new JPanel(new FlowLayout(FlowLayout.CENTER));
	// button to start the game
	JButton play;
	// label to enter a ply number
	JLabel ply = new JLabel("Enter Ply number: ");
	
	// label to who to play against
	JLabel horc = new JLabel("Human or Computer?");
	
	// field that takes output of ply input
	JTextField plyInput;
	// field that takes output of human vs computer input
	JTextField horcInput;
	
	// variables to hold the above fields values
	String ply_num;
	String secondPlayer;
	
	
	// initialize all the variables aboves and set them up
	public InitializeGame(){	
		
		panel.setBorder(new TitledBorder("Questions"));
		plyInput = new JTextField(10);
		horcInput = new JTextField(10);
		
		
		labels.add(ply);
		labels.add(horc);
		
		fields.add(plyInput);
		fields.add(horcInput);
		
		
		play = new JButton("Play!");
	
		buttonHolds.add(play);
		
		// set up on the panel
		panel.add(labels, BorderLayout.CENTER);
		panel.add(fields, BorderLayout.EAST);
		panel.add(buttonHolds, BorderLayout.SOUTH);

		
		frame.setSize(300,200);
		
		// set the panel on frame
		frame.add(panel, BorderLayout.NORTH);
		frame.setVisible(true);
		
		// attach the listener to open up the game
		play.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ply_num = plyInput.getText();
				secondPlayer = horcInput.getText();
				
				if(ply_num.length() != 0){
					Gui();
					frame.dispose();
				}
			}
			
		});
	}
	
	// methods calls the GUI class that holds the game board 
	public void Gui(){
		JFrame frame = new JFrame("Chess");
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui = new Gui();
		frame.getContentPane().add(gui.tools, BorderLayout.PAGE_START);
		frame.getContentPane().add(gui, BorderLayout.CENTER);
		frame.setVisible(true);		
	}

	
}
