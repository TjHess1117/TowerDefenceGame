/*
 * this is the game object that will determine if things are expired and if they should be viable
 * @ version nov 15
 * @author trenten hess
 */
package game;

import java.awt.Graphics;

import path.Path;

/**
 * this will check if the game objects are still good to update and if they are
 * going to be visable or not
 * 
 * @author tjhes
 *
 */
abstract public class GameObject {

	protected boolean isVisible;
	protected boolean isExpired;

	public boolean isVisible() {
		return isVisible;
	}

	public boolean isExpired() {
		return isExpired;
	}

	abstract public void update(double elapsedTime);

	abstract public void draw(Graphics g);
}
