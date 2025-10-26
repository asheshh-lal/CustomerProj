package com.example.customerserviceapp.repo;

import com.example.customerserviceapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepo extends JpaRepository<Customer, UUID> {
    Optional<Customer> findByEmail(String email);
}
