package com.wallet.service;

import com.wallet.domain.Wallet;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public interface WalletService {

    Wallet create();
    Wallet save(Wallet wallet);
    Wallet getWalletByUuid(UUID walletUUID) throws EntityNotFoundException;
    void deleteWalletByUuid(UUID walletUUID);
}