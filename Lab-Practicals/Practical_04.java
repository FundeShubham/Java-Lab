/*
Problem Statement:
Write a Java program to demonstrate the concept of Runtime Polymorphism
using method overriding.

Create a superclass Employee with data members name and salary and a method
calculateSalary(). Then create two subclasses:
1. PermanentEmployee – calculates salary as Basic Salary + Bonus.
2. ContractEmployee – calculates salary as Hours Worked × Hourly Rate.

Use method overriding so that calculateSalary() behaves differently for
each subclass. Demonstrate runtime polymorphism by creating superclass
references to subclass objects.
*/

class Employee {
    String name;
    double salary;

    // Constructor
    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // Method to calculate salary
    void calculateSalary() {
        System.out.println("Employee Salary: " + salary);
    }
}

// Subclass - Permanent Employee
class PermanentEmployee extends Employee {
    double bonus;

    PermanentEmployee(String name, double salary, double bonus) {
        super(name, salary);
        this.bonus = bonus;
    }

    // Overriding method
    @Override
    void calculateSalary() {
        double totalSalary = salary + bonus;
        System.out.println("Permanent Employee: " + name);
        System.out.println("Total Salary = " + totalSalary);
    }
}

// Subclass - Contract Employee
class ContractEmployee extends Employee {
    int hoursWorked;
    double hourlyRate;

    ContractEmployee(String name, int hoursWorked, double hourlyRate) {
        super(name, 0);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    // Overriding method
    @Override
    void calculateSalary() {
        double totalSalary = hoursWorked * hourlyRate;
        System.out.println("Contract Employee: " + name);
        System.out.println("Total Salary = " + totalSalary);
    }
}

// Main Class
public class Assignment_04 {
    public static void main(String[] args) {

        // Runtime Polymorphism
        Employee emp1 = new PermanentEmployee("Rahul", 50000, 10000);
        emp1.calculateSalary();

        System.out.println();

        Employee emp2 = new ContractEmployee("Amit", 120, 400);
        emp2.calculateSalary();
    }
}
