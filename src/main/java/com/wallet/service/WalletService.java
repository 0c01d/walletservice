package com.wallet.service;

import com.wallet.domain.Wallet;

import java.util.UUID;

public interface WalletService {

    Wallet getWalletByUuid(UUID uuid);
    Wallet createWallet(String walletRequest);
    void deleteWalletByUuid(UUID uuid);

}