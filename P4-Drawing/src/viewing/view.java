package viewing;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.Stack;

import model.Shapes;
import model.vectorModel;
import controller.vectorController;

/**
 * Creates the view part of the MVC model.
 * Here the JFrame is made, and three separate JPanels are placed in its content pane
 * (one canvasPanel for drawing, one colourPanel for colour selection, one optionsPanel for action selection)
 * Handles the JMenuBar which has functionality for new files, saving files and loading files
 * The aspectMode toggler is defined here as part of the JMenubar
 */
public class view implements ActionListener {

    private vectorModel model;
    private vectorController controller;
    private JFrame frame;
    private JMenuItem newf;
    private JMenuItem open;
    private JMenuItem save;
    private JMenuItem mode;
    private Boolean aspectMode = false;

    public view(vectorModel model, vectorController controller) {
        this.model = model;
        this.controller = controller;

        //CREATING JFRAME
        //*****************************************************************************************************//
        frame = new JFrame("Shape Drawing!");
        ImageIcon img = new ImageIcon("Paint_2020_alt.png");
        frame.setIconImage(img.getImage());
        //frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);

        //CONSTRUCTING JMENUBAR WITH MENU ITEMS
        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");

        newf = new JMenuItem("New");
        save = new JMenuItem("Save");
        open = new JMenuItem("Open");
        mode = new JMenuItem("Aspect Mode");

        newf.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        mode.addActionListener(this);

        file.add(newf);
        file.add(save);
        file.add(open);
        edit.add(mode);
        menu.add(file);
        menu.add(edit);
        frame.setJMenuBar(menu);
        //*******************************************************************************************************//

        //ADDING THE JPANEL THAT WILL HAVE ALL THE NEEDED BUTTONS EXCEPT COLOURS
        //frame.getContentPane().add(menuPanel1,BorderLayout.NORTH)

        optionsPanel options = new optionsPanel(model, controller);
        frame.getContentPane().add(options, BorderLayout.NORTH);

        //ADDING THE JPANEL THAT WILL BE THE CANVAS
        canvasPanel canvasPanel1 = new canvasPanel(model, controller);
        frame.getContentPane().add(canvasPanel1);
        frame.setVisible(true);

        //ADDING THE JPANEL FOR COLOUR BUTTONS
        colourPanel colors = new colourPanel(model, controller);
        frame.getContentPane().add(colors, BorderLayout.SOUTH);
        //MAKE JFRAME VISIBLE
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent event) {

        //IF USER CLICKS OPEN BUTTON
        if (event.getSource() == open) {

            JFileChooser fileChooser = new JFileChooser(); //CREATE NEW FILE CHOOSER
            fileChooser.setCurrentDirectory(new File(".")); //SET DEFAULT LOCATION AT PROJECT DIRECTORY
            int response = fileChooser.showOpenDialog(null); //TRACK USER ACTION IN WINDOW

            if (response == JFileChooser.APPROVE_OPTION) { //IF USER SELECTED A FILE
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()); //CREATE NEW FILE AT THE SELECTED PATH
                System.out.println(file); //PRINT NAME OF FILE IN CONSOLE

                FileInputStream fileIn = null; ////INITIALISE FILE INPUT STREAM

                try {

                    fileIn = new FileInputStream(file); //CREATE NEW FILE INPUT STREAM
                    ObjectInputStream objectIS = null; //INITIALISE OBJECT INPUT STREAM
                    objectIS = new ObjectInputStream(fileIn); //CREATE NEW OBJECT INPUT STREAM
                    vectorModel newModel = (vectorModel) objectIS.readObject(); //READ MODEL FROM FILE
                    ArrayList<Shapes> newShapes = newModel.getShapeList(); //GET OBJECT LIST FROM FILE
                    Stack<Shapes> newStack = newModel.getRedoStack();
                    //System.out.println(newShapes.size()); //PRINT HOW MANY SHAPES IN THAT DRAWING
                    controller.controlNewFile(newShapes, newStack); //SENT NEW OBJECT LIST TO THE CONTROLLER

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }

        //IF USER CLICKS SAVE
        else if (event.getSource() == save) {
            JFileChooser fileChooser = new JFileChooser();  //CREATE NEW FILE CHOOSER
            fileChooser.setCurrentDirectory(new File(".")); //SET DEFAULT LOCATION AT PROJECT DIRECTORY
            int response = fileChooser.showSaveDialog(null); //TRACK USER ACTION IN WINDOW

            if (response == JFileChooser.APPROVE_OPTION) { //USER SAVED A FILE
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()); //CREATE NEW FILE AT THE SELECTED PATH
                System.out.println(file);  //PRINT OUT NAME OF FILE

                FileOutputStream fileOS = null;   //INITIALISE FILE OUTPUT STREAM
                try {
                    fileOS = new FileOutputStream(file); //CREATE NEW OUTPUT STREAM
                    ObjectOutputStream objectOS = null; //INITIALISE OBJECT OUTPUT STREAM
                    objectOS = new ObjectOutputStream(fileOS); //CREATE NEW OBJECT OUTPUT STREAM
                    objectOS.writeObject(model); //WRITE THE CURRENT MODEL TO THE FILE
                    //CLOSE STREAMS
                    fileOS.close();
                    objectOS.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        // IF USER CLICKS NEW, CLEAR PAGE
        else if (event.getSource() == newf) {
            controller.controlClear();
        }

        //IF USER CLICKS ASPECT MODE, CHANGE ASPECT MODE
        else if (event.getSource() == mode) {

            aspectMode = !aspectMode; // FLIP VALUE OF ASPECT MODE
            controller.controlAspectMode(aspectMode); //CONTROL ASPECT MODE
            if (aspectMode) {
                mode.setBackground(Color.gray);
            } else {
                mode.setBackground(Color.WHITE);
            }
        }
    }

}


