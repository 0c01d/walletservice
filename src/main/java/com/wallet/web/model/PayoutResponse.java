package com.wallet.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wallet.domain.Payout;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PayoutResponse {
    Integer id;
    UUID walletUUID;
    Integer value;


    public PayoutResponse(Payout payout) {
        this.id = payout.getId();
        this.walletUUID = payout.getWallet().getUuid();
        this.value = payout.getValue();
    }
}
