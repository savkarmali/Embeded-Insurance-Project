package com.embededinsurance.policy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PolicyProducer {

    private static final String POLICY_TOPIC = "policy-updates";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendPolicyUpdate(String message) {
        kafkaTemplate.send(POLICY_TOPIC, message);
    }
}
