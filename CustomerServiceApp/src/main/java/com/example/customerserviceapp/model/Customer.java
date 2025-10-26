package com.example.customerserviceapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "customers")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String firstName;

    @Column(nullable = false, length = 120)
    private String lastName;

    @Column(nullable = false, unique = true, length = 180)
    private String email;
    // add more fields later (phone, location, etc.)
}
