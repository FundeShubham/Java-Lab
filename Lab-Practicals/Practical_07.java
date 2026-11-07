/*Problem Statement : 
Develop a Java program that creates two threads: one prints even numbers and the other prints odd numbers within a given range.
Use appropriate synchronization to ensure outputs do not overlap. Include exception handling for invalid input ranges.
*/


import java.util.Scanner;

class NumberPrinter {

    private int start;
    private int end;

    NumberPrinter(int start, int end) {
        this.start = start;
        this.end = end;
    }

    synchronized void printEven() {
        System.out.println("\nEven Numbers:");

        for (int i = start; i <= end; i++) {
            if (i % 2 == 0) {
                System.out.println("Even Thread: " + i);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Even thread interrupted.");
                }
            }
        }
    }
    synchronized void printOdd() {
        System.out.println("\nOdd Numbers:");

        for (int i = start; i <= end; i++) {
            if (i % 2 != 0) {
                System.out.println("Odd Thread: " + i);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Odd thread interrupted.");
                }
            }
        }
    }
}

class EvenThread extends Thread {

    private NumberPrinter printer;

    EvenThread(NumberPrinter printer) {
        this.printer = printer;
    }

    public void run() {
        printer.printEven();
    }
}

class OddThread extends Thread {

    private NumberPrinter printer;

    OddThread(NumberPrinter printer) {
        this.printer = printer;
    }

    public void run() {
        printer.printOdd();
    }
}

public class Practical7 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter starting number: ");
            int start = sc.nextInt();

            System.out.print("Enter ending number: ");
            int end = sc.nextInt();

            if (start > end) {
                throw new IllegalArgumentException(
                    "Invalid range! Starting number must be less than ending number."
                );
            }

            NumberPrinter printer = new NumberPrinter(start, end);

            EvenThread evenThread = new EvenThread(printer);
            OddThread oddThread = new OddThread(printer);

            evenThread.start();
            oddThread.start();

            evenThread.join();
            oddThread.join();

            System.out.println("\nBoth threads completed successfully.");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());

        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: Please enter integer values only.");

        } catch (InterruptedException e) {
            System.out.println("Error: Thread was interrupted.");

        } finally {
            sc.close();
        }
    }
}
