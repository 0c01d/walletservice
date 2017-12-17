package com.wallet.web;

import com.wallet.domain.Deposit;
import com.wallet.domain.Payout;
import com.wallet.web.model.DepositRequest;
import com.wallet.web.model.DepositResponse;
import com.wallet.web.model.PayoutRequest;
import com.wallet.web.model.PayoutResponse;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URI;
import java.util.UUID;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("PayoutController Test")
public class PayoutControllerTest {

    private URI l;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void payout() {
        Payout payoutExp = new Payout()
                .setValue(100);
        l = restTemplate.postForLocation("/payout/", payoutExp);
    }

    @Test
    @DisplayName("Create_Deposit")
    void createDeposit() {
        PayoutRequest payoutRequest = new PayoutRequest()
                .setValue(100)
                .setWalletUUID(UUID.fromString("23b78e32-d319-4611-afbd-7c5a1ef6c70d"));
        PayoutResponse actualResponse = restTemplate.postForObject("/deposit/", payoutRequest, PayoutResponse.class);
        assertEquals("Invalid response", 100, actualResponse.getValue());
    }
}
