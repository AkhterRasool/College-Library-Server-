package com.akhter.CollegeLibraryServer.controller;

import com.akhter.CollegeLibraryServer.entity.Book;
import com.akhter.CollegeLibraryServer.entity.Student;
import com.akhter.CollegeLibraryServer.exception.AuthorNotFoundException;
import com.akhter.CollegeLibraryServer.exception.BookNotFoundException;
import com.akhter.CollegeLibraryServer.service.BookService;
import com.akhter.CollegeLibraryServer.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private BookService bookService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/title/{title}")
    public Book getBookByTitle(@PathVariable String title) throws BookNotFoundException {
        return bookService.findBookByTitle(title);
    }

    @GetMapping("/author/{author}")
    public List<Book> getBookByAuthor(@PathVariable String author) throws AuthorNotFoundException {
        return bookService.findByBookByAuthor(author);
    }

    @GetMapping("/issuesandfines/{rollNumber}")
    public Student getStudentDetails(@PathVariable String rollNumber) {
        return studentService.findByRollNumber(rollNumber);
    }
}

