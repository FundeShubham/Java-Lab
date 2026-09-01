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

// Base class 
abstract class Transport { 
    double distance; 

    Transport(double distance) { 
        this.distance = distance; 
    } 

    abstract double calculateFare(); 
} 

// Bus class 
class Bus extends Transport { 
    Bus(double distance) { 
        super(distance); 
    } 

    @Override 
    double calculateFare() { 
        return distance * 5; // Rs.5 per km 
    } 
} 

// Train class 
class Train extends Transport { 
    String travelClass; 

    Train(double distance, String travelClass) { 
        super(distance); 
        this.travelClass = travelClass; 
    } 

    @Override 
    double calculateFare() { 
        if (travelClass.equalsIgnoreCase("Sleeper")) 
            return distance * 3; 
        else 
            return distance * 6; 
    } 
} 

// Flight class 
class Flight extends Transport { 
    String travelClass; 

    Flight(double distance, String travelClass) { 
        super(distance); 
        this.travelClass = travelClass; 
    } 

    @Override 
    double calculateFare() { 
        if (travelClass.equalsIgnoreCase("Economy")) 
            return distance * 10; 
        else 
            return distance * 20; 
    } 
} 

// Main class 
public class Assignment_05 { 
    public static void main(String[] args) { 
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of bookings: ");
        int n = scanner.nextInt();
        Transport[] bookings = new Transport[n]; 

        for (int i = 0; i < n; i++) {
            System.out.println("\nBooking " + (i + 1) + ":");
            System.out.println("1. Bus");
            System.out.println("2. Train");
            System.out.println("3. Flight");
            System.out.print("Select transport type (1-3): ");
            int type = scanner.nextInt();
            
            System.out.print("Enter distance (in km): ");
            double distance = scanner.nextDouble();
            scanner.nextLine(); // consume newline
            
            if (type == 1) {
                bookings[i] = new Bus(distance);
            } else if (type == 2) {
                System.out.print("Enter travel class (Sleeper/AC): ");
                String travelClass = scanner.nextLine();
                bookings[i] = new Train(distance, travelClass);
            } else if (type == 3) {
                System.out.print("Enter travel class (Economy/Business): ");
                String travelClass = scanner.nextLine();
                bookings[i] = new Flight(distance, travelClass);
            } else {
                System.out.println("Invalid type. Defaulting to Bus.");
                bookings[i] = new Bus(distance);
            }
        }

        System.out.println("\n--- Fare Details ---");
        for (Transport t : bookings) { 
            System.out.println(t.getClass().getSimpleName() + " Fare: Rs. " + t.calculateFare()); 
        } 
        
        scanner.close();
    } 
}
}
