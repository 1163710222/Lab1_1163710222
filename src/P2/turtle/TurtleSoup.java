/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.List;
import java.util.ArrayList;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
	//通过对forward和turn的调用，使得乌龟能够在图上走
    public static void drawSquare(Turtle turtle, int sideLength) {
    	for(int i = 1;i<=4;i++)
    	{
        	turtle.forward(sideLength);
        	turtle.turn(90);
    	}
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    //通过公式计算内角的大小
    public static double calculateRegularPolygonAngle(int sides) {
    		return 180-(double)360/sides;
    }	

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    //通过内角大小计算边的数量，并且通过加上0.5，保证强转时能够保持正确结果
    public static int calculatePolygonSidesFromAngle(double angle) {
    		return (int)(360/(180-angle)+0.5);
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    
    //通过计算出来的内角大小，画出图形
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        double tep = calculateRegularPolygonAngle(sides);
    	for(int i = 1;i<=sides;i++)
        {
    		turtle.forward(sideLength);
        	turtle.turn(180-tep);	
        }
    }

    /**
     * Given the current direction, current location, and a target location, calculate the heading
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentHeading. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentHeading current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to heading (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    
    //通过计算出来的角度，进行与当前方向的求差，从而得出结果
    public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY,
                                                 int targetX, int targetY) {
    	double Y = (double)targetY-currentY;
    	double X = (double)targetX-currentX;
    	double tool;
    	if(X==0){
    		if(Y>0)
    			tool=90;
    		else
    			tool=270;
    			
    	}
    	else{
    		tool = Math.atan2(Y,X)*180/Math.PI;//获得参照于原先0度的角
    	}
    	
    	if(tool<90)//判断出角度的大小
    	{
    		tool = 90-tool;
    	}
    	else
    	{
    		tool = tool-90;
    	}
    	return (tool-currentHeading+360)%360;
    }

    /**
     * Given a sequence of points, calculate the heading adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateHeadingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of heading adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    
    //调用 calculateHeadingToPoint从而进行针对数组的角度调整
    public static List<Double> calculateHeadings(List<Integer> xCoords, List<Integer> yCoords) {
        List<Double> turhead = new ArrayList<>();
        double header = TurtleSoup.calculateHeadingToPoint(0.0,xCoords.get(0), yCoords.get(0), xCoords.get(1), yCoords.get(1));
        turhead.add(header);
        for(int i=1;i<xCoords.size()-1;i++)
        {
        	header=TurtleSoup.calculateHeadingToPoint(turhead.get(i-1),xCoords.get(i), yCoords.get(i), xCoords.get(i+1), yCoords.get(i+1));
        	turhead.add(header);
        }
        return turhead;
    }

    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
    	
    	for(int i = 1;i<=360;i++)
        {
     	   turtle.forward(1);
     	   turtle.turn(1);
        }
        for(int i = 1;i<360;i++)
        {
     	   turtle.forward(1);
     	   turtle.turn(359);
        }
           
       
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

//        drawSquare(turtle, 40);
        // draw the window
        drawPersonalArt(turtle);
         turtle.draw();
    }

}
