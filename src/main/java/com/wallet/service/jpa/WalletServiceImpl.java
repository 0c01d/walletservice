package com.wallet.service.jpa;

import com.wallet.domain.Wallet;
import com.wallet.repository.WalletRepository;
import com.wallet.service.WalletService;
import com.wallet.web.model.WalletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;


    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;

    }

    @Override
    @Transactional(readOnly = true)
    public Wallet getWalletByUuid(UUID uuid) {
        Wallet wallet = walletRepository.findWalletByUuid(uuid);
        if (wallet == null) {
            throw new EntityNotFoundException("Wallet '{" + uuid + "}' not found");
        }
        return wallet;
    }
    @Override
    @Transactional
    public Wallet createWallet(WalletRequest walletRequest) {
        Wallet wallet = new Wallet()
                .setUuid(UUID.randomUUID())
                .setBalance(0);
        return walletRepository.save(wallet);
    }


    @Override
    @Transactional
    public void deleteWalletByUuid(UUID uuid){
        walletRepository.deleteWalletByUuid(uuid);
    }
}