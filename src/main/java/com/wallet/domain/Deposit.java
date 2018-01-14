package com.wallet.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "deposit", schema = "wallet_service")
public class Deposit {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deposit_wallet_uuid")
    private final Wallet wallet;

    @Column(name = "deposit_value")
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

    public Deposit(Wallet wallet, BigDecimal value) {
        this.wallet = wallet;
        this.value = value;
    }
}