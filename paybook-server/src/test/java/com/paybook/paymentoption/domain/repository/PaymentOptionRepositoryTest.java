package com.paybook.paymentoption.domain.repository;

import com.paybook.common.CustomDataJpaTest;
import com.paybook.paymentoption.domain.PaymentOption;
import com.paybook.user.domain.User;
import com.paybook.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@CustomDataJpaTest
class PaymentOptionRepositoryTest {

    @Autowired
    private PaymentOptionRepository paymentOptionRepository;

    @Autowired
    private UserRepository userRepository;

    private PaymentOption paymentOption1;

    private PaymentOption paymentOption2;

    private PaymentOption paymentOption3;

    @BeforeEach
    void setUp() {
        User user = User.builder()
                .id(1L)
                .name("test one")
                .username("test1")
                .email("test1@test.com")
                .password("password")
                .build();
        User saveUser = userRepository.save(user);

        paymentOption1 = new PaymentOption(1L, saveUser.getId(), "title1", "대한은행", "결제방법 테스트1");
        paymentOption2 = new PaymentOption(2L, saveUser.getId(), "title2", "민국은행", "결제방법 테스트2");
        paymentOption3 = new PaymentOption(3L, saveUser.getId(), "title3", "만세은행", "결제방법 테스트3");
    }

    @DisplayName("결재방법이 저장된다.")
    @Test
    void save() {
        //when
        List<PaymentOption> paymentOptions = savePaymentOption();

        //then
        assertThat(paymentOptions.size()).isGreaterThan(0);
    }

    @DisplayName("결재방법을 아이디로 조회한다.")
    @Test
    void find() {
        //given
        PaymentOption save = savePaymentOption().get(0);

        //when
        PaymentOption find = paymentOptionRepository.findById(save.getId()).orElseThrow();

        //then
        assertAll(
                () -> assertThat(find.getUserId()).isEqualTo(save.getUserId()),
                () -> assertThat(find.getId()).isEqualTo(save.getId()),
                () -> assertThat(find.getTitle()).isEqualTo(save.getTitle()),
                () -> assertThat(find.getBank()).isEqualTo(save.getBank()),
                () -> assertThat(find.getNote()).isEqualTo(save.getNote())
        );
    }

    @DisplayName("결재방법을 모두 조회한다.")
    @Test
    void findAll() {
        //given
        List<PaymentOption> savePaymentOptions = savePaymentOption();

        //when
        List<PaymentOption> paymentOptions = paymentOptionRepository.findAll();

        //then
        assertThat(paymentOptions.size()).isEqualTo(savePaymentOptions.size());
    }

    @DisplayName("결재방법을 아이디로 삭제한다.")
    @Test
    void delete() {
        //given
        List<PaymentOption> paymentOptions = savePaymentOption();

        //when
        paymentOptionRepository.deleteById(paymentOptions.get(0).getId());
        List<PaymentOption> afterDeletePaymentOptions = paymentOptionRepository.findAll();

        //then
        assertThat(afterDeletePaymentOptions.size()).isEqualTo(paymentOptions.size() - 1);

    }

    private List<PaymentOption> savePaymentOption() {
        List<PaymentOption> paymentOptions = Arrays.asList(paymentOption1, paymentOption2, paymentOption3);

        return paymentOptionRepository.saveAll(paymentOptions);
    }
}