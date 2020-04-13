package com.akhter.CollegeLibraryServer.integration;

import com.akhter.CollegeLibraryServer.entity.Author;
import com.akhter.CollegeLibraryServer.entity.Book;
import com.akhter.CollegeLibraryServer.entity.BookLocation;
import com.akhter.CollegeLibraryServer.exception.BookNotFoundException;
import com.akhter.CollegeLibraryServer.repository.AuthorRepository;
import com.akhter.CollegeLibraryServer.repository.BookLocationRepository;
import com.akhter.CollegeLibraryServer.repository.BookRepository;
import com.akhter.CollegeLibraryServer.service.BookService;
import com.akhter.CollegeLibraryServer.util.BookUtils;
import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ModifyingBookServiceTest extends BaseDBIntegrationTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookLocationRepository bookLocationRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @After
    public void clearTables() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        bookLocationRepository.deleteAll();
    }

    @Override
    public ClassPathResource[] sqlScripts() {
        return new ClassPathResource[0];
    }

    @Test
    public void addNewLocationsForExistingBook() throws BookNotFoundException {
        String title = "Computer Architecture";
        Book bookWithoutLocations = BookUtils.completeBookSetup(title);
        bookWithoutLocations.setBookLocations(Collections.emptyList());
        Book bookWithLocations = BookUtils.completeBookSetup(title);

        bookService.insertOrUpdate(bookWithoutLocations);
        bookService.insertOrUpdate(bookWithLocations);

        assertThat(bookRepository.count(), Is.is(1L));
        assertThat(bookLocationRepository.count(), Is.is(1L));
        Book savedBook = bookService.findBook(title);
        assertFalse(savedBook.getBookLocations().isEmpty());
    }

    @Test
    public void updateLocationsForExistingBook() throws BookNotFoundException {
        String title = "Computer Architecture";
        Book book = BookUtils.completeBookSetup(title);
        bookService.insertOrUpdate(book);
        BookLocation newBookLocation = new BookLocation();
        newBookLocation.setRack(1);
        newBookLocation.setRow(4);
        newBookLocation.setCol(3);
        book.setBookLocations(Collections.singletonList(newBookLocation));

        bookService.insertOrUpdate(book);

        assertThat(bookRepository.count(), Is.is(1L));
        assertThat(bookLocationRepository.count(), Is.is(2L));
        Book savedBook = bookService.findBook(title);
        List<BookLocation> updatedBookLocation = savedBook.getBookLocations();
        assertFalse(updatedBookLocation.isEmpty());
        assertThat(updatedBookLocation.size(), Is.is(1));
        assertEquals(updatedBookLocation.get(0), newBookLocation);
    }

    @Test
    @Transactional
    @DirtiesContext
    public void updateAuthorForExistingBook() throws BookNotFoundException {
        String title = "Computer Architecture";
        Book book = BookUtils.completeBookSetup(title);
        bookService.insertOrUpdate(book);
        Book bookWithUpdatedAuthor = BookUtils.completeBookSetup(title);

        Author newAuthor = new Author();
        newAuthor.setName("Henry Williams");
        bookWithUpdatedAuthor.setAuthors(Collections.singletonList(newAuthor));

        bookService.insertOrUpdate(bookWithUpdatedAuthor);

        Book savedBook = bookService.findBook(title);
        List<Author> savedAuthors = savedBook.getAuthors();
        assertThat(savedAuthors.size(), Is.is(1));
        Assertions.assertEquals(savedAuthors.get(0), newAuthor);
    }
}
