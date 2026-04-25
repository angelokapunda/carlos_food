package com.algaworks.carlosfood_api.domain.exception;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException{

    public GrupoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public GrupoNaoEncontradoException(Long grupoId) {
        this(String.format("O Grupo de código %d não está cadastrado", grupoId));
    }
}
