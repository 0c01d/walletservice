package com.wallet.web.model;

import java.math.BigDecimal;
import java.util.UUID;

public class PayoutRequest {
    private final UUID walletUUID;
    private final BigDecimal value;

    public UUID getWalletUUID() {
        return walletUUID;
    }

    public BigDecimal getValue() {
        return value;
    }

    public PayoutRequest(UUID walletUUID, BigDecimal value) {
        this.walletUUID = walletUUID;
        this.value = value;
    }
}
