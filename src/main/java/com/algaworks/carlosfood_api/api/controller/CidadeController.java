package com.algaworks.carlosfood_api.api.controller;

import com.algaworks.carlosfood_api.api.assembler.CidadeModelAssembler;
import com.algaworks.carlosfood_api.api.assembler.CidadeModelDesassembler;
import com.algaworks.carlosfood_api.api.model.CidadeModel;
import com.algaworks.carlosfood_api.api.model.input.CidadeInput;
import com.algaworks.carlosfood_api.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.carlosfood_api.domain.exception.NegocioException;
import com.algaworks.carlosfood_api.domain.model.Cidade;
import com.algaworks.carlosfood_api.domain.service.CadastroCidadeService;
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
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @Autowired
    private CidadeModelAssembler cidadeModelAssembler;

    @Autowired
    private CidadeModelDesassembler cidadeModelDesassembler;

    @GetMapping
    public List<CidadeModel> listar() {
        var cidades = cadastroCidade.listar();
        return cidadeModelAssembler.toColletionModel(cidades);
    }

    @GetMapping("/{cidadeId}")
    public CidadeModel buscar (@PathVariable Long cidadeId) {
        var cidade = cadastroCidade.buscarOuFalhar(cidadeId);
        return cidadeModelAssembler.toModel(cidade);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeModel salvar(@RequestBody @Valid CidadeInput cidadeInput) {
        try {
            var cidade = cadastroCidade.salvar(cidadeModelDesassembler.toDomainObject(cidadeInput));
            return cidadeModelAssembler.toModel(cidade);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cidadeId}")
    public CidadeModel actualizar(@RequestBody @Valid CidadeInput cidadeInput, @PathVariable Long cidadeId) {
        Cidade cidadeActual = cadastroCidade.buscarOuFalhar(cidadeId);
        cidadeModelDesassembler.copyToDomainObject(cidadeInput, cidadeActual);
        try {
            var cidade = cadastroCidade.salvar(cidadeActual);
            return cidadeModelAssembler.toModel(cidade);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }


    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir (@PathVariable Long cidadeId) {
        cadastroCidade.excluir(cidadeId);
    }
}
