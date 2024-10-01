/*
 * this is the astroid shoot class that will be astroids when enemys are near
 * @ version december 6
 * @author trenten hess
 */
package game;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class AstroidShot2 extends GameObject
{
	double xSpeed;
	double ySpeed;
	private Control control;
	private State state;
	private int x;
	private int y;
	private Enemy enemy;
	public AstroidShot2(Control control, State state, Enemy enemy, AstroidShooter2 astroidShooter2)
	{
		this.control = control;
		this.state = state;
		this.enemy = enemy;
		// these variables should be the difference in 
		//the x and y between the tower and the enemy
//		Point p = enemy.enemyposition();
//		double enemyX = p.getX();
//		double enemyY = p.getY();
//		xSpeed = x - enemyX;
//		ySpeed = y - enemyY;
		x = astroidShooter2.x;
		y = astroidShooter2.y;
		isVisible = true;
		isExpired = false;
	}
	@Override
	public void update(double elapsedTime) {
//		if(!(state.findNearestEnemy(x, y) == null))
//				enemy = state.findNearestEnemy(x, y);
		if(enemy.isExpired == false && !(state.findNearestEnemy(x, y) == null))
		{
			Point p = enemy.enemyposition();
			double enemyX = p.getX();
			double enemyY = p.getY();
			xSpeed = (-1 * (x - enemyX)) * 10;
			ySpeed = (-1 * (y - enemyY)) * 10;
			
			int changeX = (int) (xSpeed * elapsedTime);
			int changeY = (int) (ySpeed * elapsedTime);
			x += changeX;
			y += changeY;
			
			if (p.distance(x,y) < 20)
			{
				if(enemy.isExpired == false && !(state.findNearestEnemy(x, y) == null))
				{
					state.findNearestEnemy(x, y).isExpired = true;;
				}
			}
		}
		else
		{
			this.isExpired = true;
		}
		
//		Point p = enemy.enemyposition();
//		double enemyX = p.getX();
//		double enemyY = p.getY();
//		xSpeed = (-1 * (x - enemyX)) * 5;
//		ySpeed = (-1 * (y - enemyY)) * 5;
//		
//		int changeX = (int) (xSpeed * elapsedTime);
//		int changeY = (int) (ySpeed * elapsedTime);
//		x += changeX;
//		y += changeY;
//		if (p.distance(x,y) < 50)
//			{
//				enemy.isExpired = true;
//				this.isExpired = true;
//			}
		}
	@Override
	public void draw(Graphics g) {
		BufferedImage astroid = control.getImage("astroid.png");
		g.drawImage(astroid, x, y, null);
//		System.out.println(x);
//		System.out.println(y);
	}
	public Point astroidPosition() {
		Point p = new Point(x,y);
		return p;
	}
	
	
	
}