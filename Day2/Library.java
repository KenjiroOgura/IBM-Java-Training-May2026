
import java.util.ArrayList;

public class Library {
    ArrayList<Book> lib;
    public Library(){
        lib = new ArrayList<>();
        Book book1 = new Book("Project Hail Mary","Andy Weir");
        Book book2 = new Book("A Song of Ice and Fire", "George R.R. Martin");
        Book book3 = new Book("The Lord of The Rings","John Ronald Reuel Tolkien");
        lib.add(book1);
        lib.add(book2);
        lib.add(book3);
    }

    public void addBook(Book b){
        lib.add(b);
    }

    public void showAllBooks(){
        for (Book b : lib) {
            System.out.println(b.getInfo());
            System.out.println();
        }
    }

    public void borrowBook(String title){
        for (Book b : lib) {
            if(b.getTitle().toLowerCase().equals(title.toLowerCase())){
                b.borrowBook();
                
            }
        }
    }

    public void returnBook(String title){
        for (Book b : lib) {
            if(title.toLowerCase().equals(b.getTitle().toLowerCase())){
                b.returnBook();
            }
        }
    }
}
