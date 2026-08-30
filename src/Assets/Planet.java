package Assets;

import Utilities.Vector2D;
import Model.GameEngine;
import java.awt.Graphics2D;
import Game.Game;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import Textures.TextureLoader;

import Model.Score;
import View.LeftPanel;
//import Math.random;

public class Planet extends GameObject implements Runnable{

	private PlanetType planetType;
	private int planetRadius;
	private int planetMass;
	
	private boolean atRest;
	private boolean restThreadRunning;
	
	private Color testCol;
	
	private BufferedImage planetTexture;
	private AffineTransform affineTransformation;
	private AffineTransform identityAffineTransformation;
	
	private double startX;
	private double planetRotation;
	
	private Vector2D restPosition;
	
	private static  ArrayList<Planet> planetArray = new ArrayList<Planet>();
	
	
	public enum PlanetType{
		PLUTO, MOON, MERCURY, TITAN, MARS, EARTH, NEPTUNE, JUPITER, PLANETX, ALIENPLANET
	}

	
	public Planet(Vector2D position, Vector2D velocity){
		super(position, velocity);
		this.planetType = genereatePlanetType();
		this.PlanetTypeInitialisation();
		
		this.affineTransformation = new AffineTransform();
		this.identityAffineTransformation = new AffineTransform();
		
		this.atRest = false;
		this.restThreadRunning = false;
		
		startX = this.position.x;
		
		restPosition = new Vector2D(0,0);
	}

	
	@Override
	public void update(){
		if(!atRest){
			position.set(position.getAdded(velocity.getMultiplied(GameEngine.deltaTime)));
			velocity.set(velocity.getAdded(acceleration.getMultiplied(GameEngine.deltaTime)));
		}
		// System.out.println("Is resting: " + atRest);
		
		/* 
		System.out.println("acc " + acceleration.getMultiplied(GameEngine.deltaTime));
		System.out.println("pos " + position);
		System.out.println("vel " + velocity); 
		*/
		
		if(position.x < 50 + planetRadius && velocity.x <= 0){
			position.set(50 + planetRadius, position.y);
			velocity.set(-velocity.x * 0.4, velocity.y * 0.7);
		}
		if(position.x > 750 - planetRadius && velocity.x >= 0){
			position.set(750 - planetRadius, position.y);
			velocity.set(-velocity.x * 0.4, velocity.y * 0.7);
		}
		if(position.y >= 750 - planetRadius && velocity.y >= 0){
			position.set(position.x, 750 - planetRadius);
			velocity.set(velocity.x * 0.96, -velocity.y * 0.3); 
		}
		
		if(velocity.getLength() < 15 && !restThreadRunning && !atRest){
			new Thread(this).start();
		}
	
		//ArrayList<Planet> planetArray = Game.getCurrentGame().getGameEngine().getPlanetArray();
		
		for(int i = 0; i<planetArray.size(); ++i){
			if(this.isCollidingWithOtherPlanet(planetArray.get(i))){
				if(this.planetType == planetArray.get(i).planetType){
					//System.out.println("Merge");
					this.position.set((this.position.x + planetArray.get(i).position.x)/2, (this.position.y + planetArray.get(i).position.y)/2);
					planetArray.remove(i);
					
					this.upgradePlanetType();
					this.PlanetTypeInitialisation();
					
					new Score(this.planetType, this.position.clone());
					LeftPanel.updateScoreText();
					
					//new VisualEffect(TextureLoader.MERGE_EFFECT, 7, 50, this.position, this.planetRadius*2, this.planetRadius*2, false);
					new VisualEffectTransient(this.position, new Vector2D(0,0), TextureLoader.MERGE_EFFECT, 7, 50, this.planetRadius*2, this.planetRadius*2);
					
					this.atRest = false;
				}
				else{
					this.collisionCalculation(planetArray.get(i));
					if(planetArray.get(i).velocity.getLength() > 15){
						planetArray.get(i).atRest = false;
					}
				}
			}
		}
		
		// Update AffineTransfromation used to draw planet graphics
		planetRotation = startX - this.position.x;
		double affineRotation = planetRotation/planetRadius;
		this.identityAffineTransformation.rotate(-affineRotation, this.position.x, this.position.y);
		this.affineTransformation.setTransform(identityAffineTransformation);
		this.identityAffineTransformation.setToIdentity();
		
		
		// planetRotation = startX - this.position.x;
		// double affineRotation = planetRotation/planetRadius;
		// this.affineTransformation = AffineTransform.getRotateInstance(-affineRotation, this.position.x, this.position.y);
	}
	
	@Override
	public void run(){
		this.restThreadRunning = true;
		this.restPosition.set(position);
		int radiusLimit = 5;
		
		try {
			Thread.sleep(1000); // Pause for 5 seconds
			
			if(this.velocity.getLength() < 5){
				this.atRest = true;
				velocity.setZero();
			}
			if( position.x > restPosition.x - radiusLimit &&
				position.x < restPosition.x + radiusLimit &&
				position.y > restPosition.y - radiusLimit &&
				position.y < restPosition.y + radiusLimit ){
				
				this.atRest = true;
				velocity.setZero();
			}
			
		} 
		catch (InterruptedException e) {
			System.err.println("Thread interrupted: " + e.getMessage());
			Thread.currentThread().interrupt(); // Restore interrupt status
		}
		
		this.restThreadRunning = false;
	}
	
	public void collisionCalculation(Planet other){
		
		Vector2D collisionVector = Vector2D.subtract(other.position, this.position);//Vector from other to this
		Vector2D normal = ((collisionVector).getDivided(collisionVector.getLength())).getNormalized();
		//CONSTRUCTION
		float ID = this.planetRadius+other.planetRadius;
		float AD = (float)collisionVector.getLength();
		
		this.position.add(normal.getMultiplied(-1*((ID-AD)/2)));
		other.position.add(normal.getMultiplied(1*((ID-AD)/2)));
		
		//ENDED
		double top = (this.velocity.dot(normal) - other.velocity.dot(normal)) * (0.85 + 1); //the 0.85 was formaly 0.95, changing this number affects the bounce of planets on other planets
		double bottom = (1f/this.planetMass) + (1f/other.planetMass);  //0.04
		double impulse = top/bottom;
		
		this.velocity.set(this.velocity.getSubtracted(normal.getMultiplied(impulse/this.planetMass)));
		other.velocity.set(other.velocity.getAdded(normal.getMultiplied(impulse/other.planetMass)));
		
		/* 
		System.out.println("v1 " + this.velocity);
		System.out.println("v2 " + other.velocity);
		System.out.println("normal " + normal);
		
		System.out.println("top " + top);
		System.out.println("bottom " + bottom );
		System.out.println("impu " + impulse); 
		*/
		
	}
	
	private boolean isCollidingWithOtherPlanet(Planet other){
		if(this != other){
			if(this.position.distance(other.position) < this.planetRadius + other.planetRadius){
				return true;			
			}	
		}
		return false;
	}
	
	// Creates a new planet the is of type PLUTO, MOON or MERCURY.
	private PlanetType genereatePlanetType(){
		
		double random = Math.random();
		if(random < 0.33){
			return PlanetType.PLUTO;
		}
		else if(random > 0.66){
			return PlanetType.MOON;
		}
		else{
			return PlanetType.MERCURY;
		} 
	}
	
	@Override
	public void draw(Graphics2D g2D){
		AffineTransform originalTransfrom = g2D.getTransform();
		g2D.transform(affineTransformation);
		g2D.drawImage(planetTexture, (int)(position.x - planetRadius), (int)(position.y - planetRadius), planetRadius*2, planetRadius*2,null);
		g2D.setTransform(originalTransfrom);
		
		// double rotation = planetRotation/planetRadius;
		// g2D.rotate(-rotation, this.position.x, this.position.y); 
		// g2D.drawImage(planetTexture, (int)(position.x - planetRadius), (int)(position.y - planetRadius), planetRadius*2, planetRadius*2,null);
		// g2D.rotate(rotation, this.position.x, this.position.y); 
	}
	
	private void PlanetTypeInitialisation(){
		PlanetType planetType = this.planetType;
		switch (planetType) {
			case PLUTO:
				this.planetRadius = 20;
				this.planetMass = 50;
				this.testCol = Color.GRAY;
				this.planetTexture = TextureLoader.PLUTO;
				break;
			case MOON:
				this.planetRadius = 40;
				this.planetMass = 100;
				this.testCol = Color.LIGHT_GRAY;
				this.planetTexture = TextureLoader.MOON;
				break;
			case MERCURY:
				this.planetRadius = 60;
				this.planetMass = 200;
				this.testCol = Color.GREEN;
				this.planetTexture = TextureLoader.MERCURY;
				break;
			case TITAN:
				this.planetRadius = 80;
				this.planetMass = 400;
				this.testCol = Color.RED;
				this.planetTexture = TextureLoader.TITAN;
				break;
			case MARS:
				this.planetRadius = 100;
				this.planetMass = 800;
				this.testCol = Color.BLUE;
				this.planetTexture = TextureLoader.MARS;
				break;
			case EARTH:
				this.planetRadius = 120;
				this.planetMass = 900;
				this.testCol = Color.YELLOW;
				this.planetTexture = TextureLoader.EARTH;
				break;
			case NEPTUNE:
				this.planetRadius = 140;
				this.planetMass = 1000;
				this.testCol = Color.MAGENTA;
				this.planetTexture = TextureLoader.NEPTUNE;
				break;
			case JUPITER:
				this.planetRadius = 160;
				this.planetMass = 1200;
				this.testCol = Color.CYAN;
				this.planetTexture = TextureLoader.JUPITER;
				break;
			case PLANETX:
				this.planetRadius = 180;
				this.planetMass = 1400;
				this.testCol = Color.ORANGE;
				this.planetTexture = TextureLoader.PLANETX;
				break;
			default:
				//this.planetRadius = 200;
				//this.planetMass = 1600;
				//this.testCol = Color.BLACK;
				//this.planetTexture = TextureLoader.PLANETX;
				new VisualEffectTransient(this.position, new Vector2D(0,0), TextureLoader.SUPER_NOVA_EFFECT, 9, 180, this.planetRadius*2, this.planetRadius*2);
				planetArray.remove(this);
				break;
		}
	}
	
	private void upgradePlanetType(){
		PlanetType planetType = this.planetType;
		switch (planetType) {
			case PLUTO:
				//System.out.println("PLUTO -> MOON");
				this.planetType = PlanetType.MOON;
				this.testCol = Color.GRAY;
				break;
			case MOON:
				//System.out.println("MOON -> MERCURY");
				this.planetType = PlanetType.MERCURY;
				this.testCol = Color.LIGHT_GRAY;
				break;
			case MERCURY:
				//System.out.println("MERCURY -> TITAN");
				this.planetType = PlanetType.TITAN ;
				this.testCol = Color.GREEN;
				break;
			case TITAN:
				//System.out.println("TITAN -> MARS");
				this.planetType = PlanetType.MARS;
				this.testCol = Color.RED;
				break;
			case MARS:
				//System.out.println("MARS -> EARTH");
				this.planetType = PlanetType.EARTH;
				this.testCol = Color.BLUE;
				break;
			case EARTH:
				//System.out.println("EARTH -> NEPTUNE");
				this.planetType = PlanetType.NEPTUNE;
				this.testCol = Color.YELLOW;
				break;
			case NEPTUNE:
				//System.out.println("NEPTUNE -> JUPITER");
				this.planetType = PlanetType.JUPITER;
				this.testCol = Color.MAGENTA;
				break;
			case JUPITER:
				//System.out.println("JUPITER -> PLANETX");
				this.planetType = PlanetType.PLANETX;
				this.testCol = Color.CYAN;
				break;
			case PLANETX:
				//System.out.println("PLANETX -> ALIENPLANET");
				this.planetType = PlanetType.ALIENPLANET;
				this.testCol = Color.ORANGE;
				break;
			default:
				//AlienPlanet
				//System.out.println("AlienPlanet -> LIMIT");
				this.planetType = PlanetType.MOON;
				this.testCol = Color.ORANGE;
			
		}			
	}
	
	
	public int getRadius(){
		return this.planetRadius;
	}
	
	public PlanetType getPlanetType(){
		return this.planetType;
	}
	
	public BufferedImage getPlanetTexture(){
		return this.planetTexture;
	}
	
	public static ArrayList<Planet> getPlanetArray(){
		return Planet.planetArray;
	}
}