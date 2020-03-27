package com.akhter.CollegeLibraryServer.repository;

import com.akhter.CollegeLibraryServer.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

    boolean deleteBookByTitle(String bookTitle);
}
