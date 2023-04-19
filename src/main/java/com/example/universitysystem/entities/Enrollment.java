package com.example.universitysystem.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.Date;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Enrollment {

    @EmbeddedId
    private EnrollmentId enrollmentId;
    private LocalDate enrolledDate;
    private String studentEnrolEmail;
}
