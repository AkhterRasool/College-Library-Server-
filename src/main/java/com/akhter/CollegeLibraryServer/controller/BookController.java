package com.akhter.CollegeLibraryServer.controller;

import com.akhter.CollegeLibraryServer.entity.Book;
import com.akhter.CollegeLibraryServer.repository.BookLocationRepository;
import com.akhter.CollegeLibraryServer.repository.BookRepository;
import com.akhter.CollegeLibraryServer.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookLocationRepository bookLocationRepository;

    @PostMapping("/insert")
    public void insertBook(@RequestBody Book book) {
        bookService.insertBook(book);
    }

    @DeleteMapping(value = "/delete/{title}")
    public boolean deleteBook(@PathVariable String title) {
        return bookService.deleteBook(title);
    }

    @PutMapping(value = "/update/")
    public boolean updateBook(@RequestBody Book book) {
        return bookService.updateBookDetails(book);
    }


    @GetMapping(value = "/clearAll")
    public void clearAll() {
        bookRepository.deleteAll();
        bookLocationRepository.deleteAll();
    }


}

