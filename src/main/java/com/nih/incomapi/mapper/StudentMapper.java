package com.nih.incomapi.mapper;

import com.nih.incomapi.entity.Course;
import com.nih.incomapi.entity.Student;
import com.nih.incomapi.dto.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "courseIds", expression = "java(toCourseIds(student.getCourses()))")
    StudentDto toDto(Student student);

    @Mapping(target = "courses", ignore = true)
    Student toEntity(StudentDto dto);

    @Mapping(target = "courses", ignore = true)
    void updateEntity(StudentDto dto, @MappingTarget Student student);

    default Set<Long> toCourseIds(Set<Course> courses) {
        if (courses == null) return null;
        return courses.stream().map(Course::getCourseId).collect(Collectors.toSet());
    }
}
