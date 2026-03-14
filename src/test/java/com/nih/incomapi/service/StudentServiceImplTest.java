package com.nih.incomapi.service;

import com.nih.incomapi.dto.StudentDto;
import com.nih.incomapi.entity.Course;
import com.nih.incomapi.entity.Student;
import com.nih.incomapi.mapper.StudentMapper;
import com.nih.incomapi.repository.CourseRepository;
import com.nih.incomapi.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock StudentRepository studentRepository;
    @Mock CourseRepository courseRepository;
    @Mock StudentMapper studentMapper;
    @InjectMocks StudentServiceImpl studentService;

    Student student;
    StudentDto studentDto;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setStudentId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");

        studentDto = new StudentDto();
        studentDto.setStudentId(1L);
        studentDto.setFirstName("John");
        studentDto.setLastName("Doe");
    }

    @Test
    void findAll_returnsMappedList() {
        when(studentRepository.findAll()).thenReturn(List.of(student));
        when(studentMapper.toDto(student)).thenReturn(studentDto);

        List<StudentDto> result = studentService.findAll();

        assertThat(result).containsExactly(studentDto);
    }

    @Test
    void searchByLastName_returnsMappedList() {
        when(studentRepository.findByLastNameContainingIgnoreCase("Doe")).thenReturn(List.of(student));
        when(studentMapper.toDto(student)).thenReturn(studentDto);

        List<StudentDto> result = studentService.searchByLastName("Doe");

        assertThat(result).containsExactly(studentDto);
    }

    @Test
    void findById_returnsDto() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentMapper.toDto(student)).thenReturn(studentDto);

        assertThat(studentService.findById(1L)).isEqualTo(studentDto);
    }

    @Test
    void findById_throwsWhenNotFound() {
        when(studentRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> studentService.findById(99L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void create_savesAndReturnsDto() {
        studentDto.setCourseIds(null);
        when(studentMapper.toEntity(studentDto)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(student);
        when(studentMapper.toDto(student)).thenReturn(studentDto);

        assertThat(studentService.create(studentDto)).isEqualTo(studentDto);
        verify(studentRepository).save(student);
    }

    @Test
    void create_resolvesCourses() {
        Course course = new Course();
        course.setCourseId(10L);
        studentDto.setCourseIds(Set.of(10L));

        when(studentMapper.toEntity(studentDto)).thenReturn(student);
        when(courseRepository.findAllById(Set.of(10L))).thenReturn(List.of(course));
        when(studentRepository.save(student)).thenReturn(student);
        when(studentMapper.toDto(student)).thenReturn(studentDto);

        studentService.create(studentDto);

        assertThat(student.getCourses()).containsExactly(course);
    }

    @Test
    void update_updatesAndReturnsDto() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentRepository.save(student)).thenReturn(student);
        when(studentMapper.toDto(student)).thenReturn(studentDto);

        assertThat(studentService.update(1L, studentDto)).isEqualTo(studentDto);
        verify(studentMapper).updateEntity(studentDto, student);
    }

    @Test
    void update_throwsWhenNotFound() {
        when(studentRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> studentService.update(99L, studentDto))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void delete_callsRepository() {
        studentService.delete(1L);
        verify(studentRepository).deleteById(1L);
    }
}
