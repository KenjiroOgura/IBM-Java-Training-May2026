public class Book {
    private String title;
    private String author;
    private boolean available;

    public Book() {
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public void borrowBook() {
        if (available) {
            available = false;
            System.out.println(title + " has been borrowed");
        } else {
            System.out.println("Book is already borrowed.");
        }
    }

    public void returnBook() {
        available = true;
        System.out.println(title + " has been returned");
    }

    public void getInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Availability: " + available);
    }

    public String getTitle() {
        return title;
    }
}