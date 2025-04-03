package com.embededinsurance.claims.client;

import com.embededinsurance.claims.dto.Policy;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "policy-service", url = "http://localhost:8081/api/policies")
public interface PolicyFeignClient {

    @GetMapping("/reference/{policyReference}")
    public Policy getPolicyByReference(@PathVariable String policyReference);



}
