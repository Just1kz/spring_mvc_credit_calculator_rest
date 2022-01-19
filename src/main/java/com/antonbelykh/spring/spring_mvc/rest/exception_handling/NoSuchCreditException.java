package com.antonbelykh.spring.spring_mvc.rest.exception_handling;

public class NoSuchCreditException extends RuntimeException {
    public NoSuchCreditException(String message) {
        super(message);
    }
}
