/*
 * Assignment No. 1
 *
 * Problem Statement:
 * Design and implement a Java program to manage student academic data
 * using Object-Oriented Programming (OOP) principles.
 *
 * The application should:
 * 1. Store the student's name (String).
 * 2. Store the student's roll number (int).
 * 3. Store marks of multiple subjects using an integer array (int[]).
 * 4. Apply string manipulation methods to format the student's name.
 * 5. Calculate and return the total marks.
 * 6. Calculate and return the average marks.
 */

import java.util.Scanner;

class Student {

    String name;
    int rollNo;
    int[] marks;

    Student(String name, int rollNo, int[] marks) {
        this.name = name;
        this.rollNo = rollNo;
        this.marks = marks;
    }

    String formatName() {
        return name.toUpperCase();
    }

    int calculateTotal() {
        int total = 0;

        for (int mark : marks) {
            total += mark;
        }

        return total;
    }

    double calculateAverage() {
        return (double) calculateTotal() / marks.length;
    }

    void display() {
        System.out.println("Student Name : " + formatName());
        System.out.println("Roll No      : " + rollNo);

        System.out.print("Marks        : ");
        for (int mark : marks) {
            System.out.print(mark + " ");
        }

        System.out.println();
        System.out.println("Total Marks  : " + calculateTotal());
        System.out.println("Average      : " + calculateAverage());
    }
}

public class Assignment_01 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Roll No: ");
        int rollNo = sc.nextInt();

        System.out.print("Enter Number of Subjects: ");
        int n = sc.nextInt();

        int[] marks = new int[n];

        System.out.println("Enter Marks:");

        for (int i = 0; i < n; i++) {
            marks[i] = sc.nextInt();
        }

        Student s = new Student(name, rollNo, marks);

        s.display();
    }
}
