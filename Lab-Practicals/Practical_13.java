/* Design a basic calculator using AWT that performs operations like 
addition, subtraction, multiplication, and division. Use TextField,
Button, and Label components, and arrange them neatly using GridLayout */



import java.awt.*;
import java.awt.event.*;

public class Calculator extends Frame implements ActionListener {

    TextField display;
    Label info;
    double num1 = 0;
    String op = "";
    boolean startNew = true;

    Calculator() {
        super("Calculator");
        setLayout(new BorderLayout(5, 5));

        info = new Label("Simple Calculator", Label.CENTER);
        display = new TextField("0");
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 22));

        Panel top = new Panel(new GridLayout(2, 1, 5, 5));
        top.add(info);
        top.add(display);
        add(top, BorderLayout.NORTH);

        String[] labels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };
        Panel keys = new Panel(new GridLayout(4, 4, 5, 5));
        for (String s : labels) {
            Button b = new Button(s);
            b.setFont(new Font("Arial", Font.BOLD, 18));
            b.addActionListener(this);
            keys.add(b);
        }
        add(keys, BorderLayout.CENTER);

        Button clear = new Button("C");
        clear.setFont(new Font("Arial", Font.BOLD, 18));
        clear.addActionListener(this);
        add(clear, BorderLayout.SOUTH);

        setSize(300, 380);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        try {
            if (cmd.matches("[0-9.]")) {
                enterDigit(cmd);
            } else if (cmd.equals("C")) {
                reset();
            } else if (cmd.equals("=")) {
                if (!op.isEmpty() && !startNew) {
                    compute();
                    op = "";
                }
            } else { // + - * /
                if (!op.isEmpty() && !startNew) {
                    compute();
                } else {
                    num1 = Double.parseDouble(display.getText());
                }
                op = cmd;
                startNew = true;
            }
        } catch (ArithmeticException | NumberFormatException ex) {
            String msg = (ex instanceof ArithmeticException) ? ex.getMessage() : "Invalid input";
            reset();
            display.setText("Error");
            info.setText(msg);
        }
    }

    private void enterDigit(String d) {
        if (startNew) {
            display.setText(d.equals(".") ? "0." : d);
            startNew = false;
        } else if (d.equals(".") && display.getText().contains(".")) {
            return;
        } else {
            display.setText(display.getText() + d);
        }
        info.setText("Simple Calculator");
    }

    private void compute() {
        double num2 = Double.parseDouble(display.getText());
        double result;
        switch (op) {
            case "+": result = num1 + num2; break;
            case "-": result = num1 - num2; break;
            case "*": result = num1 * num2; break;
            case "/":
                if (num2 == 0) throw new ArithmeticException("Cannot divide by zero");
                result = num1 / num2;
                break;
            default: return;
        }
        num1 = result;
        display.setText(format(result));
        startNew = true;
    }

    private String format(double v) {
        if (v == (long) v) return String.valueOf((long) v);
        return String.valueOf(v);
    }

    private void reset() {
        num1 = 0;
        op = "";
        startNew = true;
        display.setText("0");
        info.setText("Simple Calculator");
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
