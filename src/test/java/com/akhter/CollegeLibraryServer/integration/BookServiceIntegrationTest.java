package com.akhter.CollegeLibraryServer.integration;

import com.akhter.CollegeLibraryServer.entity.Author;
import com.akhter.CollegeLibraryServer.entity.Book;
import com.akhter.CollegeLibraryServer.entity.BookLocation;
import com.akhter.CollegeLibraryServer.exception.AuthorNotFoundException;
import com.akhter.CollegeLibraryServer.exception.BookNotFoundException;
import com.akhter.CollegeLibraryServer.service.BookService;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsIterableContaining;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookServiceIntegrationTest extends BaseDBIntegrationTest {

    @Autowired
    private BookService bookService;

    @Override
    public ClassPathResource[] sqlScripts() {
        return new ClassPathResource[] {
                new ClassPathResource("sql/book-service-integ-test-data.sql")
        };
    }

    @Test
    public void findBookByTitle() throws BookNotFoundException {
        String givenTitle = "Computer Architecture";
        String expectedAuthor = "Henry Williams";
        Book book = bookService.findBook(givenTitle);
        assertThat(book.getTitle(), Is.is(givenTitle));
        List<BookLocation> bookLocation = book.getBookLocation();
        assertFalse(bookLocation.isEmpty());
        List<Author> authors = book.getAuthors();
        assertFalse(authors.isEmpty());
        assertThat(authors.size(), Is.is(1));
        List<String> authorNames = authors
                .stream()
                .map(Author::getName)
                .collect(Collectors.toList());
        assertThat(authorNames, IsIterableContaining.hasItem(expectedAuthor));
    }

    @Test
    public void findAuthor() throws AuthorNotFoundException {
        String givenAuthor = "Henry Williams";
        Author author = bookService.findAuthor(givenAuthor);
        assertNotNull(author);
        assertThat(author.getName(), Is.is(givenAuthor));
        List<Book> books = author.getAuthoredBooks();
        assertFalse(books.isEmpty());
        boolean allBooksMatchGivenAuthor =
                books.
                stream()
                .map(Book::getAuthors)
                .allMatch(authorList -> authorList.stream().allMatch(currAuthor -> currAuthor.getName().equals(givenAuthor)));
        assertTrue(allBooksMatchGivenAuthor);
    }
}
