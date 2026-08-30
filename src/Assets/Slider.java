package Assets;

import Utilities.Vector2D;
import Utilities.Constants;
import java.awt.Graphics2D;
import java.awt.Color;
import Game.Game;
import java.util.Random;
import java.awt.image.BufferedImage;

public class Slider implements UpdateDrawCycle{
	
	private int length;
	private int yValue; // top of slider
	
	private Vector2D leftLimitVertex;
	private Vector2D rightLimitVertex;
	
	private int yLimit; // for green lime
	private Vector2D position;
	
	private Planet currentPlanet;
	private Planet nextPlanet;
	private int counter = 120; //Temporary wait mechanism
	
	public Slider(Vector2D leftLimitVertex, Vector2D rightLimitVertex, int yLimit){
	    this.leftLimitVertex = leftLimitVertex;
		this.rightLimitVertex = rightLimitVertex;
		this.yLimit = yLimit;
		
		this.yValue = (int)leftLimitVertex.y - 30; //placrd 30 pxs abo
		this.length = (int)(rightLimitVertex.x - leftLimitVertex.x);
		
		this.position = new Vector2D(this.length/2 , this.yValue);
		
		this.currentPlanet = new Planet(this.position.clone(), new Vector2D(0,0));
		this.nextPlanet = new Planet(this.position.clone(), new Vector2D(0,0));
	}

	@Override
	public void update(){
		if(Constants.movingLeft){
			position.x -= 5;
			this.sliderLeftLimit();
			currentPlanet.position.set(position.x,position.y);
		}
		else if(Constants.movingRight){
			position.x += 5;
			this.sliderRightLimit();
			currentPlanet.position.set(position.x,position.y);
		}
		
		if(Constants.movingBack && counter > 80){
			counter = 0;
			Game.getCurrentGame().getGameEngine().getPlanetArray().add(currentPlanet);
			
			this.currentPlanet = this.nextPlanet;
			
			this.sliderLeftLimit();
			this.sliderRightLimit();
			
			this.currentPlanet.position.set(this.position);			
			this.nextPlanet = new Planet(this.position.clone(),new Vector2D(0,0));
		}
		
		++counter;
	}
	
	@Override
	public void draw(Graphics2D g2D){
		g2D.drawLine(50,yValue, 50+length, yValue);
		g2D.setColor(Color.GREEN);
		g2D.fillOval((int)position.x, (int)position.y, 3, 3);
		g2D.drawLine((int)position.x, (int)position.y, (int)position.x, yLimit);
		currentPlanet.draw(g2D);
	}
	
	public BufferedImage getNextPlanetTexture(){
		return nextPlanet.getPlanetTexture();
	}
	
	public void sliderLeftLimit(){
		if(position.x < leftLimitVertex.x + currentPlanet.getRadius()){
			position.x = leftLimitVertex.x + currentPlanet.getRadius();
		}
	}
	
	public void sliderRightLimit(){
		if(position.x > rightLimitVertex.x - currentPlanet.getRadius()){
			position.x = rightLimitVertex.x - currentPlanet.getRadius();
		}
	}
	
	public int getYValue(){
		return this.yValue;
	}

}