package model;

import java.awt.Color;
import java.awt.geom.GeneralPath;

/**
 * Defines the hexagon shape with its unique GeneralPath
 */
public class myHexagon extends Shapes {

    Color color;
    GeneralPath recs = new GeneralPath();

    public myHexagon(int x1, int y1, int x2, int y2, Color color, Boolean fillMode) {
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


        int heightDiff = Math.abs(y1-y2)/4;
        int midX = x1 + Math.abs(x1-x2)/2;

        //1
        recs.moveTo(x1, y1+heightDiff);
        //2
        recs.lineTo(midX,y1);
        //3
        recs.lineTo(x2,y1+heightDiff);
        //4
        recs.lineTo(x2,y2-heightDiff);
        //5
        recs.lineTo(midX,y2);
        //6
        recs.lineTo(x1,y2-heightDiff);
        //Back to 1
        recs.closePath();

    }

    public Color getColour() {
        return color;
    }

    public GeneralPath getPolyLine() {
        return recs;
    }

    String shape = "Hexagon";

    public String getShapeType() {
        return shape;
    }
}