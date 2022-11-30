package com.evertonmota.autorizador.controller.exceptionhandler;

public class TransactionRequestException extends RuntimeException {
    public TransactionRequestException() {
    }

    public TransactionRequestException(String message) {
        super(message);
    }

    public TransactionRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionRequestException(Throwable cause) {
        super(cause);
    }

    public TransactionRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
