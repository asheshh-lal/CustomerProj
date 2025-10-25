package com.example.customerserviceapp.controller;

import com.example.customerserviceapp.dto.CreateCustomerResponseDTO;
import com.example.customerserviceapp.dto.CreateCustomerRequestDTO;
import com.example.customerserviceapp.dto.GlobalApiResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")

public class CustomerController
{
//    private final CustomerService customerService;

    //    @GetMapping("/{customerID}")
//    public Customer getCustomer(@PathVariable("customerID") String customerId){
//        return customerService.getCustomerById(customerId);
//    }
    @PostMapping("/")
    ResponseEntity<GlobalApiResponse> createCustomer(@RequestBody CreateCustomerRequestDTO request)
    {
        log.info("Creating customer..");
        // Here you would typically call a service method to save the customer
        return new ResponseEntity<>
                (
                        new GlobalApiResponse("SUCCESS","Customer created successfully",null),
                        HttpStatus.CREATED
                );
    }

    @GetMapping("/{id}/customer")
    ResponseEntity<GlobalApiResponse> getCustomer
            (
                    @PathVariable(name = "id") String id,
                    @RequestParam(value = "location",required = false)
                    String location
            )
    {

        log.info("Getting customer..");
//            if(location != null && !location.equalsIgnoreCase("KATHMANDU"))
//            {
//                log.info("location parameter provided: {}", location);
//                return new ResponseEntity<>
//                        (
//                        new GlobalApiResponse("FAILURE","Customer not found",location),
//                        HttpStatus.NOT_FOUND
//                        );
//            }
//            return new ResponseEntity<>
//            (
//                    new GlobalApiResponse("SUCCESS","Customer retrieved successfully",null),
//                    HttpStatus.OK
//            );
        CreateCustomerResponseDTO response = CreateCustomerResponseDTO.builder()
                .customerId(UUID.randomUUID().toString())
                .firstName("John")
                .lastName("Doe")
                .email("test@gmail.com")
                        .build();
        return new ResponseEntity<>
                (
                        new GlobalApiResponse("SUCCESS","Customer retrieved successfully",response),
                        HttpStatus.OK
                );
    }

}
