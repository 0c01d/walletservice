package com.wallet.web.controller;

import com.wallet.domain.Payout;
import com.wallet.exception.InsufficientFundsException;
import com.wallet.service.PayoutService;
import com.wallet.web.model.PayoutRequest;
import com.wallet.web.model.PayoutResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payout")
public class PayoutController {

    private final PayoutService payoutService;

    public PayoutController(PayoutService payoutService) {
        this.payoutService = payoutService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public PayoutResponse createPayout(@Valid @RequestBody PayoutRequest payoutRequest, HttpServletResponse response)
            throws InsufficientFundsException {
        Payout payout = payoutService.create(payoutRequest);
        response.addHeader(HttpHeaders.LOCATION, "/payout/" + payoutRequest.getWalletUUID());
        return new PayoutResponse(payout);
    }

    @RequestMapping(value = "/{depositUUID}", method = RequestMethod.GET)
    public List<PayoutResponse> getListPayoutByUuid(@PathVariable("depositUUID") UUID uuid,
                                                    @RequestParam(value = "page", required = false) Integer page,
                                                    @RequestParam(value = "size", required = false) Integer size) {
        return payoutService.getPayoutByWalletUuid(uuid, page, size)
                .getContent()
                .stream()
                .map(PayoutResponse::new)
                .collect(Collectors.toList());
    }
}