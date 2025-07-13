package com.edufees.receiptservice.service;

import com.edufees.receiptservice.dto.ReceiptDTO;

import java.util.List;

public interface ReceiptService {
    ReceiptDTO createReceipt(ReceiptDTO dto);
    ReceiptDTO getReceiptById(Long id);
    List<ReceiptDTO> getReceiptsByStudentId(String studentId);
}
