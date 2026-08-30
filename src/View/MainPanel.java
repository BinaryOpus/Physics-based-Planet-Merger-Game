package View;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Color;

import Game.Game;
import Model.GameEngine;
import Utilities.*;

import Assets.Planet;
import Assets.VisualEffectTransient;
import Assets.VisualEffectLooped;

import Textures.TextureLoader;
import java.util.ArrayList;
import Model.Score;

import java.awt.Toolkit;
public class MainPanel extends JPanel implements Runnable{
	//GameEngine gameEngine;
	
	private int imageOneCurrentX;
	
	int counter = 0;
	
	// private ArrayList<Planet> planetArray = Game.getCurrentGame().getGameEngine().getPlanetArray();
	// private ArrayList<VisualEffectTransient> visualEffectTransientArray = Game.getCurrentGame().getGameEngine().getVisualEffectTransientArray();
	// private ArrayList<VisualEffectLooped> visualEffectLoopedArray = Game.getCurrentGame().getGameEngine().getVisualEffectLoopedArray();
	
	private ArrayList<Planet> planetArray;
	private ArrayList<VisualEffectTransient> visualEffectTransientArray;
	private ArrayList<VisualEffectLooped> visualEffectLoopedArray;
	
	public MainPanel(/*GameEngine gameEngine*/){
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(800,790));
		new Thread(this).start();
		
		planetArray = Game.getCurrentGame().getGameEngine().getPlanetArray();
		visualEffectTransientArray = Game.getCurrentGame().getGameEngine().getVisualEffectTransientArray();
		visualEffectLoopedArray = Game.getCurrentGame().getGameEngine().getVisualEffectLoopedArray();
	}
	
	@Override	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		long startTime = System.currentTimeMillis();
		
		Graphics2D g2D = (Graphics2D) g;
		
		g2D.drawImage(  TextureLoader.WALL_SIX,(0 + counter), 0, (int)(this.getWidth()*3.5) + counter, this.getHeight(),	
						0,0,800,210, this);
		g2D.drawImage(  TextureLoader.WALL_SIX,((int)(this.getWidth()*3.5) + counter), 0, this.getWidth()*7 + counter, this.getHeight(),
						0,0,800,210, this);
		
		Game.getCurrentGame().getGameEngine().getLevel().draw(g2D);
		
		//ArrayList<Planet> planetArray = Game.getCurrentGame().getGameEngine().getPlanetArray();
		for(int i = 0; i<planetArray.size(); ++i){
			planetArray.get(i).draw(g2D);
		}
		
		// for(int i = 0; i<VisualEffect.getVisualEffectArray().size(); ++i){
			// VisualEffect.getVisualEffectArray().get(i).draw(g2D);
		// }
		
		for(int i = 0; i<visualEffectTransientArray.size(); ++i){
			visualEffectTransientArray.get(i).draw(g2D);
		}
		
		for(int i = 0; i<visualEffectLoopedArray.size(); ++i){
			visualEffectLoopedArray.get(i).draw(g2D);
		}
		
		for(int i = 0; i<Score.getScoreArray().size(); ++i){
			Score.getScoreArray().get(i).draw(g2D);
		}
		//System.out.println("Draw time " + (System.currentTimeMillis() - startTime) );
		
	}
	
	@Override
	public void run(){
		while(this.isVisible()){
			try {
				Thread.sleep(80); // Pause for 5 seconds
				counter-=1;
				imageOneCurrentX = (int)(this.getWidth()*3.5) + counter;
			} 
			catch (InterruptedException e) {
				System.err.println("Thread interrupted: " + e.getMessage());
				Thread.currentThread().interrupt(); // Restore interrupt status
			}
			if(imageOneCurrentX < 0){
				counter = 0;
			}
		}
	}
}
