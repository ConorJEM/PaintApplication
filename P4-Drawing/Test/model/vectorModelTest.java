package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import viewing.view;
import controller.vectorController;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

/**
 * Tests covering basic functionality of the vectorModel class
 */
public class vectorModelTest {

    private vectorModel model;
    private vectorController controller;
    private viewing.view view;

    @Before
    public void setup() {

        model = new vectorModel();
        controller = new vectorController(model);
        view = new view(model, controller);

    }

    @Test
    public void checkListenerList() {
        assertEquals(model.getListeners().size(), 1); //CHECK THERE IS ONE LISTENER
    }

    @Test
    public void checkEmptyList() {
        assertEquals(model.getShapeList().size(), 0); //CHECK THAT SHAPE LIST IS EMPTY
    }

    @Test
    public void checkListSize() {
        controller.controlNewShape(100, 50, 200, 200); //CREATE NEW RECTANGLE (x,y,x2,y2)
        assertEquals(model.getShapeList().size(), 1); //CHECK LIST HAS 1 SHAPE
    }

    @Test
    public void checkDefaultShape() {
        assertEquals(model.getShapeMode(), "Line");
    }

    @Test
    public void checkCorrectShape() {
        controller.controlShape("Rectangle"); //SET SHAPE SELECTION TO RECTANGLE
        assertEquals(model.getShapeMode(), "Rectangle");
    }

    @Test
    public void checkDefaultColour() {
        assertEquals(model.getCurrentColour(), Color.BLACK);
    }

    @Test
    public void checkCorrectColour() {
        controller.controlColor(Color.RED); //SET COLOUR SELECTION TO RED
        assertEquals(model.getCurrentColour(), Color.RED);
    }

    @Test
    public void checkCorrectAspect() {
        controller.controlAspectMode(true); //SET ASPECT MODE TO TRUE (SQUARE MODE)
        assertEquals(model.getAspectMode(), true);
    }

    @Test
    public void checkUndoFunctionality() {
        controller.controlNewShape(100, 50, 200, 200); //Create Line
        controller.controlUndo(); //Undo the rectangle
        assertEquals(model.getShapeList().size(), 0);
    }

    @Test
    public void checkRedoStack() {
        controller.controlNewShape(100, 50, 200, 200); //Create Line
        controller.controlUndo(); //Undo the rectangle
        assertEquals(model.getRedoStack().size(), 1);
    }

    @Test
    public void checkRedoStackContent() {
        controller.controlNewShape(100, 50, 200, 200); //Create Line
        controller.controlUndo(); //Undo the rectangle
        assertTrue(model.getRedoStack().get(0) instanceof myLine);

    }
    @Test
    public void checkRedoStackContentShape() {
        controller.controlNewShape(100, 50, 200, 200); //Create Line
        controller.controlUndo(); //Undo the rectangle
        assertEquals(model.getRedoStack().get(0).getShapeType(),"Line");
    }

    @Test
    public void checkRedoFunctionality() {
        controller.controlNewShape(100, 50, 200, 200); //CREATE RECTANGLE
        controller.controlUndo(); //Undo the rectangle
        controller.controlRedo(); //Redo the rectangle
        assertEquals(model.getShapeList().size(), 1);
    }

    @Test
    public void checkRedoStackAfterRedo() {
        controller.controlNewShape(100, 50, 200, 200); //CREATE RECTANGLE
        controller.controlUndo(); //Undo the rectangle
        controller.controlRedo(); //Redo the rectangle
        assertEquals(model.getRedoStack().size(), 0);
    }

    @Test
    public void checkClearFunction() {
        controller.controlNewShape(100, 50, 200, 200); //CREATE RECTANGLE
        controller.controlNewShape(200, 50, 200, 200); //CREATE 2ND RECTANGLE
        controller.controlClear(); //CLEAR THE SHAPE LIST
        assertEquals(model.getShapeList().size(), 0);
    }

    @Test
    public void checkFillMode() {
        controller.controlFillMode();
        assertEquals(model.getFillMode(),true);
    }

    @Test
    public void checkNotDragged() {

        assertTrue(model.getDragged() == null);
    }

    @Test
    public void checkDragged() {
        controller.controlDraggedShape(100,200,200,300);
        assertTrue(model.getDragged() != null);
    }

    @Test
    public void checkNullifiedDragged() {
        controller.controlDraggedShape(100,200,200,300);
        controller.controlNullifyDragged();
        assertTrue(model.getDragged() == null);
    }

}


