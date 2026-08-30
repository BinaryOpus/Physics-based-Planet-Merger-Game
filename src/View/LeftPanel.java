package View;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.image.BufferedImage;
import Textures.TextureLoader;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Image;

import Model.Score;


public class LeftPanel extends JPanel{

	// NORTH panel with fixed height
	private JPanel northPanel = new JPanel();
	// SOUTH panel with fixed height
	private JPanel southPanel = new JPanel();
	
	private static JLabel scoreText;
	
	public LeftPanel(){
		this.setLayout(null);
		this.setPreferredSize(new Dimension(300,900));
		this.scoreText = new JLabel("" + Score.getTotalScore());
		//this.setBackground(Color.MAGENTA);
		this.panelSetup();
	}
	
	private void panelSetup(){
		//northPanel.setBackground(Color.CYAN);
		// northPanel.setPreferredSize(new Dimension(250, 300)); // width ignored, height respected
		// northPanel.add(new JLabel(new ImageIcon(TextureLoader.WALL_TWO.getScaledInstance(250, 300, Image.SCALE_FAST))));
		// northPanel.add(scoreText);
		// this.add(northPanel, BorderLayout.NORTH);
		//this.setBorder(new EmptyBorder(100, 100, 700, 100));
		
		scoreText.setBounds(60, 200, 300, 55); 
		scoreText.setFont(new Font("Arial Black", Font.BOLD,35));
		this.add(scoreText);
		//southPanel.setBackground(Color.PINK);
		//southPanel.setPreferredSize(new Dimension(250, 500));
		// southPanel.add(new JLabel(new ImageIcon(TextureLoader.WALL_THREE.getScaledInstance(200, 500, Image.SCALE_FAST))));
		// southPanel.setOpaque(false);
		//this.add(southPanel, BorderLayout.SOUTH);
	}
	
	@Override	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		
		g2D.drawImage(TextureLoader.SIDE_PANEL, 0, 0, getWidth(),getHeight(), this);
		
		//Draw Score assets
		g2D.drawImage(TextureLoader.WALL_TWO, 30, 60, 240, 240, this);
		
		//Draw multiplication
		g2D.drawImage( TextureLoader.WALL_THREE, 30, 320, 240, 410, null);
	}
	
	public static void updateScoreText(){
		LeftPanel.scoreText.setText("" + Score.getTotalScore());
	}
}