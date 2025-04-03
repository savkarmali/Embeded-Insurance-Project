package com.embededinsurance.claims.client;

import com.embededinsurance.claims.dto.Customer;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "customer-service", url = "http://localhost:8082/customers")
public interface CustomerFeignClient {

    @GetMapping("/reference/{customerReference}")
    public Customer getCustomeByReference(@PathVariable String customerReference);
}
