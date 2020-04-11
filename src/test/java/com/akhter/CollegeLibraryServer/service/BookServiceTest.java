package com.akhter.CollegeLibraryServer.service;

import com.akhter.CollegeLibraryServer.entity.Author;
import com.akhter.CollegeLibraryServer.entity.Book;
import com.akhter.CollegeLibraryServer.exception.AuthorNotFoundException;
import com.akhter.CollegeLibraryServer.exception.BookNotFoundException;
import com.akhter.CollegeLibraryServer.repository.AuthorRepository;
import com.akhter.CollegeLibraryServer.repository.BookRepository;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void findBookByTitle() throws BookNotFoundException {
        String title = "computer architecture";
        Book book = new Book();
        book.setTitle(title);
        when(bookRepository.findByTitle(title)).thenReturn(Optional.ofNullable(book));

        Book bookByTitle = bookService.findBook(title);

        assertThat(bookByTitle.getTitle(), Is.is(title));
    }

    @Test(expected = BookNotFoundException.class)
    public void findBookWhenItDoesNotExist() throws BookNotFoundException {
        String title = "computer architecture";
        when(bookRepository.findByTitle(title)).thenReturn(Optional.empty());

        bookService.findBook(title);
    }

    @Test
    public void findBookByAuthor() throws AuthorNotFoundException {
        String authorName = "Albert Silberschatsz";
        Author givenAuthor = new Author();
        givenAuthor.setName(authorName);
        when(authorRepository.findByName(authorName)).thenReturn(Optional.of(givenAuthor));

        Author author = bookService.findAuthor(authorName);

        assertThat(author.getName(), Is.is(authorName));
    }

    @Test(expected = AuthorNotFoundException.class)
    public void findInvalidAuthor() throws AuthorNotFoundException {
        String authorName = "aoisudasda";
        when(authorRepository.findByName(authorName)).thenReturn(Optional.empty());

        bookService.findAuthor(authorName);
    }
}