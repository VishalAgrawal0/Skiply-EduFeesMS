package com.edufees.receiptservice.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReceiptDTO {

    @NotBlank(message = "Student ID is required")
    private String studentId;

    @NotNull(message = "Amount is required")
    private Double amount;

    @NotNull(message = "Payment date is required")
    private LocalDate paymentDate;

    private Long receiptId;
    private String paymentMode;
    private String description;

    private String referenceId;
    private String cardNumberMasked;
    private String cardType;
    private String currency;

    // No-arg constructor
    public ReceiptDTO() {
    }

    // All-args constructor
    public ReceiptDTO(String studentId, Double amount, LocalDate paymentDate,
                      Long receiptId, String paymentMode, String description,
                      String referenceId, String cardNumberMasked,
                      String cardType, String currency) {
        this.studentId = studentId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.receiptId = receiptId;
        this.paymentMode = paymentMode;
        this.description = description;
        this.referenceId = referenceId;
        this.cardNumberMasked = cardNumberMasked;
        this.cardType = cardType;
        this.currency = currency;
    }

    // Getters and Setters

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Long getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getCardNumberMasked() {
        return cardNumberMasked;
    }

    public void setCardNumberMasked(String cardNumberMasked) {
        this.cardNumberMasked = cardNumberMasked;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
