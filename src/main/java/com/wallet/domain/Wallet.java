package com.wallet.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "wallet", schema = "wallet_service")
public class Wallet {

    @Id
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "balance")
    private BigDecimal balance;

    public UUID getUuid() {
        return uuid;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Wallet() {}

    public Wallet(UUID uuid, BigDecimal balance) {
        this.uuid = uuid;
        this.balance = balance;
    }
}