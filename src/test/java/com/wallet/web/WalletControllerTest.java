package com.wallet.web;

import com.wallet.domain.Wallet;
import com.wallet.web.model.WalletResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.test.util.AssertionErrors.assertEquals;

import java.math.BigDecimal;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("WalletController Test")
public class WalletControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Create_Wallet")
    void createWallet() {
        Wallet wallet = new Wallet(UUID.fromString("23b78e32-d319-4611-afbd-7c5a1ef6c70d"), BigDecimal.ZERO);
        WalletResponse actualResponse = restTemplate.postForObject("/wallet/", wallet, WalletResponse.class);
        assertEquals("Invalid response", 0, actualResponse.getBalance() );
    }
}
