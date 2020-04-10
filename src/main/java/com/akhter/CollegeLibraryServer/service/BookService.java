package com.akhter.CollegeLibraryServer.service;

import com.akhter.CollegeLibraryServer.entity.Book;
import com.akhter.CollegeLibraryServer.entity.BookLocation;
import com.akhter.CollegeLibraryServer.exception.AuthorNotFoundException;
import com.akhter.CollegeLibraryServer.exception.BookNotFoundException;
import com.akhter.CollegeLibraryServer.repository.BookLocationRepository;
import com.akhter.CollegeLibraryServer.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookLocationRepository bookLocationRepository;

    public Book findBookByTitle(String title) throws BookNotFoundException {
        Optional<Book> book = bookRepository.findByTitle(title);
        return book.orElseThrow(BookNotFoundException::new);
    }

    public List<Book> findByBookByAuthor(String author) throws AuthorNotFoundException {
        throw new UnsupportedOperationException("Functionality is pending.");
    }

    public void insertBook(Book book) {
        Optional<Book> existingBook = bookRepository.findByTitle(book.getTitle());
        if (!existingBook.isPresent()) {
            bookRepository.save(book);
        } else {
            throw new IllegalArgumentException("Book already present");
        }
    }

    public boolean deleteBook(String title) {
        Optional<Book> book = bookRepository.findByTitle(title);
        if (book.isPresent()) {
            bookRepository.delete(book.get());
            return true;
        } else {
            return false;
        }
    }

    public boolean updateBookDetails(Book updatedBook) {
        Book existingBook =
                bookRepository
                        .findByTitle(updatedBook.getTitle())
                        .orElseThrow(() -> new IllegalArgumentException("Book does not exist."));

        updatedBook.setId(existingBook.getId());
        List<BookLocation> existingLocations = bookLocationRepository.findAll();
        List<BookLocation> updatedLocations = updatedBook.getBookLocation();
        for (int i = 0; i < updatedLocations.size(); i++) {
            BookLocation newBookLocation = updatedLocations.get(i);
            updateIdsIfExist(newBookLocation, existingLocations);
        }
        bookRepository.save(updatedBook);
        return true;
    }

    private void updateIdsIfExist(BookLocation newBookLocation, List<BookLocation> existingLocations) {
        if (existingLocations.contains(newBookLocation)) {
            BookLocation oldLocation = existingLocations.get(existingLocations.indexOf(newBookLocation));
            newBookLocation.setId(oldLocation.getId());
            existingLocations.remove(oldLocation);
        }
    }
}
