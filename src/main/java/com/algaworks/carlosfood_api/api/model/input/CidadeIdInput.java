package com.algaworks.carlosfood_api.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CidadeIdInput {

    @NotNull
    private Long id;

}
