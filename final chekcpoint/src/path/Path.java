/* 
 *this class is the class of methods to help me create a pathEditor // 
 *now it is also to help find coordinates of the path
 * 
 * Author: Trenten Hess
 * version: nov 15th
 * 
 */
package path;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JMenuItem;

public class Path {
	ArrayList<Point> pathArray; // these are the fields fo the path class
	// this is the default constructor

	public Path() {
		this.pathArray = new ArrayList<Point>();
	}

	public Path(Scanner s) // this is the constructor to take a text file and create a path objects
	{
		this.pathArray = new ArrayList<Point>();
		int numberOfPoints = s.nextInt();
		for (int i = 0; i < numberOfPoints; i++) {
			int x = s.nextInt();
			int y = s.nextInt();
			Point temp = new Point(x, y);
			this.pathArray.add(temp);
		}

	}

	/**
	 * the following method will help find the number of points in the path array
	 * 
	 * @return int the number of points in the path array
	 */
	public int getPointCount() {
		return this.pathArray.size();
	}

	/**
	 * the following method will find the x value at the specified point
	 * 
	 * @return int and the x value of point specified
	 * @param int some n //spot in the array
	 */
	public int getX(int n) {
		Point temp = this.pathArray.get(n);
		return (int) temp.getX();
	}

	/**
	 * the following method will find the y value at the specified point
	 * 
	 * @return int & the y value of point specified
	 * @param int n // some spot in the array
	 */
	public int getY(int n) {
		Point temp = this.pathArray.get(n);
		return (int) temp.getY();
	}

	/**
	 * the following method will help add the point given by and x and y coordinate
	 * 
	 * @param int x, int y
	 */
	public void add(int x, int y) {
		Point pointGiven = new Point(x, y);
		this.pathArray.add(pointGiven);
	}

	/**
	 * the following method will help find the number of points in the path array
	 * 
	 * @return string of the size of pointarrray and the points in the point array
	 */
	public String toString() {
		String answer = "";
		int size = getPointCount();

		for (int i = 0; i < size; i++) {
			String s;
			s = (int) this.pathArray.get(i).getX() + "  " + (int) this.pathArray.get(i).getY() + "\n";
			answer = answer + s;
		}
		return size + "\n" + answer;
	}

	/**
	 * the following method will draw from one specified point to another specified
	 * point
	 * 
	 * @return the number of points in the path array
	 * @param Graphics
	 */
	public void draw(Graphics g) {
		g.setColor(Color.CYAN);
		for (int i = 0; i < getPointCount(); i++) {
			if (i == 0)
				continue;
			g.drawLine(getX(i - 1), getY(i - 1), getX(i), getY(i));
		}

	}

	/**
	 * Given a percentage between 0% and 100%, this method calculates the location
	 * along the path that is exactly this percentage along the path. The location
	 * is returned in a Point object (integer x and y), and the location is a screen
	 * coordinate.
	 * 
	 * If the percentage is less than 0%, the starting position is returned. If the
	 * percentage is greater than 100%, the final position is returned.
	 * 
	 * Callers must not change the x or y coordinates of any returned point object
	 * (or the caller could be changing the path).
	 * 
	 * @param percentTraveled a distance along the path
	 * @return the screen coordinate of this position along the path
	 */
	public Point convertToCoordinates(double percentTraveled) {
		if (percentTraveled < 0) {
			Point startingSpot = new Point(getX(0), getY(0));
			return startingSpot;
		}

		int numOfPoints = getPointCount();

		if (percentTraveled > 1) {
			Point endSpot = new Point(getX(numOfPoints), getY(numOfPoints));
			return endSpot;
		}

		int totalPath = totalLength(numOfPoints);
		int segmentNum = 0;
		int pixlesTravled = (int) (percentTraveled * totalPath);
		int pixlesRemaining = pixlesTravled;
		
		for (int i = 0; i < numOfPoints - 1; i++) {
			if (pixlesRemaining > segmentLength(i)) {
				segmentNum++;
				pixlesRemaining = pixlesRemaining - segmentLength(i);
			}
			else
			{
				break;
			}

		}
		
		// Weighted average
		double sp = ((pixlesRemaining) / (double) segmentLength(segmentNum));
		double x = ((1 - sp) * getX(segmentNum)) + (sp) * getX(segmentNum + 1);
		double y = ((1 - sp) * getY(segmentNum)) + (sp) * getY(segmentNum + 1);
		Point point = new Point((int) x, (int) y);
//		System.out.println("segment number " + segmentNum);
//		System.out.println("segment length " +segmentLength(segmentNum));
//		System.out.println("segment percent " + sp);
		return point;

	}

	/**
	 * finds the length of a given segment
	 * 
	 * @param the segment you want to calculate
	 * @return the length of the segment
	 */
	private int segmentLength(int i) {
		int segleng = 1;
		int x = getX(i);
		int y = getY(i);
		int xSub2 = getX(i + 1);
		int ySub2 = getY(i + 1);
		segleng = (int) Math.sqrt(((xSub2 - x) * (xSub2 - x)) + ((ySub2 - y) * (ySub2 - y)));
		return segleng;
	}

	/**
	 * this will help find the total length of the path
	 * 
	 * @param the number of points in the path
	 * @return double the total length of the path in pixels
	 */
	private int totalLength(int numOfPoints) {
		int totalLength = 0;
		for (int i = 0; i < numOfPoints - 1; i++) {
			int temp = segmentLength(i);
			totalLength = totalLength + temp;
		}
		return totalLength;
	}

}
