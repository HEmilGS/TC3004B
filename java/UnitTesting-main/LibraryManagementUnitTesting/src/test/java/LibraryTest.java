import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class LibraryTest {
    @Test
    public void testCheckOutBookSuccess() {
        Library library = new Library();
        Book book = new Book("The Hobbit", "J.R.R. Tolkien");
        Patron patron = new Patron("John Doe");
        
        library.addBook(book);
        library.addPatron(patron);
        
        assertTrue(library.checkOutBook(patron, book, 14));
        assertTrue(book.isCheckedOut());
    }
    
    @Test
    public void testReturnBookSuccess() {
        Library library = new Library();
        Book book = new Book("1984", "George Orwell");
        Patron patron = new Patron("Jane Smith");
        
        library.addBook(book);
        library.addPatron(patron);
        library.checkOutBook(patron, book, 14);
        
        assertTrue(library.returnBook(patron));
        assertFalse(book.isCheckedOut());
    }
    
    @Test
    public void testCheckOutNonexistentBook() {
        Library library = new Library();
        Book book = new Book("Nonexistent", "Anonymous");
        Patron patron = new Patron("John Doe");
        
        library.addPatron(patron);
        assertFalse(library.checkOutBook(patron, book, 14));
    }
    
    @Test
    public void testReturnBookNotCheckedOut() {
        Library library = new Library();
        Patron patron = new Patron("Jane Smith");
        library.addPatron(patron);
        
        assertFalse(library.returnBook(patron));
    }
}