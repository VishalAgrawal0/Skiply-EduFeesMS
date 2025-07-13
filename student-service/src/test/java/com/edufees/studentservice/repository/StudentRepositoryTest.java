package com.edufees.studentservice.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.edufees.studentservice.entity.Student;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository repository;

    @Test
    void testSaveAndFindById() {
        // Arrange
        Student student = new Student();
        student.setStudentId("202");
        student.setName("Jane Doe");
        student.setGrade("11");
        student.setMobileNumber("987654321");
        student.setSchoolName("Riverdale School");

        // Act
        repository.save(student);
        Optional<Student> found = repository.findById("202");

        // Assert
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Jane Doe");
    }
}
