package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Utilities.Constants;

public class UserInputListener implements KeyListener{
	
	private int keyCode;
	private int activeMovement;
	private int activeRotation;
	
	public UserInputListener(){
		this.keyCode = 0;

		//if up has asctive key backward movement is ignored
		// if active key is null(only aftere release) when the next key pressed it becomes the active one
		this.activeMovement = 0;
		this.activeRotation = 0;
	}
	
    @Override
	public void keyTyped(KeyEvent e) {
		//System.out.println("Key Typed: " + e.getKeyChar());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyCode = e.getKeyCode();
		
		//Up Arrow Key
		if(keyCode == KeyEvent.VK_UP && activeMovement == 0){
			Constants.movingFWRD = true;
			this.activeMovement = keyCode;
		}
		//Down Arrow Key
		else if(keyCode == KeyEvent.VK_DOWN && activeMovement == 0){
			Constants.movingBack = true;
			this.activeMovement = keyCode;
		}
		//Left Arrow Key
		if(keyCode == KeyEvent.VK_LEFT && activeMovement == 0){
			Constants.movingLeft = true;
			this.activeMovement = keyCode;
		}
		//Right Arrow Key
		else if(keyCode == KeyEvent.VK_RIGHT && activeMovement == 0){
			Constants.movingRight = true;
			this.activeMovement= keyCode;
		}
		
		//A Key, rotation
		if(keyCode == KeyEvent.VK_A && activeRotation == 0){
			Constants.rotatingLeft = true;
			this.activeRotation = keyCode;
		}
		//D Key, rotation
		else if(keyCode == KeyEvent.VK_D && activeRotation == 0){
			Constants.rotatingRight = true;
			this.activeRotation = keyCode;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyCode = e.getKeyCode();
		
		//Up arrow key
		if(keyCode == KeyEvent.VK_UP && keyCode == activeMovement){
			Constants.movingFWRD = false;
			this.activeMovement = 0;
		}
		//Down arrow key
		else if(keyCode == KeyEvent.VK_DOWN && keyCode == activeMovement){
			Constants.movingBack = false;
			this.activeMovement = 0;
		}
		//Left Arrow Key
		if(keyCode == KeyEvent.VK_LEFT &&  keyCode == activeMovement){
			Constants.movingLeft = false;
			this.activeMovement = 0;
		}
		//Right Arrow Key
		else if(keyCode == KeyEvent.VK_RIGHT &&  keyCode == activeMovement){
			Constants.movingRight = false;
			this.activeMovement= 0;
		}
		
		//A Key
		if(keyCode == KeyEvent.VK_A && keyCode == activeRotation){
		    Constants.rotatingLeft = false;
			this.activeRotation = 0;
		}
		//D Key
		else if(keyCode == KeyEvent.VK_D && keyCode == activeRotation){
		    Constants.rotatingRight = false;
			this.activeRotation = 0;
		}
	}
}