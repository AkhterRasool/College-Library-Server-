package com.akhter.CollegeLibraryServer.service;

import com.akhter.CollegeLibraryServer.entity.Student;
import com.akhter.CollegeLibraryServer.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student findByRollNumber(String rollNumber) {
        return studentRepository.findByRollNumber(rollNumber)
                .orElseThrow(() -> new IllegalArgumentException("This roll number does not exist."));
    }
}
