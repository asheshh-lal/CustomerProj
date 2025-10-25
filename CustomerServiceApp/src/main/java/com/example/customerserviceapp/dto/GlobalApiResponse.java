package com.example.customerserviceapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor

public class GlobalApiResponse {
    private String status;
    private String message;
    private Object data;
}
