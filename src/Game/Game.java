package Game;

import View.MainDisplay;
import Utilities.Constants;
import Model.GameEngine;

//javac Game/*.java Model/*.java View/*.java Controller/*.java Assets/*.java Utilities/*.java Textures/*.java

public class Game{
	private static Game currentGame;
	private GameEngine gameEngine; //The display relies on output from the gameEngine, so create the gameEngine first
	private MainDisplay display; //The 3D display for the game

	public Game(){
	    // this.gameEngine = new GameEngine();
		// this.display = new MainDisplay("Accretional Impact");
	}
	
	private void initialiseGame(){
		this.gameEngine = new GameEngine();
		this.display = new MainDisplay("Accretional Impact");
	}
	
	public static void main(String[] args){
		currentGame = new Game();
		Game.currentGame.initialiseGame();
		
		while(true){
			currentGame.gameEngine.update();
			currentGame.display.repaint();
		}
	}
	
	public static Game getCurrentGame(){
		return currentGame;
	}
	
	public GameEngine getGameEngine(){
		return gameEngine;
	}
	
	public MainDisplay getMainDisplay(){
		return display;
	}
}