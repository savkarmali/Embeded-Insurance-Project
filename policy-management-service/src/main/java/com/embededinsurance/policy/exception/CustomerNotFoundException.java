package com.embededinsurance.policy.exception;

public class CustomerNotFoundException extends RuntimeException{

    // Constructor with error message only
    public CustomerNotFoundException(String message) {
        super(message);     // Pass the message to the superclass
    }

}
