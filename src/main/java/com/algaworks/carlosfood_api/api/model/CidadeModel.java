package com.algaworks.carlosfood_api.api.model;

import lombok.Data;

@Data
public class CidadeModel {

    private Long id;
    private String nome;
    private EstadoModel estado;

}
