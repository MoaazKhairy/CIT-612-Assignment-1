package com.example.universitysystem.controllers;

import com.example.universitysystem.entities.Student;
import com.example.universitysystem.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/students")
    public String saveStudent(@RequestBody Student student){
        return studentService.saveStudent(student);
    }
}
