package com.evertonmota.autorizador.repository;


import com.evertonmota.autorizador.entity.Card;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

@Transactional
// Usar o MongoDB remover as // e as teclas ctrl + alt + o
//public interface CardRepository  extends MongoRepository<Card, UUID> {
public interface CardRepository  extends JpaRepository<Card, UUID> {

    Card getByNumeroCartao(String s);

    Card save(Card card);
}
