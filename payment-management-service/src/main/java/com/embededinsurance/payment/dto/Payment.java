package com.embededinsurance.payment.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "billing_360")
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String policyReference;

    @Column(nullable = false)
    private String customerReference;

    @Column(unique = true)
    private String transactionId;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status; // SUCCESS, PENDING, FAILED

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters, setters, and constructors…
}
