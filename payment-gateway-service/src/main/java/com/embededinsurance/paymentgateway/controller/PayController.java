package com.embededinsurance.paymentgateway.controller;

import com.embededinsurance.paymentgateway.service.PayService;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment-gateway")
public class PayController {

    @Autowired
    private PayService payService;

    @CrossOrigin(origins = "http://localhost:63342") // Enable CORS for this endpoint
    @PostMapping("/create-order")
    public String createOrder(@RequestParam int amount, @RequestParam String currency){
        try {
            return payService.createOrder(amount, currency, "receiptId_100");
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }
    }

}
