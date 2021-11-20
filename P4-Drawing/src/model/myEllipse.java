package model;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
/**
 * Defines the ellipse, with its own unique GeneralPath.
 *
 * If aspectMode=true, a circle will be drawn.
 */
public class myEllipse extends Shapes{

    Color color;
    GeneralPath recs;
    Boolean aspectMode;
    public myEllipse(int x1, int y1, int x2, int y2, Color color,Boolean fillMode,Boolean aspectMode) {
        this.aspectMode= aspectMode;
        this.fillMode =fillMode;
        this.color=color;

        if(!aspectMode) {   //FOR ELLIPSE
            if (x1 > x2) {
                int b = x1;
                x1 = x2;
                x2 = b;
            }
            if (y1 > y2) {
                int c = y1;
                y1 = y2;
                y2 = c;
            }

            int width = Math.abs(x1 - x2);
            int height = Math.abs(y1 - y2);
            //MAKE A NEW ELLIPSE SHAPE FROM LIBRARY
            Shape ellipse = new Ellipse2D.Double(x1, y1, width, height);
            //GET THE GENERAL PATH FROM THAT ELLIPSE
            recs = new GeneralPath(ellipse);
        }else if(aspectMode){  //FOR CIRCLE
            int r = Math.min(Math.abs(x1 - x2), Math.abs(y1-y2));

            if(x1>x2){
                x1=x1-r;
            }
            if(y1>y2){
                y1=y1-r;
            }

            Shape ellipse = new Ellipse2D.Double(x1,y1,r,r);
            recs = new GeneralPath(ellipse);
        }
    }

    public Color getColour(){
        return color;
    }

    public GeneralPath getPolyLine(){
        return recs;
    }

    String shape = "Ellipse";

    public String getShapeType(){
        return shape;
    }
}