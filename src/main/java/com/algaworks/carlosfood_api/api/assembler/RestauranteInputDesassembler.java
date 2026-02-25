package com.algaworks.carlosfood_api.api.assembler;

import com.algaworks.carlosfood_api.api.model.input.RestauranteInput;
import com.algaworks.carlosfood_api.domain.model.Cozinha;
import com.algaworks.carlosfood_api.domain.model.Restaurante;
import org.aspectj.asm.IModelFilter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDesassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurante toDomainObject(RestauranteInput restauranteInput) {
       return modelMapper.map(restauranteInput, Restaurante.class);
    }

    public void copyToDomainOblect(RestauranteInput restauranteInput, Restaurante restaurante) {
        // Para evitar  o erro - Caused by: org.hibernate.HibernateException: identifier of an instance of
        // com.algaworks.carlosfood_api.domain.model.Cozinha was altered from 2 to 1.
        restaurante.setCozinha(new Cozinha());

        modelMapper.map(restauranteInput, restaurante);
    }
}
