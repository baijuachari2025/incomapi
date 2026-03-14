package com.nih.incomapi;

import com.nih.incomapi.service.CourseService;
import com.nih.incomapi.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class IncomapiApplicationTests {

	@MockitoBean
	CourseService courseService;

	@MockitoBean
	StudentService studentService;

	@Test
	void contextLoads() {
	}

}
