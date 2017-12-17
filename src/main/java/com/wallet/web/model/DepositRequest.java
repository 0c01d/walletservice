package com.wallet.web.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
public class DepositRequest {
    UUID walletUUID;
    Integer value;
}
