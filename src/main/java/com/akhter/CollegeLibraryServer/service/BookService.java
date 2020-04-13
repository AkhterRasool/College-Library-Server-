package com.akhter.CollegeLibraryServer.service;

import com.akhter.CollegeLibraryServer.entity.Author;
import com.akhter.CollegeLibraryServer.entity.Book;
import com.akhter.CollegeLibraryServer.entity.BookLocation;
import com.akhter.CollegeLibraryServer.exception.AuthorNotFoundException;
import com.akhter.CollegeLibraryServer.exception.BookNotFoundException;
import com.akhter.CollegeLibraryServer.repository.AuthorRepository;
import com.akhter.CollegeLibraryServer.repository.BookLocationRepository;
import com.akhter.CollegeLibraryServer.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookLocationRepository bookLocationRepository;

    @Autowired
    private EntityManager entityManager;

    public Book findBook(String title) throws BookNotFoundException {
        Optional<Book> book = bookRepository.findByTitle(title);
        return book.orElseThrow(BookNotFoundException::new);
    }

    public Author findAuthor(String authorName) throws AuthorNotFoundException {
        Optional<Author> author = authorRepository.findByName(authorName);
        return author.orElseThrow(AuthorNotFoundException::new);
    }

    @Transactional
    public Book insertOrUpdate(Book book) {
        mapToExistingAuthors(book);
        mapToExistingLocations(book);
        setBookIdIfExists(book);
        return bookRepository.save(book);
    }

    private void setBookIdIfExists(Book book) {
        try {
            Book existingBook = findBook(book.getTitle());
            book.setId(existingBook.getId());
        } catch (BookNotFoundException e) {
            //Simply Ignore
        }
    }

    private void mapToExistingLocations(Book book) {
        List<BookLocation> newBookLocations = new LinkedList<>(book.getBookLocations());
        List<BookLocation> existingLocations = bookLocationRepository.findAll();
        existingLocations.retainAll(newBookLocations);
        newBookLocations.removeAll(existingLocations);
        newBookLocations.addAll(existingLocations);
        book.setBookLocations(newBookLocations);
    }

    private void mapToExistingAuthors(Book book) {
        List<Author> newAuthors = new LinkedList<>(book.getAuthors());
        List<Author> existingAuthors = authorRepository.findAll();
        existingAuthors.retainAll(newAuthors);
        newAuthors.removeAll(existingAuthors);
        newAuthors.addAll(existingAuthors);
        book.setAuthors(newAuthors);
    }
}
