public class Book{
    private String title;
    private String author;
    private boolean available;

    public Book(){
    }
    public Book(String title,String author){
        this.title=title;
        this.author=author;
        this.available=true;
    }

    public void borrowBook(){
        if(available){
            available=false;
            System.out.println("Book has been borrowed\n");

        }else{
            System.out.println("Book is already borrowed.");
        }
    }
    public void returnBook(){
        if(!available){
            available=true;
            System.out.println("Book has been returned.");
        }else{
            System.out.println("Book is already returned.");
        }
     
    }

    public String getInfo(){
        return "Title: "+title+"\nAuthor: "+author+"\nAvailability: "+available;
        
    }

    public String getTitle(){
        return title;
    }
}