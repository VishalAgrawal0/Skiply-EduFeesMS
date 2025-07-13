package com.edufees.receiptservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "student-service", url = "http://localhost:8081/api/v1/students")
public interface StudentClient {

    @GetMapping("/{id}")
    String validateStudent(@PathVariable("id") String studentId);
}
