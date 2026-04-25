package com.algaworks.carlosfood_api.api.controller;

import com.algaworks.carlosfood_api.api.assembler.GrupoModelAssemble;
import com.algaworks.carlosfood_api.api.assembler.GrupoModelDesassembler;
import com.algaworks.carlosfood_api.api.model.GrupoModel;
import com.algaworks.carlosfood_api.api.model.input.GrupoInput;
import com.algaworks.carlosfood_api.domain.exception.CidadeNaoEncotradoException;
import com.algaworks.carlosfood_api.domain.exception.CozinhaNaoEncontradoException;
import com.algaworks.carlosfood_api.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.carlosfood_api.domain.exception.NegocioException;
import com.algaworks.carlosfood_api.domain.service.GrupoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private GrupoModelAssemble grupoModelAssemble;

    @Autowired
    private GrupoModelDesassembler grupoModelDesassembler;

    @PostMapping
    public ResponseEntity<GrupoModel> salvar (@RequestBody @Valid GrupoInput grupoInput) {
        var grupo = grupoService.salvar(grupoModelDesassembler.toDomainObject(grupoInput));
        return ResponseEntity.status(HttpStatus.CREATED).body(grupoModelAssemble.toModel(grupo));
    }

    @GetMapping
    public ResponseEntity<List<GrupoModel>> listar () {
        var grupos = grupoModelAssemble.toCollectionMoodel(grupoService.listar());
        return ResponseEntity.ok(grupos);
    }

    @GetMapping("/{grupoId}")
    public ResponseEntity<GrupoModel> buscar (@PathVariable Long grupoId) {
        var grupo = grupoModelAssemble.toModel(grupoService.buscarOuFalhar(grupoId));
        return ResponseEntity.ok(grupo);
    }

    @PutMapping("/{grupoId}")
    public ResponseEntity<GrupoModel> actualizar (@RequestBody @Valid GrupoInput grupoInput, @PathVariable Long grupoId) {
        var grupoActual = grupoService.buscarOuFalhar(grupoId);
        grupoModelDesassembler.copyToObject(grupoInput, grupoActual);
        try {
            return ResponseEntity.ok(grupoModelAssemble.toModel(grupoService.salvar(grupoActual)));
        } catch (GrupoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("/{grupoId}")
    public void excluir(@PathVariable Long grupoId){
        grupoService.excluir(grupoId);
    }
}
