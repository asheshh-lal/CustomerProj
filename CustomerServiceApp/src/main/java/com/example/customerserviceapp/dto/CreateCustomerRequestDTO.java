package com.example.customerserviceapp.dto;


import lombok.*;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;


@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder

public class CreateCustomerRequestDTO {

    private String firstName;
    private String lastName;
    private String email;
    private LocalDate phoneNumber;
    private String location;

}
