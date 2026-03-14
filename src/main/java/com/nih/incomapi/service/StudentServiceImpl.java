package com.nih.incomapi.service;

import com.nih.incomapi.entity.Course;
import com.nih.incomapi.entity.Student;
import com.nih.incomapi.mapper.StudentMapper;
import com.nih.incomapi.dto.StudentDto;
import com.nih.incomapi.repository.CourseRepository;
import com.nih.incomapi.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentMapper studentMapper;

    @Override
    @Transactional(readOnly = true)
    public List<StudentDto> findAll() {
        return studentRepository.findAll().stream().map(studentMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDto findById(Long id) {
        return studentMapper.toDto(getOrThrow(id));
    }

    @Override
    public StudentDto create(StudentDto dto) {
        Student student = studentMapper.toEntity(dto);
        student.setCourses(resolveCourses(dto.getCourseIds()));
        return studentMapper.toDto(studentRepository.save(student));
    }

    @Override
    public StudentDto update(Long id, StudentDto dto) {
        Student student = getOrThrow(id);
        studentMapper.updateEntity(dto, student);
        student.setCourses(resolveCourses(dto.getCourseIds()));
        return studentMapper.toDto(studentRepository.save(student));
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    private Student getOrThrow(Long id) {
        return studentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Student not found: " + id));
    }

    private Set<Course> resolveCourses(Set<Long> ids) {
        if (ids == null || ids.isEmpty()) return new HashSet<>();
        return new HashSet<>(courseRepository.findAllById(ids));
    }
}
