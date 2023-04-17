package com.paybook.paymentoption.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PaymentOptionTest {

    @DisplayName("결제방법 객체가 정상적으로 생성된다.")
    @Test
    void create() {
        PaymentOption paymentOption = new PaymentOption(1L, 1L, null, "bank", "note");

        assertThat(paymentOption.getBank()).isEqualTo("bank");
    }
}