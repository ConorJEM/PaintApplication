package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import viewing.view;
import controller.vectorController;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

/**
 *
 *  Tests covering the vectorModel Class, for the properties of the shapes it makes.
 *
 */
public class vectorModelCreationTest {

    private vectorModel model;
    private vectorController controller;
    private viewing.view view;

    @Before
    public void setup() {

        model = new vectorModel();
        controller = new vectorController(model);
        view = new view(model, controller);

        controller.controlShape("Rectangle"); //SET SHAPE SELECTION TO RECTANGLE
        controller.controlColor(Color.RED); //SET COLOUR SELECTION TO RED
        controller.controlNewShape(100,50,200,200); //CREATE NEW RECTANGLE (x,y,x2,y2)
    }

    @Test
    public void checkListSize(){
        assertEquals(model.getShapeList().size(),1);
    }

    @Test
    public void checkCorrectShape(){
        assertEquals(model.getShapeList().get(0).getShapeType(),"Rectangle");
    }

    @Test
    public void checkCorrectColour(){
        assertEquals(model.getShapeList().get(0).getColour(),Color.RED);
    }

    @Test
    public void checkCorrectAspect(){
        controller.controlAspectMode(true); //SET ASPECT MODE TO TRUE (SQUARE MODE)
        assertTrue(!model.getShapeList().get(0).getAspectMode()); //THE ASPECT MODE OF THE PREVIOUSLY MADE SHAPE SHOULD STILL BE FALSE
    }

    @Test
    public void checkHexagon(){
        controller.controlAspectMode(true); //SET ASPECT MODE TO TRUE (SQUARE MODE)
        controller.controlShape("Hexagon");
        controller.controlNewShape(100,50,200,200); //CREATE NEW Hexagon (x,y,x2,y2)
        assertEquals(model.getShapeList().get(1).getShapeType(),"Hexagon"); //THE ASPECT MODE OF THE PREVIOUSLY MADE SHAPE SHOULD STILL BE FALSE
    }


}
