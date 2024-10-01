/*
 * this is the flydino class that will make the fly dino object
 * @ version nov 22
 * @author trenten hess
 */
package game;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class FlyDino extends Enemy {
	public FlyDino(Control control, State state)
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
		double velocity = 1.0 / 4;
		percentage += velocity * elapsedTime;
		if (percentage > .95)
		{
			isExpired= true;
			state.subtractLives(1);
		}

	}

	/**
	 * this will draw the dino
	 * 
	 * @param Graphics
	 */
	@Override
	public void draw(Graphics g) {
		Point loc = control.getPath().convertToCoordinates(percentage);
		BufferedImage flydino = control.getImage("flydino.png");
		g.drawImage(flydino, loc.x - flydino.getWidth()/2, loc.y - flydino.getHeight()/2, null);

	}

}
