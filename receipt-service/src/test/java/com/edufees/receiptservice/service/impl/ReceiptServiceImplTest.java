package com.edufees.receiptservice.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.edufees.receiptservice.client.StudentClient;
import com.edufees.receiptservice.dto.ReceiptDTO;
import com.edufees.receiptservice.entity.Receipt;
import com.edufees.receiptservice.exception.ReceiptNotFoundException;
import com.edufees.receiptservice.repository.ReceiptRepository;

import feign.FeignException;

class ReceiptServiceImplTest {

    @Mock
    private ReceiptRepository repository;

    @Mock
    private StudentClient studentClient;

    @InjectMocks
    private ReceiptServiceImpl service;

    private ReceiptDTO dto;
    private Receipt savedReceipt;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        dto = new ReceiptDTO();
        dto.setStudentId("101");
        dto.setAmount(500.00);
        dto.setPaymentMode("CARD");
        dto.setPaymentDate(LocalDate.now());
        dto.setDescription("Test Receipt");
        dto.setReferenceId("TXN123");
        dto.setCardNumberMasked("XXXX-XXXX-XXXX-1234");
        dto.setCardType("VISA");
        dto.setCurrency("AED");

        savedReceipt = new Receipt();
        savedReceipt.setReceiptId(1L);
        savedReceipt.setStudentId("101");
        savedReceipt.setAmount(500.00);
        savedReceipt.setPaymentMode("CARD");
        savedReceipt.setPaymentDate(dto.getPaymentDate());
        savedReceipt.setDescription("Test Receipt");
        savedReceipt.setReferenceId("TXN123");
        savedReceipt.setCardNumberMasked("XXXX-XXXX-XXXX-1234");
        savedReceipt.setCardType("VISA");
        savedReceipt.setCurrency("AED");
    }

    @Test
    void testCreateReceiptSuccess() {
        when(studentClient.validateStudent("101")).thenReturn("Valid");
        when(repository.save(any(Receipt.class))).thenReturn(savedReceipt);

        ReceiptDTO result = service.createReceipt(dto);

        assertNotNull(result);
        assertEquals("101", result.getStudentId());
        assertEquals(500.00, result.getAmount());
        verify(repository, times(1)).save(any(Receipt.class));
        verify(studentClient, times(1)).validateStudent("101");
    }

    @Test
    void testCreateReceiptThrowsIfStudentNotFound() {
        when(studentClient.validateStudent("101"))
                .thenThrow(FeignException.NotFound.class);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            service.createReceipt(dto);
        });

        assertTrue(ex.getMessage().contains("Student not found"));
        verify(repository, never()).save(any());
    }

    @Test
    void testCreateReceiptThrowsOnFeignOtherError() {
        when(studentClient.validateStudent("101"))
                .thenThrow(mock(FeignException.class)); // generic Feign error

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            service.createReceipt(dto);
        });

        assertTrue(ex.getMessage().contains("Error validating student"));
        verify(repository, never()).save(any());
    }

    @Test
    void testGetReceiptByIdSuccess() {
        when(repository.findById(1L)).thenReturn(Optional.of(savedReceipt));

        ReceiptDTO result = service.getReceiptById(1L);

        assertNotNull(result);
        assertEquals("101", result.getStudentId());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testGetReceiptByIdNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ReceiptNotFoundException.class, () -> {
            service.getReceiptById(1L);
        });
    }

    @Test
    void testGetReceiptsByStudentIdReturnsList() {
        when(repository.findByStudentId("101")).thenReturn(Arrays.asList(savedReceipt));

        List<ReceiptDTO> result = service.getReceiptsByStudentId("101");

        assertEquals(1, result.size());
        assertEquals("101", result.get(0).getStudentId());
        verify(repository, times(1)).findByStudentId("101");
    }

    @Test
    void testGetReceiptsByStudentIdReturnsEmptyList() {
        when(repository.findByStudentId("101")).thenReturn(Collections.emptyList());

        List<ReceiptDTO> result = service.getReceiptsByStudentId("101");

        assertTrue(result.isEmpty());
        verify(repository, times(1)).findByStudentId("101");
    }
}
