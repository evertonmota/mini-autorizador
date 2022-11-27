package com.evertonmota.autorizador.controller;

import com.evertonmota.autorizador.entity.Card;
import com.evertonmota.autorizador.service.CardService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// @AllArgsConstructor(onConstructor = @__(@Autowired)
@AllArgsConstructor
@RestController
public class CardController {

    private final CardService service;


    private static final Logger LOGGER = LoggerFactory.getLogger(CardController.class);

    /**
     *
     * @param card
     * @return
     */
    @RequestMapping(value="/cartoes", method = RequestMethod.POST)
    public ResponseEntity<Card> salvar(@RequestBody  @Valid Card card){
        LOGGER.info("Aplicação Startada.");
        return ResponseEntity.ok(service.salvar(card));
    }

    @RequestMapping(value="/cartoes/{numeroCartao}", method = RequestMethod.GET)
    public ResponseEntity<Card> find(@PathVariable String  numeroCartao){
        return ResponseEntity.ok(service.findNumeroCartao(numeroCartao));
    }
}
