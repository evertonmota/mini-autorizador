package com.evertonmota.autorizador.service;

import com.evertonmota.autorizador.entity.Card;
import com.evertonmota.autorizador.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor
@Service
public class CardService {

    private final CardRepository repository;

    public Card salvar(Card card) {
        card.setSaldo(new BigDecimal( "500.00"));

        return this.repository.save(card);
    }

    public Card findNumeroCartao(String numeroCartao) {
        return this.repository.getByNumeroCartao(numeroCartao);
    }
}
