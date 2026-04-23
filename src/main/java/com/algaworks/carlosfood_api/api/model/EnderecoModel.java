package com.algaworks.carlosfood_api.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class EnderecoModel {

    private String cep;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private CidadeModel cidade;
}
