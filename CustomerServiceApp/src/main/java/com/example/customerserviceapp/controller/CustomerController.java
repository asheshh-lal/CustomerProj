package com.example.customerserviceapp.controller;

import com.example.customerserviceapp.dto.CreateCustomerRequestDTO;
import com.example.customerserviceapp.dto.GlobalApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final com.example.customerserviceapp.service.CustomerService customerService;

    public CustomerController(com.example.customerserviceapp.service.CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<GlobalApiResponse> createCustomer(@RequestBody CreateCustomerRequestDTO request) {
        log.info("Creating customer..");
        var payload = customerService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new GlobalApiResponse("SUCCESS", "Customer created successfully", payload));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalApiResponse> getCustomer(@PathVariable("id") UUID id) {
        log.info("Getting customer..");
        var payload = customerService.get(id);
        return ResponseEntity.ok(new GlobalApiResponse("SUCCESS", "Customer retrieved successfully", payload));
    }
}
