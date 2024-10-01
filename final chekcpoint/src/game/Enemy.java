/*
 * this is the enemy superclass that will be all of the enemy
 * @ version dec 6
 * @author trenten hess
 */
package game;

import java.awt.Graphics;
import java.awt.Point;

import path.Path;

public abstract class Enemy extends GameObject{
	protected Control control;
	protected State state;
	protected double percentage;
	
	public Enemy(Control control, State state)
	{
		this.control = control;
		this.state = state;
		this.percentage = 0;
		isVisible = true;
		isExpired = false;
	}
	/**
	 * this will find the enemy position on the path
	 * @return point (x,y) of enemy
	 */
	public Point enemyposition() {
		Path p = control.getPath(); // sus
		Point position = p.convertToCoordinates(percentage);
		return position;
	}

	abstract public void update(double elapsedTime);

	abstract public void draw(Graphics g);

}
