package com.wallet.service;

import com.wallet.domain.Deposit;
import com.wallet.exception.DepositLimitException;
import com.wallet.web.model.DepositRequest;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface DepositService {

    Deposit create(DepositRequest depositRequest) throws DepositLimitException;
    Page<Deposit> getDepositByWalletUuid(UUID walletUUID, Integer page, Integer size);
}
