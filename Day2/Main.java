package Day2;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        Library lib = new Library();
        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        String choice = "";
        String book = "";
        do { 
            System.out.println("\nWelcome to my Library");
            System.out.println("B - Borrow book");
            System.out.println("R - Return Book");
            System.out.println("S - Show all books");
            System.out.println("Q - Quit");
            try {
                choice = sc.nextLine();
                choice=choice.toLowerCase();
            } catch (InputMismatchException e) {
                System.out.println("Choose only what is displayed");
            }
            switch (choice) {
                case "b":
                    System.out.print("Enter book to borrow:");
                    book=sc.nextLine();
                    lib.borrowBook(book);
                    break;
                case "r":
                    System.out.print("Enter book to return:");
                    book = sc.nextLine();
                    lib.returnBook(book);
                    break;
                case "s":
                    lib.showAllBooks();
                    break;
                case "q":
                    System.out.println("Exiting Program");
                    quit=true;
                    sc.close();
                    break;
                default:
                    System.out.println("Choose only the available choices");
            }
            
            
        } while (!quit);
        

    }
}
