package com.embededinsurance.claims.client;

import com.embededinsurance.claims.dto.Customer;
import com.embededinsurance.claims.exception.CustomerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class CustomerServiceRestClient {

    @Autowired
    private RestTemplate restTemplate;

    public Customer fetchCustomerByReference(String customerReference){
        Customer customer = null;
        try {
            customer = restTemplate.getForObject("http://localhost:8082/api/customers/reference/" + customerReference, Customer.class);
        }catch (HttpServerErrorException errorException) {
            log.error("CustomerServiceRestClient::fetchCustomerByReference caught the HttpServer error {}", errorException.getResponseBodyAsString());
            throw new CustomerNotFoundException(errorException.getResponseBodyAsString());
        }catch (Exception e){
            log.error("CustomerServiceRestClient::fetchCustomerByReference caught the Generic error {}", e.getMessage());
        }
        return customer;
    }
}
