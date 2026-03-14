package com.nih.incomapi.repository;

import com.nih.incomapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
    List<Student> findByLastNameContainingIgnoreCase(String lastName);
}
