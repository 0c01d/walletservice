package com.wallet.web;

import com.wallet.domain.Deposit;
import com.wallet.web.model.DepositRequest;
import com.wallet.web.model.DepositResponse;
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
@DisplayName("DepositController Test")
class DepositControllerTest {

    private URI l;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void deposit() {
        Deposit depositExp = new Deposit()
                .setValue(100);
        l = restTemplate.postForLocation("/deposit/", depositExp);
    }

    @Test
    @DisplayName("Create_Deposit")
    void createDeposit() {
        DepositRequest depositRequest = new DepositRequest()
                .setValue(100)
                .setWalletUUID(UUID.fromString("23b78e32-d319-4611-afbd-7c5a1ef6c70d"));
        DepositResponse actualResponse = restTemplate.postForObject("/deposit/", depositRequest, DepositResponse.class);
        assertEquals("Invalid response", 100, actualResponse.getValue());
    }

   /* @Test
    @DisplayName("Get Deposit")
    void getDeposit() {

        DepositResponse expectedResponse  = restTemplate.getForObject(l, DepositResponse.class);
        assertEquals("Invalid response",100 , expectedResponse.getValue());

    }*/
}
