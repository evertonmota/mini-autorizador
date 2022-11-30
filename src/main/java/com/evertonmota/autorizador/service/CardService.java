package com.evertonmota.autorizador.service;

import com.evertonmota.autorizador.controller.exceptionhandler.TransactionRequestException;
import com.evertonmota.autorizador.entity.Card;
import com.evertonmota.autorizador.repository.CardRepository;
import com.evertonmota.autorizador.service.exception.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class CardService {

    private final CardRepository repository;

    public Card salvar(Card card) {
        card.setSaldo(new BigDecimal("500"));
        return this.repository.save(card);
    }

    public Card findByNumeroCartao(String numeroCartao) {

        return this.repository.getByNumeroCartao(numeroCartao)
                .orElseThrow( () -> new ObjectNotFoundException("Cartão não encontrado : " +numeroCartao));
    }

    public void createTransaction(String numeroCartao, String senhaCartao, BigDecimal valor) {

        this.repository.getByNumeroCartao(numeroCartao)
                .orElseThrow( () -> new TransactionRequestException("CARTAO_INEXISTENTE"));

        this.repository.findByNumeroCartaoAndSenha(numeroCartao, senhaCartao)
                .orElseThrow( () -> new TransactionRequestException("SENHA_INVALIDA" ));

        this.repository.findByNumeroCartaoAndSaldo(numeroCartao, valor)
                .orElseThrow( () -> new TransactionRequestException("SALDO_INSUFICIENTE" ));

        this.repository.updateBalance(valor, numeroCartao, senhaCartao);
    }
}
