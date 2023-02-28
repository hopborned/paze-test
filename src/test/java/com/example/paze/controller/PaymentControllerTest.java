package com.example.paze.controller;

import com.example.paze.service.PspService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PaymentController.class)
class PaymentControllerTest {

    @MockBean
    private PspService pspService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenGetRedirectUrlThenRedirect() throws Exception {
        when(pspService.getRedirectUrl(100)).thenReturn("/redirectUrl");

        mockMvc
                .perform(post("/submit")
                        .param("numberInput", "100")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/redirectUrl"));
    }

}