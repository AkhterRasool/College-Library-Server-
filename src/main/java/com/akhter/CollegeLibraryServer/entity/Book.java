package com.akhter.CollegeLibraryServer.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;


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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_author_mapping",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    private List<Author> authors;

    public Book() {}

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

    @JsonIgnore
    public List<Author> getAuthors() {
        return authors;
    }

    @JsonProperty("authors")
    public List<String> getAuthorNames() {
        return getAuthors()
                .stream()
                .map(Author::getName)
                .collect(Collectors.toList());
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
