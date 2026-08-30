package View;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.ImageIcon;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.awt.Dimension;
// import java.awt.Color;

import Game.Game;
// import Model.GameEngine;
// import Utilities.*;

// import Assets.Planet;
// import Assets.VisualEffectTransient;
// import Assets.VisualEffectLooped;

import Textures.TextureLoader;
// import java.util.ArrayList;
// import Model.Score;

// import java.awt.Toolkit;

public class GameOverPanel extends JPanel{

	private JButton playAgainButton;
	
	public GameOverPanel(){
		playAgainButton = new JButton("Play Again");
		playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				Game.getCurrentGame().getMainDisplay().changeToMainGameScreen();
            }
        });
		this.add(playAgainButton);
	}

	@Override	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		
	}
}