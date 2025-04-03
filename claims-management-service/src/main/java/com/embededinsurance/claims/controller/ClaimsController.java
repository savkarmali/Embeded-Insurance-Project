package com.embededinsurance.claims.controller;

import com.embededinsurance.claims.dto.Claim;
import com.embededinsurance.claims.service.ClaimsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
public class ClaimsController {

    @Autowired
    private ClaimsService claimsService;

    @GetMapping("/{id}")
    public Claim getClaim(@PathVariable Long id) {
        return claimsService.getClaim(id);
    }

    @GetMapping
    public List<Claim> getClaims() {
        return claimsService.getAllClaims();
    }

    @PostMapping
    public Claim fileClaim(@RequestBody Claim claim) {
        return claimsService.validateAndCreateClaim(claim);
    }

    @PutMapping("/{id}")
    public Claim updatePolicy(@PathVariable Long id, @RequestBody Claim claim) {
        return claimsService.updateClaim(id, claim);
    }

    @DeleteMapping("/{id}")
    public void deletePolicy(@PathVariable Long id) {
        claimsService.deleteClaim(id);
    }
}
