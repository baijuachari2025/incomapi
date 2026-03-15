package com.nih.incomapi.contoller;

import com.nih.incomapi.dto.PagedResponse;
import com.nih.incomapi.dto.StudentDto;
import com.nih.incomapi.dto.StudentSearchRequest;
import com.nih.incomapi.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<StudentDto> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/searchByLastName")
    public List<StudentDto> searchByLastName(@RequestParam String lastName) {
        return studentService.searchByLastName(lastName);
    }

    @PostMapping("/search")
    public PagedResponse<StudentDto> search(@RequestBody StudentSearchRequest req) {
        return studentService.search(req);
    }

    @GetMapping("/{id}")
    public StudentDto findById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDto create(@RequestBody StudentDto dto) {
        return studentService.create(dto);
    }

    @PutMapping("/{id}")
    public StudentDto update(@PathVariable Long id, @RequestBody StudentDto dto) {
        return studentService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }

    @PostMapping("/{studentId}/enroll/{courseId}")
    public StudentDto enrollCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        return studentService.enrollCourse(studentId, courseId);
    }
}
