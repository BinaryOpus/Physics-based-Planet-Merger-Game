package Assets;

import java.awt.Graphics2D;
import Utilities.Vector2D;
import Textures.TextureLoader;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import Game.Game;
import Assets.VisualEffect;

public class Level implements UpdateDrawCycle{

	//private BufferedImage leftBarrier;
	//private BufferedImage bottomBarrier;
	//private BufferedImage rightBarrier;
	
	
	private class Barrier{
	
		private Vector2D vertex1;
		private Vector2D vertex2;
		
		private Barrier(Vector2D vertex1, Vector2D vertex2){
			this.vertex1 = vertex1;
			this.vertex2 = vertex2;
		}	
	}
	
	private Barrier leftBarrier;
	private Barrier middleBarrier;
	private Barrier rightBarrier;
	
	private Slider slider;
	
	// private long loseConditionCooldown;
	// private boolean loseConditionCooldownTimerActive;
	// private long loseConditionCooldownTimerStartTime;
	
	private long loseConditionCountdown;
	private boolean loseConditionCountdownActive;
	private long loseConditionCountdownStartTime;
	
	private VisualEffectLooped warningEffect;
	private boolean warningEffectActive;
	
	public boolean gameOver;
	
	
	public Level(){
		this.leftBarrier = new Barrier(new Vector2D(50,100), new Vector2D(50,750));
		this.rightBarrier = new Barrier(new Vector2D(750,750), new Vector2D(750,100));
		this.middleBarrier = new Barrier(new Vector2D(50,750), new Vector2D(750,750));
	
		
		slider = new Slider(this.leftBarrier.vertex1, this.rightBarrier.vertex2, (int)this.middleBarrier.vertex1.y);
		
		this.warningEffect = new VisualEffectLooped(new Vector2D((middleBarrier.vertex1.x + middleBarrier.vertex2.x)/2,(leftBarrier.vertex1.y + leftBarrier.vertex2.y)/2), new Vector2D(0,0), TextureLoader.WARNING_EFFECT, 2, 150, 100, 50);
		loseConditionCountdown = 4000;
		loseConditionCountdownActive = false;
		
		this.gameOver = false;
	}

	@Override
	public void update(){
		slider.update();
		loseCondition();
		
	}
	
	private void loseCondition(){
	
		if(!isPlanetOverLimit()){
			loseConditionCountdownActive = false;
			warningEffect.remove();
			warningEffectActive = false;
		}
		else if(isPlanetOverLimit()){
		
			if(!loseConditionCountdownActive){
				loseConditionCountdownStartTime = System.currentTimeMillis();
				loseConditionCountdownActive = true;
			}
			else if(System.currentTimeMillis() - loseConditionCountdownStartTime > 1500 && !warningEffectActive){
				warningEffect = new VisualEffectLooped(new Vector2D((middleBarrier.vertex1.x + middleBarrier.vertex2.x)/2,(leftBarrier.vertex1.y + leftBarrier.vertex2.y)/2), new Vector2D(0,0), TextureLoader.WARNING_EFFECT, 2, 150, 450, 250);
				warningEffectActive = true;
				//System.out.println("Hello");
			}
			else if(System.currentTimeMillis() - loseConditionCountdownStartTime > 4500){
				this.gameOver = true;
				Game.getCurrentGame().getMainDisplay().changeToGameOverScreen();
			}
		}
	}
	
	private boolean isPlanetOverLimit(){
		ArrayList<Planet> planetArray = Game.getCurrentGame().getGameEngine().getPlanetArray();
	
		for(int i = 0; i<planetArray.size(); ++i){
			if(planetArray.get(i).getPosition().y - planetArray.get(i).getRadius() < slider.getYValue()){
				return true;
			} 
		}
		return false;
	}

	
	@Override
	public void draw(Graphics2D g2D){
		
		//left Barrier
		//Right Barrier
		//Middle Barrier
		g2D.drawImage(TextureLoader.BARRIER_VERTICAL, (int)leftBarrier.vertex1.x-20, (int)leftBarrier.vertex1.y, 20, (int)leftBarrier.vertex2.y -(int)leftBarrier.vertex1.y, null);
		g2D.drawImage(TextureLoader.BARRIER_VERTICAL, (int)rightBarrier.vertex2.x, (int)rightBarrier.vertex2.y, 20, (int)leftBarrier.vertex2.y -(int)leftBarrier.vertex1.y, null);
		g2D.drawImage(TextureLoader.BARRIER_HORIZONTAL, (int)leftBarrier.vertex2.x-20, (int)leftBarrier.vertex2.y, (int)middleBarrier.vertex2.x -(int)middleBarrier.vertex1.x + 40, 20, null);
		
		// Test draw methods to destermine barrier Position
		g2D.setColor(Color.MAGENTA);
		g2D.drawLine((int)leftBarrier.vertex1.x,(int)leftBarrier.vertex1.y,(int)leftBarrier.vertex2.x,(int)leftBarrier.vertex2.y);
		g2D.drawLine((int)rightBarrier.vertex1.x,(int)rightBarrier.vertex1.y,(int)rightBarrier.vertex2.x,(int)rightBarrier.vertex2.y);
		g2D.drawLine((int)middleBarrier.vertex1.x,(int)middleBarrier.vertex1.y,(int)middleBarrier.vertex2.x,(int)middleBarrier.vertex2.y);
		
		slider.draw(g2D);
		
	}
	
	public Slider getSlider(){
		return this.slider;
	}
	
}