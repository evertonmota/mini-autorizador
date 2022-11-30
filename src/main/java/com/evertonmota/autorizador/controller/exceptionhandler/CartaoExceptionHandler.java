package com.evertonmota.autorizador.controller.exceptionhandler;

import com.evertonmota.autorizador.service.exception.AuthorizationException;
import com.evertonmota.autorizador.service.exception.DataIntegrityException;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class CartaoExceptionHandler {

    public static final String MSG_ERRO_GENERICO = "Ocorreu um erro inesperado no sistema. Tente novamente e se o problema persistir,"
            + " entre em contato com o administrador.";

    // Padrão do Controler Advice.  Implementando uma Classe Auxiliar que vai interceptar as Exceções.
    // O Método vai receber a Exceção que estorou e as informações da requisição.

    @ExceptionHandler(ObjectNotFoundException.class)
    // Para indicar que é um tratador de exceções deste tipo de exceção.
    public ResponseEntity<StandardException> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

        // Objeto nao encontrado, a mensagem da exceççao, e o horário local do sistema.
        StandardException err = new StandardException(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }


    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardException> dataIntegraty(DataIntegrityException e, HttpServletRequest request) {

        StandardException err = new StandardException(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardException> dataIntegratyViolation(DataIntegrityViolationException e, HttpServletRequest request) {

        StandardException err = new StandardException(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardException> validation(MethodArgumentNotValidException e, HttpServletRequest request) {

        ValidationException err = new ValidationException(HttpStatus.BAD_REQUEST.value(), "Erro de validação", System.currentTimeMillis());
        //  acesso todos erros de campo, que aconteceream nesta exceção.
        for (FieldError x : e.getBindingResult().getFieldErrors()) {

            err.addErrors(x.getField(), x.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(AuthorizationException.class) // Para indicar que é um tratador de exceções deste tipo de exceção.
    public ResponseEntity<StandardException> Authorization(ObjectNotFoundException e, HttpServletRequest request) {

        // Objeto nao encontrado, a mensagem da exceççao, e o horário local do sistema.
        StandardException err = new StandardException(HttpStatus.FORBIDDEN.value(), e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
    }

    @ExceptionHandler(SQLException.class) // Para indicar que é um tratador de exceções deste tipo de exceção.
    public ResponseEntity<StandardException> sqlException(ObjectNotFoundException e, HttpServletRequest request) {

        // Objeto nao encontrado, a mensagem da exceççao, e o horário local do sistema.
        StandardException err = new StandardException(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
    }


}
