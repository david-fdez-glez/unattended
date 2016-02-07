package com.sky.library;

import org.junit.*;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;


/**
 * Unit test for simple App.
 */
public class AppTest  {

    BookServiceImpl bookService;
    Book book;

    private static final String BOOK_REFERENCE_PREFIX = "BOOK-";

    private static final String THE_GRUFFALO_REFERENCE =  "GRUFF472";
    private static final String THE_WIND_IN_THE_WILLOWS_REFERENCE =  "WILL987";
    private static final String NOT_EXISTING_REFERENCE =   "999";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void before() {
        bookService = new BookServiceImpl();
    }

    @Test
    public void invalidTextRetrieveBook() throws BookNotFoundException, BookReferencePrefix{
        System.out.println("\nINVALID-TEXT retrieveBook " + THE_GRUFFALO_REFERENCE);
        exception.expect(BookReferencePrefix.class);
        book =  bookService.retrieveBook(THE_GRUFFALO_REFERENCE);
    }

    @Test
    public void bookNotFoundRetrieveBook() throws  BookNotFoundException, BookReferencePrefix{
        System.out.println("\nBookNotFoundException retrieveBook " + BOOK_REFERENCE_PREFIX + NOT_EXISTING_REFERENCE);
        exception.expect(BookNotFoundException.class);
        book = bookService.retrieveBook(BOOK_REFERENCE_PREFIX + NOT_EXISTING_REFERENCE)  ;
    }

    @Test
    public void retrieveBook() throws BookNotFoundException, BookReferencePrefix {
        System.out.println("\nRetrieveBook " + BOOK_REFERENCE_PREFIX +THE_GRUFFALO_REFERENCE);
        book = bookService.retrieveBook(BOOK_REFERENCE_PREFIX + THE_GRUFFALO_REFERENCE) ;
        Book bookExpected = new Book(BOOK_REFERENCE_PREFIX +THE_GRUFFALO_REFERENCE, "The Gruffalo", "A mouse taking a walk in the woods");
        assertEquals(bookExpected,book);
        System.out.println("Result " + book.toString());

    }

    @Test
    public void invalidTextGetBookSummary() throws BookNotFoundException, BookReferencePrefix {
        System.out.println("\nINVALID-TEXT getBookSummary " + THE_GRUFFALO_REFERENCE);
        exception.expect(BookReferencePrefix.class);
        String result = bookService.getBookSummary(THE_GRUFFALO_REFERENCE);
    }

    @Test
    public void bookNotFoundGetBookSummary() throws BookNotFoundException, BookReferencePrefix {
        System.out.println("\nBookNotFoundException getBookSummary " + BOOK_REFERENCE_PREFIX + NOT_EXISTING_REFERENCE);
        exception.expect(BookNotFoundException.class);
        String result = bookService.getBookSummary(BOOK_REFERENCE_PREFIX + NOT_EXISTING_REFERENCE);
    }

    @Test
    public void getBookSummaryLess9Words() throws BookNotFoundException, BookReferencePrefix {
        System.out.println("\ngetBookSummaryA " + BOOK_REFERENCE_PREFIX +THE_GRUFFALO_REFERENCE);
        String result = bookService.getBookSummary(BOOK_REFERENCE_PREFIX + THE_GRUFFALO_REFERENCE);
        assertEquals("[BOOK-GRUFF472] The Gruffalo - A mouse taking a walk in the woods", result);
        System.out.println(result);
    }

    @Test
    public void getBookSummaryMore9Words() throws BookNotFoundException, BookReferencePrefix {
        System.out.println("\ngetBookSummaryA " + BOOK_REFERENCE_PREFIX +THE_WIND_IN_THE_WILLOWS_REFERENCE);
        String result = bookService.getBookSummary(BOOK_REFERENCE_PREFIX + THE_WIND_IN_THE_WILLOWS_REFERENCE);
        assertEquals("[BOOK-WILL987] The Wind In The Willows - With the arrival of spring and fine weather outside...", result);
        System.out.println(result);

    }

}
