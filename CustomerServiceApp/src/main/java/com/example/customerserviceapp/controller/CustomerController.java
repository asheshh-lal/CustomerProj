package com.example.customerserviceapp.controller;

import com.example.customerserviceapp.dto.CreateCustomerRequestDTO;
import com.example.customerserviceapp.dto.GlobalApiResponse;
import jakarta.validation.Valid;
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

    @GetMapping("/by-email")
    public ResponseEntity<GlobalApiResponse> getCustomerByEmail(@RequestParam("email") String email)
    {
        log.info("Getting customer by email: {}", email);
        var payload = customerService.getByEmail(email);
        return ResponseEntity.ok(
                new GlobalApiResponse("SUCCESS", "Customer retrieved successfully", payload)
        );
    }

    @GetMapping
    public ResponseEntity<GlobalApiResponse> getAllCustomers() {
        var payload = customerService.getAll();
        return ResponseEntity.ok(
                new GlobalApiResponse("SUCCESS", "Customers retrieved successfully", payload)
        );
    }

    @PutMapping("/by-email")
    public ResponseEntity<GlobalApiResponse> updateByEmail(@RequestParam("email") String email, @Valid @RequestBody CreateCustomerRequestDTO request) {
        log.info("Updating customer with email: {}", email);
        // Implementation for updateByEmail would go here
        var payload = customerService.updateByEmail(email, request);
        return ResponseEntity.ok(
                new GlobalApiResponse("SUCCESS", "Customer updated successfully", null)
        );
    }

    @DeleteMapping("/by-email")
    public ResponseEntity<GlobalApiResponse> deleteByEmail(@RequestParam("email") String email) {
        log.info("Deleting customer with email: {}", email);
        customerService.deleteByEmail(email);
        return ResponseEntity.ok(
                new GlobalApiResponse("SUCCESS", "Customer deleted successfully", null)
        );
    }






}
