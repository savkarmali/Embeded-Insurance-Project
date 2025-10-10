package com.embededinsurance.customer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CustomerConsumer {

    @KafkaListener(topics = "policy-updates", groupId = "group_id")
    public void consume(String message) {
        System.out.println("Received message : " + message);
        //Business logic for customer profile
    }
}
