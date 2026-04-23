package com.algaworks.carlosfood_api.api.assembler;

import com.algaworks.carlosfood_api.api.model.input.FormaPagamentoInput;
import com.algaworks.carlosfood_api.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoModelDisassemble {

    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamento toModelObject(FormaPagamentoInput formaPagamentoInput) {
        return modelMapper.map(formaPagamentoInput, FormaPagamento.class);
    }

    public void copyToObject(FormaPagamentoInput formaPagamentoInput, FormaPagamento formaPagamento) {
        modelMapper.map(formaPagamentoInput, formaPagamento);
    }
}
