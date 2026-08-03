import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    private Library library;
    private Member martin;
    private Book book;

@BeforeEach
void initialize(){
    library = new Library();
    book = new Book("Clean Code", "Robert C. Martin", "123", 1);
    library.addBook(book);

    martin = library.registerMember("Martin");
}


    @Test
    void borrowBookSuccessfully() {
        boolean result = library.borrowBook(martin.getId(), "Clean Code");
        assertTrue(result);
        assertEquals(0, book.getQuantity());
        assertEquals(1, martin.getBorrowedBooks().size());
    }

    @Test
    void borrowAlreadyBorrowedBook() {
        library.borrowBook(martin.getId(), "Clean Code");

        boolean result = library.borrowBook(martin.getId(), "Clean Code");
        assertFalse(result);
        assertEquals(0, book.getQuantity());
        assertEquals(1, martin.getBorrowedBooks().size());
    }

    @Test
    void returnBookSuccessfully() {
        library.borrowBook(martin.getId(), "Clean Code");

        assertTrue(library.returnBook(martin.getId(), "Clean Code"));
        assertEquals(1, book.getQuantity());
        assertEquals(0, martin.getBorrowedBooks().size());
    }

    @Test
    void findNonExistingBook() {
        assertNull(library.findBookByTitle("The Witcher"));
        assertNull(library.findBookByISBN("1231313"));
    }

    @Test
    void duplicateISBN() {
        library.addBook(book);
        assertFalse(library.addBook(book));
    }
}