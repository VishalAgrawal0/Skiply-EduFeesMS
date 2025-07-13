package com.edufees.studentservice.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edufees.studentservice.constants.FeeConstants;
import com.edufees.studentservice.dto.StudentDTO;
import com.edufees.studentservice.entity.Fees;
import com.edufees.studentservice.entity.Student;
import com.edufees.studentservice.exception.StudentNotFoundException;
import com.edufees.studentservice.model.FeeDefinition;
import com.edufees.studentservice.repository.FeesRepository;
import com.edufees.studentservice.repository.StudentRepository;
import com.edufees.studentservice.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FeesRepository feesRepository;

    @Override
    @Transactional
    public StudentDTO addStudent(StudentDTO dto) {
        logger.info("Adding new student with ID: {}", dto);

        // Save student first
        Student student = mapToEntity(dto);
        Student saved = studentRepository.save(student);
        logger.debug("Student saved successfully: {}", saved);

        // Fetch fees based on grade
        List<FeeDefinition> feeDefinitions = FeeConstants.getFeesByGrade(saved.getGrade());

        List<Fees> feesList = feeDefinitions.stream().map(def -> {
            Fees f = new Fees();
            f.setStudentId(saved.getStudentId());
            f.setType(def.getType().name()); 
            f.setAmount((double) def.getAmount()); 
            return f;
        }).toList();

        feesRepository.saveAll(feesList);
        logger.debug("Fees saved for student ID: {}", saved);

        return mapToDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDTO getStudentById(String studentId) {
        logger.info("Fetching student by ID: {}", studentId);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> {
                    logger.warn("Student not found for ID: {}", studentId);
                    return new StudentNotFoundException("Student ID not found: " + studentId);
                });
        logger.debug("Student retrieved successfully: {}", student);
        return mapToDTO(student);
    }

    private Student mapToEntity(StudentDTO dto) {
        Student s = new Student();
        s.setStudentId(dto.getStudentId());
        s.setName(dto.getName());
        s.setGrade(dto.getGrade());
        s.setMobileNumber(dto.getMobileNumber());
        s.setSchoolName(dto.getSchoolName());
        return s;
    }

    private StudentDTO mapToDTO(Student s) {
        StudentDTO dto = new StudentDTO();
        dto.setStudentId(s.getStudentId());
        dto.setName(s.getName());
        dto.setGrade(s.getGrade());
        dto.setMobileNumber(s.getMobileNumber());
        dto.setSchoolName(s.getSchoolName());
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getPendingFeesByStudentId(String studentId) {
        List<Fees> feesList = feesRepository.findByStudentId(studentId);
        return feesList.stream().mapToInt(fee -> fee.getAmount().intValue()).sum();
    }
}
