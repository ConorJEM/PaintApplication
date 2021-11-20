package model;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * vectorModel is the main class of the Model in the MVC design. The model stores all attributes of the users current button selections,
 * the shape list, the redoStack, handles the creation of new objects by creating new Shapes.
 *
 * The vectorModel updates the listener (The view) by calling changed() after every change, forcing the view to update (repaint).
 *
 * A number of methods are included involving the logic for different buttons e.g. clear, undo ,redo.
 */
public class vectorModel implements Serializable {

    private List<Listener> listeners; //LIST OF ALL LISTENERS

    ArrayList<Shapes> shapeList = new ArrayList<Shapes>(); //LIST OF ALL SHAPES DRAWN

    Stack<Shapes> redoStack = new Stack<Shapes>(); //LIST OF ALL SHAPES IN THE REDO STACK

    String shapeMode = "Line";     //DEFAULT DRAWING MODE IS DRAWING LINES

    Boolean fillMode = false; //DEFAULT FILLMODE IS FALSE

    private Color colour=Color.BLACK;    //DEFAULT COLOUR IS BLACK

    Shapes dragged; //CURRENT SHAPE BEING DRAGGED ACROSS SCREEN (TEMPORARY SHAPE)

    Boolean aspectMode=false;// BY DEFAULT FIXED ASPECT MODE IS OFF (FALSE)

    //METHODS FOR LISTENERS ******************************************************

    public interface Listener {
        void update();
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }
    //For testing
    public List<Listener> getListeners(){
        return listeners;
    }

    public vectorModel() {
        listeners = new ArrayList<Listener>();
    }

    private void changed() {
        for (Listener listener : listeners) {
            listener.update();
        }
    }
    //*************************************************************************************

    /**
     * undoLast removes the last drawn shape from the array of shapes to draw, 'shapeList'
     *
     * this 'undone' shape is added to the redoStack
     */
    public void undoLast() {

        if (shapeList.size() > 0) {
            //First add the shape to be removed to the 'undo stack'
            Shapes undone = shapeList.get(shapeList.size() - 1);
            redoStack.push(undone);
            //Removes the last element in the shapes array, undoing the last made shape.
            shapeList.remove(shapeList.size() - 1);

            changed();
        }

    }

    /**
     * redo adds the last undone shape back into the shapeList. If more shapes are drawn, redo will still function and reprint the last
     * shape to be undone.
     */
    public void redo() {
        //Checks that the redostack isnt empty

        if (!redoStack.empty()) {
            //adds the previously undone object back into the object list and removes the object from the stack.
            shapeList.add(redoStack.pop());
            changed();
        }
    }

    public Stack<Shapes> getRedoStack(){
        return redoStack;
    }

    /**
     * Clears all shapes from shapeList, and clears the redoStack.
     */
    public void clearShapes() {
        //Clears all shapes from our shape list
        shapeList.clear();
        //When we clear the page, we will clear the redoStack
        redoStack.clear();
        changed();
    }

    //Changes the fillMode state of the model
    public void changeFillMode() {
        if (!fillMode) {
            fillMode = true;
        } else if (fillMode) {
            fillMode = false;
        }
    }

    public boolean getFillMode() {
        return fillMode;
    }

    public Color getCurrentColour(){
        return colour;
    }

    public void setColour(Color color){
        this.colour = color;
        changed();
    }

    public void setShapeMode(String shapeType) {
        this.shapeMode = shapeType;
    }

    public String getShapeMode() {
        return shapeMode;
    }

    /**
     * Creates a new Shape, adding it into the list of shapes to be drawn
     * @param x The initial x coordinate
     * @param y The initial y coordinate
     * @param x2 The final x coordinate
     * @param y2 The final y coordinate
     */
    public void modelNewShape(int x, int y, int x2, int y2){
        if (shapeMode=="Line"){
            shapeList.add(new myLine(x, y, x2, y2, colour));
            changed();
        }else if (shapeMode=="Cross"){
            shapeList.add(new myDiagonalCross(x, y, x2, y2, colour, fillMode));
            changed();
        }else if (shapeMode=="Rectangle"){
            shapeList.add(new myRectangle(x, y, x2, y2, colour, fillMode,aspectMode));
            changed();
        }else if (shapeMode=="Ellipse"){
            shapeList.add(new myEllipse(x, y, x2, y2, colour, fillMode,aspectMode));
            changed();
        }else if (shapeMode=="Triangle"){
            shapeList.add(new myTriangle(x, y, x2, y2, colour, fillMode));
            changed();
        }else if (shapeMode=="Hexagon"){
            shapeList.add(new myHexagon (x,y,x2,y2,colour, fillMode));
            changed();
        }else if (shapeMode=="Parallelogram"){
            shapeList.add(new myParallelogram (x,y,x2,y2,colour, fillMode));
            changed();
        }else if (shapeMode=="Random"){
            shapeList.add(new myRandom (x,y,x2,y2,colour, fillMode));
            changed();
        }
    }

    public ArrayList<Shapes> getShapeList() {
        return shapeList;
    }

    /**
     * Updates the temporary variable Shape, dragged, to enable shapes to be drawn as they are dragged
     * @param x The initial x coordinate
     * @param y The initial y coordinate
     * @param x2 The final x coordinate
     * @param y2 The final y coordinate
     */
    public void modelDraggedShape(int x, int y, int x2, int y2){
        if (shapeMode=="Line"){
            dragged = new myLine (x,y,x2,y2,colour);
            changed();
        }else if (shapeMode=="Cross"){
            dragged = new myDiagonalCross (x,y,x2,y2,colour, fillMode);
            changed();
        }else if (shapeMode=="Rectangle"){
            dragged = new myRectangle (x,y,x2,y2,colour, fillMode,aspectMode);
            changed();
        }else if (shapeMode=="Ellipse"){
            dragged = new myEllipse (x,y,x2,y2,colour, fillMode,aspectMode);
            changed();
        }else if (shapeMode=="Triangle"){
            dragged = new myTriangle (x,y,x2,y2,colour, fillMode);
            changed();
        }else if (shapeMode=="Hexagon"){
            dragged = new myHexagon (x,y,x2,y2,colour, fillMode);
            changed();
        }else if (shapeMode=="Parallelogram"){
            dragged = new myParallelogram (x,y,x2,y2,colour, fillMode);
            changed();
        }else if (shapeMode=="Random"){
            dragged = new myRandom (x,y,x2,y2,colour, fillMode);
            changed();
        }
    }

    //Returns dragged shape
    public Shapes getDragged(){
        return dragged;
    }

    //Sets the temporary dragged shape to null
    public void nullifyDragged(){
        dragged=null;
        changed();
    }

    /**
     * Changes the model to that of the newFile being loaded in by the user.
     * @param newShapeList the shapeList from the loaded file.
     * @param newStack the undoStack from the loaded file.
     */
    public void changeNewFile(ArrayList<Shapes> newShapeList,Stack<Shapes> newStack){
        shapeList=newShapeList;
        redoStack=newStack;
        changed();
    }

    public void setAspectMode(Boolean state){
        if(state){
            aspectMode=true;
        }else{
            aspectMode=false;
        }
    }
    public boolean getAspectMode(){
        return aspectMode;
    }

}
