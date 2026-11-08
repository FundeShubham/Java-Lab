/* Create a Java application with AWT that allows users to type diary entries in a 
TextArea and save them to a file using FileDialog. Use BorderLayout and organize
controls using nested Panels. Include input validation and confirmation dialogs. */



import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DiaryApp extends Frame implements ActionListener {

    TextField titleField;
    TextArea entryArea;
    Button saveBtn, clearBtn, exitBtn;
    Label dateLabel, statusLabel;
    boolean saved = true; 

    DiaryApp() {
        super("My Diary");
        setLayout(new BorderLayout(10, 10));

        Label heading = new Label("Dear Diary", Label.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 18));

        String now = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a"));
        dateLabel = new Label("Date: " + now, Label.CENTER);

        titleField = new TextField(30);
        Panel titleRow = new Panel(new BorderLayout(5, 5));
        titleRow.add(new Label("Title:"), BorderLayout.WEST);
        titleRow.add(titleField, BorderLayout.CENTER);

        Panel header = new Panel(new GridLayout(3, 1, 3, 3));
        header.add(heading);
        header.add(dateLabel);
        header.add(titleRow);
        add(header, BorderLayout.NORTH);

        entryArea = new TextArea();
        entryArea.setFont(new Font("Arial", Font.PLAIN, 14));
        add(entryArea, BorderLayout.CENTER);

        statusLabel = new Label("Write your entry and click Save.");

        saveBtn = new Button("Save");
        clearBtn = new Button("Clear");
        exitBtn = new Button("Exit");
        saveBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        exitBtn.addActionListener(this);

        Panel buttons = new Panel(new FlowLayout());
        buttons.add(saveBtn);
        buttons.add(clearBtn);
        buttons.add(exitBtn);

        Panel footer = new Panel(new BorderLayout());
        footer.add(statusLabel, BorderLayout.NORTH);
        footer.add(buttons, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);

        entryArea.addTextListener(e -> saved = false);
        titleField.addTextListener(e -> saved = false);

        setSize(550, 480);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                exitApp();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == saveBtn) saveEntry();
        else if (src == clearBtn) clearEntry();
        else if (src == exitBtn) exitApp();
    }

    private void saveEntry() {
        String title = titleField.getText().trim();
        String entry = entryArea.getText().trim();

        if (title.isEmpty()) {
            showDialog("Missing Title", "Please enter a title.", false);
            return;
        }
        if (title.length() > 50) {
            showDialog("Title Too Long", "Title must be 50 characters or less.", false);
            return;
        }
        if (entry.isEmpty()) {
            showDialog("Empty Entry", "Your diary entry is empty.", false);
            return;
        }
        if (entry.length() < 10) {
            showDialog("Too Short", "Entry must be at least 10 characters.", false);
            return;
        }

        if (!showDialog("Confirm Save", "Save this diary entry?", true)) return;

        FileDialog fd = new FileDialog(this, "Save Diary Entry", FileDialog.SAVE);
        fd.setFile("diary.txt");
        fd.setVisible(true);

        if (fd.getFile() == null) { 
            statusLabel.setText("Save cancelled.");
            return;
        }

        String name = fd.getFile();
        if (!name.contains(".")) name += ".txt";
        File file = new File(fd.getDirectory(), name);

        if (file.exists() &&
                !showDialog("File Exists", name + " already exists. Overwrite?", true)) {
            statusLabel.setText("Save cancelled.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(dateLabel.getText());
            bw.newLine();
            bw.write("Title: " + title);
            bw.newLine();
            bw.newLine();
            bw.write(entry);
            bw.newLine();
            saved = true;
            statusLabel.setText("Saved to " + file.getAbsolutePath());
            showDialog("Success", "Diary entry saved successfully.", false);
        } catch (IOException ex) {
            statusLabel.setText("Save failed.");
            showDialog("File Error", "Could not save: " + ex.getMessage(), false);
        }
    }

    private void clearEntry() {
        if (titleField.getText().isEmpty() && entryArea.getText().isEmpty()) {
            statusLabel.setText("Nothing to clear.");
            return;
        }
        if (showDialog("Confirm Clear", "Clear the title and entry?", true)) {
            titleField.setText("");
            entryArea.setText("");
            saved = true;
            statusLabel.setText("Cleared.");
        }
    }
  
    private void exitApp() {
        if (!saved && !showDialog("Unsaved Changes",
                "You have unsaved changes. Exit anyway?", true)) {
            return;
        }
        System.exit(0);
    }

    private boolean showDialog(String title, String message, boolean confirm) {
        final boolean[] result = {false};
        final Dialog d = new Dialog(this, title, true);
        d.setLayout(new BorderLayout(10, 10));
        d.add(new Label(message, Label.CENTER), BorderLayout.CENTER);

        Panel p = new Panel(new FlowLayout());
        if (confirm) {
            Button yes = new Button("Yes");
            Button no = new Button("No");
            yes.addActionListener(ev -> { result[0] = true; d.dispose(); });
            no.addActionListener(ev -> d.dispose());
            p.add(yes);
            p.add(no);
        } else {
            Button ok = new Button("OK");
            ok.addActionListener(ev -> d.dispose());
            p.add(ok);
            result[0] = true;
        }
        d.add(p, BorderLayout.SOUTH);

        d.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                d.dispose();
            }
        });

        d.setSize(380, 140);
        d.setLocationRelativeTo(this);
        d.setVisible(true);
        return result[0];
    }

    public static void main(String[] args) {
        new DiaryApp();
    }
}
