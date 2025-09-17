package com.algaworks.carlosfood_api.domain.exception;

public class EstadoNaoEncotradoException extends EntidadeNaoEncontradaException{

    public EstadoNaoEncotradoException(String mensagem) {
        super(mensagem);
    }

    public EstadoNaoEncotradoException(Long estadoId) {
        this(String.format(
                "Não existe estado de código %d cadastrado", estadoId
        ));
    }
}
