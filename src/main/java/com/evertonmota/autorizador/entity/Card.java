package com.evertonmota.autorizador.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;


@Data
@Entity
//@Document // se for usar o MongoDB.
public class Card {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    @Size(min=16, max = 16)
    private String numeroCartao;


    @NotBlank
    @Size(min=4, max = 4)
    private String senha;

    @Min(0)
    private BigDecimal saldo;
}
