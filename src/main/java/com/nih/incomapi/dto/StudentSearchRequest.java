package com.nih.incomapi.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class StudentSearchRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String gender;
    private String major;
    private LocalDate dateOfBirth;
    private LocalDate enrollmentDate;
    private BigDecimal minGpa;
    private BigDecimal maxGpa;

    private int page = 0;
    private int size = 20;
    private String sortBy = "studentId";
    private String sortDir = "asc";
}
