package com.embededinsurance.payment.service;

import com.embededinsurance.payment.dto.Payment;
import com.embededinsurance.payment.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @BeforeEach
    void SetUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePayment(){

        Payment payment = new Payment();
        payment.setId(1L);
        payment.setPolicyId(12345L);
        payment.setCustomerId(1212L);
        payment.setAmount(20);
        payment.setPaymentStatus("pending");

        //Mocking the save method
        when(paymentRepository.save(payment)).thenReturn(payment);

        //When: calling the createPayment method
        Payment result = paymentService.createPayment(payment);

        //Then: Verifying the result and interactions
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(12345L, result.getPolicyId());
        assertEquals(1212L, result.getCustomerId());
        assertEquals(20, result.getAmount());
        assertEquals("pending", result.getPaymentStatus());
        verify(paymentRepository, times(1)).save(payment);

    }

}
