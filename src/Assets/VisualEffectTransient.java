package Assets;

import Model.GameEngine;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import Textures.TextureLoader;
import Utilities.Vector2D;

public class VisualEffectTransient extends VisualEffectCopy{

	protected static ArrayList<VisualEffectTransient> visualEffectTransientArray = new ArrayList<VisualEffectTransient>();
	
	public VisualEffectTransient(Vector2D position, Vector2D velocity, BufferedImage visualEffectTexture, int numberOfFrames, long frameDurationMillies, int width, int height){
		super(position, velocity, visualEffectTexture, numberOfFrames, frameDurationMillies, width, height);
		visualEffectTransientArray.add(this);
	}
	
	@Override
	protected void updateActiveFrame(){
		long currentTime = System.currentTimeMillis();
		if(currentTime - startTime > frameDurationMillies * numberOfFrames){
			
			//VisualEffectTransient.visualEffectArray.remove(this);
			this.remove();
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
	
	public void remove(){
		visualEffectTransientArray.remove(this);
	}
	
	@Override
	public void update(){
		position.set(position.getAdded(velocity.getMultiplied(GameEngine.deltaTime)));
		updateActiveFrame();
	}
	
	public static ArrayList<VisualEffectTransient> getVisualEffectTransientArray(){
		return visualEffectTransientArray;
	}
}