package com.paybook.paymentoption.dto;

import com.paybook.paymentoption.domain.PaymentOption;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PaymentOptionRequest {

    private String title;

    private String bank;

    private String note;

    @Builder
    private PaymentOptionRequest(String title, String bank, String note) {
        this.title = title;
        this.bank = bank;
        this.note = note;
    }

    public PaymentOption toPaymentOption(Long userId) {
        return PaymentOption.builder()
                .userId(userId)
                .title(title)
                .bank(bank)
                .note(note)
                .build();
    }
}
