package com.akhter.CollegeLibraryServer.controller;

import com.akhter.CollegeLibraryServer.exception.AuthorNotFoundException;
import com.akhter.CollegeLibraryServer.exception.BookNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = { BookNotFoundException.class })
    public String bookNotFound(Exception ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(value = { AuthorNotFoundException.class })
    public String authorNotFound(Exception ex) {
        return ex.getMessage();
    }
}
