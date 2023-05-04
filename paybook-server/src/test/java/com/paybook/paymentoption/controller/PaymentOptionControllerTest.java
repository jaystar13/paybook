package com.paybook.paymentoption.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paybook.common.documentation.WithSecurity;
import com.paybook.common.dto.WithMockCustomUser;
import com.paybook.paymentoption.dto.PaymentOptionRequest;
import com.paybook.paymentoption.dto.PaymentOptionResponse;
import com.paybook.paymentoption.service.PaymentOptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PaymentOptionController.class)
class PaymentOptionControllerTest extends WithSecurity {

    @MockBean
    private PaymentOptionService paymentOptionService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext) {
        super.setUp(webApplicationContext);

        objectMapper = new ObjectMapper();
    }

    @DisplayName("결제방법을 모두 조회한다.")
    @WithMockCustomUser
    @Test
    void findPaymentOptions() throws Exception {
        // given
        List<PaymentOptionResponse> paymentOptionResponses = Arrays.asList(
                new PaymentOptionResponse(1L, "title1", "bank1", "note1"),
                new PaymentOptionResponse(2L, "title2", "bank2", "note2")
        );

        given(paymentOptionService.findPaymentOptionAll()).willReturn(paymentOptionResponses);

        // then
        mockMvc.perform(get("/api/payment-options/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("결제방법을 아이디로 한건 조회한다.")
    @WithMockCustomUser
    @Test
    void findPaymentOption() throws Exception {
        // given
        given(paymentOptionService.findPaymentOption(anyLong())).willReturn(
                new PaymentOptionResponse(1L, "title1", "bank1", "note1").withId(1L)
        );

        // then
        mockMvc.perform(get("/api/payment-options/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("결제방법을 생성한다.")
    @WithMockCustomUser
    @Test
    void addPaymentOption() throws Exception {
        // given
        PaymentOptionRequest paymentOptionRequest = PaymentOptionRequest.builder()
                .title("title")
                .bank("bank")
                .note("note")
                .build();

        given(paymentOptionService.save(any(), any())).willReturn(1L);

        // then
        mockMvc.perform(post("/api/payment-options")
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(paymentOptionRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/payment-options/1"))
                .andDo(print());
    }

    @DisplayName("결제방법을 수정한다.")
    @WithMockCustomUser
    @Test
    void updatePaymentOption() throws Exception {
        // given
        PaymentOptionRequest updatePaymentOptionRequest = PaymentOptionRequest.builder()
                .title("수정된 급여계좌")
                .bank("대박은행")
                .note("수정된 비고")
                .build();

        // then
        mockMvc.perform(put("/api/payment-options/{id}", 1L)
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(updatePaymentOptionRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("결제방법을 단건 삭제한다.")
    @WithMockCustomUser
    @Test
    void deletePaymentOption() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(delete("/api/payment-options/{id}", 1L)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

}