/*
  Problem Statement:
  To create a Java program that copies data from one file to another using
  a separate thread. Handle exceptions such as FileNotFoundException and
  IOException, and log errors into a separate error log file using exception
  handling mechanisms.
 */

import java.io.*;

class FileCopyThread extends Thread {

    private String sourceFile;
    private String destinationFile;

    public FileCopyThread(String source, String destination) {
        this.sourceFile = source;
        this.destinationFile = destination;
    }

    @Override
    public void run() {

        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
 
            fis = new FileInputStream(sourceFile);

 
            fos = new FileOutputStream(destinationFile);

            int data;

 
            while ((data = fis.read()) != -1) {
                fos.write(data);
            }

            System.out.println("File copied successfully.");

        } catch (FileNotFoundException e) {

            logError("FileNotFoundException: " + e.getMessage());

        } catch (IOException e) {

            logError("IOException: " + e.getMessage());

        } finally {

            try {
 
                if (fis != null) {
                    fis.close();
                }

                if (fos != null) {
                    fos.close();
                }

            } catch (IOException e) {
                logError("Error closing file: " + e.getMessage());
            }
        }
    }

    private void logError(String message) {

        try (
            FileWriter fw = new FileWriter("error.log", true);
            PrintWriter pw = new PrintWriter(fw)
        ) {
            pw.println("Error: " + message);

        } catch (IOException e) {

            System.out.println("Unable to write to log file.");
        }
    }
}

public class Assignment_08 {

    public static void main(String[] args) {

        String source = "source.txt";
        String destination = "destination.txt";

        FileCopyThread thread =
                new FileCopyThread(source, destination);

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted.");
        }
    }
}
