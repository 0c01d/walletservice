package com.wallet.web.model;

import com.wallet.domain.Wallet;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
public class WalletResponse {

    private Integer balance;
    private UUID uuid;


    public WalletResponse(Wallet wallet) {
        this.balance = wallet.getBalance();
        this.uuid = wallet.getUuid();
    }
}
