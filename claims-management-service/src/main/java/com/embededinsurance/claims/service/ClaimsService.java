package com.embededinsurance.claims.service;

import com.embededinsurance.claims.dto.Claim;

import java.util.List;

public interface ClaimsService {

    Claim getClaim(Long id);
    
    List<Claim> getAllClaims();

    Claim validateAndCreateClaim(Claim claim);

    Claim updateClaim(Long id, Claim claim);

    void deleteClaim(Long id);
}
