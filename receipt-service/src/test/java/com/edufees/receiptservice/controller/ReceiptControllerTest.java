package com.edufees.receiptservice.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.edufees.receiptservice.dto.ReceiptDTO;
import com.edufees.receiptservice.service.ReceiptService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ReceiptController.class)
class ReceiptControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReceiptService receiptService;

    @Autowired
    private ObjectMapper objectMapper;

    private ReceiptDTO receiptDTO;

    @BeforeEach
    void setUp() {
        receiptDTO = new ReceiptDTO();
        receiptDTO.setReceiptId(1L);
        receiptDTO.setStudentId("101");
        receiptDTO.setAmount(500.00);
        receiptDTO.setPaymentMode("CARD");
        receiptDTO.setPaymentDate(LocalDate.now());
        receiptDTO.setDescription("Test Receipt");
        receiptDTO.setReferenceId("TXN123");
        receiptDTO.setCardNumberMasked("XXXX-XXXX-XXXX-1234");
        receiptDTO.setCardType("VISA");
        receiptDTO.setCurrency("AED");
    }

    @Test
    void testCreateReceipt() throws Exception {
        when(receiptService.createReceipt(any(ReceiptDTO.class))).thenReturn(receiptDTO);

        mockMvc.perform(post("/api/v1/receipts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(receiptDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentId").value("101"))
                .andExpect(jsonPath("$.amount").value(500.00));
    }

    @Test
    void testGetReceiptById() throws Exception {
        when(receiptService.getReceiptById(1L)).thenReturn(receiptDTO);

        mockMvc.perform(get("/api/v1/receipts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.receiptId").value(1))
                .andExpect(jsonPath("$.studentId").value("101"));
    }

    @Test
    void testGetReceiptsByStudent() throws Exception {
        when(receiptService.getReceiptsByStudentId("101")).thenReturn(Arrays.asList(receiptDTO));

        mockMvc.perform(get("/api/v1/receipts/student/101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].studentId").value("101"));
    }
}
