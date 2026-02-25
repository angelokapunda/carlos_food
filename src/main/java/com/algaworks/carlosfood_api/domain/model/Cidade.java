package com.algaworks.carlosfood_api.domain.model;

import com.algaworks.carlosfood_api.core.validation.Groups;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(of = "id")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @Valid
    @NotNull
    @ManyToOne
    @ConvertGroup(to = Groups.CidadeId.class)
    private Estado estado;

    public @Valid @NotNull @ConvertGroup(to = Groups.CidadeId.class) Estado getEstado() {
        return estado;
    }

    public void setEstado(@Valid @NotNull @ConvertGroup(to = Groups.CidadeId.class) Estado estado) {
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank String getNome() {
        return nome;
    }

    public void setNome(@NotBlank String nome) {
        this.nome = nome;
    }
}
