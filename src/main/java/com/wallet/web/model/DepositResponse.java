package com.wallet.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wallet.domain.Deposit;

import java.math.BigDecimal;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepositResponse {

    private final Long id;
    private final UUID walletUUID;
    private final BigDecimal value;

    public Long getId() {
        return id;
    }

    public UUID getWalletUUID() {
        return walletUUID;
    }

    public BigDecimal getValue() {
        return value;
    }

    public DepositResponse(Deposit deposit) {
        this.id = deposit.getId();
        this.walletUUID = deposit.getWallet().getUuid();
        this.value = deposit.getValue();
    }
}