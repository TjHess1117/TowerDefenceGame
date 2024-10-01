/*
 * this is the isGameOver class that will simply draw the game over picture
 * @ version nov 28
 * @author trenten hess
 */
package game;

import java.awt.Graphics;

public class IsGameOver extends GameObject{
	private Control control;
	private State state;
	public IsGameOver(Control control, State state) {
		this.state = state;
		this.control = control;
	    isVisible = true;
	    isExpired = false;
	}
	/**
	 * this will not do anything but we need it
	 */
	@Override
	public void update(double elapsedTime)
	{
		// TODO Auto-generated method stub
		
	}
	/**
	 * adds the background image
	 */
	@Override
	public void draw(Graphics g) 
	{
		 g.drawImage(control.getImage("gameOverPic.png"), 200, 200, null); 
	}
}
