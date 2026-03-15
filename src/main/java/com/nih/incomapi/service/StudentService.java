package com.nih.incomapi.service;

import com.nih.incomapi.dto.PagedResponse;
import com.nih.incomapi.dto.StudentDto;
import com.nih.incomapi.dto.StudentSearchRequest;

import java.util.List;

public interface StudentService {
    List<StudentDto> findAll();
    List<StudentDto> searchByLastName(String lastName);
    PagedResponse<StudentDto> search(StudentSearchRequest req);
    StudentDto findById(Long id);
    StudentDto create(StudentDto dto);
    StudentDto update(Long id, StudentDto dto);
    void delete(Long id);
    StudentDto enrollCourse(Long studentId, Long courseId);
}
