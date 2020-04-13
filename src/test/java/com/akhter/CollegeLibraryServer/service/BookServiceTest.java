package com.akhter.CollegeLibraryServer.service;

import com.akhter.CollegeLibraryServer.entity.Author;
import com.akhter.CollegeLibraryServer.entity.Book;
import com.akhter.CollegeLibraryServer.entity.BookLocation;
import com.akhter.CollegeLibraryServer.exception.AuthorNotFoundException;
import com.akhter.CollegeLibraryServer.exception.BookNotFoundException;
import com.akhter.CollegeLibraryServer.repository.AuthorRepository;
import com.akhter.CollegeLibraryServer.repository.BookLocationRepository;
import com.akhter.CollegeLibraryServer.repository.BookRepository;
import com.akhter.CollegeLibraryServer.util.BookUtils;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookLocationRepository bookLocationRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void findBookByTitle() throws BookNotFoundException {
        String title = "Computer Architecture";
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

    @Test
    public void insertBook() {
        Book book = BookUtils.completeBookSetup("Computer Architecture");
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        bookService.insertOrUpdate(book);

        verify(bookRepository).save(book);
    }

    @Test
    public void updateBookLocationIdWithExistingLocationId() {
        Book book = BookUtils.completeBookSetup("Computer Architecture");
        BookLocation existingLocation = new BookLocation();
        existingLocation.setId(1);
        existingLocation.setCol(7);
        existingLocation.setRack(3);
        existingLocation.setRow(12);
        when(bookLocationRepository.findAll()).thenReturn(Collections.singletonList(existingLocation));
        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);

        bookService.insertOrUpdate(book);

        verify(bookRepository).save(bookArgumentCaptor.capture());
        Book savedBook = bookArgumentCaptor.getValue();
        List<BookLocation> savedBookLocation = savedBook.getBookLocations();
        assertThat(savedBookLocation.size(), Is.is(1));
        BookLocation firstSavedLocation = savedBookLocation.get(0);
        assertEquals(firstSavedLocation.getId(), 1);
        assertEquals(firstSavedLocation, existingLocation);
    }
}