package com.evertonmota.autorizador.controller.exceptionhandler;

import java.util.ArrayList;
import java.util.List;

public class ValidationException extends StandardException {


    private static final long serialVersionUID = 1L;

    private List<FieldMessage> listErrors = new ArrayList<>();

    public ValidationException(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }

    public List<FieldMessage> getErrors() {
        return listErrors;
    }

    public void addErrors(String fieldName, String message) {
        listErrors.add(new FieldMessage(fieldName, message));
    }
}

