package com.sky.library;

import com.sky.library.util.Constants;

public class BookServiceImpl implements BookService {

    private BookRepositoryStub bookRepositoryStub;

    public BookServiceImpl() {
        bookRepositoryStub = new BookRepositoryStub();
    }

    @Override
    public Book retrieveBook(String bookReference) throws BookNotFoundException, BookReferencePrefix {

        if(bookReference.startsWith(Constants.BOOK_REFERENCE_PREFIX)) {
            Book book = bookRepositoryStub.retrieveBook(bookReference);

            if(book != null) {
                return book;
            } else {
               throw new BookNotFoundException("BookNotFoundException");
            }
        }   else {
            throw  new BookReferencePrefix("Exception Book Reference must begin with " +Constants.BOOK_REFERENCE_PREFIX);
        }
    }

    @Override
    public String getBookSummary(String bookReference) throws BookNotFoundException , BookReferencePrefix {
        String result = "";

        if(bookReference.startsWith(Constants.BOOK_REFERENCE_PREFIX)) {
            Book book = bookRepositoryStub.retrieveBook(bookReference);

            if(book != null) {
                result = getSummary(book);
            } else {
                throw new BookNotFoundException("BookNotFoundException");
            }
        } else {
            throw  new BookReferencePrefix("Exception Book Reference must begin with " +Constants.BOOK_REFERENCE_PREFIX);
        }
        return result;
    }


    private String getSummary(Book book) {

        String[] splitString = book.getReview().split("[\\W]");
        String summary = "";
        String endSummary = "";
        int index = splitString.length;
        if(index > 9) {
            index = 9;
            endSummary = "...";
        }
        for(int i=0; i < index; i++) {
            summary +=   " " + splitString[i];
        }
        summary += endSummary;
        return "["+ book.getReference() +"] " + book.getTitle() + " -"  + summary;
    }
}
