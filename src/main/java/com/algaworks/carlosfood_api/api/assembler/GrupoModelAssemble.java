package com.algaworks.carlosfood_api.api.assembler;

import com.algaworks.carlosfood_api.api.model.GrupoModel;
import com.algaworks.carlosfood_api.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GrupoModelAssemble {

    @Autowired
    private ModelMapper modelMapper;

    public GrupoModel toModel(Grupo grupo) {
        return modelMapper.map(grupo, GrupoModel.class);
    }

    public List<GrupoModel> toCollectionMoodel(List<Grupo> grupos) {
        return grupos.stream()
                .map(grupo-> toModel(grupo))
                .toList();
    }

}
