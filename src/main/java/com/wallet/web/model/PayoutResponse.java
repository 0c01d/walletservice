package com.wallet.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wallet.domain.Payout;

import java.math.BigDecimal;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PayoutResponse {
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

    public PayoutResponse(Payout payout) {
        this.id = payout.getId();
        this.walletUUID = payout.getWallet().getUuid();
        this.value = payout.getValue();
    }
}
