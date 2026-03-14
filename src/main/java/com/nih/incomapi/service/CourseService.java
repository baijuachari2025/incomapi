package com.nih.incomapi.service;

import com.nih.incomapi.dto.CourseDto;

import java.util.List;

public interface CourseService {
    List<CourseDto> findAll();
    CourseDto findById(Long id);
    CourseDto create(CourseDto dto);
    CourseDto update(Long id, CourseDto dto);
    void delete(Long id);
}
