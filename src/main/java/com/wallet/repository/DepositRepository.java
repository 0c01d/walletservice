package com.wallet.repository;

import com.wallet.domain.Deposit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepositRepository extends JpaRepository<Deposit, Long> {

    Page<Deposit> findAllDepositByWalletUuid(UUID uuid, Pageable pageable);

}

