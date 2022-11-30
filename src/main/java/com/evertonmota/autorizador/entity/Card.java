package com.evertonmota.autorizador.entity;

import com.evertonmota.autorizador.dto.CardDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
    @Column(unique = true)
    private String numeroCartao;


    @NotBlank
    @Size(min=4, max = 4)
    private String senha;

    private BigDecimal saldo;

    public Card(
            UUID id,
            @NotEmpty(message = "Preenchimento obrigatório") @Length(min = 16, max = 16, message = "O tamanho deve ser 16 digitos") String numeroCartao,
            @NotBlank @Size(min = 4, max = 4) String senha
    ) {
        this.id = id;
        this.numeroCartao = numeroCartao;
        this.senha = senha;

    }

    public CardDTO toDto() {
        return new CardDTO(this.getId() , this.getNumeroCartao(), this.getSenha(), this.getSaldo());
    }

    public Card() {
    }
}
