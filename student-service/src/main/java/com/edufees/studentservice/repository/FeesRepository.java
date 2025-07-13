package com.edufees.studentservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edufees.studentservice.entity.Fees;

public interface FeesRepository extends JpaRepository<Fees, Long> {
    List<Fees> findByStudentId(String studentId);
}
