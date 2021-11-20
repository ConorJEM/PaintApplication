package controller;

import model.Shapes;
import model.vectorModel;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Controller Class.
 *
 * Handles all calls for when the view will change the state of the model.
 */
public class vectorController implements Serializable {

    private vectorModel model;

    public vectorController(vectorModel model){
        this.model = model;
    }

    public void controlNewShape(int x, int y, int x2, int y2){
            model.modelNewShape(x,y,x2,y2);
    }

    public void controlClear(){
        model.clearShapes();
    }

    public void controlShape(String shapeType){
        model.setShapeMode(shapeType);
    }

    public void controlUndo(){
        model.undoLast();
    }

    public void controlRedo(){
        model.redo();
    }

    public void controlFillMode(){
        model.changeFillMode();
    }

    public void controlColor(Color color){
        model.setColour(color);
    }

    public void controlDraggedShape(int x, int y, int x2, int y2){
        model.modelDraggedShape(x,y,x2,y2);
    }

    public void controlNullifyDragged(){
        model.nullifyDragged();
    }

    /**
     * Controls a newFile being loaded in by the user.
     * @param newShapeList the shapeList from the loaded file.
     * @param newStack the undoStack from the loaded file.
     */
    public void controlNewFile(ArrayList<Shapes> newShapeList, Stack<Shapes> newStack){
        model.changeNewFile(newShapeList,newStack);
    }

    public void controlAspectMode(boolean state){
        model.setAspectMode(state);
    }

}
