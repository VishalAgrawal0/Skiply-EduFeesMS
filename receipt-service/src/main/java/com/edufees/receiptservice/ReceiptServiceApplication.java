package com.edufees.receiptservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.edufees.receiptservice.client")
public class ReceiptServiceApplication {

	private static final Logger logger = LoggerFactory.getLogger(ReceiptServiceApplication.class);

	public static void main(String[] args) {
		logger.info("Starting Receipt Service Application...");
		SpringApplication.run(ReceiptServiceApplication.class, args);
		logger.info("Receipt Service Application started successfully.");
	}
}
