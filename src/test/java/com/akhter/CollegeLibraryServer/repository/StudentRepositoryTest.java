package com.akhter.CollegeLibraryServer.repository;

import com.akhter.CollegeLibraryServer.entity.Book;
import com.akhter.CollegeLibraryServer.entity.BookLocation;
import com.akhter.CollegeLibraryServer.entity.Student;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class StudentRepositoryTest extends RepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void getStudentWithIssuesAndFines() {
        String roll = "1234567890";
        String bookTitle = "computer architecture";
        Student student = new Student();
        student.setRollNumber(roll);
        student.setFineAmount(123.312);
        Book book = new Book();
        book.setTitle(bookTitle);
        BookLocation bookLocation = new BookLocation();
        bookLocation.setRack(2);
        bookLocation.setRow(3);
        bookLocation.setCol(4);
        book.setBookLocations(Collections.singletonList(bookLocation));
        student.setBooksIssued(Collections.singletonList(book));

        entityManager.persist(student);

        Optional<Student> byRollNumber = studentRepository.findByRollNumber(roll);
        assertTrue(byRollNumber.isPresent());
        Student persistedStudent = byRollNumber.get();
        assertThat(persistedStudent.getRollNumber(), Is.is(roll));
        assertThat(persistedStudent.getBooksIssued().get(0).getTitle(), Is.is(bookTitle));
    }
}