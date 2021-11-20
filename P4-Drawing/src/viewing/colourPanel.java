package viewing;

import model.vectorModel;
import controller.vectorController;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class defines the colourPanel JPanel which has all the buttons for colour selection.
 */
public class colourPanel extends JPanel implements ActionListener {

    private vectorModel model;
    private vectorController controller;

    JButton b1;
    JButton b2;
    JButton b3;
    JButton b4;
    JButton b5;
    JButton b6;

    public colourPanel(vectorModel model, vectorController controller) {
        this.model = model;
        this.controller = controller;
        JPanel colourButtons = new JPanel();

        colourButtons.setBorder(new EmptyBorder(0, 10, 10, 10));
        colourButtons.setLayout(new GridLayout(2, 3));

        //Create a new JButton for each colour selection
        b1 = new JButton("Black");
        b2 = new JButton("Gray");
        b3 = new JButton("Red");
        b4 = new JButton("Blue");
        b5 = new JButton("Orange");
        b6 = new JButton("Yellow");

        //Changing Background and Foreground for different Colors
        b1.setBackground(Color.black);
        b1.setForeground(Color.white); //(Foreground seems changes the text color)
        b2.setBackground(Color.gray);
        b3.setBackground(Color.RED);
        b4.setBackground(Color.BLUE);
        b4.setForeground(Color.white); //(Foreground seems changes the text color)
        b5.setBackground(Color.ORANGE);
        b6.setBackground(Color.YELLOW);

        //add all buttons to the JPanel
        colourButtons.add(b1);
        colourButtons.add(b2);
        colourButtons.add(b3);
        colourButtons.add(b4);
        colourButtons.add(b5);
        colourButtons.add(b6);

        addActionListenerForButtons(this);

        add(colourButtons);
    }

    //Add one action listener for all the buttons
    public void addActionListenerForButtons(ActionListener al) {
        b1.addActionListener(al);
        b2.addActionListener(al);
        b3.addActionListener(al);
        b4.addActionListener(al);
        b5.addActionListener(al);
        b6.addActionListener(al);
    }

    //IF A BUTTON IS PRESSED, TELL THE CONTROLLER TO HANDLE THE COLOUR CHANGE
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == b1) {
            controller.controlColor(Color.BLACK);
        } else if (event.getSource() == b2) {
            controller.controlColor(Color.GRAY);
        } else if (event.getSource() == b3) {
            controller.controlColor(Color.RED);
        } else if (event.getSource() == b4) {
            controller.controlColor(Color.BLUE);
        } else if (event.getSource() == b5) {
            controller.controlColor(Color.ORANGE);
        } else if (event.getSource() == b6) {
            controller.controlColor(Color.YELLOW);
        }
    }
}
