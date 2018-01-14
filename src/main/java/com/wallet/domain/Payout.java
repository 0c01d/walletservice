package com.wallet.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "payout", schema = "wallet_service")
public class Payout {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet")

    private final Wallet wallet;

    @Column(name = "value")
    private final BigDecimal value;

    public Long getId() {
        return id;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Payout(Wallet wallet, BigDecimal value) {
        this.wallet = wallet;
        this.value = value;
    }
}