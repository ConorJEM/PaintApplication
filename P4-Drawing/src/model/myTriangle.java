package model;

import java.awt.Color;
import java.awt.geom.GeneralPath;

public class myTriangle extends Shapes {

    Color color;
    GeneralPath recs = new GeneralPath();

    public myTriangle(int x1, int y1, int x2, int y2, Color color, Boolean fillmode) {
        this.fillMode = fillmode;
        this.color = color;

        //PolyLine for drawing a triangle
        recs.moveTo(x1, y2);
        recs.lineTo(x1+(x2-x1)/2, y1);
        recs.lineTo(x2, y2);
        recs.closePath();

    }

    public GeneralPath getPolyLine() {
        return recs;
    }

    String shape = "Triangle";

    public String getShapeType() {
        return shape;
    }

    public Color getColour(){
        return color;
    }
}