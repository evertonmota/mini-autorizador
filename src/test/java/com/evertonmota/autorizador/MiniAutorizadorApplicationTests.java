package com.evertonmota.autorizador;

import com.evertonmota.autorizador.controller.exceptionhandler.StandardException;
import com.evertonmota.autorizador.dto.CardDTO;
import com.evertonmota.autorizador.entity.Card;
import com.evertonmota.autorizador.repository.CardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

// Este Teste funcional depende do banco rodando.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MiniAutorizadorApplicationTests {

    @Autowired
    TestRestTemplate client;

    @Autowired
    private CardRepository cardRepository;

    @Test
    @DisplayName("Deve salvar um cartao com 500 de saldo com sucesso.")
    void saveSuccess() {
        //SETUP
        //execução

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Content-Type", List.of("application/json"));
        HttpEntity<String> body = new HttpEntity<>("""
                {				
                    "numeroCartao": "6549873025634501",
                    "senha": "1234"
                }""", headers);

        ResponseEntity<String> response = client.exchange("/cartoes", HttpMethod.POST, body, String.class);
        //verificação
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Optional<Card> card = this.cardRepository.getByNumeroCartao("6549873025634501");
        Assertions.assertTrue(card.isPresent());
        Assertions.assertEquals(new BigDecimal("500.00"), card.get().getSaldo());
    }


    @Test
    @DisplayName("Deve retornar http 422 ao salvar o cartao com o numero ja existente.")
    void duplicateCard() {

        //SETUP
        //execução

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Content-Type", List.of("application/json"));
        HttpEntity<String> body = new HttpEntity<>("""
                {				
                    "numeroCartao": "6549873025634501",
                    "senha": "1234"
                }""", headers);

        client.exchange("/cartoes", HttpMethod.POST, body, String.class);


        MultiValueMap<String, String> headers2 = new HttpHeaders();
        headers2.put("Content-Type", List.of("application/json"));
        HttpEntity<String> body2 = new HttpEntity<>("""
                {				
                    "numeroCartao": "6549873025634501",
                    "senha": "1234"
                }""", headers2);

        ResponseEntity<String> response2 = client.exchange("/cartoes", HttpMethod.POST, body2, String.class);

        //verificação
        Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response2.getStatusCode());
    }

    @Test
    @DisplayName("Deve retornar o saldo de um dado cartao.")
    void getBalance() {

        //SETUP

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Content-Type", List.of("application/json"));
        HttpEntity<String> body = new HttpEntity<>("""
                {				
                    "numeroCartao": "6549873025634501",
                    "senha": "1234"
                }""", headers);

        ResponseEntity<String> response = client.exchange("/cartoes", HttpMethod.POST, body, String.class);

        //execução
        var responseEntity = client.getForEntity("/cartoes/6549873025634501", CardDTO.class);

        //verificação
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(new BigDecimal("500.00"), responseEntity.getBody().getSaldo());
    }

    @Test
    @DisplayName("Deve retornar http 404 quando o numero do cartão não existir.")
    void getBalanceWhenCardNotFound() {

        //SETUP

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Content-Type", List.of("application/json"));
        HttpEntity<String> body = new HttpEntity<>("""
                {				
                    "numeroCartao": "6549873025634501",
                    "senha": "1234"
                }""", headers);

        ResponseEntity<String> response = client.exchange("/cartoes", HttpMethod.POST, body, String.class);

        //execução
        var responseEntity = client.getForEntity("/cartoes/6549873025634502", StandardException.class);

        //verificação
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Deve retornar a transação quando o numero do cartão não existir.")
    void transactionSuccess() {

        //SETUP
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Content-Type", List.of("application/json"));
        HttpEntity<String> body = new HttpEntity<>("""
                {				
                    "numeroCartao": "6549873025634501",
                    "senha": "1234"
                }""", headers);

        ResponseEntity<String> response = client.exchange("/cartoes", HttpMethod.POST, body, String.class);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());



        // Execução
        MultiValueMap<String, String> headersTrancation = new HttpHeaders();
        headersTrancation.put("Content-Type", List.of("application/json"));
        HttpEntity<String> bodyTransaction = new HttpEntity<>("""
                {				
                    "numeroCartao": "6549873025634501",
                    "senhaCartao": "1234",
                    "valor": 10.00
                }""", headersTrancation);

        ResponseEntity<String> responseTransaction = client.exchange("/transacoes", HttpMethod.POST, bodyTransaction, String.class);


        //verificação
        Assertions.assertEquals(HttpStatus.CREATED, responseTransaction.getStatusCode());
        Optional<Card> card = this.cardRepository.getByNumeroCartao("6549873025634501");
        Assertions.assertTrue(card.isPresent());
        Assertions.assertEquals(new BigDecimal("490.00"), card.get().getSaldo());
    }

}
