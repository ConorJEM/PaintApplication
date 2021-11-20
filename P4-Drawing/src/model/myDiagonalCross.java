package model;

import java.awt.Color;
import java.awt.geom.GeneralPath;

/**
 * Class for defining the diagonal cross shape with its unique GeneralPath
 *
 */
public class myDiagonalCross extends Shapes {

    Color color;
    GeneralPath recs = new GeneralPath();

    public myDiagonalCross(int x1, int y1, int x2, int y2, Color color, Boolean fillmode) {
        this.fillMode = fillmode;
        this.color = color;

        if(x1>x2){
            int b=x1;
            x1=x2;
            x2=b;
        }
        if(y1>y2){
            int c=y1;
            y1=y2;
            y2=c;
        }

        //Some logic to set the thickness of the cross relative to the height and width
        int xthickness = Math.abs(x2-x1)/6;
        int ythickness= Math.abs(y2-1)/6;
        double xdistance=(Math.sqrt(2)/2)*xthickness;
        double ydistance=(Math.sqrt(2)/2)*ythickness;
        int dx= (int) xdistance;
        int dy= (int) ydistance;

        //1
        recs.moveTo(x1, y1+dy);
        //2
        recs.lineTo(x1+dx,y1);
        //3
        recs.lineTo(x1+Math.abs(x2-x1)/2, y2-Math.abs(y1-y2)/2-dy);
        //4
        recs.lineTo(x2-dx,y1);
        //5
        recs.lineTo(x2,y1+dy);
        //6
        recs.lineTo(x1+Math.abs(x2-x1)/2+dx, y1+Math.abs(y1-y2)/2);
        //7
        recs.lineTo(x2,y2-dy);
        //8
        recs.lineTo(x2-dx,y2);
        //9
        recs.lineTo(x1+Math.abs(x2-x1)/2, y1+Math.abs(y1-y2)/2+dy);
        //10
        recs.lineTo(x1+dx,y2);
        //11
        recs.lineTo(x1,y2-dy);
        //12
        recs.lineTo(x1+Math.abs(x2-x1)/2-dx, y1+Math.abs(y1-y2)/2);
        //Back to 1
        recs.closePath();

    }

    public Color getColour() {
        return color;
    }

    public GeneralPath getPolyLine() {
        return recs;
    }

    String shape = "Diagonal Cross";

    public String getShapeType() {
        return shape;
    }
}