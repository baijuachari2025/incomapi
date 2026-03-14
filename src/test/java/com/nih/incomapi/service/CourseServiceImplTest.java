package com.nih.incomapi.service;

import com.nih.incomapi.dto.CourseDto;
import com.nih.incomapi.entity.Course;
import com.nih.incomapi.mapper.CourseMapper;
import com.nih.incomapi.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock CourseRepository courseRepository;
    @Mock CourseMapper courseMapper;
    @InjectMocks CourseServiceImpl courseService;

    Course course;
    CourseDto courseDto;

    @BeforeEach
    void setUp() {
        course = new Course();
        course.setCourseId(1L);
        course.setCourseName("Intro to Java");
        course.setCourseCode("CS101");

        courseDto = new CourseDto();
        courseDto.setCourseId(1L);
        courseDto.setCourseName("Intro to Java");
        courseDto.setCourseCode("CS101");
    }

    @Test
    void findAll_returnsMappedList() {
        when(courseRepository.findAll()).thenReturn(List.of(course));
        when(courseMapper.toDto(course)).thenReturn(courseDto);

        assertThat(courseService.findAll()).containsExactly(courseDto);
    }

    @Test
    void findById_returnsDto() {
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(courseMapper.toDto(course)).thenReturn(courseDto);

        assertThat(courseService.findById(1L)).isEqualTo(courseDto);
    }

    @Test
    void findById_throwsWhenNotFound() {
        when(courseRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> courseService.findById(99L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void create_savesAndReturnsDto() {
        when(courseMapper.toEntity(courseDto)).thenReturn(course);
        when(courseRepository.save(course)).thenReturn(course);
        when(courseMapper.toDto(course)).thenReturn(courseDto);

        assertThat(courseService.create(courseDto)).isEqualTo(courseDto);
        verify(courseRepository).save(course);
    }

    @Test
    void update_updatesAndReturnsDto() {
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(courseRepository.save(course)).thenReturn(course);
        when(courseMapper.toDto(course)).thenReturn(courseDto);

        assertThat(courseService.update(1L, courseDto)).isEqualTo(courseDto);
        verify(courseMapper).updateEntity(courseDto, course);
    }

    @Test
    void update_throwsWhenNotFound() {
        when(courseRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> courseService.update(99L, courseDto))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void delete_callsRepository() {
        courseService.delete(1L);
        verify(courseRepository).deleteById(1L);
    }
}
