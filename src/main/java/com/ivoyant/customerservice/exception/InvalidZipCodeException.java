package com.ivoyant.customerservice.exception;

public class InvalidZipCodeException extends RuntimeException {

    public InvalidZipCodeException(String message) {
        super(message);
    }
}