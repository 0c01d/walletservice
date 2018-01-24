package com.wallet.service.jpa;

import com.wallet.domain.Payout;
import com.wallet.domain.Wallet;
import com.wallet.exception.InsufficientFundsException;
import com.wallet.repository.PayoutRepository;
import com.wallet.service.PayoutService;
import com.wallet.web.model.PayoutRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class PayoutServiceImpl implements PayoutService {

    private final PayoutRepository payoutRepository;
    private final WalletServiceImpl walletService;

    @Autowired
    public PayoutServiceImpl(PayoutRepository payoutRepository, WalletServiceImpl walletService) {
        this.payoutRepository = payoutRepository;
        this.walletService = walletService;
    }

    @Override
    @Transactional
    public Payout create(PayoutRequest payoutRequest) throws InsufficientFundsException {
        Wallet wallet = walletService.getWalletByUuid(payoutRequest.getWalletUUID());

        if (wallet.getBalance().compareTo(payoutRequest.getValue()) < 0) {
            throw new InsufficientFundsException("Payout {" + payoutRequest.getValue() + "} more than your balance");
        }

        Payout payout = new Payout(wallet, payoutRequest.getValue());
        wallet.setBalance(wallet.getBalance().subtract(payoutRequest.getValue()));

        walletService.save(wallet);
        return payoutRepository.save(payout);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Payout> getPayoutByWalletUuid(UUID walletUUID, Integer page, Integer size) {
        if (page == null) page = 0;
        if (size == null) size = 5;
        return payoutRepository.findAllPayoutByWalletUuid(walletUUID, PageRequest.of(page, size));
    }
}