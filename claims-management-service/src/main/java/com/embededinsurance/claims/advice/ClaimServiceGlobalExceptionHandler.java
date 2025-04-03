package com.embededinsurance.claims.advice;

import com.embededinsurance.claims.dto.CustomErrorResponse;
import com.embededinsurance.claims.exception.CustomerNotFoundException;

import com.embededinsurance.claims.exception.PolicyNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ClaimServiceGlobalExceptionHandler {

    @ExceptionHandler(PolicyNotFoundException.class)
    public ResponseEntity<?> handlePolicyNotFoundException(PolicyNotFoundException ex) throws JsonProcessingException {
        log.error("Handling PolicyNotFoundException: {}", ex.getMessage());
        CustomErrorResponse errorResponse = new ObjectMapper()
                .readValue(ex.getMessage(), CustomErrorResponse.class);
        return ResponseEntity.internalServerError().body(errorResponse);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> handleCustomerNotFoundException(CustomerNotFoundException ex) throws JsonProcessingException {
        log.error("Handling CustomerNotFoundException: {}", ex.getMessage());
        CustomErrorResponse errorResponse = new ObjectMapper()
                .readValue(ex.getMessage(), CustomErrorResponse.class);
        return ResponseEntity.internalServerError().body(errorResponse);
    }
}
