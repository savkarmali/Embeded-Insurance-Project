package com.embededinsurance.claims.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "claims")
@Getter
@Setter
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long policyId;
    private Long customerId;
    private String status;
    private String description;

    // Getters and Setters
}
