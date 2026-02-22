package com.algaworks.carlosfood_api.api.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RestauranteModel {

    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaModel cozinha;

    public CozinhaModel getCozinha() {
        return cozinha;
    }

    public void setCozinha(CozinhaModel cozinha) {
        this.cozinha = cozinha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getTaxaFrete() {
        return taxaFrete;
    }

    public void setTaxaFrete(BigDecimal taxaFrete) {
        this.taxaFrete = taxaFrete;
    }
}
