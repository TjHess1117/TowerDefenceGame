/*
 * this is the astroid shooter class that will make the asteroid shooters when you click the button
 * @ version nov 22
 * @author trenten hess
 */

package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import path.Path;

public class AstroidShooter2 extends GameObject implements Clickable{
			
		private Control control;
		private State state;
		private boolean isMoving;
		protected int x;
		protected int y;
		private double cooldownTime = 3.0;  // time that we are not allowed to fire
		
		public AstroidShooter2(Control control, State state, boolean isMoving) {
			this.isMoving = isMoving;
			this.control = control;
			this.state = state;
			isVisible = true;
			isExpired = false;
		}
		/**
		 * updates the position to draw if the object is moving to follow the mouse
		 * @param time
		 */
		@Override
		public void update(double elapsedTime) {
			if(isMoving)
			{
				x = control.getMouseX() - 20;
				y = control.getMouseY() - 20;
			}
			cooldownTime -= elapsedTime;

				Enemy e = state.findNearestEnemy(x, y);
				if(cooldownTime <= 0 && !(e == null))
				{
					// if the distance is less then 200 fire at it
					if(e.enemyposition().distance(x, y) < 300 && cooldownTime < 0)
					{
						state.addGameObject(new AstroidShot2(control,state, e, this));
						// System.out.println(state.getFrameObjects());
					// and reset the cooldown to 1 second.
					cooldownTime = 3.0;
					if(e.enemyposition().distance(x, y) < 50)
						e.isExpired = true;
				}
				
			}
		}
		/**
		 * draws the astroid shooter using get image
		 * @param graphics
		 */
		@Override
		public void draw(Graphics g) {
			BufferedImage astroidShooter = control.getImage("astroidShooter.png");
			g.drawImage(astroidShooter, x, y, null);
		}
		/**
		 * checks to see if the object should consume a click
		 * @param mouse x and mouseY the x and y cordnents for 
		 * @return if it consumes the click or not true or false
		 */
		@Override
		public boolean consumeClick(int mouseX, int mouseY) {
			if(x > 600)
				return false;
			
			mouseX = control.getMouseX();
			mouseY = control.getMouseY();
			if(isMoving == true)
			{
				isMoving = false;
				return true;
			}
			// for now I will do this
			return false;
			//check to see if this object should respond
			// to the click. if so... do something
			//and return true
			// if it should not respond 
			// return false;
		}
		/**
		 * this would have been useful if i wasn't lazy
		 * @return nothing of use
		 */
		private boolean isNearPath() {
			
			return false;
		}
}
