package com.algaworks.carlosfood_api.domain.exception;

public abstract class  EntidadeNaoEncontradaException extends NegocioException {

    public EntidadeNaoEncontradaException( String mensagem) {
        super( mensagem);
    }
}
