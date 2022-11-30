package com.evertonmota.autorizador.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionDTO{

    private String numeroCartao;
    private String senhaCartao;
    private BigDecimal valor;
}
