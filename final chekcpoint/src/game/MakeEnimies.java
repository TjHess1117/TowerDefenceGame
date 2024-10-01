/*
 * this is the MakeEnimies class that will make enemies in a specific order that i pick using time.
 * @ version nov 22
 * @author trenten hess
 */
package game;

import java.awt.Graphics;

public class MakeEnimies extends GameObject
{
	private Control control;
	private State state;
	private double level_1;
	private double level_2 = 20;
	private double level_3 = 30;
	private double timeToAddMoney = 1.0;
	private double totalTime;
	public MakeEnimies(Control control, State state){
		this.control = control;
		this.state = state;
		
	}
{
}
/**
 * the update method for the enemy generation
 */
@Override
public void update(double elapsedTime) {
	
	double totalGameTime = state.getTotalTime();
	// level 1
	if(level_1 < totalGameTime)
	{
		level_1 += 4;
		state.addGameObject(new Dino(control,state));
	}
	//level 2
	if(level_2 < totalGameTime)
	{
		level_2 += 2.5;
		state.addGameObject(new FlyDino(control,state));
	}
	// level 3
	if(level_3 < totalGameTime)
	{
		level_3 += 3;
		state.addGameObject(new FlyDino(control,state));
		state.addGameObject(new Dino(control,state));
	}
	totalTime += elapsedTime;
	if(totalTime > timeToAddMoney)
	{
		state.addMoney(2);
		timeToAddMoney += 1;
	}
	
	
	
	
	
}
@Override
public void draw(Graphics g) {
	// TODO Auto-generated method stub
	
}
}
