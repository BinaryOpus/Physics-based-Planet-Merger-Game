package Assets;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import Textures.TextureLoader;
import Utilities.Vector2D;

public class VisualEffect implements UpdateDrawCycle{

	
	private BufferedImage visualEffectTexture;
	private int numberOfFrames;
	private long frameDurationMillies;
	private Vector2D position;
	
	private int drawFrameWidth;
	private int drawFrameHeight;
	
	private BufferedImage activeFrame;
	private int activeFrameIndex;
	private long startTime;
	
	private boolean looped;
	
	private static ArrayList<VisualEffect> visualEffectArray = new ArrayList<VisualEffect>();
	
	public VisualEffect(BufferedImage visualEffectTexture, int numberOfFrames, long frameDurationMillies, Vector2D position, int drawFrameWidth, int drawFrameHeight, boolean looped){
		this.visualEffectTexture = visualEffectTexture;
		this.numberOfFrames = numberOfFrames;
		this.frameDurationMillies = frameDurationMillies;
		this.position = position;
		
		this.drawFrameWidth = drawFrameWidth;
		this.drawFrameHeight = drawFrameHeight;
		
		this.looped = looped;
		
		this.initialiseActiveFrame();
		
		VisualEffect.visualEffectArray.add(this);
	}
	
	@Override
	public void update(){
		updateActiveFrame();
	}
	
	@Override
	public void draw(Graphics2D g2D){
		g2D.drawImage(activeFrame, (int)(position.x - (drawFrameWidth/2)), (int)(position.y - (drawFrameHeight/2)), drawFrameWidth, drawFrameHeight,null);
	}
	
	private void initialiseActiveFrame(){
		this.startTime = System.currentTimeMillis();
		this.activeFrameIndex = 1;
		this.activeFrame = visualEffectTexture.getSubimage(0, 0, visualEffectTexture.getWidth()/numberOfFrames, visualEffectTexture.getHeight());
	}
	
	private void updateActiveFrame(){
		long currentTime = System.currentTimeMillis();
		if(currentTime - startTime > frameDurationMillies * numberOfFrames){
			if(looped){
				this.reset();
				return;
			}
			else{
				VisualEffect.visualEffectArray.remove(this);
				return;
			}
		}
		else if( (currentTime - startTime) > frameDurationMillies * activeFrameIndex ){
			this.activeFrame = visualEffectTexture.getSubimage( (visualEffectTexture.getWidth()/numberOfFrames) * activeFrameIndex,
																0, 
																visualEffectTexture.getWidth()/numberOfFrames, 
																visualEffectTexture.getHeight());
			++activeFrameIndex;
		}
	}
	
	public static ArrayList<VisualEffect> getVisualEffectArray(){
		return VisualEffect.visualEffectArray;
	}
	
	private void reset(){
		this.startTime = System.currentTimeMillis();
		this.activeFrameIndex = 1;
		this.activeFrame = visualEffectTexture.getSubimage(0, 0, visualEffectTexture.getWidth()/numberOfFrames, visualEffectTexture.getHeight());
	}
	
	public static void remove(VisualEffect visualEffect){
		VisualEffect.remove(visualEffect);
	}
	
	// public void destroy(){
		// VisualEffect.remove(this);
		// this = null;
	// }

}