package com.edufees.studentservice.service;

import com.edufees.studentservice.dto.StudentDTO;

public interface StudentService {
    StudentDTO addStudent(StudentDTO studentDTO);
    StudentDTO getStudentById(String studentId);

    Integer getPendingFeesByStudentId(String studentId);
}
