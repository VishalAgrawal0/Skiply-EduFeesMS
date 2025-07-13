package com.edufees.receiptservice.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "receipts")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receiptId;

    private String studentId;
    private Double amount;
    private String paymentMode;
    private LocalDate paymentDate;
    private String description;

    private String referenceId;
    private String cardNumberMasked;
    private String cardType;
    private String currency = "AED";

    // No-arg constructor
    public Receipt() {
    }

    // All-args constructor
    public Receipt(Long receiptId, String studentId, Double amount, String paymentMode, LocalDate paymentDate,
                   String description, String referenceId, String cardNumberMasked,
                   String cardType, String currency) {
        this.receiptId = receiptId;
        this.studentId = studentId;
        this.amount = amount;
        this.paymentMode = paymentMode;
        this.paymentDate = paymentDate;
        this.description = description;
        this.referenceId = referenceId;
        this.cardNumberMasked = cardNumberMasked;
        this.cardType = cardType;
        this.currency = currency;
    }

    // Getters and Setters

    public Long getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }

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

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
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
