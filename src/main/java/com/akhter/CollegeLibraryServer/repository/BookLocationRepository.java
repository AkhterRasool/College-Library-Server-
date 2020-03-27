package com.akhter.CollegeLibraryServer.repository;

import com.akhter.CollegeLibraryServer.entity.BookLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookLocationRepository extends JpaRepository<BookLocation, Integer> {

}
