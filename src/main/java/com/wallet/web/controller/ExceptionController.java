package com.wallet.web.controller;

import com.wallet.exception.DepositLimitException;
import com.wallet.exception.InsufficientFundsException;
import com.wallet.web.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice(annotations = RestController.class)
public class ExceptionController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResponse notFound(EntityNotFoundException exception) {
        logger.error("Entity Not Found exception:" + exception.getMessage());
        return new ErrorResponse("EntityNotFound", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataAccessException.class)
    public ErrorResponse dataAccess(DataAccessException exception) {
        logger.error("DataAccessException exception: " + exception.getMessage());
        return new ErrorResponse("DataAccess", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
    @ExceptionHandler(InsufficientFundsException.class)
    public ErrorResponse insufficientFunds(InsufficientFundsException exception) {
        logger.error("InsufficientFundsException exception: " + exception.getMessage());
        return new ErrorResponse("InsufficientFunds", exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DepositLimitException.class)
    public ErrorResponse depositLimit(DepositLimitException exception) {
        logger.error("(DepositLimitException exception: " + exception.getMessage());
        return new ErrorResponse("(DepositLimit", exception.getMessage());
    }
}
