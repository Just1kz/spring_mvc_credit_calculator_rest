package com.antonbelykh.spring.spring_mvc.rest.exception_handling;

public class NoSuchPaymentException extends RuntimeException {
    public NoSuchPaymentException(String message) {
        super(message);
    }
}
