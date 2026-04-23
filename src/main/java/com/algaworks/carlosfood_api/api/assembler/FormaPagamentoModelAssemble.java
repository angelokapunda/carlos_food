package com.algaworks.carlosfood_api.api.assembler;

import com.algaworks.carlosfood_api.api.model.FormaPagamentoModel;
import com.algaworks.carlosfood_api.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FormaPagamentoModelAssemble {

    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
        return modelMapper.map(formaPagamento, FormaPagamentoModel.class);
    }

    public List<FormaPagamentoModel> toCollectionModel(List<FormaPagamento> formaPagamentos) {
        return formaPagamentos.stream()
                .map(formaPagamento -> toModel(formaPagamento))
                .toList();
    }
}
