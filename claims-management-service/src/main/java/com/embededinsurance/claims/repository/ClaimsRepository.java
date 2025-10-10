package com.embededinsurance.claims.repository;

import com.embededinsurance.claims.dto.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimsRepository extends JpaRepository<Claim, Long> {
}
