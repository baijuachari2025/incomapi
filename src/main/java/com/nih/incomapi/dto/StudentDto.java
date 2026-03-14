package com.nih.incomapi.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
public class StudentDto {
    private Long studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String gender;
    private LocalDate enrollmentDate;
    private String major;
    private BigDecimal gpa;
    private Set<Long> courseIds;
}
