package com.embededinsurance.claims.client;

import com.embededinsurance.claims.dto.Customer;
import com.embededinsurance.claims.dto.Policy;
import com.embededinsurance.claims.exception.CustomerNotFoundException;
import com.embededinsurance.claims.exception.PolicyNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class PolicyServiceRestClient {

    @Autowired
    private RestTemplate restTemplate;

    public Policy fetchPolicyByReference(String policyReference){
        Policy policy = null;
        try{
            policy = restTemplate.getForObject("http://localhost:8081/api/policies/reference/" + policyReference, Policy.class);
        }catch (HttpServerErrorException errorException) {
            log.error("PolicyServiceRestClient::fetchPolicyByReference caught the HttpServer error {}", errorException.getResponseBodyAsString());
            throw new PolicyNotFoundException(errorException.getResponseBodyAsString());
        }catch (Exception e){
            log.error("PolicyServiceRestClient::fetchPolicyByReference caught the Generic error {}", e.getMessage());
        }
        return policy;
    }
}
