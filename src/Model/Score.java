package Model;


import Utilities.Vector2D;
import Assets.UpdateDrawCycle;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Textures.TextureLoader;
import Assets.Planet;
import Assets.Planet.PlanetType;

public class Score implements UpdateDrawCycle{

	private static int totalScore;
	private static ArrayList<Score> scoreArray = new ArrayList<Score>();
	
	
	private Vector2D position;
	private Vector2D velocity;
	private BufferedImage scoreTexture;
	private long creationTime;
	
	public Score(Planet.PlanetType planetType, Vector2D position){
	
		this.position  = position;
		this.velocity = new Vector2D(0,-70);
		this.scoreTexture = initialiseScoreTexture(planetType);
		this.creationTime = System.currentTimeMillis();
		
		Score.scoreArray.add(this);
	
		//System.out.println("total score " + Score.totalScore);

	}
	
	@Override
	public void update(){
		
		if(System.currentTimeMillis() - creationTime < 3000){
			position.set(position.getAdded(velocity.getMultiplied(GameEngine.deltaTime)));
		}
		else{
			Score.scoreArray.remove(this);
		}
	}
	
	private BufferedImage initialiseScoreTexture(Planet.PlanetType planetType){
		switch (planetType) {
			case MOON:
				Score.totalScore += 100;
				return TextureLoader.SCORE_100;
			case MERCURY:
				Score.totalScore += 200;
				return TextureLoader.SCORE_200;
			case TITAN:
				Score.totalScore += 400;
				return TextureLoader.SCORE_400;
			case MARS:
				Score.totalScore += 800;
				return TextureLoader.SCORE_800;
			case EARTH:
				Score.totalScore += 1600;
				return TextureLoader.SCORE_1600;
			case NEPTUNE:
				Score.totalScore += 3200;
				return TextureLoader.SCORE_3200;
			case JUPITER:
				Score.totalScore += 6400;
				return TextureLoader.SCORE_6400;
			case PLANETX:
				Score.totalScore += 12800;
				return TextureLoader.SCORE_12800;
			default:
				Score.totalScore += 25600;
				return TextureLoader.SCORE_25600;
		}
	}
	
	@Override
	public void draw(Graphics2D g2D){
		g2D.drawImage(scoreTexture,(int)position.x, (int)position.y, 60, 30, null);
	}
	
	public static ArrayList<Score> getScoreArray(){
		return Score.scoreArray;
	}
	
	public static int getTotalScore(){
		return Score.totalScore;
	}
	
	public static void resetScore(){
		Score.totalScore = 0;
	}
}