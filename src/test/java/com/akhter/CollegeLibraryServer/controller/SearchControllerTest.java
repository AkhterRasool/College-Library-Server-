package com.akhter.CollegeLibraryServer.controller;

import com.akhter.CollegeLibraryServer.service.BookService;
import com.akhter.CollegeLibraryServer.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class SearchControllerTest {

    @Mock
    private BookService bookService;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private SearchController searchController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = standaloneSetup(searchController).build();
    }

    @Test
    public void searchBook() throws Exception {
        String title = "Operating Systems Concepts";
        mockMvc
                .perform(MockMvcRequestBuilders.get("/search/title/{title}", title))
                .andExpect(status().isOk());

        verify(bookService).findBook(title);
    }

    @Test
    public void searchAuthor() throws Exception {
        String author = "Henry Williams";
        mockMvc
                .perform(MockMvcRequestBuilders.get("/search/author/{author}", author))
                .andExpect(status().isOk());

        verify(bookService).findAuthor(author);
    }

    @Test
    public void searchIssuesAndFines() throws Exception {
        String rollNumber = "123456789";
        mockMvc
                .perform(MockMvcRequestBuilders.get("/search/issuesandfines/{rollNumber}", rollNumber))
                .andExpect(status().isOk());

        verify(studentService).findByRollNumber(rollNumber);
    }
}