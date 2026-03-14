package com.nih.incomapi.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CourseDto {
    private Long courseId;
    private String courseName;
    private String courseCode;
    private Integer credits;
    private String department;
    private LocalDate startDate;
    private LocalDate endDate;
}
