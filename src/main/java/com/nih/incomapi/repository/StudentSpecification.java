package com.nih.incomapi.repository;

import com.nih.incomapi.dto.StudentSearchRequest;
import com.nih.incomapi.entity.Student;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class StudentSpecification {

    public static Specification<Student> from(StudentSearchRequest r) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (r.getFirstName() != null && !r.getFirstName().isEmpty())
                predicates.add(cb.like(cb.lower(root.get("firstName")), "%" + r.getFirstName().toLowerCase() + "%"));
            if (r.getLastName() != null && !r.getLastName().isEmpty())
                predicates.add(cb.like(cb.lower(root.get("lastName")), "%" + r.getLastName().toLowerCase() + "%"));
            if (r.getEmail() != null && !r.getEmail().isEmpty())
                predicates.add(cb.like(cb.lower(root.get("email")), "%" + r.getEmail().toLowerCase() + "%"));
            if (r.getPhone() != null && !r.getPhone().isEmpty())
                predicates.add(cb.like(root.get("phone"), "%" + r.getPhone() + "%"));
            if (r.getGender() != null && !r.getGender().isEmpty())
                predicates.add(cb.equal(cb.lower(root.get("gender")), r.getGender().toLowerCase()));
            if (r.getMajor() != null && !r.getMajor().isEmpty())
                predicates.add(cb.like(cb.lower(root.get("major")), "%" + r.getMajor().toLowerCase() + "%"));
            if (r.getDateOfBirth() != null)
                predicates.add(cb.equal(root.get("dateOfBirth"), r.getDateOfBirth()));
            if (r.getEnrollmentDate() != null)
                predicates.add(cb.equal(root.get("enrollmentDate"), r.getEnrollmentDate()));
            if (r.getMinGpa() != null && r.getMinGpa().compareTo(java.math.BigDecimal.ZERO) != 0)
                predicates.add(cb.greaterThanOrEqualTo(root.get("gpa"), r.getMinGpa()));
            if (r.getMaxGpa() != null && r.getMaxGpa().compareTo(java.math.BigDecimal.ZERO) != 0)
                predicates.add(cb.lessThanOrEqualTo(root.get("gpa"), r.getMaxGpa()));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
