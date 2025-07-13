package com.edufees.studentservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edufees.studentservice.dto.StudentDTO;
import com.edufees.studentservice.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/students")
@Tag(name = "Student API", description = "Operations related to student management")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @Operation(summary = "Add a new student")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Student created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public StudentDTO addStudent(@Valid @RequestBody StudentDTO dto) {
        logger.info("POST /api/v1/students - Adding new student :", dto);
        StudentDTO created = studentService.addStudent(dto);
        logger.debug("Student created successfully: {}", created);
        return created;
    }

    @Operation(summary = "Get student by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Student found"),
        @ApiResponse(responseCode = "404", description = "Student not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public StudentDTO getStudent(@PathVariable("id") String id) {
        logger.info("GET /api/v1/students/{} - Fetching student details", id);
        StudentDTO student = studentService.getStudentById(id);
        logger.debug("Student retrieved: {}", student);
        return student;
    }

    @Operation(summary = "Get pending fees by student ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pending fees fetched successfully"),
        @ApiResponse(responseCode = "404", description = "Student not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/fees/{studentId}")
    public Integer getPendingFees(@PathVariable("studentId") String studentId) {
        logger.info("GET /api/v1/students/fees/{} - Fetching pending fees", studentId);
        Integer totalFees = studentService.getPendingFeesByStudentId(studentId);
        logger.debug("Pending fees for student ID {}: {}", studentId, totalFees);
        return totalFees;
    }
}
