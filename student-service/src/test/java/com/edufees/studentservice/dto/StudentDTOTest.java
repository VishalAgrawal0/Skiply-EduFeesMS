package com.edufees.studentservice.dto;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class StudentDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidStudentDTO() {
        StudentDTO dto = new StudentDTO(
                "101",
                "John",
                "10",
                "987654321",
                "ABC School"
        );

        Set<ConstraintViolation<StudentDTO>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void testInvalidStudentDTO_MissingFields() {
        StudentDTO dto = new StudentDTO(); // empty

        Set<ConstraintViolation<StudentDTO>> violations = validator.validate(dto);
        assertThat(violations).hasSize(5);
    }

    @Test
    void testInvalidMobileNumberPattern() {
        StudentDTO dto = new StudentDTO(
                "102",
                "Jane",
                "9",
                "1234abc89", // invalid mobile number
                "XYZ School"
        );

        Set<ConstraintViolation<StudentDTO>> violations = validator.validate(dto);
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("mobileNumber"));
    }
}
