/* Design an applet-based form that collects user inputs (like name, age, and feedback)
and stores this data into a file on the system. Ensure proper validation and
use exception handling for missing or incorrect inputs and I/O failures */


import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDateTime;

class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }
}

public class FeedbackForm extends Applet implements ActionListener {

    static final String FILE_NAME = "feedback.txt";

    TextField nameField, ageField;
    TextArea feedbackArea;
    Button submitBtn, clearBtn;
    Label statusLabel;

    public void init() {
        setLayout(new BorderLayout(10, 10));

        Panel top = new Panel(new GridLayout(2, 2, 8, 8));
        top.add(new Label("Name:"));
        nameField = new TextField(20);
        top.add(nameField);
        top.add(new Label("Age:"));
        ageField = new TextField(5);
        top.add(ageField);

        Panel center = new Panel(new BorderLayout(5, 5));
        center.add(new Label("Feedback:"), BorderLayout.NORTH);
        feedbackArea = new TextArea(6, 30);
        center.add(feedbackArea, BorderLayout.CENTER);

        Panel buttons = new Panel(new FlowLayout());
        submitBtn = new Button("Submit");
        clearBtn = new Button("Clear");
        buttons.add(submitBtn);
        buttons.add(clearBtn);
        statusLabel = new Label("");

        Panel bottom = new Panel(new GridLayout(2, 1));
        bottom.add(buttons);
        bottom.add(statusLabel);

        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        submitBtn.addActionListener(this);
        clearBtn.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearBtn) {
            clearForm();
            return;
        }

        try {
            String name = nameField.getText().trim();
            String ageText = ageField.getText().trim();
            String feedback = feedbackArea.getText().trim();

            int age = validate(name, ageText, feedback);
            saveToFile(name, age, feedback);

            showStatus("Saved successfully to " + FILE_NAME, Color.GREEN.darker());
            clearForm();
        } catch (ValidationException ex) {
            showStatus("Error: " + ex.getMessage(), Color.RED);
        } catch (IOException ex) {
            showStatus("File error: " + ex.getMessage(), Color.RED);
        }
    }

    private int validate(String name, String ageText, String feedback)
            throws ValidationException {

        if (name.isEmpty())
            throw new ValidationException("Name is required.");
        if (!name.matches("[A-Za-z ]+"))
            throw new ValidationException("Name can contain only letters and spaces.");

        if (ageText.isEmpty())
            throw new ValidationException("Age is required.");

        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException ex) {
            throw new ValidationException("Age must be a whole number.");
        }
        if (age < 1 || age > 120)
            throw new ValidationException("Age must be between 1 and 120.");

        if (feedback.isEmpty())
            throw new ValidationException("Feedback cannot be empty.");

        return age;
    }
  
    private void saveToFile(String name, int age, String feedback) throws IOException {
        String cleanFeedback = feedback.replace("\r", " ").replace("\n", " ");
        String record = LocalDateTime.now() + " | " + name + " | " + age + " | " + cleanFeedback;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(record);
            bw.newLine();
        }
    }

    private void clearForm() {
        nameField.setText("");
        ageField.setText("");
        feedbackArea.setText("");
    }

    private void showStatus(String message, Color color) {
        statusLabel.setForeground(color);
        statusLabel.setText(message);
    }

    public static void main(String[] args) {
        Frame f = new Frame("Feedback Form");
        FeedbackForm app = new FeedbackForm();
        app.init();
        f.add(app);
        f.setSize(450, 350);
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
