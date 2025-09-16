package com.algaworks.carlosfood_api.domain.service;

import com.algaworks.carlosfood_api.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.carlosfood_api.domain.model.Cozinha;
import com.algaworks.carlosfood_api.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

    public static final String MSG_ENTIDADE_NAO_ENCONTRADA = "Não existe um cadastro de cozinha com código %d";
    public static final String MSG_ENTIDADE_EM_USO = "A cozinha de código %d não pode ser removida, pois esta em uso";

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    public void excluir(Long cozinhaId) {
        var cozinha = buscarOuFalhar(cozinhaId);
        cozinhaRepository.delete(cozinha);
    }

    public Cozinha buscarOuFalhar(Long cozinhaId) {
        return cozinhaRepository.findById(cozinhaId).orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format(MSG_ENTIDADE_NAO_ENCONTRADA, cozinhaId))
        );
    }
}
