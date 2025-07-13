package com.edufees.receiptservice.repository;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.edufees.receiptservice.entity.Receipt;

@DataJpaTest
class ReceiptRepositoryTest {

    @Autowired
    private ReceiptRepository receiptRepository;

    @BeforeEach
    void setUp() {
        Receipt receipt1 = new Receipt();
        receipt1.setStudentId("101");
        receipt1.setAmount(500.0);
        receipt1.setPaymentMode("CARD");
        receipt1.setPaymentDate(LocalDate.now());
        receipt1.setDescription("Test receipt 1");
        receipt1.setReferenceId("TXN1");
        receipt1.setCardNumberMasked("XXXX-XXXX-XXXX-1111");
        receipt1.setCardType("VISA");
        receipt1.setCurrency("AED");

        Receipt receipt2 = new Receipt();
        receipt2.setStudentId("101");
        receipt2.setAmount(700.0);
        receipt2.setPaymentMode("CASH");
        receipt2.setPaymentDate(LocalDate.now());
        receipt2.setDescription("Test receipt 2");
        receipt2.setReferenceId("TXN2");
        receipt2.setCardType("NA");
        receipt2.setCurrency("AED");

        receiptRepository.save(receipt1);
        receiptRepository.save(receipt2);
    }

    @Test
    void testFindByStudentId() {
        List<Receipt> receipts = receiptRepository.findByStudentId("101");

        assertThat(receipts).hasSize(2);
        assertThat(receipts.get(0).getStudentId()).isEqualTo("101");
    }

    @Test
    void testSaveReceipt() {
        Receipt receipt = new Receipt();
        receipt.setStudentId("102");
        receipt.setAmount(300.0);
        receipt.setPaymentMode("UPI");
        receipt.setPaymentDate(LocalDate.now());
        receipt.setDescription("Test receipt new");
        receipt.setReferenceId("TXN3");
        receipt.setCardType("NA");
        receipt.setCurrency("AED");

        Receipt saved = receiptRepository.save(receipt);

        assertThat(saved.getReceiptId()).isNotNull();
        assertThat(saved.getStudentId()).isEqualTo("102");
    }
}
