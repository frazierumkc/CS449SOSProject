package sprint0;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Gui {
  {
    // Creating instance of JFrame
    JFrame frame = new JFrame("Gui Test");


    // Label with text
    JLabel label1 = new JLabel("Hello World");
    label1.setBounds(150, 0, 220, 50);
    label1.setHorizontalAlignment(SwingConstants.CENTER);;
    frame.add(label1);

    // Text field
    JTextField textField1 = new JTextField("Enter text...");
    textField1.setBounds(150, 50, 220, 50);
    frame.add(textField1);

    // Check boxes
    JCheckBox checkBox1 = new JCheckBox("Hello");
    JCheckBox checkBox2 = new JCheckBox("Hi");
    checkBox1.setBounds(10, 150, 100, 20);
    checkBox2.setBounds(10, 200, 100, 20);
    frame.add(checkBox1);
    frame.add(checkBox2);

    // Radio buttons
    JRadioButton radio1 = new JRadioButton("Hot");
    JRadioButton radio2 = new JRadioButton("Cold");
    radio1.setBounds(200, 150, 100, 20);
    radio2.setBounds(200, 200, 100, 20);
    ButtonGroup radioGroup = new ButtonGroup();
    radioGroup.add(radio1);
    radioGroup.add(radio2);
    frame.add(radio1);
    frame.add(radio2);

    // Button that changes label text
    JButton button1 = new JButton("This Button Does Something");
    button1.setBounds(150, 300, 220, 50);
    frame.add(button1);
    button1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        label1.setText("You pressed button!!!");
      }
    });

    frame.setSize(500, 400);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    // Draw lines
    frame.getContentPane().add(new Lines ());
    frame.setVisible(true);

  }

  // Draw dividing lines
  @SuppressWarnings("serial")
  class Lines extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawLine(0, 110, 500, 110);
      g.drawLine(0, 250, 500, 250);
      g.drawLine(150, 110, 150, 250);

    }
  }


  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new Gui(); 
      }
    });
  }
}
