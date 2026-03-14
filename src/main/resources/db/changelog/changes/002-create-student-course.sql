-- liquibase formatted sql

-- changeset incomapi:002
CREATE TABLE incom.student (
    student_id      BIGSERIAL PRIMARY KEY,
    first_name      VARCHAR(100) NOT NULL,
    last_name       VARCHAR(100) NOT NULL,
    email           VARCHAR(150) NOT NULL UNIQUE,
    phone           VARCHAR(20),
    date_of_birth   DATE,
    gender          VARCHAR(20),
    enrollment_date DATE,
    major           VARCHAR(100),
    gpa             NUMERIC(3, 2)
);

CREATE TABLE incom.course (
    course_id   BIGSERIAL PRIMARY KEY,
    course_name VARCHAR(150) NOT NULL,
    course_code VARCHAR(20)  NOT NULL UNIQUE,
    credits     INTEGER,
    department  VARCHAR(100),
    start_date  DATE,
    end_date    DATE
);

CREATE TABLE incom.student_course (
    student_id BIGINT NOT NULL REFERENCES incom.student(student_id) ON DELETE CASCADE,
    course_id  BIGINT NOT NULL REFERENCES incom.course(course_id) ON DELETE CASCADE,
    PRIMARY KEY (student_id, course_id)
);
