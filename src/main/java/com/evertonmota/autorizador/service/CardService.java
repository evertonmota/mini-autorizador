package com.evertonmota.autorizador.service;

import com.evertonmota.autorizador.entity.Card;
import com.evertonmota.autorizador.repository.CardRepository;
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
        return this.repository.getByNumeroCartao(numeroCartao);
    }


}
