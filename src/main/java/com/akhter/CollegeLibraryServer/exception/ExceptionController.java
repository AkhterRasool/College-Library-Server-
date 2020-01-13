package com.akhter.CollegeLibraryServer.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = { BookNotFoundException.class })
    public String bookNotFound(Exception ex) {
        return ex.getMessage();
    }
}
