package View;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JLayeredPane;
import java.awt.Dimension;
import java.awt.Graphics;
import Utilities.Constants;
import View.MainPanel;
import Controller.UserInputListener;
import Model.GameEngine;

public class MainDisplay extends JFrame{
	private JPanel cardOne;
	private JPanel mainPanel;
	private JPanel rightPanel;
	private JPanel leftPanel;
	
	private JPanel cardTwo;
	private JPanel gameOverPanel;
	
	private JPanel cardPanel;
	
	private CardLayout cardLayout;

	public MainDisplay(String name){
		this.initialiseDisplay();
	}
	
	private void initialiseDisplay(){
	
		this.cardOne = new JPanel();
		this.cardOne.setLayout(new BorderLayout()); 
		
		this.mainPanel = new MainPanel();
		this.rightPanel = new RightPanel();
		this.leftPanel = new LeftPanel();
		
		cardOne.add(mainPanel, BorderLayout.CENTER);
		cardOne.add(rightPanel, BorderLayout.EAST);
		cardOne.add(leftPanel, BorderLayout.WEST);
		
		this.cardTwo = new JPanel();
		this.cardTwo.setLayout(new BorderLayout()); 
		
		this.gameOverPanel = new GameOverPanel();
		
		cardTwo.add(gameOverPanel, BorderLayout.CENTER);
		
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		cardPanel.add(cardOne, "GamePanel");
        cardPanel.add(cardTwo, "EndScreenPanel");
		
		this.add(cardPanel);
		
		//cardLayout.show(cardPanel, "EndScreenPanel");
		cardLayout.show(cardPanel, "GamePanel");
		
		this.pack();
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.addKeyListener(new UserInputListener());
		
		this.setVisible(true);
	}
	
	// public CardLayout getCardLayout(){
		// return this.cardLayout;
	// }
	
	public void changeToGameOverScreen(){
		cardLayout.show(cardPanel, "EndScreenPanel");
	}
	
	public void changeToMainGameScreen(){
		cardLayout.show(cardPanel, "GamePanel");
		this.requestFocus();
	}
}