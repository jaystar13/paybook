package com.paybook.paymentoption.service;

import com.paybook.common.exception.EntityNotFoundException;
import com.paybook.common.exception.ErrorCode;
import com.paybook.paymentoption.domain.PaymentOption;
import com.paybook.paymentoption.domain.repository.PaymentOptionRepository;
import com.paybook.paymentoption.dto.PaymentOptionRequest;
import com.paybook.paymentoption.dto.PaymentOptionResponse;
import com.paybook.user.domain.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Service
public class PaymentOptionService {
    private final PaymentOptionRepository paymentOptionRepository;

    public Long save(User user, PaymentOptionRequest paymentOptionRequest) {
        PaymentOption paymentOption = paymentOptionRequest.toPaymentOption(user.getId());
        paymentOptionRepository.save(paymentOption);

        return paymentOption.getId();
    }

    public PaymentOptionResponse findPaymentOption(Long id) {
        PaymentOption paymentOption = findById(id);
        return PaymentOptionResponse.of(paymentOption);
    }

    public void updatePaymentOption(User user, Long id, PaymentOptionRequest paymentOptionRequest) {
        PaymentOption paymentOption = findById(id);
        paymentOption.update(paymentOptionRequest.toPaymentOption(user.getId()));
    }

    private PaymentOption findById(Long id) {
        return paymentOptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.PAYMENT_OPTION_NOT_FOUND));
    }
}
