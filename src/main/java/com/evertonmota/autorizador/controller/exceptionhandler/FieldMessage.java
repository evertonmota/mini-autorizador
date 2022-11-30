package com.evertonmota.autorizador.controller.exceptionhandler;

import lombok.Data;

import java.io.Serializable;

@Data
public class FieldMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fieldName;
    private String fieldMessage;

    public FieldMessage() {

    }

    public FieldMessage(String fieldName, String fieldMessage) {
        super();
        this.fieldName = fieldName;
        this.fieldMessage = fieldMessage;
    }

}