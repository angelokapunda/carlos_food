package com.algaworks.carlosfood_api.domain.repository;

import com.algaworks.carlosfood_api.domain.model.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepositoryQueris {
    List<Restaurante> find(String nome, BigDecimal taxaFreteInicial,
                           BigDecimal taxaFreteFinal);
}
