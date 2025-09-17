package com.algaworks.carlosfood_api.domain.service;

import com.algaworks.carlosfood_api.domain.exception.CozinhaNaoEncontradoException;
import com.algaworks.carlosfood_api.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.carlosfood_api.domain.model.Cozinha;
import com.algaworks.carlosfood_api.domain.model.Restaurante;
import com.algaworks.carlosfood_api.domain.repository.CozinhaRepository;
import com.algaworks.carlosfood_api.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvar (Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new CozinhaNaoEncontradoException(cozinhaId));

        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }

    public Restaurante buscarOuFalhar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId).orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
    }

    public void excluir(Long restauranteId) {
        var restaurante = buscarOuFalhar(restauranteId);
        restauranteRepository.deleteById(restaurante.getId());

    }
}
