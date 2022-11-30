package com.evertonmota.autorizador.service.exception;

public class ObjectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ObjectNotFoundException(String msg ) {
        super(msg);
    }

    // recebe a mensagem e a causa da exceção.
    public ObjectNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
