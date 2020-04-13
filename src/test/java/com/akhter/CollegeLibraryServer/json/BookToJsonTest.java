package com.akhter.CollegeLibraryServer.json;

import com.akhter.CollegeLibraryServer.entity.Author;
import com.akhter.CollegeLibraryServer.entity.Book;
import com.akhter.CollegeLibraryServer.entity.BookLocation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class BookToJsonTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void jsonToPOJO() throws JsonProcessingException {
        Book book = objectMapper.readValue(expectedInputJson(), Book.class);

        assertThat(book.getTitle(), Is.is("Computer Architecture"));
        List<Author> authors = book.getAuthors();
        List<BookLocation> bookLocations = book.getBookLocations();
        assertFalse(authors.isEmpty());
        assertThat(authors.size(), Is.is(1));
        Author firstAuthor = authors.get(0);
        assertThat(firstAuthor.getName(), Is.is("Henry Williams"));
        assertFalse(bookLocations.isEmpty());
        BookLocation expectedBookLocation1 = new BookLocation();
        expectedBookLocation1.setRack(7);
        expectedBookLocation1.setRow(5);
        expectedBookLocation1.setCol(1);
        BookLocation expectedBookLocation2 = new BookLocation();
        expectedBookLocation2.setRack(8);
        expectedBookLocation2.setRow(2);
        expectedBookLocation2.setCol(1);
        assertEquals(bookLocations.get(0), expectedBookLocation1);
        assertEquals(bookLocations.get(1), expectedBookLocation2);
    }


    @Test
    public void pojoToJson() throws JsonProcessingException {
        Book book = objectMapper.readValue(expectedInputJson(), Book.class);

        String jsonOutput = objectMapper.writeValueAsString(book);

        assertEquals(expectedOutputJson(), jsonOutput);
    }

    public String expectedInputJson() {
        return "{\"title\":\"Computer Architecture\",\"bookLocations\":[{\"rack\":7,\"row\":5,\"col\":1},{\"rack\":8,\"row\":2,\"col\":1}],\"authors\":[{\"name\":\"Henry Williams\"}]}";
    }

    public String expectedOutputJson() {
        return "{\"title\":\"Computer Architecture\",\"bookLocations\":[{\"rack\":7,\"row\":5,\"col\":1},{\"rack\":8,\"row\":2,\"col\":1}],\"authors\":[\"Henry Williams\"]}";
    }
}
