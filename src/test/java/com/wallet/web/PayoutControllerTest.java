package com.wallet.web;

import com.wallet.web.model.PayoutRequest;
import com.wallet.web.model.PayoutResponse;
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

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("PayoutController Test")
public class PayoutControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Create_Deposit")
    void createDeposit() {
        PayoutRequest payoutRequest =
                new PayoutRequest(UUID.fromString("23b78e32-d319-4611-afbd-7c5a1ef6c70d"), BigDecimal.valueOf(100));
        PayoutResponse actualResponse = restTemplate.postForObject("/deposit/", payoutRequest, PayoutResponse.class);
        assertEquals("Invalid response", 100, actualResponse.getValue());
    }
}
