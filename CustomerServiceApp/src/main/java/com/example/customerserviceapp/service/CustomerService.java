// src/main/java/com/example/customerserviceapp/service/CustomerService.java
package com.example.customerserviceapp.service;

import com.example.customerserviceapp.dto.CreateCustomerRequestDTO;
import com.example.customerserviceapp.dto.CreateCustomerResponseDTO;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    CreateCustomerResponseDTO create(CreateCustomerRequestDTO req);
    CreateCustomerResponseDTO get(UUID id);
    CreateCustomerResponseDTO getByEmail(String email);
    List<CreateCustomerResponseDTO> getAll();
    CreateCustomerResponseDTO updateByEmail(String email, CreateCustomerRequestDTO req);
    void deleteByEmail(String email);
}
