package com.algaworks.carlosfood_api.core.modelmapper;

import com.algaworks.carlosfood_api.api.model.EnderecoModel;
import com.algaworks.carlosfood_api.domain.model.Endereco;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

      var modelMapper = new ModelMapper();
        var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoModel.class);
//        enderecoToEnderecoModelTypeMap.<String>addMapping(
//                enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
//                (enderecoDest, value) -> enderecoDest.getCidade().setEstado(value)
//        );
        return modelMapper;
    }
}
