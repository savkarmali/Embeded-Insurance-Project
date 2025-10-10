package com.embededinsurance.claims.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "claim_360")
@Data
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String claimNumber;

    @Column(nullable = false)
    private String policyReference;

    @Column(nullable = false)
    private String customerReference;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClaimStatus claimStatus; // FILED, APPROVED, REJECTED

    @Column(nullable = false)
    private double claimAmount;

    @Column(nullable = false)
    private LocalDate dateFiled;

    private LocalDate dateResolved;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters, setters, and constructors…
}
