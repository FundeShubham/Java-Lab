/*Create a Java Applet that allows the user to enter a
temperature in Celsius or Fahrenheit and convert it 
to the other scale. Use basic GUI elements like TextField, Label, and Button*/


import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class TempConverter extends Applet implements ActionListener {

    TextField input;
    Label title, inputLabel, resultLabel;
    Button cToF, fToC;

    public void init() {
        setLayout(new GridLayout(4, 2, 8, 8));

        title = new Label("Temperature Converter");
        inputLabel = new Label("Enter temperature:");
        input = new TextField(10);
        cToF = new Button("Celsius to Fahrenheit");
        fToC = new Button("Fahrenheit to Celsius");
        resultLabel = new Label("Result: ");

        add(title);
        add(new Label(""));
        add(inputLabel);
        add(input);
        add(cToF);
        add(fToC);
        add(resultLabel);

        cToF.addActionListener(this);
        fToC.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            double value = Double.parseDouble(input.getText().trim());
            if (e.getSource() == cToF) {
                double f = value * 9 / 5 + 32;
                resultLabel.setText(String.format("Result: %.2f F", f));
            } else {
                double c = (value - 32) * 5 / 9;
                resultLabel.setText(String.format("Result: %.2f C", c));
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Result: Invalid input");
        }
    }

  
    public static void main(String[] args) {
        Frame f = new Frame("Temperature Converter");
        TempConverter app = new TempConverter();
        app.init();
        f.add(app);
        f.setSize(400, 220);
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
