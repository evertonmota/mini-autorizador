package com.evertonmota.autorizador.dto;

import com.evertonmota.autorizador.entity.Card;
//import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
@ToString
public class CardDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 16 ,  max = 16, message = "O tamanho deve ser 16 digitos")
    private String numeroCartao;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 4 ,  max = 4, message = "O tamanho deve ser igual a 4 digitos")
    private String senha;

    private BigDecimal saldo;

    public Card toEntity() {
        return new Card(this.getId() , this.getNumeroCartao(), this.getSenha());
    }
}