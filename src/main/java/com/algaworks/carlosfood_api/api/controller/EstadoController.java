package com.algaworks.carlosfood_api.api.controller;

import com.algaworks.carlosfood_api.api.exceptionHandler.Problem;
import com.algaworks.carlosfood_api.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.carlosfood_api.domain.exception.NegocioException;
import com.algaworks.carlosfood_api.domain.model.Estado;
import com.algaworks.carlosfood_api.domain.repository.EstadoRepository;
import com.algaworks.carlosfood_api.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstado;

    @GetMapping
    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    @GetMapping("/{estadoId}")
    public Estado buscar(@PathVariable Long estadoId) {
        return cadastroEstado.buscarOuFalhar(estadoId);
    }

    @PostMapping
    public ResponseEntity<Estado> salvar (@RequestBody Estado estado) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastroEstado.salvar(estado));
    }

    @PutMapping("/{estadoId}")
    public Estado actualizar (@RequestBody Estado estado, @PathVariable Long estadoId) {

        Estado estadoActual = cadastroEstado.buscarOuFalhar(estadoId);

        BeanUtils.copyProperties(estado, estadoActual, "id");
        return cadastroEstado.salvar(estadoActual);
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long estadoId) {
        cadastroEstado.excluir(estadoId);
    }

}
