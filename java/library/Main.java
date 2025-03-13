import java.util.ArrayList;
import java.util.Scanner;

class Book {
    public String title;
    public String author;
    public int ISBN;
    public int NAvailableCopies;

    public Book(String title, String author, int ISBN, int NAvailableCopies) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.NAvailableCopies = NAvailableCopies;
    }

    public void bookUpdate(String title, String author, int ISBN, int NAvailableCopies) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.NAvailableCopies = NAvailableCopies;
    }
}

class Library {
    public ArrayList<Patron> patrons;
    public ArrayList<Book> books;

    public Library() {
        patrons = new ArrayList<>();
        books = new ArrayList<>();
    }
}

class Patron {
    public String name;
    public int ID;
    public String contactDetails;
    public ArrayList<Book> borrowedBooks;

    public Patron(String name, int ID, String contactDetails) {
        this.name = name;
        this.ID = ID;
        this.contactDetails = contactDetails;
        this.borrowedBooks = new ArrayList<>();
    }

    public void patronUpdate(String name, int ID, String contactDetails) {
        this.name = name;
        this.ID = ID;
        this.contactDetails = contactDetails;
    }
}

class Librarian {
    public Library library;

    public Librarian(Library library) {
        this.library = library;
    }

    public void addBook(String title, String author, int ISBN, int NAvailableCopies) {
        Book newBook = new Book(title, author, ISBN, NAvailableCopies);
        library.books.add(newBook);
        System.out.println("Book has been added.");
    }

    public void removeBook(String title) {
        library.books.removeIf(book -> book.title.equalsIgnoreCase(title));
    }

    public void addPatron(String name, int ID, String contactDetails) {
        Patron newPatron = new Patron(name, ID, contactDetails);
        library.patrons.add(newPatron);
    }

    public void seePatrons() {
        for (Patron patron : library.patrons) {
            System.out.println("Name: " + patron.name + ", ID: " + patron.ID + ", Contact details: " + patron.contactDetails);
        }
    }

    public void seeBooks() {
        System.out.println("Books in the library:");
        for (Book book : library.books) {
        System.out.println("Title: " + book.title + " | Author: " + book.author + " | Copies: " + book.NAvailableCopies);
        }
    }

    public void borrowBook(Book book, Patron patron) {
        if (book.NAvailableCopies > 0) {
            book.NAvailableCopies--;
            patron.borrowedBooks.add(book);
            System.out.println("Book has been borrowed.");
        } else {
            System.out.println("Book is not available.");
        }
    }

    public void returnBook(Book book, Patron patron) {
        patron.borrowedBooks.remove(book);
        book.NAvailableCopies++;

        System.out.println("Book has been returned.");
    }
}

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Librarian librarian = new Librarian(library);
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nWelcome to the library");
            System.out.println("Choose an option: ");
            System.out.println("1. Add a book");
            System.out.println("2. Remove a book");
            System.out.println("3. See books");
            System.out.println("4. Add a patron");
            System.out.println("5. See patrons");
            System.out.println("6. Edit a patron");
            System.out.println("7. Edit a book");
            System.out.println("8. Borrow a book");
            System.out.println("9. Return a book");
            System.out.println("10. Books per patron");
            System.out.println("11. Search for a book");
            System.out.println("12. Search for a patron");
            System.out.println("13. Exit");
            System.out.print("Enter choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            switch (choice) {
                case 1:
                    System.out.print("Enter the title of the book: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter the author of the book: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter the ISBN of the book: ");
                    int ISBN = scanner.nextInt();
                    System.out.print("Enter the number of copies: ");
                    int NAvailableCopies = scanner.nextInt();
                    librarian.addBook(title, author, ISBN, NAvailableCopies);
                    break;

                case 2:
                    System.out.print("Enter the title of the book to remove: ");
                    String titleToRemove = scanner.nextLine();
                    librarian.removeBook(titleToRemove);
                    break;

                case 3:
                    librarian.seeBooks();
                    break;

                case 4:
                    System.out.print("Enter the name of the patron: ");
                    String name = scanner.nextLine();
                    int ID = library.patrons.size() + 1;
                    System.out.print("The ID of the patron is: " + ID);
                    scanner.nextLine();
                    System.out.print("Enter contact details: ");
                    String contactDetails = scanner.nextLine();
                    librarian.addPatron(name, ID, contactDetails);
                    break;

                case 5:
                    librarian.seePatrons();
                    break;

                case 6:
                    System.out.print("Enter the name of the patron to edit: ");
                    String nameToEdit = scanner.nextLine();
                    for (Patron patron : library.patrons) {
                        if (patron.name.equalsIgnoreCase(nameToEdit)) {
                            System.out.print("New name: ");
                            patron.name = scanner.nextLine();
                            scanner.nextLine();
                            System.out.print("New contact details: ");
                            patron.contactDetails = scanner.nextLine();
                        }
                    }
                    break;

                case 7:
                    System.out.print("Enter the title of the book to edit: ");
                    String titleToEdit = scanner.nextLine();
                    for (Book book : library.books) {
                        if (book.title.equalsIgnoreCase(titleToEdit)) {
                            System.out.print("New title: ");
                            book.title = scanner.nextLine();
                            System.out.print("New author: ");
                            book.author = scanner.nextLine();
                            System.out.print("New ISBN: ");
                            book.ISBN = scanner.nextInt();
                            System.out.print("New copies: ");
                            book.NAvailableCopies = scanner.nextInt();
                        }
                    }
                    break;
                case 8:
                    System.out.print("Enter the name of the book you want to borrow: ");
                    String bookToBorrow = scanner.nextLine();
                    System.out.print("Enter the name of the patron: ");
                    String patronName = scanner.nextLine();
    
                    for(Book book : library.books){
                        if(book.title.equals(bookToBorrow)){
                            for(Patron patron : library.patrons){
                                if(patron.name.equals(patronName)){
                                    librarian.borrowBook(book, patron);
                                }
                            }
                        }
                    }
                    break;
    
                case 9:
                    System.out.print("Enter the name of the book you want to return: ");
                    String bookToReturn = scanner.nextLine();
                    System.out.print("Enter the name of the patron: ");
                    String patronNameReturn = scanner.nextLine();

                    for(Book book : library.books){
                        if(book.title.equals(bookToReturn)){
                            for(Patron patron : library.patrons){
                                if(patron.name.equals(patronNameReturn)){
                                    librarian.returnBook(book, patron);
                                }
                            }
                        }
                    }
                    break;
    
                case 10:  
                    System.out.println("Enter the name of the patron: ");
                    String nameOfPatron = scanner.nextLine();
                    for(Patron patron : library.patrons){
                        if(patron.name.equals(nameOfPatron)){
                            if(patron.borrowedBooks.size() == 0){
                                System.out.println("Patron: " + nameOfPatron + " | has no borrowed books");
                            }else{
                                for(Book book : patron.borrowedBooks){
                                    System.out.println("Patron: " + nameOfPatron + " | has : " + book.title);
                                }
                            }
                        }
                    }
                    break;
    
                case 11:
                    System.out.println("Enter the title, author or ISBN of the book you want to search for: ");
                    String search = scanner.nextLine();
                    for(Book book : library.books){
                        if(book.title.equals(search) || book.author.equals(search) || book.ISBN == Integer.parseInt(search)){
                            System.out.println("Title: " + book.title + " | Author: " + book.author + " | Available copies: " + book.NAvailableCopies);
                        }
                    }
                    break;
                
    
                case 12:
                    System.out.println("Enter the ID of the patron you want to search for: ");
                    int IDToSearch = scanner.nextInt();
                    for(Patron patron : library.patrons){
                        if(patron.ID == IDToSearch){
                            System.out.println("Name: " + patron.name + " | ID: " + patron.ID + " | Contact details: " + patron.contactDetails);
                            for(Book book : patron.borrowedBooks){
                                System.out.print("Borrowed books: " + book.title + " | ");
                            }
                        }
                    }
    
                    break;
    
    
                case 13:
                    System.out.println("Goodbye");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
    
    
    
        }
    }
}