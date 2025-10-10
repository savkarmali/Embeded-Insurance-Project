package com.embededinsurance.policy.exception;

public class PolicyNotFoundException extends RuntimeException {

    public PolicyNotFoundException(String msg) {
        super(msg);
    }
}
