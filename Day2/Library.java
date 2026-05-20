import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book b) {
        books.add(b);
    }

    public void showAllBooks() {
        for (Book b : books) {
            b.getInfo();
        }
    }

    public void borrowBook(String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                b.borrowBook();
            }else {
                System.out.println("Book not found");

            }
        }
    }

    public void returnBook(String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                b.returnBook();
            }else {
            	System.out.println("Book not found");
            }
        }
        
    }
}
