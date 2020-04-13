package com.akhter.CollegeLibraryServer.util;

import com.akhter.CollegeLibraryServer.entity.Author;
import com.akhter.CollegeLibraryServer.entity.Book;
import com.akhter.CollegeLibraryServer.entity.BookLocation;
import org.hamcrest.core.Is;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class BookUtils {

    public static Book completeBookSetup(String title) {
        Book book = new Book();
        book.setTitle(title);
        Author author = new Author();
        author.setName("Andrew Tanenbaum");
        author.setAuthoredBooks(Arrays.asList(book));
        book.setAuthors(Arrays.asList(author));
        BookLocation bookLocation = new BookLocation();
        bookLocation.setCol(7);
        bookLocation.setRack(3);
        bookLocation.setRow(12);
        book.setBookLocations(Arrays.asList(bookLocation));
        return book;
    }

    public static void assertEquality(Book book1, Book book2) {
        assertEquals(book1.getTitle(), book2.getTitle());
        assertEquals(book1.getAuthors(), book2.getAuthors());
        List<BookLocation> bookLocation1 = book1.getBookLocations();
        List<BookLocation> bookLocation2 = book1.getBookLocations();
        assertThat(bookLocation1.size(), Is.is(bookLocation2.size()));
        assertEquals(bookLocation1, bookLocation2);
    }
}
