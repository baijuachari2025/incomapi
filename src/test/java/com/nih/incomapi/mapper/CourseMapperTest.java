package com.nih.incomapi.mapper;

import com.nih.incomapi.dto.CourseDto;
import com.nih.incomapi.entity.Course;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class CourseMapperTest {

    private final CourseMapper mapper = Mappers.getMapper(CourseMapper.class);

    @Test
    void toDto_mapsAllFields() {
        Course course = new Course();
        course.setCourseId(1L);
        course.setCourseName("Intro to Java");
        course.setCourseCode("CS101");
        course.setCredits(3);
        course.setDepartment("Computer Science");
        course.setStartDate(LocalDate.of(2025, 1, 15));
        course.setEndDate(LocalDate.of(2025, 5, 15));

        CourseDto dto = mapper.toDto(course);

        assertThat(dto.getCourseId()).isEqualTo(1L);
        assertThat(dto.getCourseName()).isEqualTo("Intro to Java");
        assertThat(dto.getCourseCode()).isEqualTo("CS101");
        assertThat(dto.getCredits()).isEqualTo(3);
        assertThat(dto.getDepartment()).isEqualTo("Computer Science");
        assertThat(dto.getStartDate()).isEqualTo(LocalDate.of(2025, 1, 15));
        assertThat(dto.getEndDate()).isEqualTo(LocalDate.of(2025, 5, 15));
    }

    @Test
    void toEntity_mapsAllFieldsExceptStudents() {
        CourseDto dto = new CourseDto();
        dto.setCourseId(2L);
        dto.setCourseName("Advanced Java");
        dto.setCourseCode("CS201");
        dto.setCredits(4);
        dto.setDepartment("Computer Science");
        dto.setStartDate(LocalDate.of(2025, 6, 1));
        dto.setEndDate(LocalDate.of(2025, 12, 1));

        Course course = mapper.toEntity(dto);

        assertThat(course.getCourseId()).isEqualTo(2L);
        assertThat(course.getCourseName()).isEqualTo("Advanced Java");
        assertThat(course.getCourseCode()).isEqualTo("CS201");
        assertThat(course.getCredits()).isEqualTo(4);
        assertThat(course.getDepartment()).isEqualTo("Computer Science");
        assertThat(course.getStartDate()).isEqualTo(LocalDate.of(2025, 6, 1));
        assertThat(course.getEndDate()).isEqualTo(LocalDate.of(2025, 12, 1));
        assertThat(course.getStudents()).isEmpty(); // students ignored
    }

    @Test
    void updateEntity_updatesFieldsWithoutTouchingStudents() {
        Course course = new Course();
        course.setCourseName("Old Name");
        course.setCourseCode("OLD101");

        CourseDto dto = new CourseDto();
        dto.setCourseName("New Name");
        dto.setCourseCode("NEW101");
        dto.setCredits(5);

        mapper.updateEntity(dto, course);

        assertThat(course.getCourseName()).isEqualTo("New Name");
        assertThat(course.getCourseCode()).isEqualTo("NEW101");
        assertThat(course.getCredits()).isEqualTo(5);
        assertThat(course.getStudents()).isEmpty(); // untouched
    }
}
