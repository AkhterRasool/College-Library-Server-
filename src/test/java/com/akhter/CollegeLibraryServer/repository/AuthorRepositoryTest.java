package com.akhter.CollegeLibraryServer.repository;


import com.akhter.CollegeLibraryServer.entity.Author;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class AuthorRepositoryTest extends RepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void findByAuthor() {
        String authorName = "Henry Williams";
        Author author = new Author();
        author.setName(authorName);

        entityManager.persist(author);

        Optional<Author> optAuthor =  authorRepository.findByName(authorName);
        assertTrue(optAuthor.isPresent());
        Author retrievedAuthor = optAuthor.get();
        assertThat(retrievedAuthor.getName(), Is.is(authorName));
    }
}
