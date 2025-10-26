package com.example.customerserviceapp.service.impl;

import com.example.customerserviceapp.dto.CreateCustomerRequestDTO;
import com.example.customerserviceapp.dto.CreateCustomerResponseDTO;
import com.example.customerserviceapp.model.Customer;
import com.example.customerserviceapp.repo.CustomerRepo;
import com.example.customerserviceapp.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional // write operations by default
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo repo;

    public CustomerServiceImpl(CustomerRepo repo) {
        this.repo = repo;
    }

    @Override
    public CreateCustomerResponseDTO create(CreateCustomerRequestDTO req) {
        // Optional: basic duplicate check on unique email
        repo.findByEmail(req.getEmail()).ifPresent(existing -> {
            throw new IllegalArgumentException("Email already exists: " + req.getEmail());
        });

        Customer saved = repo.save(
                Customer.builder()
                        .firstName(req.getFirstName())
                        .lastName(req.getLastName())
                        .email(req.getEmail())
                        .build()
        );

        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public CreateCustomerResponseDTO get(UUID id) {
        Customer c = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + id));
        return toResponse(c);
    }

    // ----- helpers -----

    private CreateCustomerResponseDTO toResponse(Customer c) {
        return CreateCustomerResponseDTO.builder()
                .customerId(c.getId().toString())
                .firstName(c.getFirstName())
                .lastName(c.getLastName())
                .email(c.getEmail())
                .build();
    }
}
