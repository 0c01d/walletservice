package com.wallet.web.controller;

import com.wallet.domain.Wallet;
import com.wallet.service.WalletService;
import com.wallet.web.model.WalletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final WalletService walletService;

    @Autowired
    WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @RequestMapping(value = "/{walletUUID}", method = RequestMethod.GET)
    public WalletResponse getWalletByUUID(@PathVariable("walletUUID") final UUID uuid) {
        return new WalletResponse(walletService.getWalletByUuid(uuid));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public WalletResponse createWallet(@RequestBody String ignored, HttpServletResponse response) {
        Wallet wallet = walletService.create();
        response.addHeader(HttpHeaders.LOCATION, "/wallet/" + wallet.getUuid());
        return new WalletResponse(wallet);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{walletUUID}", method = RequestMethod.DELETE)
    public void deleteWalletByUUID(@PathVariable("walletUUID") final UUID uuid) {
        walletService.deleteWalletByUuid(uuid);
    }
}
