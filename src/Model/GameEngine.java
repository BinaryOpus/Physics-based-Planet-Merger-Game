package Model;

import Assets.Planet;
import Assets.Level;
import Assets.VisualEffect;
import Assets.VisualEffectTransient;
import Assets.VisualEffectLooped;
import Utilities.*;
import Game.Game;
import java.util.ArrayList;

public class GameEngine{
	//private Slider slider;
	private Level level;
	private ArrayList<Planet> planetArray = Planet.getPlanetArray();
	
	private ArrayList<Score> ScoreArray = Score.getScoreArray();
	
	//private ArrayList<VisualEffect> visualEffectArray = VisualEffect.getVisualEffectArray();
	
	private ArrayList<VisualEffectTransient> visualEffectTransientArray = VisualEffectTransient.getVisualEffectTransientArray();
	private ArrayList<VisualEffectLooped> visualEffectLoopedArray = VisualEffectLooped.getVisualEffectLoopedArray();
	
	private static long updateTime;
	public static float deltaTime;
	
	public GameEngine(){
		deltaTime = 0;
		level = new Level();
	}
	
	public void update(){
		long startTime = System.currentTimeMillis();
		
		for(int i = 0; i<planetArray.size(); ++i){
			planetArray.get(i).update();
		}
		
		level.update();
		
		for(int i = 0; i<ScoreArray.size(); ++i){
			ScoreArray.get(i).update();
		}
		
		// for(int i = 0; i<visualEffectArray.size(); ++i){
			// visualEffectArray.get(i).update();
		// }
		
		for(int i = 0; i<visualEffectTransientArray.size(); ++i){
			visualEffectTransientArray.get(i).update();
		}
		
		for(int i = 0; i<visualEffectLoopedArray.size(); ++i){
			visualEffectLoopedArray.get(i).update();
		}
		
		if(level.gameOver){
			planetArray.clear();
			Score.resetScore();
			level.gameOver = false;
		}
		
		long endTime = System.currentTimeMillis();
		updateTime = endTime - startTime;
		
		//System.out.println("UPDATE" + updateTime);
		
		if( updateTime < Constants.TARGET_FRAME_DURATION){
			try{
				//System.out.println("Sleeping for " + (Constants.TARGET_FRAME_DURATION - updateTime));
				Thread.sleep(Constants.TARGET_FRAME_DURATION - updateTime);
			} 
			catch(InterruptedException e){
				Thread.currentThread().interrupt();
			}
		}
		
		GameEngine.deltaTime = (float)(System.currentTimeMillis() - startTime)/ 1000 ;
		//System.out.println("DT " + GameEngine.deltaTime);
	}
	
	public ArrayList<Planet> getPlanetArray(){
		return this.planetArray;
	}
	
	public ArrayList<VisualEffectTransient> getVisualEffectTransientArray(){
		return this.visualEffectTransientArray;
	}
	
	public ArrayList<VisualEffectLooped> getVisualEffectLoopedArray(){
		return this.visualEffectLoopedArray;
	}
	
	public Level getLevel(){
		return this.level;
	}
	
}