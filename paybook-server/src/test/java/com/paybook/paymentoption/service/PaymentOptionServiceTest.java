package com.paybook.paymentoption.service;

import com.paybook.common.exception.AuthException;
import com.paybook.common.exception.EntityNotFoundException;
import com.paybook.common.exception.ErrorCode;
import com.paybook.paymentoption.domain.PaymentOption;
import com.paybook.paymentoption.domain.repository.PaymentOptionRepository;
import com.paybook.paymentoption.dto.PaymentOptionRequest;
import com.paybook.paymentoption.dto.PaymentOptionResponse;
import com.paybook.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentOptionServiceTest {

    private PaymentOptionService paymentOptionService;

    @Mock
    private PaymentOptionRepository paymentOptionRepository;

    private PaymentOptionRequest paymentOptionRequest;

    private User user;

    @BeforeEach
    void setUp() {
        paymentOptionService = new PaymentOptionService(paymentOptionRepository);

        user = User.builder()
                .id(1L)
                .name("test one")
                .username("test1")
                .email("test1@test.com")
                .password("password")
                .build();

        paymentOptionRequest = PaymentOptionRequest.builder()
                .title("급여이체 결제")
                .bank("대한은행")
                .note("급여계좌")
                .build();
    }

    @DisplayName("결제방법을 저장한다.")
    @Test
    void save() {
        // when
        paymentOptionService.save(user, paymentOptionRequest);

        // then
        verify(paymentOptionRepository).save(any());
    }

    @DisplayName("결제방법을 아이디로 조회한다.")
    @Test
    void findPaymentOption() {
        // given
        PaymentOption salary = PaymentOption.builder()
                .id(1L)
                .title("급여계좌")
                .bank("대한은행")
                .note("급여계좌 테스트용")
                .build();

        when(paymentOptionRepository.findById(anyLong())).thenReturn(Optional.of(salary));

        // when
        PaymentOptionResponse paymentOptionResponse = paymentOptionService.findPaymentOption(1L);

        // then
        assertAll(
                () -> assertThat(paymentOptionResponse.getTitle()).isEqualTo(salary.getTitle()),
                () -> assertThat(paymentOptionResponse.getBank()).isEqualTo(salary.getBank()),
                () -> assertThat(paymentOptionResponse.getNote()).isEqualTo(salary.getNote())
        );
    }

    @DisplayName("존재하지 않는 결제방법 조회시 예외를 발생한다.")
    @Test
    void findPaymentOptionNotFoundException() {
        // given
        when(paymentOptionRepository.findById(anyLong())).thenReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> paymentOptionService.findPaymentOption(0L))
                .isExactlyInstanceOf(EntityNotFoundException.class);

    }

    @DisplayName("모든 결제방법을 조회한다.")
    @Test
    void findPaymentOptionAll() {
        // given
        PaymentOption salary = PaymentOption.builder()
                .id(1L)
                .title("급여계좌")
                .bank("대한은행")
                .note("급여계좌 테스트용")
                .build();

        PaymentOption loan = PaymentOption.builder()
                .id(2L)
                .title("대출계좌")
                .bank("대한은행")
                .note("대출계좌 테스트용")
                .build();

        List<PaymentOption> paymentOptions = Arrays.asList(salary, loan);

        when(paymentOptionRepository.findAll()).thenReturn(paymentOptions);

        // when
        List<PaymentOptionResponse> paymentOptionResponses = paymentOptionService.findPaymentOptionAll();

        // then
        assertThat(paymentOptionResponses.size()).isEqualTo(paymentOptions.size());
    }

    @DisplayName("결제방법을 수정한다.")
    @Test
    void updatePaymentOption() {
        // given
        PaymentOptionRequest updateRequest = PaymentOptionRequest.builder()
                .title("update title")
                .bank("update bank")
                .note("update note")
                .build();

        PaymentOption paymentOption = PaymentOption.builder()
                .id(1L)
                .userId(user.getId())
                .title("title")
                .bank("bank")
                .note("note")
                .build();

        when(paymentOptionRepository.findById(anyLong())).thenReturn(Optional.of(paymentOption));

        // when
        paymentOptionService.updatePaymentOption(user, 1L, updateRequest);

        // then
        assertAll(
                () -> assertThat(paymentOption.getTitle()).isEqualTo(updateRequest.getTitle()),
                () -> assertThat(paymentOption.getBank()).isEqualTo(updateRequest.getBank()),
                () -> assertThat(paymentOption.getNote()).isEqualTo(updateRequest.getNote())
        );

    }

    @DisplayName("결제방법을 단건 삭제한다.")
    @Test
    void deletePaymentOption() {
        // given
        PaymentOption salary = PaymentOption.builder()
                .id(2L)
                .userId(1L)
                .title("급여계좌")
                .bank("대한은행")
                .note("급여계좌 테스트용")
                .build();

        // when
        when(paymentOptionRepository.findById(anyLong())).thenReturn(Optional.of(salary));

        paymentOptionService.deletePaymentOption(user, 1L);

        // then
        verify(paymentOptionRepository).deleteById(1L);
    }

    @DisplayName("권한이 없는 유저가 결제방법을 삭제시 오류를 발생한다.")
    @Test
    void deletePaymentOptionException() {
        // given
        PaymentOption salary = PaymentOption.builder()
                .id(1L)
                .userId(2L)
                .title("급여계좌")
                .bank("대한은행")
                .note("급여계좌 테스트용")
                .build();

        // when
        when(paymentOptionRepository.findById(anyLong())).thenReturn(Optional.of(salary));

        // then
        assertThatThrownBy(() -> paymentOptionService.deletePaymentOption(user, 1L))
                .isExactlyInstanceOf(AuthException.class)
                .hasMessage(ErrorCode.PAYMENT_OPTION_UNAUTHORIZED.getMessage());
    }
}
