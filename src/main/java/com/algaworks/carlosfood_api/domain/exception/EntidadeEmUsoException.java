package com.algaworks.carlosfood_api.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(HttpStatus.CONFLICT)
public class EntidadeEmUsoException extends ResponseStatusException {

    public EntidadeEmUsoException(HttpStatusCode status, String reason) {
        super(status, reason);
    }

    public EntidadeEmUsoException(String mensagem) {
        this(HttpStatus.CONFLICT,  mensagem);
    }

}
