package com.embededinsurance.policy.advice;

import com.embededinsurance.policy.dto.CustomErrorResponse;
import com.embededinsurance.policy.exception.CustomerNotFoundException;
import com.embededinsurance.policy.exception.PolicyNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class PolicyServiceGlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> handleCustomerNotFoundException(CustomerNotFoundException ex) throws JsonProcessingException {
        log.error("Handling CustomerNotFoundExceptionFromPolicyService: {}", ex.getMessage());
        CustomErrorResponse errorResponse = new ObjectMapper()
                .readValue(ex.getMessage(), CustomErrorResponse.class);
        return ResponseEntity.internalServerError().body(errorResponse);
    }

    @ExceptionHandler(PolicyNotFoundException.class)
    public ResponseEntity<?> handlePolicyNotFoundException(PolicyNotFoundException ex) {
        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
        //        .errorCode(GlobalErrorCode.ERROR_CUSTOMER_NOT_FOUND)
                .errorMessage(ex.getMessage())
                .build();
        log.error("Handling PolicyNotFoundException: {}", ex.getMessage());
        return ResponseEntity.internalServerError().body(errorResponse);
    }
}
