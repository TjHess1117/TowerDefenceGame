/*
 * this is the state class that is resposible for the state of the game and has lots of helper functions to
 * control the state of the game
 * @ version nov 15
 * @author trenten hess
 */
package game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class State {
	private List<GameObject> currentFrameGameObjects;
	private List<GameObject> nextFrameGameObjects;
	protected int money;
	private int lives;
	private long timeStart = System.currentTimeMillis();
	private double elapsedTime; // seconds since last frame
	private double totalTime = 0;
	/**
	 * this will check to see if the game is over
	 * @return if the game is over or not boolean
	 */
	public boolean isGameOver() {
		if(this.getLives() <= 0)
		return true;
		return false;
	}
	/**
	 * gets the total time
	 * @return int totatl time
	 */
	public double getTotalTime() {
		return totalTime;
	}
	/**
	 * gets the game time
	 * @return int game time
	 */
	public double getElapsedTime()
	{
		return elapsedTime;
	}
	/**
	 * sets the state up correctly with an array of game objects
	 */
	public State() {
		currentFrameGameObjects = new ArrayList<GameObject>();
	}
	/**
	 * gets the money currently in the game
	 * @return int money
	 */
	public int getMoney()
	{
		return money;
	}
	/**
	 * gets the lives
	 * @return lives as an int
	 */
	public int getLives() 
	{
		return lives;
	}
	// note I can't think of a time you would want to add lives
	public void setLives(int x) {
		lives = x;
	}
	/**
	 * you lose the amount passed into the parameters
	 * @param some amount of lives you want to lose
	 */
	public void subtractLives(int x) {
		lives = lives - x;
	}
	/**
	 * makes your money be set to whatever you want
	 * @param the value you want the money to be set to
	 */
	public void setMoney(int x) {
		money = x;
	}
	/**
	 * adds a specified amount of money
	 * @param amount of money you want to add
	 */
	public void addMoney(int x) {
		money = money + x;
	}
	/**
	 * subtracts a specified amount of money
	 * @param amount of money you want to subtract
	 */
	public void subtractMoney(int x) {
		money = money - x;
	}
	
	/**
	 * this will get the list of objects in the frame
	 * 
	 * @return the list of game objects
	 */
	public List<GameObject> getFrameObjects() {
		return currentFrameGameObjects;
	}

	/**
	 * this will start the frame of game play
	 */
	public void startFrame() {
		nextFrameGameObjects = new ArrayList<GameObject>(); // Creates empty list
		nextFrameGameObjects.addAll(currentFrameGameObjects); // Add all the current ones to the new list.
																		// This is more clear
			// calculate the elapsed time
		long timeStop = System.currentTimeMillis();
		long elapsedMillis = timeStop - timeStart;
		elapsedTime = elapsedMillis / 1000.0;
		
			//keep track of this frames start time as the last time
		timeStart = System.currentTimeMillis(); // for some reason this is the pivotal line of code
		totalTime = totalTime + elapsedTime;
	}

	/**
	 * this will finish the frame of game play
	 */
	public void finishFrame()
	{
//		Iterator<GameObject> itr = currentFrameGameObjects.iterator();
//	    while (itr.hasNext()) {
//	      GameObject go = itr.next();
//	      if (go.isExpired)
//	        itr.remove();
//	    }
//	    currentFrameGameObjects = currentFrameGameObjects;
//	}
		int x  = currentFrameGameObjects.size();
//		System.out.println(currentFrameGameObjects);
//		 nextFrameGameObjects = new ArrayList<GameObject>();
		for(int i = 0; i < x; i++)
		{
			if(currentFrameGameObjects.get(i).isExpired)
			{
//				nextFrameGameObjects.add(currentFrameGameObjects.get(i));
				int thingToKill = 0;
				for(int j = 0; j < nextFrameGameObjects.size(); j++)
				{
					if(nextFrameGameObjects.get(j).equals(currentFrameGameObjects.get(i)))
							{
								thingToKill = j;
							}
				}
				nextFrameGameObjects.remove(thingToKill);
			}
		}
		this.currentFrameGameObjects = nextFrameGameObjects;
		this.nextFrameGameObjects = null; // I added this -- it makes it clear there is only a current list now.
	}

	/**
	 * this will add the game game object specified
	 * 
	 * @param game object
	 */
	public void addGameObject(GameObject go) {
		nextFrameGameObjects.add(go);
	}
	/**
	 *  this will find the nearest enemy to something you want.
	 * @param x
	 * @param y
	 * @return the closest enemy to the said coordinates
	 */
	public Enemy findNearestEnemy(int x, int y)
	{
		List<GameObject> list = this.getFrameObjects();
		Enemy closestSoFar = null;
		for(GameObject go : list)
		{
			int i =0;
			if (go instanceof Enemy)
			{
				i++;
			    Enemy e = (Enemy) go;
			    if(i == 1) {
			    	closestSoFar = e;
			    }
			    else {
			    // test distance between enemy and x,y
			    Point p = e.enemyposition();
			    double enemyX = p.getX();
			    double enemyY = p.getY();
			    double distanceBetween = Math.sqrt(((x-enemyX)*(x-enemyX))+((y-enemyY)*(y-enemyY)));
			    // keep closest one. or the current one if none so far
			    if (distanceBetween < closestSoFar.enemyposition().distance(y, y)) {
			    	closestSoFar = e;
			    }
			    }
			}
		}
		return closestSoFar;
		/**
		 * this is a dumb method that does nothing of use
		 */
	}
	public AstroidShot findNearestAstroid(int x, int y)
	{
		List<GameObject> list = this.getFrameObjects();
		AstroidShot closestSoFar = null;
		for(GameObject go : list)
		{
			int i =0;
			if (go instanceof AstroidShot)
			{
				i++;
			    AstroidShot a = (AstroidShot) go;
			    if(i == 1) {
			    	closestSoFar = a;
			    }
			    else {
			    // test distance between enemy and x,y
			    Point p = a.astroidPosition();
			    double astroidX = p.getX();
			    double astroidY = p.getY();
			    double distanceBetween = Math.sqrt(((x-astroidX)*(x-astroidX))+((y-astroidY)*(y-astroidY)));
			    // keep closest one. or the current one if none so far
			    if (distanceBetween < closestSoFar.astroidPosition().distance(y, y)) {
			    	closestSoFar = a;
			    }
			    }
			}
		}
		return closestSoFar;
	}


}
