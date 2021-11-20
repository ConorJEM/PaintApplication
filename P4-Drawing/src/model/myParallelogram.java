package model;

import java.awt.Color;
import java.awt.geom.GeneralPath;

/**
 * Defines the shape parallelogram with its own general path
 */
public class myParallelogram extends Shapes {

    Color color;
    GeneralPath recs = new GeneralPath();

    public myParallelogram(int x1, int y1, int x2, int y2, Color color, Boolean fillMode) {
        this.fillMode = fillMode;
        this.color = color;



        int dX = (x2-x1)/3;
        //IF THE USER MAKES A PARALLELOGRAM WHERE IT WILL SLANT DIFFERENT DIRECTIONS DEPENDING ON WHERE THE USER DRAGS
        recs.moveTo(x1+dX,y1);
        recs.lineTo(x2+dX,y1);
        recs.lineTo(x2,y2);
        recs.lineTo(x1,y2);


        recs.closePath();

    }

    public Color getColour() {
        return color;
    }

    public GeneralPath getPolyLine() {
        return recs;
    }

    String shape = "Parallelogram";

    public String getShapeType() {
        return shape;
    }
}