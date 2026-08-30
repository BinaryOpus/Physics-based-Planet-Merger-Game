package Textures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TextureLoader{
	
	//public static final BufferedImage WALL_ONE;
	public static final BufferedImage WALL_TWO;
	public static final BufferedImage WALL_THREE;
	public static final BufferedImage WALL_FOUR;
	public static final BufferedImage WALL_FIVE;
	public static final BufferedImage WALL_SIX;
	
	public static final BufferedImage BARRIER_VERTICAL;
	public static final BufferedImage BARRIER_HORIZONTAL;
	
	public static final BufferedImage SIDE_PANEL;
	
	public static final BufferedImage PLUTO;
	public static final BufferedImage MOON;
	public static final BufferedImage MERCURY;
	public static final BufferedImage TITAN;
	public static final BufferedImage MARS;
	public static final BufferedImage EARTH;
	public static final BufferedImage NEPTUNE;
	public static final BufferedImage JUPITER;
	public static final BufferedImage PLANETX;
	
	public static final BufferedImage SCORE_100;
	public static final BufferedImage SCORE_200;
	public static final BufferedImage SCORE_400;
	public static final BufferedImage SCORE_800;
	public static final BufferedImage SCORE_1600;
	public static final BufferedImage SCORE_3200;
	public static final BufferedImage SCORE_6400;
	public static final BufferedImage SCORE_12800;
	public static final BufferedImage SCORE_25600;
	
	public static final BufferedImage MERGE_EFFECT;
	public static final BufferedImage WARNING_EFFECT;
	public static final BufferedImage SUPER_NOVA_EFFECT;
	
	
	static {
		//BufferedImage tempWallOneImage = null;
		BufferedImage tempWallTwoImage = null;
		BufferedImage tempWallThreeImage = null;
		BufferedImage tempWallFourImage = null;
		BufferedImage tempWallFiveImage = null;
		BufferedImage tempWallSixImage = null;
		
		BufferedImage tempBarrierVertical = null;
		BufferedImage tempBarrierHprizontal = null;
		
		BufferedImage tempSidePanel = null;
		
		BufferedImage tempPluto = null;
		BufferedImage tempMoon = null;
		BufferedImage tempMercury = null;
		BufferedImage tempTiatn = null;
		BufferedImage tempMars = null;
		BufferedImage tempEarth = null;
		BufferedImage tempNeptune = null;
		BufferedImage tempJupiter = null;
		BufferedImage tempPlanetX = null;
		
		BufferedImage tempScore100 = null;
		BufferedImage tempScore200 = null;
		BufferedImage tempScore400 = null;
		BufferedImage tempScore800 = null;
		BufferedImage tempScore1600 = null;
		BufferedImage tempScore3200 = null;
		BufferedImage tempScore6400 = null;
		BufferedImage tempScore12800 = null;
		BufferedImage tempScore25600 = null;
		
		BufferedImage tempMergeEffect = null;
		BufferedImage tempWarningEffect = null;
		BufferedImage tempSuperNovaEffect = null;
		
		try{
			//tempWallOneImage = ImageIO.read(new File("Textures/Planets/DoesHeKnow.jpg"));
			tempWallTwoImage = ImageIO.read(new File("Textures/Planets/Score.png"));
			tempWallThreeImage = ImageIO.read(new File("Textures/Planets/Multiplier.png"));
			tempWallFourImage = ImageIO.read(new File("Textures/Planets/NextPlanet.png"));
			tempWallFiveImage = ImageIO.read(new File("Textures/Planets/Progression.png"));
			tempWallSixImage = ImageIO.read(new File("Textures/Planets/Universe.png"));
			
			tempBarrierVertical = ImageIO.read(new File("Textures/Planets/BarrierVertical.png"));
			tempBarrierHprizontal = ImageIO.read(new File("Textures/Planets/BarrierHorizontal.png"));
			
			tempSidePanel = ImageIO.read(new File("Textures/Planets/SidePanelBackground.png"));
			
			tempPluto = ImageIO.read(new File("Textures/Planets/Pluto.png"));
			tempMoon = ImageIO.read(new File("Textures/Planets/Moon.png"));
			tempMercury = ImageIO.read(new File("Textures/Planets/Mercury.png"));
			tempTiatn = ImageIO.read(new File("Textures/Planets/Titan.png"));
			tempMars = ImageIO.read(new File("Textures/Planets/Mars.png"));
			tempEarth = ImageIO.read(new File("Textures/Planets/Earth.png")); 
			tempNeptune = ImageIO.read(new File("Textures/Planets/Neptune.png")); 
			tempJupiter = ImageIO.read(new File("Textures/Planets/Jupiter.png")); 
			tempPlanetX = ImageIO.read(new File("Textures/Planets/PlanetX.png")); 
			
			tempScore100 = ImageIO.read(new File("Textures/Planets/100.png")); 
			tempScore200 = ImageIO.read(new File("Textures/Planets/200.png")); 
			tempScore400 =  ImageIO.read(new File("Textures/Planets/400.png")); 
			tempScore800 =  ImageIO.read(new File("Textures/Planets/800.png"));
			tempScore1600 =  ImageIO.read(new File("Textures/Planets/1600.png"));
			tempScore3200 =  ImageIO.read(new File("Textures/Planets/3200.png"));
			tempScore6400 =  ImageIO.read(new File("Textures/Planets/6400.png"));
			tempScore12800 =  ImageIO.read(new File("Textures/Planets/12800.png"));
			tempScore25600 =  ImageIO.read(new File("Textures/Planets/25600.png"));
			
			tempMergeEffect = ImageIO.read(new File("Textures/Planets/MergeVisualEffect.png"));
			tempWarningEffect = ImageIO.read(new File("Textures/Planets/WarningVisualEffect.png"));
			tempSuperNovaEffect = ImageIO.read(new File("Textures/Planets/SuperNovaVisualEffect.png"));
		}
		catch (IOException e) {
            System.err.println("Error: Unable to load images");
            e.printStackTrace();
			//tempWallOneImage = null;
			tempWallTwoImage = null;
			tempWallThreeImage = null;
			tempWallFourImage = null;
			tempWallFiveImage = null;
			tempWallSixImage = null;
			
			tempBarrierVertical = null;
			tempBarrierHprizontal = null;
			
			tempSidePanel = null;
			
			tempPluto = null;
			tempMoon = null;
			tempMercury = null;
			tempTiatn = null;
			tempMars = null;
			tempEarth = null;
			tempNeptune = null;
			tempJupiter = null;
			tempPlanetX = null;
			
			tempScore100 = null;
			tempScore200 = null;
			tempScore400 = null;
			tempScore800 = null;
			tempScore1600 = null;
			tempScore3200 = null;
			tempScore6400 = null;
			tempScore12800 = null;
			tempScore25600 = null;
			
			tempMergeEffect = null;
			tempWarningEffect = null;
			tempSuperNovaEffect = null;
        }
		
		//WALL_ONE = tempWallOneImage;
		WALL_TWO = tempWallTwoImage;
		WALL_THREE = tempWallThreeImage;
		WALL_FOUR = tempWallFourImage;
		WALL_FIVE = tempWallFiveImage;
		WALL_SIX = tempWallSixImage;
		
		BARRIER_VERTICAL = tempBarrierVertical;
		BARRIER_HORIZONTAL = tempBarrierHprizontal;
		
		SIDE_PANEL = tempSidePanel;
		
		PLUTO = tempPluto;
		MOON = tempMoon;
		MERCURY = tempMercury;
		TITAN = tempTiatn;
		MARS = tempMars;
		EARTH = tempEarth;
		NEPTUNE = tempNeptune;
		JUPITER = tempJupiter;
		PLANETX = tempPlanetX;
		
		SCORE_100 = tempScore100;
		SCORE_200 = tempScore200;
		SCORE_400 = tempScore400;
		SCORE_800 = tempScore800;
		SCORE_1600 = tempScore1600;
		SCORE_3200 = tempScore3200;
		SCORE_6400 = tempScore6400;
		SCORE_12800 = tempScore12800;
		SCORE_25600 = tempScore25600;
		
		MERGE_EFFECT = tempMergeEffect;
		WARNING_EFFECT = tempWarningEffect;
		SUPER_NOVA_EFFECT = tempSuperNovaEffect;
	}

	

}