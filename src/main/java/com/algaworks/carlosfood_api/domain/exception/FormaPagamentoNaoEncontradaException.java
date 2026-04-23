package com.algaworks.carlosfood_api.domain.exception;

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException{

    public FormaPagamentoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
    public FormaPagamentoNaoEncontradaException(long id) {
        this(String.format("Não existe Forma de pagamento de código %d cadastrada ", id));
    }
}
