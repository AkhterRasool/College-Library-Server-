package com.akhter.CollegeLibraryServer.service;

import com.akhter.CollegeLibraryServer.entity.Book;
import com.akhter.CollegeLibraryServer.exception.AuthorNotFoundException;
import com.akhter.CollegeLibraryServer.exception.BookNotFoundException;
import com.akhter.CollegeLibraryServer.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book findBookByTitle(String title) throws BookNotFoundException {
        Optional<Book> book = bookRepository.findByTitle(title);
        return book.orElseThrow(BookNotFoundException::new);
    }

    public List<Book> findByBookByAuthor(String author) throws AuthorNotFoundException {
        List<Book> booksAuthored = bookRepository.findByAuthor(author);
        if (booksAuthored.isEmpty()) {
            throw new AuthorNotFoundException();
        }
        return booksAuthored;
    }
}
