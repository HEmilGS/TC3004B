import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class BookTest {
    @Test
    public void testBookCreation() {
        Book book = new Book("Dune", "Frank Herbert");
        assertEquals("Dune", book.getTitle());
        assertEquals("Frank Herbert", book.getAuthor());
        assertFalse(book.isCheckedOut());
        assertNull(book.getDueDate());
    }
    
    @Test
    public void testCheckOutAndReturn() {
        Book book = new Book("The Martian", "Andy Weir");
        book.checkOut(14);
        
        assertTrue(book.isCheckedOut());
        assertNotNull(book.getDueDate());
        assertEquals(LocalDate.now().plusDays(14), book.getDueDate());
        
        book.returnBook();
        assertFalse(book.isCheckedOut());
        assertNull(book.getDueDate());
    }
    
    @Test
    public void testSetDueDateWhileCheckedOut() {
        Book book = new Book("Foundation", "Isaac Asimov");
        book.checkOut(7);
        LocalDate newDueDate = LocalDate.now().plusDays(21);
        book.setDueDate(newDueDate);
        
        assertEquals(newDueDate, book.getDueDate());
    }
    
    @Test
    public void testSetDueDateWhenNotCheckedOut() {
        Book book = new Book("Brave New World", "Aldous Huxley");
        assertThrows(IllegalStateException.class, () -> {
            book.setDueDate(LocalDate.now().plusDays(7));
        });
    }
}