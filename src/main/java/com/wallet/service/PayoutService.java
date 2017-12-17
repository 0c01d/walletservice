package com.wallet.service;

import com.wallet.domain.Payout;
import com.wallet.web.model.PayoutRequest;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface PayoutService {

    Payout create(PayoutRequest payoutRequest);
    Page<Payout> getPayoutByWalletUuid(UUID uuid, Integer page, Integer size);
}
