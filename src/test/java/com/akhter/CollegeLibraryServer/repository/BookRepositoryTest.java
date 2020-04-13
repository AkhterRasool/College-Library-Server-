package com.akhter.CollegeLibraryServer.repository;

import com.akhter.CollegeLibraryServer.entity.Book;
import com.akhter.CollegeLibraryServer.util.BookUtils;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookRepositoryTest extends RepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void retrieveBook() {
        String title = "Computer Architecture";
        Book book = BookUtils.completeBookSetup(title);

        entityManager.persist(book);

        Optional<Book> retrievedBook = bookRepository.findByTitle(title);
        assertTrue(retrievedBook.isPresent());
        Book obtainedBook = retrievedBook.get();
        assertThat(obtainedBook.getTitle(), Is.is(title));
    }
}