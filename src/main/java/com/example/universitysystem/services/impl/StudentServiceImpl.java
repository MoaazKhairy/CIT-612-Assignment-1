package com.example.universitysystem.services.impl;

import com.example.universitysystem.entities.Student;
import com.example.universitysystem.repositories.StudentRepository;
import com.example.universitysystem.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public String saveStudent(Student student) {
        if (isStudentExists(student.getStudentId())){
            return "This Student is already exists";
        }
        studentRepository.save(student);
        return "Student is added successfully";
    }

    public boolean isStudentExists(Long studentId){
        return studentRepository.existsById(studentId);
    }

//    @Override
//    public Optional<Student> fetchStudentByStudentId(Long studentEnrolledId) {
//        return studentRepository.findById(studentEnrolledId);
//    }
}
