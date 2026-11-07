/*To write a Java program that collects student details (Name, Roll Number, and Marks)
and stores them in a text file using FileOutputStream.
Later, read the stored records using FileInputStream and display them in a formatted manner.*/



import java.io.*;
import java.util.Scanner;

public class StudentRecords {

    static final String FILE_NAME = "students.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("How many students? ");
        int n = Integer.parseInt(sc.nextLine().trim());

        try (FileOutputStream fos = new FileOutputStream(FILE_NAME)) {
            for (int i = 1; i <= n; i++) {
                System.out.println("\nStudent " + i);
                System.out.print("Name        : ");
                String name = sc.nextLine().trim();
                System.out.print("Roll Number : ");
                String roll = sc.nextLine().trim();
                System.out.print("Marks       : ");
                String marks = sc.nextLine().trim();

                String record = name + "," + roll + "," + marks + "\n";
                fos.write(record.getBytes());
            }
            System.out.println("\nRecords saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
            return;
        }

        try (FileInputStream fis = new FileInputStream(FILE_NAME)) {
            String data = new String(fis.readAllBytes()); // Java 9+

            System.out.println("\n================ STUDENT RECORDS ================");
            System.out.printf("%-20s %-12s %-8s%n", "Name", "Roll No", "Marks");
            System.out.println("-------------------------------------------------");

            for (String line : data.split("\n")) {
                if (line.isBlank()) continue;
                String[] p = line.split(",");
                System.out.printf("%-20s %-12s %-8s%n", p[0], p[1], p[2]);
            }
            System.out.println("=================================================");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
