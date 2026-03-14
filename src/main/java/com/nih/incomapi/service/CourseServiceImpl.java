package com.nih.incomapi.service;

import com.nih.incomapi.entity.Course;
import com.nih.incomapi.mapper.CourseMapper;
import com.nih.incomapi.dto.CourseDto;
import com.nih.incomapi.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CourseDto> findAll() {
        return courseRepository.findAll().stream().map(courseMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CourseDto findById(Long id) {
        return courseMapper.toDto(getOrThrow(id));
    }

    @Override
    public CourseDto create(CourseDto dto) {
        return courseMapper.toDto(courseRepository.save(courseMapper.toEntity(dto)));
    }

    @Override
    public CourseDto update(Long id, CourseDto dto) {
        Course course = getOrThrow(id);
        courseMapper.updateEntity(dto, course);
        return courseMapper.toDto(courseRepository.save(course));
    }

    @Override
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    private Course getOrThrow(Long id) {
        return courseRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Course not found: " + id));
    }
}
