package com.wallet.service.jpa;

import com.wallet.domain.Deposit;
import com.wallet.domain.Wallet;
import com.wallet.exception.DepositLimitException;
import com.wallet.repository.DepositRepository;
import com.wallet.service.DepositService;
import com.wallet.web.model.DepositRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class DepositServiceImpl implements DepositService {

    private final DepositRepository depositRepository;
    private final WalletServiceImpl walletService;

    private final BigDecimal depositLimit = BigDecimal.valueOf(30000);

    @Autowired
    public DepositServiceImpl(DepositRepository depositRepository, WalletServiceImpl walletService) {
        this.depositRepository = depositRepository;
        this.walletService = walletService;
    }

    @Override
    @Transactional
    public Deposit create(DepositRequest depositRequest) throws DepositLimitException {
        Wallet wallet = walletService.getWalletByUuid(depositRequest.getWalletUUID());

        if (depositRequest.getValue().compareTo(depositLimit) > 0  ) {
            throw new DepositLimitException("Deposit {" + depositRequest.getValue() + "} more than limit {" + depositLimit + "}");
        }

        Deposit deposit = new Deposit(wallet, depositRequest.getValue());
        wallet.setBalance(wallet.getBalance().add(depositRequest.getValue()));

        walletService.save(wallet);
        return depositRepository.save(deposit);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Deposit> getDepositByWalletUuid(UUID uuid, Integer page, Integer size){
        if (page == null) page = 0;
        if (size == null) size = 5;
        return depositRepository.findAllDepositByWalletUuid(uuid, PageRequest.of(page, size));

    }
}