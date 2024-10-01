/*
 * this is the menu class that will make the menue
 * @ version nov 22
 * @author trenten hess
 */
package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import path.Path;

public class Menu extends GameObject implements Clickable {
			
		private Control control;
		private State state;
		private int x,y;
		public Menu(Control control, State state) {
			
			this.control = control;
			this.state = state;
			isVisible = true;
			isExpired = false;
		}

		@Override
		public void update(double elapsedTime) {
			// TODO Auto-generated method stub
		}
	
		@Override
		public void draw(Graphics g) {
			g.setColor(Color.BLACK); // background draws first in the menu
			g.fillRect(600, 0, 200, 600);
			
			// now to draw the name of the game, money and lives
			g.setColor(Color.RED);
			g.setFont(new Font("BOLD",30,30));
			g.drawString("dino fighter!" , 625, 25);
			// draws the money
			g.setColor(Color.GREEN);
			g.setFont(new Font("DIALOG", 25,25));
			g.drawString("money: " + state.getMoney(), 625, 55);
			// draws the lives
			g.setColor(Color.BLUE);
			g.setFont(new Font("DIALOG", 25,25));
			g.drawString("Lives: " + state.getLives(), 625, 85);
			
			
			// now to draw the button
			g.setColor(Color.ORANGE);
			g.fillRoundRect(625, 100 , 55, 55, 15, 15);
			g.fillRoundRect(625, 100, 45, 45, 20, 20);
			BufferedImage astroidShooter = control.getImage("astroidShooter.png");
			g.drawImage(astroidShooter, 625, 100, null);
			
			// draw astroid shooter 2
//			g.setColor(Color.ORANGE);
//			g.fillRoundRect(695, 100 , 55, 55, 15, 15);
//			g.fillRoundRect(695, 100, 45, 45, 20, 20);
//			BufferedImage astroidShooter2 = control.getImage("astroidShooter.png");
//			g.drawImage(astroidShooter2, 695, 100, null);
			
		}

		/**
		 * checks to see if the object should consume a click
		 * @param mouse x and mouseY the x and y cordnents for 
		 * @return if it consumes the click or not true or false
		 */
		@Override
		public boolean consumeClick(int mouseX, int mouseY)
		{
			mouseX = control.getMouseX();
			mouseY = control.getMouseY();
			if(mouseX > 625 && mouseX < 675 && mouseY > 100 && mouseY < 150)
			{
				state.startFrame();
				if(!(state.money < 50))
				{
					state.addGameObject(new AstroidShooter(control, state,true));
					state.subtractMoney(50);
					state.finishFrame();
				}
				return true;
			}
//			if(mouseX > 695 && mouseX < 710 && mouseY > 100 && mouseY < 150)
//			{
//				state.startFrame();
//				if(!(state.money < 25))
//				{
//					state.addGameObject(new AstroidShooter2(control, state,true));
//					state.subtractMoney(25);
//					state.finishFrame();
//				}
//				return true;
//			}
			return false;
		}

}
