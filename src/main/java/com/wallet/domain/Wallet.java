package com.wallet.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "wallets", schema = "wallet_service")
public class Wallet {

    @Id
    @Column(name = "wallet_UUID")
    private UUID uuid;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Deposit> deposits = new ArrayList<>();

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Payout> payouts = new ArrayList<>();

    @Column(name = "balance")
    private Integer balance;

}