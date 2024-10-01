/*
 * this is the control class that will run the gui and listen to action and load things and has the game clock
 * @ version nov 15
 * @author trenten hess
 */
package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import path.Path;

public class Control implements Runnable, ActionListener, MouseListener, MouseMotionListener {

	State state;
	View view;
	Path path;
	
	private int mouseX, mouseY;
	private Map<String,BufferedImage> imageCache;

		
	// This is THE control object for the game

	public Control() {
		SwingUtilities.invokeLater(this);
	}

	/**
	 * //add stuff to know about the function here All remaining initialization of
	 * the game will go in this method
	 */
	@Override
	public void run() {
		
		// build the map
		imageCache = new TreeMap<String,BufferedImage>();
		
		
		ClassLoader myLoader = this.getClass().getClassLoader();
		InputStream pathStream = myLoader.getResourceAsStream("resources/thebestdino.txt");
		Scanner pathScanner = new Scanner(pathStream);
		
		path = new Path(pathScanner);
		
		loadPath();
		
		state = new State();
		view = new View(this, state);
		
		
		state.setMoney(50);
		state.setLives(10);
		
		state.startFrame(); // Prepares the creation of the 'next' frame
		state.addGameObject(new Background(this,state)); // Add one background object to our list
		state.addGameObject(new MakeEnimies(this,state)); // adds the enemy creator
		state.addGameObject(new Menu(this,state));
		state.finishFrame(); // Mark the next frame as ready
		view.repaint();
		Timer t = new Timer(16, this); // Triggers every 16 milliseconds, reports actions to 'this' object.
		t.start();
		
		view.addMouseListener(this);
		view.addMouseMotionListener(this);
		
		view.repaint();
	}

	/**
	 * gets your path
	 * 
	 * @return
	 */
	public Path getPath() {
		return path;
	}

	private void loadPath()
	{
		try
		{
			path = new Path(new Scanner(new File("dinopath.png")));
		}
		catch(IOException e)
		{
			System.out.println("this exeption will happen once because the path will not be loaded the first time around");
		}
	}
	/**
	 * loads an image in a bad way apparently
	 * 
	 * @param filename
	 * @return BufferedImage
	 */
	public BufferedImage getImage(String filename) {
		// if the image already has been loaded just return it instead of loading it.
		//look up the file name in a map
		// if there is a key that is that filename get and return
		// that bufferedimage value from the map
		if (imageCache.containsKey(filename))
		{
			return imageCache.get(filename); // check syntax here
		}
		try {
			System.out.println("loading" + filename);
			
			ClassLoader myLoader = this.getClass().getClassLoader();
			InputStream imageStream = myLoader.getResourceAsStream("resources/" + filename);
			BufferedImage image = javax.imageio.ImageIO.read(imageStream);
			imageCache.put(filename , image);
			return  image;
		}
		catch(IOException e)
		{
			System.out.println("could not find image in control class in the getimage function");
			return null;
		}
		/*
		try {
			ClassLoader myLoader = this.getClass().getClassLoader();
			InputStream imageStream = myLoader.getResourceAsStream("resources/" + filename);
			BufferedImage image = javax.imageio.ImageIO.read(imageStream);
			return image;
		} catch (IOException e) {
			System.out.println("Could not find or load resources/" + filename);
			System.exit(0); // Close the frame, bail out.
			return null; // Does not happen, the application has exited.
		}
		*/
	}

	/**
	 * the needed method for running the frames.
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		state.startFrame();
		if(!state.isGameOver())
		{
			for (GameObject go : state.getFrameObjects())
			{
				go.update(state.getElapsedTime());
			}
			view.repaint();
			state.finishFrame();
		}
		if(state.isGameOver())
		{
			state.startFrame();
			state.addGameObject(new IsGameOver(this,state));
			state.finishFrame();
		}
		}
	
	/**
	 * helper function to get the x cord
	 * @return x cord of mouse
	 */
	public int getMouseX()
	{
		return mouseX;
	}
	/**
	 * gets the y cord of the mouse
	 * @return y cord of mouse
	 */
	public int getMouseY()
	{
		return mouseY;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * gets the cords for the mouse
	 * @param mouseEvent
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * checks to see if the mouse pressed should click on a game object
	 * @param mouseEvent
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		List<GameObject> list = state.getFrameObjects();
		
		for(GameObject go : list)
		{
			if(go instanceof Clickable)
			{
				Clickable c = (Clickable) go;
				if(c.consumeClick(mouseX, mouseY))
					break;
			}
		}
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
