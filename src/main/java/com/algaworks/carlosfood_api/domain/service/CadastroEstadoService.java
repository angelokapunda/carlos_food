package com.algaworks.carlosfood_api.domain.service;

import com.algaworks.carlosfood_api.domain.exception.EntidadeEmUsoException;
import com.algaworks.carlosfood_api.domain.exception.EstadoNaoEncotradoException;
import com.algaworks.carlosfood_api.domain.model.Estado;
import com.algaworks.carlosfood_api.domain.repository.EstadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroEstadoService {

    private static final String MSG_ENTIDADE_EM_USO = "O estado de código %d não pode ser removido, pois está em uso!";

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado buscar (Long estadoId) {
        var estado = estadoRepository.findById(estadoId);
        if (estado.isEmpty()) {
            throw new EstadoNaoEncotradoException(estadoId);
        }
        return estado.get();
    }


    @Transactional
    public Estado salvar ( Estado estado) {
        return estadoRepository.save(estado);
    }

    @Transactional
    public void excluir (Long estadoId) {
        try {
            var estado = buscarOuFalhar(estadoId);
            estadoRepository.deleteById(estado.getId());
            estadoRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_ENTIDADE_EM_USO, estadoId)
            );
        }
    }

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Estado buscarOuFalhar(Long estadoId) {
        return estadoRepository.findById(estadoId).orElseThrow(() -> new EstadoNaoEncotradoException(estadoId));
    }
}
