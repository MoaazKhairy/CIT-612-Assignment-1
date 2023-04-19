package com.example.universitysystem.services;

import com.example.universitysystem.entities.Student;

import java.util.Optional;

public interface StudentService {
    String saveStudent(Student student);
//    Optional<Student> fetchStudentByStudentId(Long studentEnrolledId);
}
