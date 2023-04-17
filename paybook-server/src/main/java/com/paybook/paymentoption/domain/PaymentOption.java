package com.paybook.paymentoption.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "payment_option")
public class PaymentOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private Long id;

    @NotNull
    private Long userId;

    @NotBlank
    private String title;

    private String bank;

    @Size(max = 1000)
    @Column(length = 1000)
    private String note;

    @Builder
    public PaymentOption(Long id, Long userId, String title, String bank, String note) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.bank = bank;
        this.note = note;
    }

    public PaymentOption update(PaymentOption requestPaymentOption) {
        this.title = requestPaymentOption.getTitle();
        this.bank = requestPaymentOption.getBank();
        this.note = requestPaymentOption.getNote();

        return this;
    }
}
