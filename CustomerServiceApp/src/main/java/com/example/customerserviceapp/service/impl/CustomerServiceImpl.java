package com.example.customerserviceapp.service.impl;

import com.example.customerserviceapp.dto.CreateCustomerRequestDTO;
import com.example.customerserviceapp.dto.CreateCustomerResponseDTO;
import com.example.customerserviceapp.model.Customer;
import com.example.customerserviceapp.repo.CustomerRepo;
import com.example.customerserviceapp.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Override
    @Transactional(readOnly = true)
    public CreateCustomerResponseDTO getByEmail(String email) {
        var c = repo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with email: " + email));
        return toResponse(c);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CreateCustomerResponseDTO> getAll() {
        return repo.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public CreateCustomerResponseDTO updateByEmail(String email, CreateCustomerRequestDTO req) {
        var existing = repo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with email: " + email));

        // Update fields
        existing.setFirstName(req.getFirstName());
        existing.setLastName(req.getLastName());
        existing.setEmail(req.getEmail());

        var updated = repo.save(existing);
        return toResponse(updated);
    }

    @Override
    public void deleteByEmail(String email) {
        var c = repo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with email: " + email));
        repo.delete(c);
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
