package com.wallet.service;

import com.wallet.domain.Deposit;
import com.wallet.web.model.DepositRequest;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface DepositService {

    Deposit create(DepositRequest depositRequest);
    Page<Deposit> getDepositByWalletUuid(UUID uuid, Integer page, Integer size);
}
