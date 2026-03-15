-- liquibase formatted sql

-- changeset incomapi:003
INSERT INTO incom.student (first_name, last_name, email, phone, date_of_birth, gender, enrollment_date, major, gpa) VALUES
('Alice',   'Johnson',  'alice.johnson@example.com',  '555-0101', '2001-03-15', 'Female', '2020-09-01', 'Computer Science', 3.85),
('Bob',     'Smith',    'bob.smith@example.com',      '555-0102', '2000-07-22', 'Male',   '2020-09-01', 'Mathematics',      3.40),
('Carol',   'Williams', 'carol.williams@example.com', '555-0103', '2001-11-05', 'Female', '2021-01-15', 'Physics',          3.60),
('David',   'Brown',    'david.brown@example.com',    '555-0104', '2002-01-30', 'Male',   '2021-09-01', 'Chemistry',        2.95),
('Eva',     'Jones',    'eva.jones@example.com',      '555-0105', '2001-06-18', 'Female', '2020-09-01', 'Biology',          3.75),
('Frank',   'Garcia',   'frank.garcia@example.com',   '555-0106', '2000-09-12', 'Male',   '2019-09-01', 'Engineering',      3.20),
('Grace',   'Martinez', 'grace.martinez@example.com', '555-0107', '2002-04-25', 'Female', '2021-09-01', 'Computer Science', 3.90),
('Henry',   'Davis',    'henry.davis@example.com',    '555-0108', '2001-08-08', 'Male',   '2020-01-15', 'Mathematics',      3.10),
('Isla',    'Wilson',   'isla.wilson@example.com',    '555-0109', '2003-02-14', 'Female', '2022-09-01', 'Physics',          3.55),
('James',   'Taylor',   'james.taylor@example.com',   '555-0110', '2000-12-01', 'Male',   '2019-09-01', 'Engineering',      2.80),
('Karen',   'Anderson', 'karen.anderson@example.com', '555-0111', '2001-05-20', 'Female', '2020-09-01', 'Biology',          3.65),
('Liam',    'Thomas',   'liam.thomas@example.com',    '555-0112', '2002-08-14', 'Male',   '2021-09-01', 'Chemistry',        3.30),
('Mia',     'Jackson',  'mia.jackson@example.com',    '555-0113', '2001-02-28', 'Female', '2020-01-15', 'Computer Science', 3.80),
('Noah',    'White',    'noah.white@example.com',     '555-0114', '2000-11-17', 'Male',   '2019-09-01', 'Mathematics',      3.15),
('Olivia',  'Harris',   'olivia.harris@example.com',  '555-0115', '2003-07-09', 'Female', '2022-09-01', 'Physics',          3.70),
('Paul',    'Martin',   'paul.martin@example.com',    '555-0116', '2001-04-03', 'Male',   '2020-09-01', 'Engineering',      2.90),
('Quinn',   'Thompson', 'quinn.thompson@example.com', '555-0117', '2002-09-22', 'Female', '2021-01-15', 'Biology',          3.45),
('Ryan',    'Garcia',   'ryan.garcia@example.com',    '555-0118', '2000-06-11', 'Male',   '2019-09-01', 'Computer Science', 3.55),
('Sophia',  'Martinez', 'sophia.martinez@example.com','555-0119', '2001-12-30', 'Female', '2020-09-01', 'Mathematics',      3.95),
('Tyler',   'Robinson', 'tyler.robinson@example.com', '555-0120', '2002-03-16', 'Male',   '2021-09-01', 'Chemistry',        2.75),
('Uma',     'Clark',    'uma.clark@example.com',      '555-0121', '2001-10-07', 'Female', '2020-01-15', 'Physics',          3.60),
('Victor',  'Rodriguez','victor.rodriguez@example.com','555-0122','2000-08-25', 'Male',   '2019-09-01', 'Engineering',      3.25),
('Wendy',   'Lewis',    'wendy.lewis@example.com',    '555-0123', '2003-01-19', 'Female', '2022-09-01', 'Biology',          3.85),
('Xander',  'Lee',      'xander.lee@example.com',     '555-0124', '2002-06-04', 'Male',   '2021-09-01', 'Computer Science', 3.40),
('Yara',    'Walker',   'yara.walker@example.com',    '555-0125', '2001-09-13', 'Female', '2020-09-01', 'Mathematics',      3.70),
('Zane',    'Hall',     'zane.hall@example.com',      '555-0126', '2000-04-28', 'Male',   '2019-09-01', 'Physics',          3.00),
('Amber',   'Allen',    'amber.allen@example.com',    '555-0127', '2002-11-02', 'Female', '2021-01-15', 'Chemistry',        3.50),
('Blake',   'Young',    'blake.young@example.com',    '555-0128', '2001-07-21', 'Male',   '2020-09-01', 'Engineering',      3.35),
('Chloe',   'Hernandez','chloe.hernandez@example.com','555-0129', '2003-05-10', 'Female', '2022-09-01', 'Computer Science', 3.90),
('Derek',   'King',     'derek.king@example.com',     '555-0130', '2000-02-15', 'Male',   '2019-09-01', 'Biology',          2.85);

INSERT INTO incom.course (course_name, course_code, credits, department, start_date, end_date) VALUES
('Introduction to Programming', 'CS101', 3, 'Computer Science', '2024-01-15', '2024-05-15'),
('Calculus I',                  'MA101', 4, 'Mathematics',      '2024-01-15', '2024-05-15'),
('General Physics',             'PH101', 4, 'Physics',          '2024-01-15', '2024-05-15'),
('Data Structures',             'CS201', 3, 'Computer Science', '2024-01-15', '2024-05-15'),
('Linear Algebra',              'MA201', 3, 'Mathematics',      '2024-01-15', '2024-05-15');

-- Enroll Alice (1) in CS101, MA101, CS201
-- Enroll Bob   (2) in MA101, MA201
-- Enroll Grace (7) in CS101, CS201, PH101
-- Enroll Carol (3) in PH101
-- Enroll Eva   (5) in MA101, MA201
-- Enroll Henry (8) in MA101
-- Enroll Isla  (9) in PH101, MA201
-- Enroll Mia   (13) in CS101, CS201
-- Enroll Noah  (14) in MA201
-- Enroll Ryan  (18) in CS101
INSERT INTO incom.student_course (student_id, course_id) VALUES
(1, 1), (1, 2), (1, 4),
(2, 2), (2, 5),
(7, 1), (7, 3), (7, 4),
(3, 3),
(5, 2), (5, 5),
(8, 2),
(9, 3), (9, 5),
(13, 1), (13, 4),
(14, 5),
(18, 1);
