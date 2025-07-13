package com.edufees.receiptservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edufees.receiptservice.client.StudentClient;
import com.edufees.receiptservice.dto.ReceiptDTO;
import com.edufees.receiptservice.entity.Receipt;
import com.edufees.receiptservice.exception.ReceiptNotFoundException;
import com.edufees.receiptservice.repository.ReceiptRepository;
import com.edufees.receiptservice.service.ReceiptService;

import feign.FeignException;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    private static final Logger logger = LoggerFactory.getLogger(ReceiptServiceImpl.class);

    @Autowired
    private ReceiptRepository repository;

    @Autowired
    private StudentClient studentClient;

    @Override
    @Transactional 
    public ReceiptDTO createReceipt(ReceiptDTO dto) {
        logger.info("Attempting to create receipt for student ID: {}", dto.getStudentId());

        // Validating student using Feign Client
        try {
            studentClient.validateStudent(dto.getStudentId());
            logger.debug("Student validation successful for ID: {}", dto.getStudentId());
        } catch (FeignException.NotFound ex) {
            logger.warn("Student not found during validation. ID: {}", dto.getStudentId());
            throw new RuntimeException("Student not found with ID: " + dto.getStudentId());
        } catch (FeignException ex) {
            logger.error("Error validating student ID: {}. Reason: {}", dto.getStudentId(), ex.getMessage());
            throw new RuntimeException("Error validating student: " + ex.getMessage());
        }

        Receipt receipt = mapToEntity(dto);
        Receipt saved = repository.save(receipt);

        logger.info("Receipt created successfully with ID: {}", saved.getReceiptId());
        logger.debug("Created receipt details: {}", saved);

        return mapToDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ReceiptDTO getReceiptById(Long id) {
        logger.info("Fetching receipt by ID: {}", id);

        return repository.findById(id)
                .map(receipt -> {
                    logger.debug("Receipt found: {}", receipt);
                    return mapToDTO(receipt);
                })
                .orElseThrow(() -> {
                    logger.warn("Receipt not found with ID: {}", id);
                    return new ReceiptNotFoundException("Receipt not found with ID: " + id);
                });
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReceiptDTO> getReceiptsByStudentId(String studentId) {
        logger.info("Fetching all receipts for student ID: {}", studentId);

        List<ReceiptDTO> receipts = repository.findByStudentId(studentId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        logger.debug("Total receipts found: {} for student ID: {}", receipts.size(), studentId);
        return receipts;
    }

    private Receipt mapToEntity(ReceiptDTO dto) {
        Receipt r = new Receipt();
        r.setStudentId(dto.getStudentId());
        r.setAmount(dto.getAmount());
        r.setPaymentMode(dto.getPaymentMode());
        r.setPaymentDate(dto.getPaymentDate());
        r.setDescription(dto.getDescription());

        // additional fields
        r.setReferenceId(dto.getReferenceId());
        r.setCardNumberMasked(dto.getCardNumberMasked());
        r.setCardType(dto.getCardType());
        r.setCurrency(dto.getCurrency() != null ? dto.getCurrency() : "AED");

        return r;
    }

    private ReceiptDTO mapToDTO(Receipt r) {
    ReceiptDTO dto = new ReceiptDTO();
    dto.setReceiptId(r.getReceiptId());
    dto.setStudentId(r.getStudentId());
    dto.setAmount(r.getAmount());
    dto.setPaymentMode(r.getPaymentMode());
    dto.setPaymentDate(r.getPaymentDate());
    dto.setDescription(r.getDescription());
    dto.setReferenceId(r.getReferenceId());
    dto.setCardNumberMasked(r.getCardNumberMasked());
    dto.setCardType(r.getCardType());
    dto.setCurrency(r.getCurrency());
    return dto;
}
}