package com.wallet.service;

import com.wallet.domain.Wallet;
import com.wallet.web.model.WalletRequest;

import java.util.UUID;

public interface WalletService {

    Wallet getWalletByUuid(UUID uuid);
    Wallet createWallet(WalletRequest walletRequest);
    void deleteWalletByUuid(UUID uuid);

}