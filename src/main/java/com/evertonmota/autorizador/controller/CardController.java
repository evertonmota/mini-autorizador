package com.evertonmota.autorizador.controller;

import com.evertonmota.autorizador.dto.CardDTO;
import com.evertonmota.autorizador.entity.Card;
import com.evertonmota.autorizador.service.CardService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

// @AllArgsConstructor(onConstructor = @__(@Autowired)
@AllArgsConstructor
@RestController
@RequestMapping(value="/cartoes")
public class CardController {

    private final CardService service;


    private static final Logger LOGGER = LoggerFactory.getLogger(CardController.class);

    /* *
     *
     * @param card
     * @return
     */
    /*
    @RequestMapping(value="/cartoes", method = RequestMethod.POST)
    public ResponseEntity<Card> salvar(@RequestBody  @Valid Card card){
        LOGGER.info("Aplicação Startada.");
        return ResponseEntity.ok(service.salvar(card));
    }
    */

    @PostMapping
    public ResponseEntity<Card> salvar( @Valid  @RequestBody CardDTO objDto){ // Objeto Json convertido para o java automaticamente.

        LOGGER.info("Aplicação Startada.");

        Card obj = objDto.toEntity();
        obj = service.salvar(obj);
        obj.toDto();

        //Retorna a uri do novo recurso inserido
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getNumeroCartao())
                .toUri();
        return ResponseEntity.created(uri).body(obj);
        //return ResponseEntity.ok(service.salvar());
    }

    @RequestMapping(value="/cartoes/{numeroCartao}", method = RequestMethod.GET)
    public ResponseEntity<Card> find(@PathVariable String  numeroCartao){
        return ResponseEntity.ok(service.findNumeroCartao(numeroCartao));
    }
}