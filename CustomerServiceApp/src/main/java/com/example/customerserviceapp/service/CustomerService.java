// src/main/java/com/example/customerserviceapp/service/CustomerService.java
package com.example.customerserviceapp.service;

import com.example.customerserviceapp.dto.CreateCustomerRequestDTO;
import com.example.customerserviceapp.dto.CreateCustomerResponseDTO;
import java.util.UUID;

public interface CustomerService {
    CreateCustomerResponseDTO create(CreateCustomerRequestDTO req);
    CreateCustomerResponseDTO get(UUID id);
}
