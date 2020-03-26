package com.akhter.CollegeLibraryServer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import java.util.List;

@Entity
@SecondaryTables({
        @SecondaryTable(name = "fine", pkJoinColumns = @PrimaryKeyJoinColumn(name = "student_id"))
})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "rollnumber")
    private String rollNumber;

    @Column(name = "amount", table = "fine")
    private double fineAmount;

    @OneToMany
    @JoinTable(name = "student_books_issued",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id")
    )
    private List<Book> booksIssued;

    public Student() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }

    public List<Book> getBooksIssued() {
        return booksIssued;
    }

    public void setBooksIssued(List<Book> booksIssued) {
        this.booksIssued = booksIssued;
    }
}
