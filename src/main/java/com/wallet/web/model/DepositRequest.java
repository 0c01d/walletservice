package com.wallet.web.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

public class DepositRequest {
    @NotNull
    private final UUID walletUUID;

    @Positive
    private final BigDecimal value;

    public UUID getWalletUUID() {
        return walletUUID;
    }

    public BigDecimal getValue() {
        return value;
    }

    public DepositRequest(@JsonProperty("walletUUID") UUID walletUUID,
                          @JsonProperty("value") BigDecimal value) {
        this.walletUUID = walletUUID;
        this.value = value;
    }
}
