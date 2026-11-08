/* Build a simple quiz interface where users can answermultiple-choice
questions using CheckboxGroup. Manage the layout with BorderLayout and Panel.
On submission, show the score using a dialog box.*/


import java.awt.*;
import java.awt.event.*;

public class QuizApp extends Frame implements ActionListener {

    String[] questions = {
        "Which keyword is used to inherit a class in Java?",
        "Which of these is NOT a primitive data type?",
        "What is the default value of an int field in a class?",
        "Which package contains the AWT classes?",
        "Which method is the entry point of a Java program?"
    };

    String[][] options = {
        {"extends", "implements", "inherits", "super"},
        {"int", "boolean", "char", "String"},
        {"0", "null", "1", "undefined"},
        {"java.swing", "java.io", "java.awt", "java.util"},
        {"start()", "main()", "run()", "init()"}
    };

    int[] answers = {0, 3, 0, 2, 1};

    CheckboxGroup[] groups = new CheckboxGroup[questions.length];
    Checkbox[][] boxes = new Checkbox[questions.length][4];
    Button submit;

    QuizApp() {
        super("Java Quiz");
        setLayout(new BorderLayout(10, 10));

        Label title = new Label("Java Quiz: choose one answer per question", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        add(title, BorderLayout.NORTH);

        Panel center = new Panel(new GridLayout(questions.length, 1, 5, 5));
        for (int i = 0; i < questions.length; i++) {
            Panel qPanel = new Panel(new BorderLayout(5, 5));
            qPanel.add(new Label((i + 1) + ". " + questions[i]), BorderLayout.NORTH);

            Panel optPanel = new Panel(new GridLayout(2, 2));
            groups[i] = new CheckboxGroup();
            for (int j = 0; j < 4; j++) {
                boxes[i][j] = new Checkbox(options[i][j], groups[i], false);
                optPanel.add(boxes[i][j]);
            }
            qPanel.add(optPanel, BorderLayout.CENTER);
            center.add(qPanel);
        }
        add(center, BorderLayout.CENTER);

        Panel south = new Panel(new FlowLayout());
        submit = new Button("Submit");
        submit.addActionListener(this);
        south.add(submit);
        add(south, BorderLayout.SOUTH);

        setSize(520, 560);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        int score = 0;
        StringBuilder unanswered = new StringBuilder();

        for (int i = 0; i < questions.length; i++) {
            Checkbox selected = groups[i].getSelectedCheckbox();
            if (selected == null) {
                unanswered.append(i + 1).append(" ");
            } else if (selected == boxes[i][answers[i]]) {
                score++;
            }
        }

        if (unanswered.length() > 0) {
            showDialog("Incomplete", "Please answer question(s): " + unanswered.toString().trim());
        } else {
            showDialog("Result", "Your score: " + score + " / " + questions.length);
        }
    }

    private void showDialog(String title, String message) {
        final Dialog d = new Dialog(this, title, true); // modal dialog
        d.setLayout(new BorderLayout(10, 10));

        Label msg = new Label(message, Label.CENTER);
        msg.setFont(new Font("Arial", Font.BOLD, 14));
        d.add(msg, BorderLayout.CENTER);

        Button ok = new Button("OK");
        ok.addActionListener(ev -> d.dispose());
        Panel p = new Panel(new FlowLayout());
        p.add(ok);
        d.add(p, BorderLayout.SOUTH);

        d.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                d.dispose();
            }
        });

        d.setSize(320, 140);
        d.setLocationRelativeTo(this);
        d.setVisible(true);
    }

    public static void main(String[] args) {
        new QuizApp();
    }
}
