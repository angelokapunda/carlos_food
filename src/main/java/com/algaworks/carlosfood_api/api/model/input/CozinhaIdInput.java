package com.algaworks.carlosfood_api.api.model.input;

import jakarta.validation.constraints.NotNull;

public class CozinhaIdInput {

    @NotNull
    private Long id;

    public @NotNull Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }
}
