package com.akhter.CollegeLibraryServer.controller;

import com.akhter.CollegeLibraryServer.entity.Book;
import com.akhter.CollegeLibraryServer.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {


    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Before
    public void setup() {
        mockMvc = standaloneSetup(bookController).build();
    }


    @Test
    public void insertBook() throws Exception {
        mockMvc
                .perform(post("/book/insert")
                        .content(expectedInputJson())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        verify(bookService).insertOrUpdate(any(Book.class));
    }


    public String expectedInputJson() {
        return "{\"title\":\"Computer Architecture\",\"bookLocation\":[{\"rack\":7,\"row\":5,\"col\":1},{\"rack\":8,\"row\":2,\"col\":1}],\"authors\":[{\"name\":\"Henry Williams\"}]}";
    }
}
