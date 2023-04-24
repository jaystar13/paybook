package com.paybook.paymentoption.controller;

import com.paybook.common.documentation.DocumentationWithNoSecurity;
import com.paybook.common.dto.WithMockCustomUser;
import com.paybook.paymentoption.dto.PaymentOptionResponse;
import com.paybook.paymentoption.service.PaymentOptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PaymentOptionController.class)
class PaymentOptionControllerTest extends DocumentationWithNoSecurity {

    @MockBean
    private PaymentOptionService paymentOptionService;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentationContextProvider) {
        super.setUp(webApplicationContext, restDocumentationContextProvider);
    }

    @DisplayName("결제방법을 모두 조회한다.")
    @WithMockCustomUser
    @Test
    void findPaymentOption() throws Exception {
        // given
        List<PaymentOptionResponse> paymentOptionResponses = Arrays.asList(
                new PaymentOptionResponse("title1", "bank1", "note1"),
                new PaymentOptionResponse("title2", "bank2", "note2")
        );

        given(paymentOptionService.findPaymentOptionAll()).willReturn(paymentOptionResponses);

        // then
        mockMvc.perform(get("/api/payment-options/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("payment-options/all"));
    }

}