/*
 * this is the dino class that will make the dino object
 * @ version nov 22
 * @author trenten hess
 */
package game;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Dino extends Enemy{
	public Dino(Control control, State state)
	{
		super(control, state);
	}

	/**
	 * this will update the spot of the dino
	 * 
	 * @param a timer
	 */
	@Override
	public void update(double elapsedTime) {
		double velocity = 1.0 / 7;
		percentage += velocity * elapsedTime;
		if (percentage > 1)
		{
			isExpired= true;
			state.subtractLives(2);
		}
//		Point p = this.enemyposition();
//		int x = (int)p.getX();
//		int y = (int)p.getY();
//		if (!(state.findNearestAstroid(x, y) == null) && this.isExpired == false)
//		{
//			AstroidShot a = state.findNearestAstroid(x,y);
//			Point q = a.astroidPosition();
//			double distanceFrom = q.distance(p);
//			if(distanceFrom < 50)
//			{
//				a.isExpired = true;
//			}
//		}

	}

	/**
	 * this will draw the dino
	 * 
	 * @param Graphics
	 */
	@Override
	public void draw(Graphics g) {
		Point loc = control.getPath().convertToCoordinates(percentage);
		BufferedImage dino = control.getImage("dino.png");
		g.drawImage(dino, loc.x - dino.getWidth()/2, loc.y - dino.getHeight()/2, null);

	}

}
