package com.wallet.web;

import com.wallet.domain.Deposit;
import com.wallet.domain.Wallet;
import com.wallet.repository.WalletRepository;
import com.wallet.service.jpa.WalletServiceImpl;
import com.wallet.web.model.WalletRequest;
import com.wallet.web.model.WalletResponse;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.net.URI;
import java.util.ArrayList;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("WalletController Test")
public class WalletControllerTest {

    private URI l;

    @Before
    public void wallet() {
       Wallet walletExp = new Wallet()
                .setBalance(0);
       l = restTemplate.postForLocation("/wallet/" , walletExp);
    }

    @Autowired
    private TestRestTemplate restTemplate;

   /* @Autowired
    private WebApplicationContext wac;*/

  /*  @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private WalletServiceImpl walletService;*/

   /* @MockBean
    private WalletRepository walletRepositoryMock;*/

   /* @BeforeAll
    void setUpMockMvc(WebApplicationContext wac){
        mockMvc = webAppContextSetup(wac).build();
    }*/

    @Test
    @DisplayName("Create_Wallet")
    void createWallet() {
        Wallet wallet = new Wallet()
                .setUuid(UUID.fromString("23b78e32-d319-4611-afbd-7c5a1ef6c70d"))
                .setBalance(0);
        WalletResponse actualResponse = restTemplate.postForObject("/wallet/", wallet, WalletResponse.class);
        assertEquals("Invalid response", 0,actualResponse.getBalance() );
    }

   /* @Test
    @DisplayName("Get Wallet")
    void getWallet() {

        WalletResponse expectedResponse = restTemplate.getForObject(l , WalletResponse.class);
        assertEquals("Invalid response",0 , expectedResponse.getBalance());
    }*/

   /*  @Test
    @DisplayName("Delete Wallet")
    void deleteWallet(){
        restTemplate.delete(l);
         verify(walletRepositoryMock, times(1)).deleteWalletByUuid(UUID.fromString("23b78e32-d319-4611-afbd-7c5a1ef6c70d"));
     }*/


}
