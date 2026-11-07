/*
 * Problem Statement:
 * Demonstrate Runtime Polymorphism using Method Overriding in Java.
 *
 * Design a base class `StaffMember` containing common attributes such as
 * name and department. Derive specialized subclasses such as `Professor`,
 * `Administrator`, and `Librarian`, each representing a specific staff role.
 * Override a common method in each subclass to define and display its unique
 * responsibilities, thereby illustrating the concept of runtime polymorphism.
 */

class StaffMember {
    
    protected String name;
    protected String department;

    public StaffMember(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public void duties() {
        System.out.println(name + " (Dept: " + department + ") performs general staff duties.");
    }

    public void displayRole() {
        System.out.println("Role: General Staff Member");
    }
}

class Professor extends StaffMember {

    private String subject;

    public Professor(String name, String department, String subject) {
        super(name, department);
        this.subject = subject;
    }

    @Override
    public void duties() {
        System.out.println(name + " (Dept: " + department + ") teaches " + subject
                + ", conducts research, and evaluates students.");
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Professor");
    }
}

class Administrator extends StaffMember {

    private String position;

    public Administrator(String name, String department, String position) {
        super(name, department);
        this.position = position;
    }

    @Override
    public void duties() {
        System.out.println(name + " (Dept: " + department + ") as " + position
                + " manages staff, schedules, and daily operations.");
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Administrator");
    }
}
class Librarian extends StaffMember {

    private int booksManaged;

    public Librarian(String name, String department, int booksManaged) {
        super(name, department);
        this.booksManaged = booksManaged;
    }

    @Override
    public void duties() {
        System.out.println(name + " (Dept: " + department + ") manages " + booksManaged
                + " books, assists readers, and maintains library records.");
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Librarian");
    }
}

public class Assignment_05 {

    public static void main(String[] args) {
      
        StaffMember staff;
      
        staff = new Professor("Mrs. Gayatri Bendale", "Computer Science", "Java Programming");
        staff.displayRole();
        staff.duties();
        System.out.println();

        staff = new Administrator("Mrs. Swati Bhavsar", "Computer Science", "Head of Operations");
        staff.displayRole();
        staff.duties();
        System.out.println();

        staff = new Librarian("Mr. Atul", "Library", 5000);
        staff.displayRole();
        staff.duties();
    }
}
