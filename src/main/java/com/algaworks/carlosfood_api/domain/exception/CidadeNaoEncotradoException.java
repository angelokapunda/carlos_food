package com.algaworks.carlosfood_api.domain.exception;

public class CidadeNaoEncotradoException extends EntidadeNaoEncontradaException{

    public CidadeNaoEncotradoException(String mensagem) {
        super(mensagem);
    }

    public CidadeNaoEncotradoException(Long cidadeId) {
        this(String.format(
                "Não existe Cidade de código %d cadastrado", cidadeId
        ));
    }
}
