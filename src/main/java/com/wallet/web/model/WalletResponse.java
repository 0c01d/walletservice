package com.wallet.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wallet.domain.Wallet;

import java.math.BigDecimal;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WalletResponse {

    private final UUID uuid;
    private final BigDecimal balance;

    public UUID getUuid() {
        return uuid;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public WalletResponse(Wallet wallet) {
        this.uuid = wallet.getUuid();
        this.balance = wallet.getBalance();
    }
}
