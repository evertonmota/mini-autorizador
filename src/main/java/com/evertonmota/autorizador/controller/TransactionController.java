package com.evertonmota.autorizador.controller;

import com.evertonmota.autorizador.dto.CardDTO;
import com.evertonmota.autorizador.dto.TransactionDTO;
import com.evertonmota.autorizador.entity.Card;
import com.evertonmota.autorizador.service.CardService;
//import jakarta.validation.Valid;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

// @AllArgsConstructor(onConstructor = @__(@Autowired)
@AllArgsConstructor
@RestController
@RequestMapping(value="/transacoes")
public class TransactionController {

    private final CardService service;


    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    @ApiOperation(value = "Realizar uma Transação")
    @ApiResponses(value = {
            @ApiResponse(code = 201 , message = "Transação realizada com sucesso."),
            @ApiResponse(code = 422 , message = "SALDO_INSUFICIENTE | SENHA_INVALIDA |CARTAO INEXISTENTE ")
    })
    @PostMapping
    public ResponseEntity<CardDTO> insertTransaction( @Valid @RequestBody TransactionDTO objDto){ // Objeto Json convertido para o java automaticamente.

        LOGGER.debug("Recebendo Transações.");

        service.createTransaction(objDto.getNumeroCartao(), objDto.getSenhaCartao(), objDto.getValor());

        LOGGER.info("Transação realizada com sucesso.");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value="/{numeroCartao}", method = RequestMethod.GET)
    public ResponseEntity<CardDTO> find(@PathVariable String  numeroCartao){
        Card card = service.findByNumeroCartao(numeroCartao);
        CardDTO cardDTO = card.toDto();
        return ResponseEntity.ok(cardDTO);
    }
}