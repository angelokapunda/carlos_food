package com.algaworks.carlosfood_api.api.controller;

import com.algaworks.carlosfood_api.domain.exception.EntidadeEmUsoException;
import com.algaworks.carlosfood_api.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.carlosfood_api.domain.model.Estado;
import com.algaworks.carlosfood_api.domain.repository.EstadoRepository;
import com.algaworks.carlosfood_api.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
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
import java.util.Optional;

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
    public ResponseEntity<?> buscar(@PathVariable Long estadoId) {
        try {
            var estado = cadastroEstado.buscar(estadoId);
            return ResponseEntity.ok(estado);

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Estado> salvar (@RequestBody Estado estado) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastroEstado.salvar(estado));
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<?> actualizar (@RequestBody Estado estado, @PathVariable Long estadoId) {
        Optional<Estado> estadoActual = estadoRepository.findById(estadoId);
        if (estadoActual.isPresent()) {
            BeanUtils.copyProperties(estado, estadoActual.get(), "id");
            Estado estadoSalvo = cadastroEstado.salvar(estadoActual.get());
            return ResponseEntity.ok(estadoSalvo);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<?> excluir(@PathVariable Long estadoId) {
        try {
            cadastroEstado.excluir(estadoId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
