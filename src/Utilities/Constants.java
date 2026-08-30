package Utilities;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Constants{
	
	public static final Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int WIDTH = (int)SCREENSIZE.getWidth();
    public static final int HEIGHT = (int)SCREENSIZE.getHeight();
	public static final Vector2D screenCentre = new Vector2D( (int)(WIDTH/2) , (int)(HEIGHT/2) );
	
	public static boolean isMoving = false;
	public static boolean isRotating = false;

	public static boolean movingFWRD = false;
	public static boolean movingBack = false;
	public static boolean movingLeft = false;
	public static boolean movingRight = false;
	
	public static boolean rotatingLeft = false;
	public static boolean rotatingRight = false;
	
	public static final Vector2D GRAVITY = new Vector2D(0,200);
	public static final long TARGET_FRAME_DURATION = 16;   //Ideally we want one frame every 16 milliseconds, 
}