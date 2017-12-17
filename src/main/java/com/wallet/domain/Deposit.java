package com.wallet.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "deposit", schema = "wallet_service")
public class Deposit {

    @Id
    @Column(name = "deposit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deposit_wallet_uuid")
    private Wallet wallet;

    @Column(name = "deposit_value")
    private Integer value;

}