package com.example.universitysystem.services.impl;

import com.example.universitysystem.entities.Enrollment;
import com.example.universitysystem.entities.EnrollmentId;
import com.example.universitysystem.repositories.EnrollmentRepository;
import com.example.universitysystem.services.EmailService;
import com.example.universitysystem.services.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    private final EmailService emailService;

    public static final LocalDate ADD_DROP_FINAL_DATE = java.time.LocalDate.now();
    public static final int ADD_DROP_DURATION_DAYS = 14;


    @Override
    public String saveEnrollment(Enrollment enrollment) {
        if (isEnrollmentExists(enrollment.getEnrollmentId())){
            return "This Enrollment is already exists";
        }
        if (enrollment.getEnrolledDate() == null){
            enrollment.setEnrolledDate(java.time.LocalDate.now());
        }
        if (enrollment.getEnrolledDate().compareTo(ADD_DROP_FINAL_DATE) > 0){
            return "Add/Drop courses duration is finished, you can not add it now";
        }
        enrollmentRepository.save(enrollment);
        emailService.sendEmail(enrollment);
        return "Enrollment is added successfully";
    }

    @Override
    public List<Enrollment> fetchEnrollments() {
        return enrollmentRepository.findAll();
    }

    @Override
    public List<Enrollment> fetchEnrollmentsByStudentId(Long studentEnrolledId) {
        return enrollmentRepository.findByEnrollmentIdStudentEnrolledId(studentEnrolledId);
    }

    @Override
    public String deleteEnrollment(Long studentId, Long courseId) {
        EnrollmentId enrollmentId = new EnrollmentId(studentId, courseId);
        if (!isEnrollmentExists(enrollmentId)){
            return "This Enrollment is not existing";
        }
        Period period = Period.between(enrollmentRepository.findById(enrollmentId).get().getEnrolledDate() ,ADD_DROP_FINAL_DATE);
        if (period.getDays() > ADD_DROP_DURATION_DAYS) {
            return "Add/Drop courses duration is finished, you can not drop it now";
        }
        enrollmentRepository.deleteById(enrollmentId);
        return "Enrollment Deleted successfully";
    }

    @Override
    public Enrollment updateEnrollment(Long studentId, Long courseId, Enrollment enrollment) {
        Enrollment enrollmentDB = enrollmentRepository.findById(new EnrollmentId(studentId, courseId)).get();
        enrollmentDB.setEnrolledDate(enrollment.getEnrolledDate());

        return enrollmentRepository.save(enrollmentDB);
    }

    public boolean isEnrollmentExists(EnrollmentId enrollmentId){
        return enrollmentRepository.existsById(enrollmentId);
    }

}