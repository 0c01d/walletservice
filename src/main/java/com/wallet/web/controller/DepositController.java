package com.wallet.web.controller;

import com.wallet.domain.Deposit;
import com.wallet.exception.DepositLimitException;
import com.wallet.service.DepositService;
import com.wallet.web.model.DepositRequest;
import com.wallet.web.model.DepositResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/deposit")
public class DepositController {

    private final DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public DepositResponse createDeposit(@RequestBody DepositRequest depositRequest, HttpServletResponse response)
            throws DepositLimitException {
        Deposit deposit = depositService.create(depositRequest);
        response.addHeader(HttpHeaders.LOCATION, "/deposit/" + depositRequest.getWalletUUID());
        return new DepositResponse(deposit);
    }

    @RequestMapping(value = "/{depositUUID}", method = RequestMethod.GET)
    public List<DepositResponse> getListDepositByUuid(@PathVariable("depositUUID") UUID uuid,
                                                      @RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "size", required = false) Integer size) {
        return depositService.getDepositByWalletUuid(uuid, page, size)
                .getContent()
                .stream()
                .map(DepositResponse::new)
                .collect(Collectors.toList());
    }
}