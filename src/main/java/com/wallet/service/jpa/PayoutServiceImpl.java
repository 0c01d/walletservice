package com.wallet.service.jpa;

import com.wallet.domain.Payout;
import com.wallet.domain.Wallet;
import com.wallet.repository.PayoutRepository;
import com.wallet.service.PayoutService;
import com.wallet.web.model.PayoutRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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
    public Payout create(PayoutRequest payoutRequest) {
        Payout payout = new Payout();

        Wallet wallet = walletService.getWalletByUuid(payoutRequest.getWalletUUID());
        if( wallet != null) {
            payout.setWallet(wallet);
        }

        if(wallet.getBalance() >= payoutRequest.getValue()) {
            payout.setValue(payoutRequest.getValue());
            wallet.setBalance(wallet.getBalance() - payoutRequest.getValue());
        }
        else {
            throw new EntityNotFoundException("Payout {" + payoutRequest.getValue() + "} more than your balance");
        }

        return payoutRepository.save(payout);
    }
    @Override
    @Transactional(readOnly = true)
    public Page<Payout> getPayoutByWalletUuid(UUID uuid, Integer page, Integer size){
        if(page == null)
            page = 0;
        if (size == null) {
            size = 5;
        }
        return payoutRepository.findAllPayoutByWalletUuid(uuid, new PageRequest(page, size));

    }
}