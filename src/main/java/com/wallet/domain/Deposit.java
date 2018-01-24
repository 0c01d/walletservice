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
    @JoinColumn(name = "wallet")
    private Wallet wallet;

    @Column(name = "value")
    private BigDecimal value;

    public Long getId() {
        return id;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Deposit() {}

    public Deposit(Wallet wallet, BigDecimal value) {
        this.wallet = wallet;
        this.value = value;
    }
}