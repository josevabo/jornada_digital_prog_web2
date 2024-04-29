package com.example.banco.exception;

import org.springframework.http.HttpStatus;

public class NegocioException extends AbstractException {

    public NegocioException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
