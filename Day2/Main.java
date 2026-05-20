public class Main {
    public static void main(String[] args) {

        Library lib = new Library();

        lib.addBook(new Book("Project Hail Mary", "Author 1"));
        lib.addBook(new Book("A Song of Ice and Fire", "Author 2"));
        lib.addBook(new Book("The Odyssey", "Author 3"));
        
        lib.borrowBook("Book 1");
        lib.returnBook("Book 1");
        lib.showAllBooks();

        
    }
}