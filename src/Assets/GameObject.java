package Assets;

import Utilities.Vector2D;
import Utilities.Constants;

abstract class GameObject implements UpdateDrawCycle{

	protected Vector2D position;
	protected Vector2D velocity;
	protected Vector2D acceleration;
	
	public GameObject(Vector2D position, Vector2D velocity){
		this.position = position;
		this.velocity = velocity;
		this.acceleration = Constants.GRAVITY;
	}
	
	public Vector2D getPosition(){
		return this.position;
	}
}