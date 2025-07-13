package com.edufees.receiptservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edufees.receiptservice.dto.ReceiptDTO;
import com.edufees.receiptservice.service.ReceiptService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/receipts")
@Tag(name = "Receipt API", description = "Operations related to fee collection and receipts")
public class ReceiptController {

    private static final Logger logger = LoggerFactory.getLogger(ReceiptController.class);

    @Autowired
    private ReceiptService receiptService;

    @Operation(summary = "Create a new receipt for a student")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Receipt created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ReceiptDTO> createReceipt(@Valid @RequestBody ReceiptDTO dto) {
        logger.info("POST /api/v1/receipts - Creating receipt: {}", dto);
        ReceiptDTO created = receiptService.createReceipt(dto);
        logger.debug("Receipt created successfully: {}", created);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Get receipt by receipt ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Receipt found"),
        @ApiResponse(responseCode = "404", description = "Receipt not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ReceiptDTO> getReceiptById(@PathVariable("id") Long id) {
        logger.info("GET /api/v1/receipts/{} - Fetching receipt by ID", id);
        ReceiptDTO receipt = receiptService.getReceiptById(id);
        logger.debug("Fetched receipt: {}", receipt);
        return ResponseEntity.ok(receipt);
    }

    @Operation(summary = "Get all receipts for a student by student ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Receipts retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "No receipts found for the student", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ReceiptDTO>> getReceiptsByStudent(@PathVariable("studentId") String studentId) {
        logger.info("GET /api/v1/receipts/student/{} - Fetching all receipts for student", studentId);
        List<ReceiptDTO> receipts = receiptService.getReceiptsByStudentId(studentId);
        logger.debug("Number of receipts found for student ID {}: {}", studentId, receipts.size());
        return ResponseEntity.ok(receipts);
    }
}
