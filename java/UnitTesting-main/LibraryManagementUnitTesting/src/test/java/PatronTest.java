import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class PatronTest {
    @Test
    public void testPatronCreation() {
        Patron patron = new Patron("Alice Johnson");
        assertEquals("Alice Johnson", patron.getName());
        assertTrue(patron.getCheckedOutBooks().isEmpty());
    }
    
    @Test
    public void testCheckOutAndReturnBook() {
        Patron patron = new Patron("Bob Brown");
        Book book1 = new Book("The Stand", "Stephen King");
        Book book2 = new Book("The Shining", "Stephen King");
        
        patron.checkOutBook(book1);
        assertEquals(1, patron.getCheckedOutBooks().size());
        assertTrue(patron.hasCheckedOutBook(book1));
        
        patron.checkOutBook(book2);
        assertEquals(2, patron.getCheckedOutBooks().size());
        
        patron.returnBook(book1);
        assertEquals(1, patron.getCheckedOutBooks().size());
        assertFalse(patron.hasCheckedOutBook(book1));
        assertTrue(patron.hasCheckedOutBook(book2));
    }
    
    @Test
    public void testHasCheckedOutBook() {
        Patron patron = new Patron("Charlie Davis");
        Book book = new Book("Pet Sematary", "Stephen King");
        
        assertFalse(patron.hasCheckedOutBook(book));
        patron.checkOutBook(book);
        assertTrue(patron.hasCheckedOutBook(book));
    }
}