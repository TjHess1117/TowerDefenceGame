/*
 * this is the view class all it really does is paint game objects and set the window
 * @ version nov 15
 * @author trenten hess
 */
package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JPanel {

	private Control control;
	private State state;

	// the gui thread
	public View(Control control, State state) {
		this.control = control;
		this.state = state;

		JFrame frame = new JFrame("Tower Deffense");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setMinimumSize(new Dimension(800, 600));
		this.setPreferredSize(new Dimension(800, 600));
		this.setMaximumSize(new Dimension(800, 600));

		frame.setContentPane(this);

		frame.setVisible(true);

		frame.pack();
	}

	/**
	 * this will paint the game objects that are valid
	 * 
	 * @param Graphics
	 */
	public void paint(Graphics g) {
		for (GameObject go : state.getFrameObjects())
			if (go.isVisible() && !go.isExpired())
				go.draw(g);
	}

}
