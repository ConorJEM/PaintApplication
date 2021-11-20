package model;

import viewing.view;
import controller.vectorController;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import static org.junit.Assert.assertEquals;

/**
 *  A test covering a full drawing being made.
 */
public class fullDrawingTest {

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
    public void fullDrawing(){

        controller.controlShape("Rectangle"); //SET SHAPE SELECTION TO RECTANGLE
        controller.controlColor(Color.RED); //SET COLOUR SELECTION TO RED
        controller.controlNewShape(100,50,200,200); //CREATE NEW RECTANGLE (x,y,x2,y2)

        assertEquals(model.getShapeList().size(), 1);


        controller.controlShape("Hexagon"); //SET SHAPE SELECTION TO RECTANGLE
        controller.controlColor(Color.BLUE);
        controller.controlNewShape(100,100,300,400);

        assertEquals(model.getShapeList().size(), 2);
        assertEquals(model.getShapeList().get(1).getColour(), Color.BLUE);

        controller.controlShape("Triangle"); //SET SHAPE SELECTION TO RECTANGLE
        controller.controlColor(Color.BLACK);
        controller.controlFillMode();
        controller.controlNewShape(200,50,80,150);

        assertEquals(model.getShapeList().size(), 3);
        assertEquals(model.getShapeList().get(2).getColour(), Color.BLACK);
        assertEquals(model.getShapeList().get(2).getShapeType(), "Triangle");

        controller.controlUndo();
        assertEquals(model.getShapeList().size(), 2);
        assertEquals(model.getRedoStack().size(),1);
        assertEquals(model.getRedoStack().get(0).getShapeType(),"Triangle");

    }
}