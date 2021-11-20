package model;

import java.awt.Color;
import java.awt.geom.GeneralPath;
import java.io.Serializable;

/**
 * Abstract class from which all specific shapes extend.
 * Each shape has attributes colour, generalPath, shape, fillmode, aspectmode
 * This allows for shapes to be redrawn properly when doing undo/redo or performing saving and loading.
 */
public abstract class Shapes implements Serializable {
    Color color;
    GeneralPath recs;
    String shape;
    Boolean fillMode;
    Boolean aspectMode;

    public Shapes(){
    }

    public GeneralPath getPolyLine(){
        return this.recs;
    }

    public Color getColour(){
        return this.color;
    }

    public String getShapeType(){
        return this.shape;
    }

    public Boolean getFillMode(){
        return this.fillMode;
    }
    public boolean getAspectMode(){
        if(this.aspectMode==null){
            return false;
        }
        return this.aspectMode;
    }

}
