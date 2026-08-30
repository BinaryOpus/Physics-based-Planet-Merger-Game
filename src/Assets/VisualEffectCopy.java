package Assets;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import Textures.TextureLoader;
import Utilities.Vector2D;

abstract class VisualEffectCopy extends GameObject implements UpdateDrawCycle{

	protected BufferedImage visualEffectTexture;
	protected int numberOfFrames;
	protected long frameDurationMillies;
	
	private int width;
	private int height;

	protected long startTime;
	protected int activeFrameIndex;
	protected BufferedImage activeFrame;

	abstract void updateActiveFrame();
	
	public VisualEffectCopy(Vector2D position, Vector2D velocity, BufferedImage visualEffectTexture, int numberOfFrames, long frameDurationMillies, int width, int height){
		super(position, velocity);
		
		//visualEffectArray = new ArrayList<VisualEffectCopy>();
		
		this.visualEffectTexture = visualEffectTexture;
		this.numberOfFrames = numberOfFrames;
		this.frameDurationMillies = frameDurationMillies;
		
		this.width = width;
		this.height = height;
		
		this.setActiveFrame();
	}
	
	private void setActiveFrame(){
		this.startTime = System.currentTimeMillis();
		this.activeFrameIndex = 1;
		this.activeFrame = visualEffectTexture.getSubimage(0, 0, visualEffectTexture.getWidth()/numberOfFrames, visualEffectTexture.getHeight());
	}
	
	@Override
	public void draw(Graphics2D g2D){
		g2D.drawImage(activeFrame, (int)(position.x - (width/2)), (int)(position.y - (height/2)), width, height,null);
	}
}