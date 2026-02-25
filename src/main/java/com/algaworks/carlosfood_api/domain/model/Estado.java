package com.algaworks.carlosfood_api.domain.model;

import com.algaworks.carlosfood_api.core.validation.Groups;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(of = "id")
public class Estado {

    @Id
    @NotNull(groups = Groups.CidadeId.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    public @NotNull(groups = Groups.CidadeId.class) Long getId() {
        return id;
    }

    public void setId(@NotNull(groups = Groups.CidadeId.class) Long id) {
        this.id = id;
    }

    public @NotBlank String getNome() {
        return nome;
    }

    public void setNome(@NotBlank String nome) {
        this.nome = nome;
    }
}
