package com.evertonmota.autorizador.controller;

import com.evertonmota.autorizador.dto.CardDTO;
import com.evertonmota.autorizador.entity.Card;
import com.evertonmota.autorizador.service.CardService;
//import jakarta.validation.Valid;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

// @AllArgsConstructor(onConstructor = @__(@Autowired)
@AllArgsConstructor
@RestController
@RequestMapping(value="/cartoes")
public class CardController {

    private final CardService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(CardController.class);

    @ApiOperation(value = "Criar novo cartão - Todo cartão será criado com um saldo inicial de R$500,00")
    @ApiResponses(value = {
            @ApiResponse(code = 201 , message = "Cartão criado com sucesso."),
            @ApiResponse(code = 422 , message = "Este cartão já foi cadastrado.")
    })
    @PostMapping
    public ResponseEntity<CardDTO> salvar( @Valid @RequestBody CardDTO objDto){ // Objeto Json convertido para o java automaticamente.

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
    @ApiOperation(value = "Obter saldo do Cartão")
    @ApiResponses(value = {
            @ApiResponse(code = 200 , message = "Cartão encontrado com sucesso."),
            @ApiResponse(code = 404 , message = "Cartão inexistente.")
    })
    @RequestMapping(value="/{numeroCartao}", method = RequestMethod.GET)
    public ResponseEntity<CardDTO> find(@PathVariable String  numeroCartao){
        Card card = service.findByNumeroCartao(numeroCartao);
        CardDTO cardDTO = card.toDto();
        return ResponseEntity.ok(cardDTO);
    }
}