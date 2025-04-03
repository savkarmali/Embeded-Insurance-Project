package com.embededinsurance.customer.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "customer_360")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;  // Business key

    @Column(name = "phone")
    private String mobile;

    private String address;

    @Column(name = "dateOfBirth")
    private LocalDate dob;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
// Getters and Setters
