package com.embededinsurance.customer.advice;

import com.embededinsurance.customer.dto.CustomErrorResponse;
import com.embededinsurance.customer.dto.GlobalErrorCode;
import com.embededinsurance.customer.exception.CustomerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomerServiceGlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> handleCustomerNotFoundException(CustomerNotFoundException ex){
        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
        //        .errorCode(GlobalErrorCode.ERROR_CUSTOMER_NOT_FOUND)
                .errorMessage(ex.getMessage())
                .build();
        log.error("Handling CustomerNotFoundException: {}", ex.getMessage());
        return ResponseEntity.internalServerError().body(errorResponse);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> handleGenericException(Exception ex) {
//        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .errorCode(GlobalErrorCode.GENERIC_ERROR)
//                .errorMessage(ex.getMessage())
//                .build();
//        log.error("Handling Generic Exception: {}", ex.getMessage());
//        return ResponseEntity.internalServerError().body(errorResponse);
//    }
}
