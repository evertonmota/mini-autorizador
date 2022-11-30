package com.evertonmota.autorizador.controller.exceptionhandler;

import lombok.Data;

import java.io.Serializable;


@Data
public class StandardException implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer status;
    private String msg;
    private Long timeStamp;

    public StandardException(Integer status, String msg, Long timeStamp) {
        this.status = status;
        this.msg = msg;
        this.timeStamp = timeStamp;
    }
}
