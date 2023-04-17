package com.paybook.paymentoption.dto;

import com.paybook.paymentoption.domain.PaymentOption;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class PaymentOptionResponse {
    private String title;

    private String bank;

    private String note;

    public static PaymentOptionResponse of(PaymentOption paymentOption) {
        return new PaymentOptionResponse(paymentOption.getTitle(), paymentOption.getBank(), paymentOption.getNote());
    }
}
