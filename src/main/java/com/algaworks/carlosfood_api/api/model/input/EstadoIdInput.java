package com.algaworks.carlosfood_api.api.model.input;

import jakarta.validation.constraints.NotNull;

public class EstadoIdInput {

    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
