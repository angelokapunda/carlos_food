package com.algaworks.carlosfood_api.api.assembler;

import com.algaworks.carlosfood_api.api.model.input.GrupoInput;
import com.algaworks.carlosfood_api.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupoModelDesassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Grupo toDomainObject (GrupoInput grupoInput) {
        return modelMapper.map(grupoInput, Grupo.class);
    }

    public void copyToObject(GrupoInput grupoInput, Grupo grupo) {
        modelMapper.map(grupoInput, grupo);
    }
}
