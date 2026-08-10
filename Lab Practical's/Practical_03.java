/* PS : Create class Library and Overload addBook function 4 times to show Name, ID, Author and Published Date */
  

import java.util.Scanner;

class Library {

    String name;
    int id;
    int date;
    String author;

    Library(String name, int id, int date, String author) {
        this.name = name;
        this.id = id;
        this.date = date;
        this.author = author;
    }

    void display(String name) {
        System.out.println("-----Book Name-----");
        System.out.println("Book Name : " + name);
    }

    void display(String name, int id) {
        System.out.println("\n-----Book Name and ID-----");
        System.out.println("Book Name : " + name);
        System.out.println("Book ID   : " + id);
    }

    void display(String name, int id, int date) {
        System.out.println("\n-----Book Name, ID and Date-----");
        System.out.println("Book Name : " + name);
        System.out.println("Book ID   : " + id);
        System.out.println("Date      : " + date);
    }

    void display(String name, int id, int date, String author) {
        System.out.println("\n-----Book Name, ID, Date and Author-----");
        System.out.println("Book Name : " + name);
        System.out.println("Book ID   : " + id);
        System.out.println("Date      : " + date);
        System.out.println("Author    : " + author);
    }
}

class Assignment_03 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Book Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();

        System.out.print("Enter Date: ");
        int date = sc.nextInt();
        sc.nextLine(); // Consume the newline character

        System.out.print("Enter Author Name: ");
        String author = sc.nextLine();

        Library book = new Library(name, id, date, author);

        System.out.println("\nDisplaying Book Information:");
        book.display(name);
        book.display(name, id);
        book.display(name, id, date);
        book.display(name, id, date, author);

        sc.close();
    }
}
