package com.wallet.web;

import com.wallet.web.model.DepositRequest;
import com.wallet.web.model.DepositResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("DepositController Test")
class DepositControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Create_Deposit")
    void createDeposit() {
        DepositRequest depositRequest =
                new DepositRequest(UUID.fromString("23b78e32-d319-4611-afbd-7c5a1ef6c70d"), BigDecimal.valueOf(100));
        DepositResponse actualResponse = restTemplate.postForObject("/deposit/", depositRequest, DepositResponse.class);
        assertEquals("Invalid response", BigDecimal.valueOf(100), actualResponse.getValue());
    }
}
