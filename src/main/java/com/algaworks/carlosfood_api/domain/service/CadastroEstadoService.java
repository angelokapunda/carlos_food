package com.algaworks.carlosfood_api.domain.service;

import com.algaworks.carlosfood_api.domain.exception.EntidadeEmUsoException;
import com.algaworks.carlosfood_api.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.carlosfood_api.domain.model.Estado;
import com.algaworks.carlosfood_api.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService {

    private static final String MGS_ENTIDADE_NAO_ENCONTRADA = "Não existe estado de código %d cadastrado ";
    private static final String MGS_ENTIDADE_EM_USO = "O estado de código %d não pode ser removido, pois está em uso!";

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado buscar (Long estadoId) {
        var estado = estadoRepository.findById(estadoId);
        if (estado.isEmpty()) {
            throw new EntidadeNaoEncontradaException(
                    String.format(MGS_ENTIDADE_NAO_ENCONTRADA, estadoId)
            );
        }
        return estado.get();
    }


    public Estado salvar ( Estado estado) {
        return estadoRepository.save(estado);
    }

    public void excluir (Long estadoId) {
        var estado = buscarOuFalhar(estadoId);
        estadoRepository.deleteById(estado.getId());
    }

    public Estado buscarOuFalhar(Long estadoId) {
        return estadoRepository.findById(estadoId).orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format(MGS_ENTIDADE_NAO_ENCONTRADA , estadoId)
        ));
    }
}
