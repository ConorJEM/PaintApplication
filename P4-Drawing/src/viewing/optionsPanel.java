package viewing;

import model.vectorModel;
import controller.vectorController;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class that makes the options panel JPanel, that includes all the buttons for user functionality,
 * such as shape selection, fill mode, undo and redo, and clear.
 */
public class optionsPanel extends JPanel implements ActionListener {

    private vectorModel model;
    private vectorController controller;

    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton button5;
    JButton button6;
    JButton button7;
    JButton button8;
    JButton button9;
    JButton button10;
    JButton button11;
    JButton button12;

    public optionsPanel(vectorModel model, vectorController controller) {
        this.model = model;
        this.controller = controller;
        JPanel optionsPanel = new JPanel();

        //optionsPanel.setBorder(new EmptyBorder(0, 10, 10, 10));
        optionsPanel.setLayout(new GridLayout(1, 11));
        optionsPanel.setBackground(Color.gray);
        //CREATE A NEW BUTTON FOR EACH OPTION
        button1 = new JButton("Rectangle");
        button2 = new JButton("Line");
        button3 = new JButton("Ellipse");
        button4 = new JButton("Clear");
        button5 = new JButton("Undo");
        button6 = new JButton("Redo");
        button7 = new JButton("Diagonal Cross");
        button8 = new JButton("Triangle");
        button9 = new JButton("Parallelogram");
        button10 = new JButton("Hexagon");
        button11 = new JButton("Fill Mode");
        button12 = new JButton("Random");

        //ADD EACH BUTTON TO THE JPanel
        optionsPanel.add(button5);
        optionsPanel.add(button6);
        optionsPanel.add(button1);
        optionsPanel.add(button2);
        optionsPanel.add(button3);
        optionsPanel.add(button7);
        optionsPanel.add(button8);
        optionsPanel.add(button9);
        optionsPanel.add(button10);
        optionsPanel.add(button11);
        optionsPanel.add(button12);
        optionsPanel.add(button4);

        addActionListenerForButtons(this);
        add(optionsPanel);

    }

    //GIVE EVERY BUTTON THE SAME ACTION LISTENER
    public void addActionListenerForButtons(ActionListener al) {
        button1.addActionListener(al);
        button2.addActionListener(al);
        button3.addActionListener(al);
        button4.addActionListener(al);
        button5.addActionListener(al);
        button6.addActionListener(al);
        button7.addActionListener(al);
        button8.addActionListener(al);
        button9.addActionListener(al);
        button10.addActionListener(al);
        button11.addActionListener(al);
        button12.addActionListener(al);

    }

    //HANDLE BUTTON PRESSES
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == button1) { //RECTANGLE BUTTON
            controller.controlShape("Rectangle");
        } else if (event.getSource() == button2) { //LINE BUTTON
            controller.controlShape("Line");
        } else if (event.getSource() == button3) { //ELLIPSE BUTTON
            controller.controlShape("Ellipse");
        } else if (event.getSource() == button4) { //CLEAR BUTTON
            controller.controlClear();
        } else if (event.getSource() == button5) { //UNDO BUTTON
            controller.controlUndo();
        } else if (event.getSource() == button6) { //REDO BUTTON
            controller.controlRedo();
        } else if (event.getSource() == button7) { //CROSS BUTTON
            controller.controlShape("Cross");
        } else if (event.getSource() == button8) { //TRIANGLE BUTTON
            controller.controlShape("Triangle");
        } else if (event.getSource() == button9) { //PARALLELOGRAM BUTTON
            controller.controlShape("Parallelogram");
        } else if (event.getSource() == button10) {//HEXAGON BUTTON
            controller.controlShape("Hexagon");
        } else if (event.getSource() == button12) {//HEXAGON BUTTON
            controller.controlShape("Random");
        } else if (event.getSource() == button11) { //FILL MODE BUTTON
            controller.controlFillMode();

            if (model.getFillMode()) {
                button11.setBackground(Color.gray);
            } else {
                //make the button return to default appearance if fill mode isn't enabled
                button11.setBackground(new JButton().getBackground());
            }
        }
    }
}
