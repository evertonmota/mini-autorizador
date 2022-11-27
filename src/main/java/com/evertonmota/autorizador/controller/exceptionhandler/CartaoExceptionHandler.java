package com.evertonmota.autorizador.controller.exceptionhandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CartaoExceptionHandler extends ResponseEntityExceptionHandler {

    public static final  String MSG_ERRO_GENERICO = "Ocorreu um erro inesperado no sistema. Tente novamente e se o problema persistir,"
                                                    +" entre em contato com o administrador.";

}
