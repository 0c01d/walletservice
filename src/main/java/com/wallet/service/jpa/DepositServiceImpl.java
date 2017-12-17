package com.wallet.service.jpa;

import com.wallet.domain.Deposit;
import com.wallet.domain.Wallet;
import com.wallet.repository.DepositRepository;
import com.wallet.service.DepositService;
import com.wallet.web.model.DepositRequest;
import com.wallet.web.model.DepositResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
public class DepositServiceImpl implements DepositService {

    private final DepositRepository depositRepository;
    private final WalletServiceImpl walletService;

    @Autowired
    public DepositServiceImpl(DepositRepository depositRepository, WalletServiceImpl walletService) {
        this.depositRepository = depositRepository;
        this.walletService = walletService;
    }

    @Override
    public Deposit create(DepositRequest depositRequest) {
        int depositLimit = 30000;

        Deposit deposit = new Deposit();
        Wallet wallet = walletService.getWalletByUuid(depositRequest.getWalletUUID());
        if(wallet != null) {
            deposit.setWallet(wallet);
        }
        if(depositRequest.getValue() < depositLimit) {
            deposit.setValue(depositRequest.getValue());
            wallet.setBalance(wallet.getBalance() + depositRequest.getValue());
        }
        else {
            throw new EntityNotFoundException("Deposit {" + depositRequest.getValue() + "} more than limit {" + depositLimit + "}");
        }
        return depositRepository.save(deposit);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Deposit> getDepositByWalletUuid(UUID uuid, Integer page, Integer size){
        if(page == null)
            page = 0;
        if (size == null) {
            size = 5;
        }
        return depositRepository.findAllDepositByWalletUuid(uuid, new PageRequest(page, size));

    }
}