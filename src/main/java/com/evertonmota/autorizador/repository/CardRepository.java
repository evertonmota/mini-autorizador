package com.evertonmota.autorizador.repository;


import com.evertonmota.autorizador.entity.Card;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Transactional
// Usar o MongoDB remover as // e as teclas ctrl + alt + o
//public interface CardRepository  extends MongoRepository<Card, UUID> {
public interface CardRepository  extends JpaRepository<Card, UUID> {

    Optional<Card> getByNumeroCartao(String s);

    Card save(Card card);

    @Modifying
    @Query("update Card c set c.saldo = c.saldo - ?1 where c.numeroCartao = ?2  and c.senha = ?3 and (c.saldo > 0 and c.saldo > ?1)" )
    void updateBalance(BigDecimal valor , String numeroCartao, String senha) ;

    @Query("select c from Card c where c.numeroCartao = ?1  and c.senha = ?2 " )
    Optional<Card> findByNumeroCartaoAndSenha(String numeroCartao, String senhaCartao);

    @Query("select c from Card c where c.numeroCartao = ?1  and (c.saldo > 0 and c.saldo > ?2)" )
    Optional<Card> findByNumeroCartaoAndSaldo(String numeroCartao, BigDecimal valor);
}
