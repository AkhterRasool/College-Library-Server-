package com.akhter.CollegeLibraryServer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Objects;

@Entity(name = "book_location")
@Table(uniqueConstraints = @UniqueConstraint(name = "unique_location", columnNames = {"col", "rack", "row_num"}))
public class BookLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @Column
    private int rack;

    @Column(name = "row_num")
    private int row;

    @Column
    private int col;

    public BookLocation() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRack() {
        return rack;
    }

    public void setRack(int rack) {
        this.rack = rack;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rack, col, row);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        BookLocation location = (BookLocation) obj;
        return location.row == this.row && location.col == this.col && location.rack == this.rack;
    }
}
