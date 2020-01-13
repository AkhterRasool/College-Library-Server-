package com.akhter.CollegeLibraryServer.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, value = HttpStatus.NOT_FOUND)
public class BookNotFoundException extends Exception {
    private static final String BOOK_NOT_EXIST = "This book does not exist.";

    public BookNotFoundException() {
        super(BOOK_NOT_EXIST);
    }
}
