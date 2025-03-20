package com.embededinsurance.claims.service;

import com.embededinsurance.claims.dto.Claim;
import com.embededinsurance.claims.repository.ClaimsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimsServiceImpl implements ClaimsService {

    @Autowired
    private ClaimsRepository claimsRepository;

    @Override
    public Claim getClaim(Long id) {
        return claimsRepository.findById(id).orElseThrow(() -> new RuntimeException("Claim not found"));
    }

    @Override
    public List<Claim> getAllClaims() {
        return claimsRepository.findAll();
    }

    @Override
    public Claim createClaim(Claim claim) {
        return claimsRepository.save(claim);
    }

    @Override
    public Claim updateClaim(Long id, Claim updatedClaim) {
        Claim claim = getClaim(id);
        claim.setPolicyId(updatedClaim.getPolicyId());
        claim.setCustomerId(updatedClaim.getCustomerId());
        claim.setStatus(updatedClaim.getStatus());
        claim.setDescription(updatedClaim.getDescription());
        return claimsRepository.save(claim);
    }

    @Override
    public void deleteClaim(Long id) {
        claimsRepository.deleteById(id);
    }
}
