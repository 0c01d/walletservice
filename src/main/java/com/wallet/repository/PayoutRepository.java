package com.wallet.repository;

import com.wallet.domain.Payout;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PayoutRepository extends JpaRepository<Payout, Long> {

    Page<Payout> findAllPayoutByWalletUuid(UUID uuid, Pageable pageable);
}
