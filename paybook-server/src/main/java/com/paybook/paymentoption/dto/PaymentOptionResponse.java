package com.paybook.paymentoption.dto;

import com.paybook.paymentoption.domain.PaymentOption;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class PaymentOptionResponse {
    @With(value = AccessLevel.PUBLIC)
    private Long id;

    private String title;

    private String bank;

    private String note;

    public static PaymentOptionResponse of(PaymentOption paymentOption) {
        return new PaymentOptionResponse(paymentOption.getId(), paymentOption.getTitle(), paymentOption.getBank(),
                paymentOption.getNote());
    }

    public static List<PaymentOptionResponse> listOf(List<PaymentOption> paymentOptions) {
        List<PaymentOptionResponse> paymentOptionResponses = new ArrayList<>();

        for (PaymentOption paymentOption : paymentOptions) {
            paymentOptionResponses.add(of(paymentOption));
        }

        return paymentOptionResponses;
    }
}

