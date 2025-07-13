package com.edufees.studentservice.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.edufees.studentservice.dto.StudentDTO;
import com.edufees.studentservice.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private StudentDTO studentDTO;

    @BeforeEach
    void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();

    studentDTO = new StudentDTO();
    studentDTO.setStudentId("101");
    studentDTO.setName("John Doe");
    studentDTO.setGrade("10");
    studentDTO.setMobileNumber("123456789"); 
    studentDTO.setSchoolName("Springfield High");
}

    @Test
    void testAddStudent() throws Exception {
        when(studentService.addStudent(any(StudentDTO.class))).thenReturn(studentDTO);

        mockMvc.perform(post("/api/v1/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(studentDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentId").value("101"))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testGetStudent() throws Exception {
        when(studentService.getStudentById("101")).thenReturn(studentDTO);

        mockMvc.perform(get("/api/v1/students/101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentId").value("101"))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }
}
