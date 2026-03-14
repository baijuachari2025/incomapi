package com.nih.incomapi.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nih.incomapi.dto.StudentDto;
import com.nih.incomapi.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock StudentService studentService;
    @InjectMocks StudentController studentController;

    MockMvc mockMvc;
    ObjectMapper objectMapper;
    StudentDto student;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        student = new StudentDto();
        student.setStudentId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmail("john.doe@example.com");
    }

    @Test
    void findAll_returnsList() throws Exception {
        when(studentService.findAll()).thenReturn(List.of(student));

        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].studentId").value(1))
                .andExpect(jsonPath("$[0].lastName").value("Doe"));
    }

    @Test
    void searchByLastName_returnsList() throws Exception {
        when(studentService.searchByLastName("Doe")).thenReturn(List.of(student));

        mockMvc.perform(get("/api/students/searchByLastName").param("lastName", "Doe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].lastName").value("Doe"));
    }

    @Test
    void findById_returnsStudent() throws Exception {
        when(studentService.findById(1L)).thenReturn(student);

        mockMvc.perform(get("/api/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentId").value(1))
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void create_returnsCreated() throws Exception {
        when(studentService.create(any(StudentDto.class))).thenReturn(student);

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    void update_returnsUpdatedStudent() throws Exception {
        student.setFirstName("Jane");
        when(studentService.update(eq(1L), any(StudentDto.class))).thenReturn(student);

        mockMvc.perform(put("/api/students/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Jane"));
    }

    @Test
    void delete_returnsNoContent() throws Exception {
        doNothing().when(studentService).delete(1L);

        mockMvc.perform(delete("/api/students/1"))
                .andExpect(status().isNoContent());

        verify(studentService).delete(1L);
    }
}
