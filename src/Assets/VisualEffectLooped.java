package Assets;

import Model.GameEngine;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import Textures.TextureLoader;
import Utilities.Vector2D;

public class VisualEffectLooped extends VisualEffectCopy{

	protected static ArrayList<VisualEffectLooped> visualEffectLoopedArray = new ArrayList<VisualEffectLooped>();
	
	
	public VisualEffectLooped(Vector2D position, Vector2D velocity, BufferedImage visualEffectTexture, int numberOfFrames, long frameDurationMillies, int width, int height){
		super(position, velocity, visualEffectTexture, numberOfFrames, frameDurationMillies, width, height);
		visualEffectLoopedArray.add(this);
	}
	
	@Override
	protected void updateActiveFrame(){
		long currentTime = System.currentTimeMillis();
		if(currentTime - startTime > frameDurationMillies * numberOfFrames){
			
			this.reset();
			return;
		}
		else if( (currentTime - startTime) > frameDurationMillies * activeFrameIndex ){
			this.activeFrame = visualEffectTexture.getSubimage( (visualEffectTexture.getWidth()/numberOfFrames) * activeFrameIndex,
																0, 
																visualEffectTexture.getWidth()/numberOfFrames, 
																visualEffectTexture.getHeight());
			++activeFrameIndex;
		}
	}
	
	private void reset(){
		this.startTime = System.currentTimeMillis();
		this.activeFrameIndex = 1;
		this.activeFrame = visualEffectTexture.getSubimage(0, 0, visualEffectTexture.getWidth()/numberOfFrames, visualEffectTexture.getHeight());
	}
	
	public void remove(){
		visualEffectLoopedArray.remove(this);
	}
	
	@Override
	public void update(){
		position.set(position.getAdded(velocity.getMultiplied(GameEngine.deltaTime)));
		updateActiveFrame();
	}
	
	public static ArrayList<VisualEffectLooped> getVisualEffectLoopedArray(){
		return visualEffectLoopedArray;
	}
}