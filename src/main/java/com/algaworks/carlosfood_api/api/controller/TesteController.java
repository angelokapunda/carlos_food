package com.algaworks.carlosfood_api.api.controller;

import com.algaworks.carlosfood_api.domain.model.Restaurante;
import com.algaworks.carlosfood_api.domain.repository.CozinhaRepository;
import com.algaworks.carlosfood_api.domain.repository.RestauranteRepository;
import com.algaworks.carlosfood_api.infrastructor.repository.spec.RestauranteComFreteGratesSpec;
import com.algaworks.carlosfood_api.infrastructor.repository.spec.RestauranteComNomeSemelhanteSpec;
import com.algaworks.carlosfood_api.infrastructor.repository.spec.RestauranteSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/teste")
public class TesteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping("/primeiroNome")
    public List<Restaurante> primeiroNome(String nome, Long id) {
        return restauranteRepository.consultaPorNome(nome, id);
    }

    @GetMapping("/existe")
    public boolean existencia (String nome) {
        return cozinhaRepository.existsByNome(nome);
    }

    @GetMapping("/customizado")
    public List<Restaurante> find (String nome, BigDecimal taxaFreteInicial,
                                   BigDecimal taxaFreteFinal) {
        return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
    }


}
