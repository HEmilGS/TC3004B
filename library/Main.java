import java.util.ArrayList;
public class Book{

    public String title = " ";
    public String author = " ";
    public int ISBN = 0;
    public int NCopies = 0;
    public boolean NAvailable = true;

    public Book(String title, String author, int ISBN, int NCopies){
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.NCopies = NCopies;
        this.NAvailable = true;
    }

}


public class Library{
    public ArrayList<Patrons> patrons;
    public ArrayList<Book> books;



    public Library(){
        this.books = new ArrayList<>();
        this.patrons = new ArrayList<>();
    }

}

public class Patrons{

    public String name = " ";
    public int ID = 0;
    public String contactDetails = " ";
    public Library library;


    public Patrons(String name, int ID, String contactDetails, Library library){
        this.name = name;
        this.ID = ID;
        this.contactDetails = contactDetails;
        this.library = library;
    }

    public void borrowBook(Book book){

        if(book.NAvailable && book.NCopies > 0){
            book.NAvailable = false;
            book.NCopies -= 1;
            System.out.println("Book has been borrowed");
        }else{
            System.out.println("Book is not available");
        }

    }



    public void returnBook(Book book){

        if(!book.NAvailable){
            book.NAvailable = true;
            book.NCopies += 1;
            System.out.println("Book has been returned");
        }else{
            System.out.println("Book is already available");
        }

    }

    public void seeBooks(){

        for(Book book : library.books){
            System.out.println(book.title);
        }
    }



}

public class Librarians{
    private Library library;

    public Librarians(Library library){
        this.library = library;
    }

    public void addBook(String title, String author, int ISBN, int NCopies){

        Book newBook = new Book(title, author, ISBN, NCopies);
        library.books.add(newBook); 

    }

    public void removeBook(String title){

        library.books.removeIf(book -> book.title.equals(title));

    }

    public void addPatron(String name, int ID, String contactDetails, Library library){

        Patrons newPatron = new Patrons(name, ID, contactDetails, library);
        library.patrons.add(newPatron);

    }

    public void seePatrons(){

        for(Patrons patron : library.patrons){
            System.out.println(patron.name);
        }

    }

    public void seeBooks(){

        for(Book book : library.books){
            System.out.println(book.title);
        }

    }

}

public class Main {
    public static void main(String[] args){

        System.out.println("funcionamiento pendiente, las clases fueron creadas en su mayoría");



    }
}