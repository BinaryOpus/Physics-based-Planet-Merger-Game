package View;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import Textures.TextureLoader;
import java.awt.Image;
import Game.Game;


public class RightPanel extends JPanel{

	//private PlanetPanel nextPlanetPanel = new PlanetPanel();
	private JPanel mergeGuidePanel = new JPanel();
	
	
	public RightPanel(){
		
		this.setPreferredSize(new Dimension(300,900));
	}
	
	

	private void mergeGuidePanelSetup(){
		mergeGuidePanel.add(new JLabel("Hello everyone :("));
	}

	@Override	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		
		//draw panel background
		g2D.drawImage(TextureLoader.SIDE_PANEL, 0, 0, getWidth(),getHeight(), this);
		
		//Draw Next planet assets
		g2D.drawImage(TextureLoader.WALL_FOUR, 30, 60, 240, 240,this);
		g2D.drawImage( Game.getCurrentGame().getGameEngine().getLevel().getSlider().getNextPlanetTexture(), 50, 85, 200, 190, null);
		
		//Draw merge thing
		g2D.drawImage(TextureLoader.WALL_FIVE, 30, 320, 220, 420, null);
		
	}
}