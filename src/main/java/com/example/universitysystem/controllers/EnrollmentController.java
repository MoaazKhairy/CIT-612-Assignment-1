package com.example.universitysystem.controllers;

import com.example.universitysystem.entities.Enrollment;
import com.example.universitysystem.entities.EnrollmentId;
import com.example.universitysystem.services.EnrollmentService;
import com.example.universitysystem.services.impl.EnrollmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/enrollments")
    public String saveEnrollment(@RequestBody Enrollment enrollment){
        return enrollmentService.saveEnrollment(enrollment);
    }

    @GetMapping("/enrollments")
    public List<Enrollment> fetchEnrollments(){
        return enrollmentService.fetchEnrollments();
    }

    @GetMapping("/enrollments/{id}")
    public List<Enrollment> fetchEnrollmentsByStudentId(@PathVariable("id") Long studentEnrolledId){
        return enrollmentService.fetchEnrollmentsByStudentId(studentEnrolledId);
    }

    @DeleteMapping("enrollments/{studentId}/{courseId}")
    public String deleteEnrollment(@PathVariable("studentId") Long studentId, @PathVariable("courseId") Long courseId){
        return enrollmentService.deleteEnrollment(studentId, courseId);
    }

    @PutMapping("/enrollments/{studentId}/{courseId}")
    public Enrollment updateEnrollment(@PathVariable("studentId") Long studentId, @PathVariable("courseId") Long courseId, @RequestBody Enrollment enrollment){
        return enrollmentService.updateEnrollment(studentId, courseId, enrollment);
    }
}
