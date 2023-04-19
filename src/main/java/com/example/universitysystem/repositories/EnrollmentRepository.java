package com.example.universitysystem.repositories;

import com.example.universitysystem.entities.Enrollment;
import com.example.universitysystem.entities.EnrollmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {

    List<Enrollment> findByEnrollmentIdStudentEnrolledId(Long studentEnrolledId);
}
