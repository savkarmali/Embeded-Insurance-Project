package com.embededinsurance.claims.exception;

public class PolicyNotFoundException extends RuntimeException {

    public PolicyNotFoundException(String s) {
        super(s);
    }
}
