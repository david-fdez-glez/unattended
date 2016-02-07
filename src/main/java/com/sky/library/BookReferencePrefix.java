package com.sky.library;

import com.sky.library.util.Constants;

public class BookReferencePrefix extends  Exception {

    public BookReferencePrefix() {

    }

    public BookReferencePrefix(String message) {
        super(message);
        System.out.println("Exception Book Reference must begin with " + Constants.BOOK_REFERENCE_PREFIX);
    }


}
