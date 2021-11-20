package model;

import java.awt.Color;
import java.awt.geom.GeneralPath;

/**
 * Defines the line
 *
 * fillmode is always false
 */
public class myLine extends Shapes{

    Color color;
    GeneralPath recs = new GeneralPath();
    Boolean fillmode;
    public myLine(int x1, int y1, int x2, int y2, Color color) {
        fillmode=false;
        this.color=color;
        recs.moveTo(x1,y1);
        recs.lineTo(x2,y2);
    }

    String shape = "Line";

    public String getShapeType(){
        return shape;
    }

    public Color getColour(){
        return color;
    }

    public GeneralPath getPolyLine(){
        return recs;
    }
}