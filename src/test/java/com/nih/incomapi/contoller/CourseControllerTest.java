package com.nih.incomapi.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nih.incomapi.dto.CourseDto;
import com.nih.incomapi.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CourseControllerTest {

    @Mock CourseService courseService;
    @InjectMocks CourseController courseController;

    MockMvc mockMvc;
    ObjectMapper objectMapper;
    CourseDto course;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        course = new CourseDto();
        course.setCourseId(1L);
        course.setCourseName("Intro to Java");
        course.setCourseCode("CS101");
        course.setCredits(3);
        course.setDepartment("Computer Science");
        course.setStartDate(LocalDate.of(2025, 1, 15));
        course.setEndDate(LocalDate.of(2025, 5, 15));
    }

    @Test
    void findAll_returnsList() throws Exception {
        when(courseService.findAll()).thenReturn(List.of(course));

        mockMvc.perform(get("/api/courses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].courseId").value(1))
                .andExpect(jsonPath("$[0].courseCode").value("CS101"));
    }

    @Test
    void findById_returnsCourse() throws Exception {
        when(courseService.findById(1L)).thenReturn(course);

        mockMvc.perform(get("/api/courses/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseId").value(1))
                .andExpect(jsonPath("$.courseName").value("Intro to Java"));
    }

    @Test
    void create_returnsCreated() throws Exception {
        when(courseService.create(any(CourseDto.class))).thenReturn(course);

        mockMvc.perform(post("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.courseCode").value("CS101"));
    }

    @Test
    void update_returnsUpdatedCourse() throws Exception {
        course.setCourseName("Advanced Java");
        when(courseService.update(eq(1L), any(CourseDto.class))).thenReturn(course);

        mockMvc.perform(put("/api/courses/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseName").value("Advanced Java"));
    }

    @Test
    void delete_returnsNoContent() throws Exception {
        doNothing().when(courseService).delete(1L);

        mockMvc.perform(delete("/api/courses/1"))
                .andExpect(status().isNoContent());

        verify(courseService).delete(1L);
    }
}
