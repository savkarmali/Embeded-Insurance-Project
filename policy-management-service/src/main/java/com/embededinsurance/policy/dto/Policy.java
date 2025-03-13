package com.embededinsurance.policy.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "policies")
@Getter
@Setter
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String policyNumber;
    private String policyType;
    private Date effectiveDate;
    private Date expirationDate;
    private Double premium;


    // Getters and Setters
}

