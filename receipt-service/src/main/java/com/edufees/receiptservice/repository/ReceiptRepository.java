package com.edufees.receiptservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edufees.receiptservice.entity.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    List<Receipt> findByStudentId(String studentId);
}
