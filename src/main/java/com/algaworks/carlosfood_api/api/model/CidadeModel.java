package com.algaworks.carlosfood_api.api.model;

import lombok.Data;

@Data
public class CidadeModel {

    private Long id;
    private String nome;
    private EstadoModel estado;

    public EstadoModel getEstado() {
        return estado;
    }

    public void setEstado(EstadoModel estado) {
        this.estado = estado;
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
}
