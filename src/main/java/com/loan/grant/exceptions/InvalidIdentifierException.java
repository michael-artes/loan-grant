package com.loan.grant.exceptions;

public class InvalidIdentifierException extends RuntimeException {
    public InvalidIdentifierException(String message) {
        super(message);
    }
}