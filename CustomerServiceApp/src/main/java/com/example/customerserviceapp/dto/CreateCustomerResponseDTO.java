package com.example.customerserviceapp.dto;
import lombok.Builder;
import lombok.Data;


@Data
@Builder

public class CreateCustomerResponseDTO {

    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
}
