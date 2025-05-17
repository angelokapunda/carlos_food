package com.algaworks.carlosfood_api.api.controller;

import com.algaworks.carlosfood_api.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.carlosfood_api.domain.model.Cidade;
import com.algaworks.carlosfood_api.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CadastroCidadeService cadastroCidade;

    @GetMapping
    public List<Cidade> listar() {
        return cadastroCidade.listar();
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<?> buscar (@PathVariable Long cidadeId) {
        try {
            var cidade = cadastroCidade.buscar(cidadeId);
            return ResponseEntity.ok(cidade);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Cidade cidade) {
        try {
            cidade = cadastroCidade.salvar(cidade);
            return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{cidadeId}")
    public ResponseEntity<?> actualizar(@RequestBody Cidade cidade, @PathVariable Long cidadeId) {
       Cidade cidadeActual = cadastroCidade.buscar(cidadeId);
        if (cidadeActual != null) {
            BeanUtils.copyProperties(cidade, cidadeActual, "id");
            var cidadeSalva = cadastroCidade.salvar(cidadeActual);
            return ResponseEntity.ok(cidadeSalva);
        }
        return ResponseEntity.badRequest().build();
    }


    @DeleteMapping("/{cidadeId}")
    public ResponseEntity<?> excluir (@PathVariable Long cidadeId) {
        try {
            cadastroCidade.excluir(cidadeId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
