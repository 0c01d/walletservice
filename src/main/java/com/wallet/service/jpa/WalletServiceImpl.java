package com.wallet.service.jpa;

import com.wallet.domain.Wallet;
import com.wallet.repository.WalletRepository;
import com.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.UUID;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet getWalletByUuid(UUID walletUUID) throws EntityNotFoundException {
        return walletRepository.findById(walletUUID).orElseThrow(() ->
            new EntityNotFoundException("Wallet '{" + walletUUID + "}' not found")
        );
    }

    @Override
    public Wallet save(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet create() {
        Wallet wallet = new Wallet(UUID.randomUUID(), BigDecimal.ZERO);
        return walletRepository.save(wallet);
    }

    @Override
    public void deleteWalletByUuid(UUID walletUUID){
        walletRepository.deleteById(walletUUID);
    }
}