/*Title: Switch Statement and Functions
  Statement: Write a Menu driven program in Java to impliment Banking System.
  It Should read Customer Name, Account No. , Balance, Rate of Interest, Contact No. and Address.

  System Must Have Following Methods
  1. Create Acc
  2. Deposite
  3. Withdraw
  4. Compute Interest
  5. Display */

// Warning : Please Change the File name to BankSystem.java when running locally (File name and Class Name of Main Fun should be same in java)
// Alternate way : change class name BankSystem to Assignment_02

import java.util.Scanner;

class BankSystem {
    
    String customerName;
    long accNo;
    double balance;
    double rateOfInterest;
    long contactNo;
    String add;

    Scanner sc = new Scanner(System.in);

    void createAccount() {
        System.out.print("Enter Your Name: ");
        customerName = sc.nextLine();
        System.out.print("Enter Account Number: ");
        accNo = sc.nextLong();
        System.out.print("Enter Your Balance: ");
        balance = sc.nextDouble();
        System.out.print("Enter Rate of Interest (in %): ");
        rateOfInterest = sc.nextDouble();
        System.out.print("Enter Your Contact Number: ");
        contactNo = sc.nextLong();
        
        sc.nextLine(); 
        
        System.out.print("Enter Your Residential Address: ");
        add = sc.nextLine();
        System.out.println("\nAccount Created Successfully!!");
    }

    void deposit() {
        System.out.print("Enter Amount to Deposit: ");
        double amount = sc.nextDouble();

        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: " + amount);
            System.out.println("Current Balance: " + balance);
        } else {
            System.out.println("Error: Deposit amount must be positive!");
        }
    }

    void withdraw() {
        System.out.print("Enter Amount to Withdraw: ");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be positive!");
        } else if (amount > balance) {
            System.out.println("Error: Insufficient funds! Your balance is: " + balance);
        } else {
            balance -= amount;
            System.out.println("Successfully withdrawn: " + amount);
            System.out.println("Current Balance: " + balance);
        }
    }

    void computeInterest() {
        System.out.print("Enter time period (in years) to compute interest: ");
        double time = sc.nextDouble();
        
        double interest = (balance * rateOfInterest * time) / 100;
        System.out.println("Estimated Interest earned in " + time + " years: " + interest);
        System.out.println("Total projected balance: " + (balance + interest));
    }

    void display() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Customer Name       : " + customerName);
        System.out.println("Account Number      : " + accNo);
        System.out.println("Current Balance     : " + balance);
        System.out.println("Rate of Interest(%) : " + rateOfInterest);
        System.out.println("Contact Number      : " + contactNo);
        System.out.println("Residential Address : " + add);
        System.out.println("-----------------------\n");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankSystem account = new BankSystem();
        
        System.out.println("=== Welcome to the Banking System ===");
        account.createAccount();
        
        int choice;
        do {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Compute Interest");
            System.out.println("4. Display Account Details");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");
            choice = sc.nextInt();
            
            switch (choice) {
                case 1:
                    account.deposit();
                    break;
                case 2:
                    account.withdraw();
                    break;
                case 3:
                    account.computeInterest();
                    break;
                case 4:
                    account.display();
                    break;
                case 5:
                    System.out.println("Thank you for using the Banking System!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);
        
        sc.close();
    }
}
