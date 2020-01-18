package com.akhter.CollegeLibraryServer.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AuthorNotFoundException extends Exception {

    private static final String AUTHOR_NOT_FOUND = "Author not found.";

    public AuthorNotFoundException() {
        super(AUTHOR_NOT_FOUND);
    }
}
