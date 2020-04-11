package com.akhter.CollegeLibraryServer.repository;

import com.akhter.CollegeLibraryServer.entity.Book;
import com.akhter.CollegeLibraryServer.entity.BookLocation;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookRepositoryTest extends RepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void retrievalTest() {
        String title = "computer architecture";
        Book book = new Book();
        book.setTitle(title);
        BookLocation bookLocation = new BookLocation();
        bookLocation.setRack(2);
        bookLocation.setRow(3);
        bookLocation.setCol(4);
        book.setBookLocation(Arrays.asList(bookLocation));

        entityManager.persist(book);

        Optional<Book> retrievedBook = bookRepository.findByTitle(title);
        assertTrue(retrievedBook.isPresent());
        Book obtainedBook = retrievedBook.get();
        assertThat(obtainedBook.getTitle(), Is.is(title));
    }
}