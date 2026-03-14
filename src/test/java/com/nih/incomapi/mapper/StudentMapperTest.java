package com.nih.incomapi.mapper;

import com.nih.incomapi.dto.StudentDto;
import com.nih.incomapi.entity.Course;
import com.nih.incomapi.entity.Student;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class StudentMapperTest {

    private final StudentMapper mapper = Mappers.getMapper(StudentMapper.class);

    @Test
    void toDto_mapsAllFields() {
        Course course = new Course();
        course.setCourseId(10L);

        Student student = new Student();
        student.setStudentId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("john@example.com");
        student.setPhone("555-1234");
        student.setDateOfBirth(LocalDate.of(2000, 1, 15));
        student.setGender("M");
        student.setEnrollmentDate(LocalDate.of(2020, 9, 1));
        student.setMajor("CS");
        student.setGpa(new BigDecimal("3.8"));
        student.setCourses(Set.of(course));

        StudentDto dto = mapper.toDto(student);

        assertThat(dto.getStudentId()).isEqualTo(1L);
        assertThat(dto.getFirstName()).isEqualTo("John");
        assertThat(dto.getLastName()).isEqualTo("Doe");
        assertThat(dto.getEmail()).isEqualTo("john@example.com");
        assertThat(dto.getPhone()).isEqualTo("555-1234");
        assertThat(dto.getDateOfBirth()).isEqualTo(LocalDate.of(2000, 1, 15));
        assertThat(dto.getGender()).isEqualTo("M");
        assertThat(dto.getEnrollmentDate()).isEqualTo(LocalDate.of(2020, 9, 1));
        assertThat(dto.getMajor()).isEqualTo("CS");
        assertThat(dto.getGpa()).isEqualByComparingTo("3.8");
        assertThat(dto.getCourseIds()).containsExactly(10L);
    }

    @Test
    void toDto_nullCourses_returnsNullCourseIds() {
        Student student = new Student();
        student.setCourses(null);

        assertThat(mapper.toDto(student).getCourseIds()).isNull();
    }

    @Test
    void toDto_emptyCourses_returnsEmptyCourseIds() {
        Student student = new Student();
        // default empty HashSet from entity

        assertThat(mapper.toDto(student).getCourseIds()).isEmpty();
    }

    @Test
    void toEntity_mapsAllFieldsExceptCourses() {
        StudentDto dto = new StudentDto();
        dto.setStudentId(2L);
        dto.setFirstName("Jane");
        dto.setLastName("Smith");
        dto.setEmail("jane@example.com");
        dto.setPhone("555-5678");
        dto.setDateOfBirth(LocalDate.of(1999, 5, 20));
        dto.setGender("F");
        dto.setEnrollmentDate(LocalDate.of(2021, 9, 1));
        dto.setMajor("Math");
        dto.setGpa(new BigDecimal("3.5"));
        dto.setCourseIds(Set.of(5L));

        Student student = mapper.toEntity(dto);

        assertThat(student.getStudentId()).isEqualTo(2L);
        assertThat(student.getFirstName()).isEqualTo("Jane");
        assertThat(student.getLastName()).isEqualTo("Smith");
        assertThat(student.getEmail()).isEqualTo("jane@example.com");
        assertThat(student.getPhone()).isEqualTo("555-5678");
        assertThat(student.getDateOfBirth()).isEqualTo(LocalDate.of(1999, 5, 20));
        assertThat(student.getGender()).isEqualTo("F");
        assertThat(student.getEnrollmentDate()).isEqualTo(LocalDate.of(2021, 9, 1));
        assertThat(student.getMajor()).isEqualTo("Math");
        assertThat(student.getGpa()).isEqualByComparingTo("3.5");
        assertThat(student.getCourses()).isEmpty(); // courses ignored
    }

    @Test
    void updateEntity_updatesFieldsWithoutTouchingCourses() {
        Student student = new Student();
        student.setFirstName("Old");
        Course existing = new Course();
        existing.setCourseId(99L);
        student.setCourses(Set.of(existing));

        StudentDto dto = new StudentDto();
        dto.setFirstName("New");
        dto.setLastName("Name");

        mapper.updateEntity(dto, student);

        assertThat(student.getFirstName()).isEqualTo("New");
        assertThat(student.getLastName()).isEqualTo("Name");
        assertThat(student.getCourses()).containsExactly(existing); // untouched
    }
}
