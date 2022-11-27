package com.evertonmota.autorizador.controller.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    SALDO_INSUFICIENTE("/saldo-insuficiente","Saldo Insuficiente"),
    SENHA_INVALIDA("/senha-invalida","Senha Inválida"),
    CARTAO_INEXISTENTE("/cartao-inexistente","Cartão Inexistente");

    private String title;
    private String url;

    ProblemType(String title, String url) {
        this.title = "http://api/cartoes" +title;
        this.url = url;
    }
}
