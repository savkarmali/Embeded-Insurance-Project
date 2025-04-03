package com.embededinsurance.policy.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "policy_360")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String policyNumber;

    @Column(nullable = false)
    private String policyType;

    @Column(name = "premium_amount", nullable = false)
    private Double premium;

    @Column(nullable = false)
    private LocalDate effectiveDate;

    @Column(nullable = false)
    private LocalDate expirationDate;

    // A business key from the customer service
    private String customerReference;        // Links policy to customer

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // No arg constructor
    // All arg constructor
    // Getters and Setters
}

