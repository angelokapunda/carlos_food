package com.algaworks.carlosfood_api.domain.exception;

public class RestauranteNaoEncontradoException  extends EntidadeNaoEncontradaException{

    public RestauranteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    public RestauranteNaoEncontradoException(Long restauranteId) {
        this(String.format(
                "Não existe Restaurante de código %d cadastrado", restauranteId
        ));
    }
}
