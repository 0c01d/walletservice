package com.wallet.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wallet.domain.Deposit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepositResponse {

    Integer id;
    UUID walletUUID;
    Integer value;


    public DepositResponse(Deposit deposit) {
        this.id = deposit.getId();
        this.walletUUID = deposit.getWallet().getUuid();
        this.value = deposit.getValue();
    }
}