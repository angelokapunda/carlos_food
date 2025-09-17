package com.algaworks.carlosfood_api.domain.exception;

public class CozinhaNaoEncontradoException extends EntidadeNaoEncontradaException{

    public CozinhaNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    public CozinhaNaoEncontradoException(Long cozinhaId) {
        this(String.format(
                "Não existe Cozinha de código %d cadastrado", cozinhaId
        ));
    }
}
