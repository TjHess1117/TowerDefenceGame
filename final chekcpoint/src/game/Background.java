/*
 * this is the background class that will make the background
 * @ version nov 15
 * @author trenten hess
 */
package game;

import java.awt.Graphics;

public class Background extends GameObject
{
	private Control control;
	private State state;
	public Background(Control control, State state) {
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
		 g.drawImage(control.getImage("dinopath.png"), 0, 0, null); 
	}

}
