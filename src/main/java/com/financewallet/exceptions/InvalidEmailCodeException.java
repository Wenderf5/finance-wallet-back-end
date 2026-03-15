package com.financewallet.exceptions;

public class InvalidEmailCodeException extends RuntimeException {
    public InvalidEmailCodeException(String msg) {
        super(msg);
    }
}
