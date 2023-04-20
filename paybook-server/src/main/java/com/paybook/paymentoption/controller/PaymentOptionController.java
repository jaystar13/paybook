package com.paybook.paymentoption.controller;

import com.paybook.paymentoption.dto.PaymentOptionResponse;
import com.paybook.paymentoption.service.PaymentOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/payment-options")
public class PaymentOptionController {

    private final PaymentOptionService paymentOptionService;

    @GetMapping("/all")
    public ResponseEntity<List<PaymentOptionResponse>> findPaymentOptionAll() {
        List<PaymentOptionResponse> paymentOptionAll = paymentOptionService.findPaymentOptionAll();
        return ResponseEntity.ok(paymentOptionAll);
    }
}
