package viewing;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import model.Shapes;
import model.vectorModel;
import controller.vectorController;

class canvasPanel extends JPanel implements MouseListener, MouseMotionListener, vectorModel.Listener {

    int x, y, x2, y2;
    private vectorModel model;
    private vectorController controller;
    Point mouseLocation;
    ArrayList<Shapes> weirdlist;

    public canvasPanel(vectorModel model, vectorController controller) {

        this.model = model;
        this.controller = controller;
        addMouseListener(this);
        addMouseMotionListener(this);
        //This canvasPanel is added as a listener of the model
        model.addListener(this);

    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //ensures we only paint on this JPanel
        super.paintComponent(g2d);

        //FIRST PAINT THE CURRENT SHAPE BEING DRAGGED
        Shapes dragged = model.getDragged();
        if (dragged != null) {
            Color colour = dragged.getColour();
            g2d.setColor(colour);
            //Lines cannot be filled, so are always drawn, If fillmode is false then draw
            if (dragged.getShapeType().equals("Line") || !dragged.getFillMode()) {
                g2d.draw(dragged.getPolyLine());
            } else if (dragged.getFillMode()) { //If fillmode is true then fill
                g2d.fill(dragged.getPolyLine());
            }
        }


        //GETTING THE LIST OF CURRENT SHAPES FROM THE MODEL
        weirdlist = model.getShapeList();

        //PAINTING EVERYTHING INSIDE OUR SHAPES ARRAY weirdlist.
        for (int i = 0; i < weirdlist.size(); i++) {
            Shapes weird = weirdlist.get(i);
            //System.out.println("rec list working on shape" + i);
            //SETTING THE DRAW COLOUR TO THIS SHAPES COLOUR
            g2d.setColor(weird.getColour());

            //Lines cannot be filled, so are always drawn, If fillmode is false then draw
            if (weird.getShapeType().equals("Line") || !weird.getFillMode()) {
                g2d.draw(weird.getPolyLine());
            }//If fillmode is true then fill
            else if (weird.getFillMode()) {
                g2d.fill(weird.getPolyLine());
            }
        }
    }


    public void firstPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void lastPoint(int x, int y) {
        x2 = (x);
        y2 = (y);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse Clicked at " + e.getX());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Pressed at " + e.getX() + " " + e.getY());
        firstPoint(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Mouse released at " + e.getX() + " " + e.getY());
        lastPoint(e.getX(), e.getY());

        //NOW THAT THE MOUSE IS RELEASED WE HAVE OUR TWO POINTS, MAKE NEW SHAPE
        controller.controlNewShape(x, y, x2, y2);
        //WHEN THE MOUSE IS RELEASED, NULLIFY THE TEMPORARY 'DRAGGED' SHAPE
        controller.controlNullifyDragged();
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //System.out.println("Mouse dragged " + e.getX() + " " + e.getY());
        lastPoint(e.getX(), e.getY());

        controller.controlDraggedShape(x, y, x2, y2); //Handle this currently dragged shape
        repaint();

    }

    @Override
    public void mouseMoved(MouseEvent e) {

        //LOGIC FOR DETECTING WHEN THE MOUSE IS OVER A SHAPE. HAVEN'T IMPLEMENTED BEING ABLE TO CLICK ON SHAPES TO CHANGE
        //THERE COLOUR/SIZE/LOCATION YET.

        //System.out.println(e.getX()+e.getY()+" Moused MOVED!");
        mouseLocation = new Point(e.getX(),e.getY());//CREATE NEW POINT AT CURRENT MOUSE LOCATION
        for (int i = 0; i < weirdlist.size(); i++) { //LOOP THROUGH EVERY DRAWN SHAPE
            Shapes weird = weirdlist.get(i);
            if(weird.getPolyLine().contains(mouseLocation)){//CHECK IF MOUSE IS INSIDE A SHAPES POLYLINE
                //System.out.println("Mouse Over a shape!");
            }
        }
    }


    @Override
    public void update() {
        repaint();

    }


}