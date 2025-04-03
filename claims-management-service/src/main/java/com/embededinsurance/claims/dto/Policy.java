package com.embededinsurance.claims.dto;

import lombok.Getter;

@Getter
public class Policy {

    private String policyNumber;

    // A business key from the customer service
    private String customerReference;

}
