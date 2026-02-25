package com.algaworks.carlosfood_api.api.controller;

import com.algaworks.carlosfood_api.api.assembler.CozinhaModelAssembler;
import com.algaworks.carlosfood_api.api.assembler.CozinhaModelDesassembler;
import com.algaworks.carlosfood_api.api.model.CozinhaModel;
import com.algaworks.carlosfood_api.api.model.input.CozinhaInput;
import com.algaworks.carlosfood_api.domain.model.Cozinha;
import com.algaworks.carlosfood_api.domain.repository.CozinhaRepository;
import com.algaworks.carlosfood_api.domain.service.CadastroCozinhaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Autowired
    private CozinhaModelAssembler cozinhaModelAssembler;

    @Autowired
    private CozinhaModelDesassembler cozinhaModelDesassembler;

    @GetMapping
    public List<CozinhaModel> listar() {
        var cozinhas = cadastroCozinha.listar();
        return cozinhaModelAssembler.toCollectionModel(cozinhas);
    }

    @GetMapping("/{cozinhaId}")
    public CozinhaModel buscar(@PathVariable Long cozinhaId) {
        var cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);
        return cozinhaModelAssembler.toModel(cozinha);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
        var cozinha = cadastroCozinha.salvar(cozinhaModelDesassembler.toDomainObject(cozinhaInput));
        return cozinhaModelAssembler.toModel(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public CozinhaModel actualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinhaActual = cadastroCozinha.buscarOuFalhar(cozinhaId);
        cozinhaModelDesassembler.copyToDomainObject(cozinhaInput, cozinhaActual);
        return  cozinhaModelAssembler.toModel(cadastroCozinha.salvar(cozinhaActual));

    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long cozinhaId) {
        cadastroCozinha.excluir(cozinhaId);
    }


}
