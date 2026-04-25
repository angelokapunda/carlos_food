package com.algaworks.carlosfood_api.domain.service;

import com.algaworks.carlosfood_api.domain.exception.GrupoEmUsoException;
import com.algaworks.carlosfood_api.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.carlosfood_api.domain.model.Grupo;
import com.algaworks.carlosfood_api.domain.repository.GrupoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Transactional
    public Grupo salvar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    public List<Grupo> listar() {
        return grupoRepository.findAll();
    }

    public Grupo buscarOuFalhar(Long grupoId) {
        return grupoRepository.findById(grupoId).orElseThrow(
                () -> new GrupoNaoEncontradoException(grupoId));
    }

    public void excluir (Long grupoId) {
        var grupo = buscarOuFalhar(grupoId);
        try{
            grupoRepository.delete(grupo);
        } catch (DataIntegrityViolationException e) {
            throw new GrupoEmUsoException(e.getMessage());
        }
    }
}
