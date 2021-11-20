package model;
import java.awt.Color;
import java.awt.geom.GeneralPath;

/**
 * Defines the rectangle, with its own unique GeneralPath which forms the outline of a rectangle.
 *
 * If aspectMode=true, a square will be drawn.
 */
public class myRectangle extends Shapes{

    String shape = "Rectangle";
    Color color;
    Boolean aspectMode;
    GeneralPath recs = new GeneralPath();

    public myRectangle(int x1, int y1, int x2, int y2, Color color,Boolean fillMode,Boolean aspectMode) {

    this.aspectMode=aspectMode;
    this.fillMode =fillMode;
    this.color=color;

    if(!aspectMode) {
        //For drawing a rectangle
        recs.moveTo(x1, y1);
        recs.lineTo(x2, y1);
        recs.lineTo(x2, y2);
        recs.lineTo(x1, y2);
    }else{
        //For drawing a square
        int width = Math.abs(x2-x1);
        int height = Math.abs(y2-y1);
        int sLength = Math.min(width,height);

        if(x1>x2){
            x1=x1-sLength;
        }
        if(y1>y2){
            y1=y1-sLength;
        }

        recs.moveTo(x1, y1);
        recs.lineTo(x1+sLength, y1);
        recs.lineTo(x1+sLength, y1+sLength);
        recs.lineTo(x1, y1+sLength);
    }
        recs.closePath();
    }

    public Color getColour(){
        return color;
    }

    public GeneralPath getPolyLine(){
        return recs;
    }

    public String getShapeType(){
        return shape;
    }
    public boolean getAspectMode(){
        return aspectMode;
    }

}
