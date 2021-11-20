package model;

import java.awt.Color;
import java.awt.geom.GeneralPath;
import java.util.Random;

/**
 * Defines the 'shape' random
 *
 * The random shape is defined by a GeneralPath moving from 3 to 10 points with random locations.
 */
public class myRandom extends Shapes {

    Color color;
    GeneralPath recs = new GeneralPath();

    public myRandom(int x1, int y1, int x2, int y2, Color color, Boolean fillMode) {
        this.fillMode = fillMode;
        this.color = color;

        if(x1>x2){
            int tempX=x1;
            x1=x2;
            x2=tempX;
        }
        if(y1>y2){
            int tempY=y1;
            y1=y2;
            y2=tempY;
        }



        Random a = new Random();
        //CREATE RANDOM NUMBER BETWEEN 3 AND 10 (3 SO THAT IT ATLEAST MAKES A TRIANGLE)
        int numberOfPoints = a.nextInt(7)+3;


        int[] xPoints = new int[numberOfPoints];
        int[] yPoints = new int[numberOfPoints];
        //CREATE RANDOM POINTS IN THE BOUNDS OF (x1,y1) (x2,y2)
        for (int i = 0; i < xPoints.length; i++) {

            int tempX2 = Math.abs(x2-x1);
            int tempY2 = Math.abs(y2-y1);
            if(tempX2>0 && tempY2>0){
                xPoints[i] = a.nextInt(tempX2)+x1;
                yPoints[i] = a.nextInt(tempY2)+y1;
            }
        }
        recs.moveTo(xPoints[0], yPoints[0]);
        //CONNECT GENERAL PATH BETWEEN ALL THE RANDOM POINTS
        for (int i = 1; i < xPoints.length; i++) {
            recs.lineTo(xPoints[i],yPoints[i]);
        }
        recs.closePath();
    }

    public Color getColour() {
        return color;
    }

    public GeneralPath getPolyLine() {
        return recs;
    }

    String shape = "Random";

    public String getShapeType() {
        return shape;
    }
}