package com.akhter.CollegeLibraryServer.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;


@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "book_book_location",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "location_id", referencedColumnName = "id")
    )
    private List<BookLocation> bookLocation;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BookLocation> getBookLocation() {
        return bookLocation;
    }

    public void setBookLocation(List<BookLocation> bookLocation) {
        this.bookLocation = bookLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
