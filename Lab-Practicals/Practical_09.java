/*
  Problem Statement:
  To design and implement a banking system where multiple threads represent
  ATM withdrawals and deposits on shared accounts, using synchronization
  techniques to avoid race conditions and handling exceptions gracefully.
 */

class InsufficientBalanceException extends Exception {

    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class InvalidTransactionException extends Exception {

    public InvalidTransactionException(String message) {
        super(message);
    }
}

class BankAccount {

    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public synchronized void deposit(double amount)
            throws InvalidTransactionException {

        if (amount <= 0) {
            throw new InvalidTransactionException(
                    "Invalid deposit amount!"
            );
        }

        balance += amount;

        System.out.println(
                Thread.currentThread().getName()
                + " deposited: " + amount
                + " | Balance: " + balance
        );
    }

    public synchronized void withdraw(double amount)
            throws InsufficientBalanceException,
                   InvalidTransactionException {

        if (amount <= 0) {
            throw new InvalidTransactionException(
                    "Invalid withdrawal amount!"
            );
        }

        if (amount > balance) {
            throw new InsufficientBalanceException(
                    "Insufficient balance!"
            );
        }

        balance -= amount;

        System.out.println(
                Thread.currentThread().getName()
                + " withdrew: " + amount
                + " | Balance: " + balance
        );
    }

    public double getBalance() {
        return balance;
    }
}

class ATM implements Runnable {

    private BankAccount account;
    private double amount;
    private boolean isDeposit;

    public ATM(BankAccount account, double amount, boolean isDeposit) {
        this.account = account;
        this.amount = amount;
        this.isDeposit = isDeposit;
    }

    @Override
    public void run() {

        try {

            if (isDeposit) {
                account.deposit(amount);
            } else {
                account.withdraw(amount);
            }

        } catch (Exception e) {

            System.out.println(
                    Thread.currentThread().getName()
                    + " Error: " + e.getMessage()
            );
        }
    }
}

public class Assignment_09 {

    public static void main(String[] args) {

        BankAccount account = new BankAccount(1000);

        Thread t1 = new Thread(
                new ATM(account, 500, false),
                "ATM-1"
        );

        Thread t2 = new Thread(
                new ATM(account, 700, false),
                "ATM-2"
        );

        Thread t3 = new Thread(
                new ATM(account, 300, true),
                "ATM-3"
        );

        t1.start();
        t2.start();
        t3.start();

        try {

            t1.join();
            t2.join();
            t3.join();

        } catch (InterruptedException e) {

            System.out.println("Thread interrupted");
        }

        System.out.println(
                "Final Balance: " + account.getBalance()
        );
    }
}
