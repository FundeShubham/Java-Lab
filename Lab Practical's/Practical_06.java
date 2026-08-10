/*
Problem Statement:
Create a Java program that demonstrates an Array of Objects and Hierarchical Inheritance
using a base abstract class Transport and three subclasses: Bus, Train, and Flight.
The base class should declare an abstract method calculateFare() which each subclass
overrides to compute the fare according to its type and distance. Each booking stores
distance, a category/type (e.g., Bus: AC/NON-AC, Train: General/Sleeper/AC, Flight: Economy/Business/First),
and the number of passengers booked. The program should accept multiple bookings from the user,
store them in an array of Transport references (showing polymorphism), compute fares per passenger,
and display per-booking and overall totals.
*/

import java.util.Scanner;
import java.util.Locale;

abstract class Transport {
    protected double distance;       
    protected String type;           
    protected int numPassengers;     

    public Transport(double distance, String type, int numPassengers) {
        this.distance = distance;
        this.type = type;
        this.numPassengers = numPassengers;
    }

    public abstract double calculateFarePerPassenger();
  
    public void showDetails(int index) {
        double perPassenger = calculateFarePerPassenger();
        double total = perPassenger * numPassengers;
        System.out.println("Booking #" + (index + 1) + " -> " + this.getClass().getSimpleName());
        System.out.printf(Locale.US, "  Type/Class         : %s%n", type);
        System.out.printf(Locale.US, "  Distance (km)      : %.2f%n", distance);
        System.out.printf(Locale.US, "  No. of Passengers  : %d%n", numPassengers);
        System.out.printf(Locale.US, "  Fare per Passenger : %.2f%n", perPassenger);
        System.out.printf(Locale.US, "  Total Fare         : %.2f%n", total);
        System.out.println("---------------------------------------------");
    }
}

class Bus extends Transport {
    // Rates per km
    private static final double NON_AC_RATE = 5.0;
    private static final double AC_RATE = 8.0;

    public Bus(double distance, String type, int numPassengers) {
        super(distance, type, numPassengers);
    }

    @Override
    public double calculateFarePerPassenger() {
        double rate = NON_AC_RATE;
        if (type.equalsIgnoreCase("AC")) rate = AC_RATE;
        return distance * rate;
    }
}

class Train extends Transport {
    
    private static final double GENERAL_RATE = 3.0;
    private static final double SLEEPER_RATE = 5.0;
    private static final double AC_RATE = 7.0;

    public Train(double distance, String type, int numPassengers) {
        super(distance, type, numPassengers);
    }

    @Override
    public double calculateFarePerPassenger() {
        String t = type.toLowerCase();
        if (t.contains("ac")) {
            return distance * AC_RATE;
        } else if (t.contains("sleeper")) {
            return distance * SLEEPER_RATE;
        } else {
            return distance * GENERAL_RATE; 
        }
    }
}

class Flight extends Transport {
    // Rates per km for flight classes
    private static final double ECONOMY_RATE = 40.0;
    private static final double BUSINESS_RATE = 80.0;
    private static final double FIRST_RATE = 120.0;

    public Flight(double distance, String type, int numPassengers) {
        super(distance, type, numPassengers);
    }

    @Override
    public double calculateFarePerPassenger() {
        String t = type.toLowerCase();
        if (t.contains("business")) {
            return distance * BUSINESS_RATE;
        } else if (t.contains("first") || t.contains("1st")) {
            return distance * FIRST_RATE;
        } else {
            return distance * ECONOMY_RATE; 
        }
    }
}

public class Assignment_06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US);

        System.out.println("=== Transport Booking System (Array of Objects & Hierarchical Inheritance) ===");
        int n;
        while (true) {
            System.out.print("How many bookings would you like to enter? ");
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                sc.nextLine(); 
                if (n > 0) break;
            } else {
                sc.nextLine(); 
            }
            System.out.println("Please enter a positive integer for number of bookings.");
        }

        Transport[] bookings = new Transport[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for booking #" + (i + 1));
            int choice;
            while (true) {
                System.out.println("Select Transport Type: 1) Bus  2) Train  3) Flight");
                System.out.print("Choice (1-3): ");
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    sc.nextLine();
                    if (choice >= 1 && choice <= 3) break;
                } else {
                    sc.nextLine();
                }
                System.out.println("Invalid choice. Please enter 1, 2 or 3.");
            }

            double distance;
            while (true) {
                System.out.print("Enter distance in kilometers: ");
                if (sc.hasNextDouble()) {
                    distance = sc.nextDouble();
                    sc.nextLine();
                    if (distance > 0) break;
                } else {
                    sc.nextLine();
                }
                System.out.println("Please enter a positive number for distance.");
            }

            String type = "";
            if (choice == 1) {
                
                while (true) {
                    System.out.print("Enter Bus Type (AC / Non-AC): ");
                    type = sc.nextLine().trim();
                    if (type.equalsIgnoreCase("AC") || type.equalsIgnoreCase("Non-AC") || type.equalsIgnoreCase("Non AC") || type.equalsIgnoreCase("NON-AC")) {
                        break;
                    }
                    System.out.println("Invalid bus type. Try 'AC' or 'Non-AC'.");
                }
            } else if (choice == 2) {
                
                while (true) {
                    System.out.print("Enter Train Class (General / Sleeper / AC): ");
                    type = sc.nextLine().trim();
                    String tl = type.toLowerCase();
                    if (tl.contains("general") || tl.contains("sleeper") || tl.contains("ac")) {
                        break;
                    }
                    System.out.println("Invalid train class. Try 'General', 'Sleeper', or 'AC'.");
                }
            } else {
                
                while (true) {
                    System.out.print("Enter Flight Class (Economy / Business / First or 1st): ");
                    type = sc.nextLine().trim();
                    String tl = type.toLowerCase();
                    if (tl.contains("economy") || tl.contains("business") || tl.contains("first") || tl.contains("1st")) {
                        break;
                    }
                    System.out.println("Invalid flight class. Try 'Economy', 'Business', or 'First'.");
                }
            }

            int passengers;
            while (true) {
                System.out.print("Enter number of passengers for this booking: ");
                if (sc.hasNextInt()) {
                    passengers = sc.nextInt();
                    sc.nextLine();
                    if (passengers > 0) break;
                } else {
                    sc.nextLine();
                }
                System.out.println("Please enter a positive integer for passengers.");
            }

            switch (choice) {
                case 1:
                    bookings[i] = new Bus(distance, type, passengers);
                    break;
                case 2:
                    bookings[i] = new Train(distance, type, passengers);
                    break;
                default:
                    bookings[i] = new Flight(distance, type, passengers);
                    break;
            }
            System.out.println("Booking recorded.");
        }

        System.out.println("\n\n=== Booking Summary ===");
        double grandTotal = 0.0;
        for (int i = 0; i < bookings.length; i++) {
            bookings[i].showDetails(i);
            grandTotal += bookings[i].calculateFarePerPassenger() * bookings[i].numPassengers;
        }
        System.out.printf(Locale.US, "Grand Total for all bookings: %.2f%n", grandTotal);
        sc.close();
    }
}
