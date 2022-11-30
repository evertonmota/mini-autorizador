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
    public ResponseEntity<CardDTO> salvar( @Valid  @RequestBody CardDTO objDto){ // Objeto Json convertido para o java automaticamente.

        LOGGER.debug("Recebendo Cartão.");

        Card obj = objDto.toEntity();
        obj = service.salvar(obj);
        CardDTO cardDTO = obj.toDto();

        //Retorna a uri do novo recurso inserido
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getNumeroCartao())
                .toUri();
        LOGGER.info("Cartão criado com sucesso.");

        return ResponseEntity.created(uri).body(cardDTO);
        //return ResponseEntity.ok(service.salvar());
    }

    @RequestMapping(value="/{numeroCartao}", method = RequestMethod.GET)
    public ResponseEntity<CardDTO> find(@PathVariable String  numeroCartao){
        Card card = service.findByNumeroCartao(numeroCartao);
        CardDTO cardDTO = card.toDto();
        return ResponseEntity.ok(cardDTO);
    }
}