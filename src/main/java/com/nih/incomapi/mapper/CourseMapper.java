package com.nih.incomapi.mapper;

import com.nih.incomapi.entity.Course;
import com.nih.incomapi.dto.CourseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDto toDto(Course course);

    @Mapping(target = "students", ignore = true)
    Course toEntity(CourseDto dto);

    @Mapping(target = "students", ignore = true)
    void updateEntity(CourseDto dto, @MappingTarget Course course);
}
