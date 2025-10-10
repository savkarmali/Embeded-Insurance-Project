package com.embededinsurance.customer.exception;

public class CustomerNotFoundException extends RuntimeException{

    // Constructor with error message only
    public CustomerNotFoundException(String message){
        super(message);
    }
}
