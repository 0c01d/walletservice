package com.wallet.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "payout", schema = "wallet_service")
public class Payout {

    @Id
    @Column(name = "payout_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payout_wallet_uuid")
    private Wallet wallet;

    @Column(name = "payout_value")
    public Integer value;


}