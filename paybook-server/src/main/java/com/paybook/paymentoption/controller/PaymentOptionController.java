package com.paybook.paymentoption.controller;

import com.paybook.paymentoption.dto.PaymentOptionRequest;
import com.paybook.paymentoption.dto.PaymentOptionResponse;
import com.paybook.paymentoption.service.PaymentOptionService;
import com.paybook.security.CurrentUser;
import com.paybook.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
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

    @GetMapping("/{id}")
    public ResponseEntity<PaymentOptionResponse> findPaymentOption(@PathVariable Long id) {
        return ResponseEntity.ok(paymentOptionService.findPaymentOption(id));
    }

    @PostMapping
    public ResponseEntity<Void> addPaymentOption(@CurrentUser User user,
                                                 @RequestBody @Valid PaymentOptionRequest paymentOptionRequest) {
        Long saveId = paymentOptionService.save(user, paymentOptionRequest);
        return ResponseEntity.created(URI.create("/api/payment-options/" + saveId)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePaymentOption(@CurrentUser User user,
                                                    @PathVariable Long id,
                                                    @RequestBody @Valid PaymentOptionRequest paymentOptionRequest) {
        paymentOptionService.updatePaymentOption(user, id, paymentOptionRequest);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentOption(@CurrentUser User user,
                                                    @PathVariable Long id) {
        paymentOptionService.deletePaymentOption(user, id);

        return ResponseEntity.noContent().build();
    }
}
