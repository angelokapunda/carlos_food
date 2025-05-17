package com.algaworks.carlosfood_api.domain.service;

import com.algaworks.carlosfood_api.domain.exception.EntidadeEmUsoException;
import com.algaworks.carlosfood_api.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.carlosfood_api.domain.model.Estado;
import com.algaworks.carlosfood_api.domain.repository.EstadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroEstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado buscar (Long estadoId) {
        var estado = estadoRepository.findById(estadoId);
        if (estado.isEmpty()) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe nenhum estado de código %d cadastrado ", estadoId)
            );
        }
        return estado.get();
    }


    public Estado salvar ( Estado estado) {
        return estadoRepository.save(estado);
    }

    public void excluir (Long estadoId) {
        try {
            estadoRepository.deleteById(estadoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe estado de código %d cadastrado " , estadoId)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("O estado de código %d não pode ser removido, pois está em uso!", estadoId)
            );
        }
    }
}
