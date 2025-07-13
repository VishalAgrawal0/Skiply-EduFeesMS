package com.edufees.studentservice.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.edufees.studentservice.dto.StudentDTO;
import com.edufees.studentservice.entity.Fees;
import com.edufees.studentservice.entity.Student;
import com.edufees.studentservice.exception.StudentNotFoundException;
import com.edufees.studentservice.repository.FeesRepository;
import com.edufees.studentservice.repository.StudentRepository;

class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private FeesRepository feesRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    private StudentDTO testDto;
    private Student testEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testDto = new StudentDTO();
        testDto.setStudentId("101");
        testDto.setName("John Doe");
        testDto.setGrade("1");
        testDto.setMobileNumber("123456789");
        testDto.setSchoolName("Springfield High");

        testEntity = new Student();
        testEntity.setStudentId("101");
        testEntity.setName("John Doe");
        testEntity.setGrade("1");
        testEntity.setMobileNumber("123456789");
        testEntity.setSchoolName("Springfield High");
    }

    @Test
    void testAddStudent_Success() {
        when(studentRepository.save(any(Student.class))).thenReturn(testEntity);
        when(feesRepository.saveAll(anyList())).thenReturn(Collections.emptyList());

        StudentDTO result = studentService.addStudent(testDto);

        assertNotNull(result);
        assertEquals("101", result.getStudentId());
        assertEquals("John Doe", result.getName());

        verify(studentRepository, times(1)).save(any(Student.class));
        verify(feesRepository, times(1)).saveAll(anyList());
    }

    @Test
    void testGetStudentById_Success() {
        when(studentRepository.findById("101")).thenReturn(Optional.of(testEntity));

        StudentDTO result = studentService.getStudentById("101");

        assertNotNull(result);
        assertEquals("101", result.getStudentId());
        assertEquals("Springfield High", result.getSchoolName());

        verify(studentRepository, times(1)).findById("101");
    }

    @Test
    void testGetStudentById_NotFound() {
        when(studentRepository.findById("999")).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> {
            studentService.getStudentById("999");
        });

        verify(studentRepository, times(1)).findById("999");
    }

    @Test
    void testGetPendingFeesByStudentId_WithFees() {
        List<Fees> mockFees = Arrays.asList(
                createFee("101", "TUITION", 1200.0),
                createFee("101", "EXAM", 800.0)
        );

        when(feesRepository.findByStudentId("101")).thenReturn(mockFees);

        Integer total = studentService.getPendingFeesByStudentId("101");

        assertEquals(2000, total);
        verify(feesRepository, times(1)).findByStudentId("101");
    }

    @Test
    void testGetPendingFeesByStudentId_NoFees() {
        when(feesRepository.findByStudentId("101")).thenReturn(Collections.emptyList());

        Integer total = studentService.getPendingFeesByStudentId("101");

        assertEquals(0, total);
        verify(feesRepository, times(1)).findByStudentId("101");
    }

    // Utility method
    private Fees createFee(String studentId, String type, Double amount) {
        Fees f = new Fees();
        f.setStudentId(studentId);
        f.setType(type);
        f.setAmount(amount);
        return f;
    }
}
