package com.algaworks.carlosfood_api.domain.service;

import com.algaworks.carlosfood_api.domain.exception.FormaPagamentoEmUsoException;
import com.algaworks.carlosfood_api.domain.exception.FormaPagamentoNaoEncontradaException;
import com.algaworks.carlosfood_api.domain.model.FormaPagamento;
import com.algaworks.carlosfood_api.domain.repository.FormaPagamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return formaPagamentoRepository.save(formaPagamento);
    }

    public FormaPagamento buscarOuFalhar(long id) {
        return formaPagamentoRepository.findById(id)
                .orElseThrow(() -> new FormaPagamentoNaoEncontradaException(id));
    }

    public List<FormaPagamento> listar() {
        return formaPagamentoRepository.findAll();
    }

    public void excluir (long id) {
        var formaPagamneto = buscarOuFalhar(id);
        try{
            formaPagamentoRepository.delete(formaPagamneto);
        } catch (DataIntegrityViolationException ex) {
            throw new FormaPagamentoEmUsoException(String.format("A Forma de Pagamento de id %d não pode ser excluida por esta em uso", id));
        }
    }

}
