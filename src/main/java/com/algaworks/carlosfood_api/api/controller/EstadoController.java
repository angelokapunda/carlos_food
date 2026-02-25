package com.algaworks.carlosfood_api.api.controller;

import com.algaworks.carlosfood_api.api.assembler.EstadoModelAssembler;
import com.algaworks.carlosfood_api.api.assembler.EstadoModelDesassembler;
import com.algaworks.carlosfood_api.api.exceptionHandler.Problem;
import com.algaworks.carlosfood_api.api.model.EstadoModel;
import com.algaworks.carlosfood_api.api.model.input.EstadoInput;
import com.algaworks.carlosfood_api.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.carlosfood_api.domain.exception.NegocioException;
import com.algaworks.carlosfood_api.domain.model.Estado;
import com.algaworks.carlosfood_api.domain.repository.EstadoRepository;
import com.algaworks.carlosfood_api.domain.service.CadastroEstadoService;
import jakarta.validation.Valid;
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
    private CadastroEstadoService cadastroEstado;

    @Autowired
    private EstadoModelDesassembler estadoModelDesassembler;

    @Autowired
    private EstadoModelAssembler estadoModelAssembler;

    @GetMapping
    public List<EstadoModel> listar() {
        var estados = cadastroEstado.listar();
        return estadoModelAssembler.toCollectionModel(estados);
    }

    @GetMapping("/{estadoId}")
    public EstadoModel buscar(@PathVariable Long estadoId) {
        var estado = cadastroEstado.buscarOuFalhar(estadoId);
        return estadoModelAssembler.toModel(estado);
    }

    @PostMapping
    public ResponseEntity<EstadoModel> salvar (@RequestBody EstadoInput estadoInput) {
        var estado = estadoModelAssembler.toModel(cadastroEstado.
                salvar(estadoModelDesassembler.toDomainObject(estadoInput)));
        return ResponseEntity.status(HttpStatus.CREATED).body(estado);
    }

    @PutMapping("/{estadoId}")
    public EstadoModel actualizar (@RequestBody @Valid EstadoInput estadoInput, @PathVariable Long estadoId) {
        Estado estadoActual = cadastroEstado.buscarOuFalhar(estadoId);
        estadoModelDesassembler.copyToDomainObject(estadoInput, estadoActual);
        return estadoModelAssembler.toModel(cadastroEstado.salvar(estadoActual));
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long estadoId) {
        cadastroEstado.excluir(estadoId);
    }

}
