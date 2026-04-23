package com.algaworks.carlosfood_api.api.controller;

import com.algaworks.carlosfood_api.api.assembler.FormaPagamentoModelAssemble;
import com.algaworks.carlosfood_api.api.assembler.FormaPagamentoModelDisassemble;
import com.algaworks.carlosfood_api.api.model.FormaPagamentoModel;
import com.algaworks.carlosfood_api.api.model.input.FormaPagamentoInput;
import com.algaworks.carlosfood_api.domain.exception.NegocioException;
import com.algaworks.carlosfood_api.domain.service.FormaPagamentoService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.tree.ExpandVetoException;
import java.util.List;

@RestController
@RequestMapping("/formaPagamentos")
public class FormaPagamentosController {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @Autowired
    private FormaPagamentoModelAssemble formaPagamentoModelAssemble;

    @Autowired
    private FormaPagamentoModelDisassemble formaPagamentoModelDisassemble;

    @PostMapping
    public ResponseEntity<FormaPagamentoModel> salvar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        var pagamento = formaPagamentoService.salvar(formaPagamentoModelDisassemble.toModelObject(formaPagamentoInput));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(formaPagamentoModelAssemble.toModel(pagamento));
    }

    @GetMapping
    public ResponseEntity<List<FormaPagamentoModel>> listar() {
        return ResponseEntity.ok(formaPagamentoModelAssemble.
                toCollectionModel(formaPagamentoService.listar()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamentoModel> buscar(@PathVariable long id) {
        return ResponseEntity.ok(formaPagamentoModelAssemble
                .toModel(formaPagamentoService.buscarOuFalhar(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormaPagamentoModel> actualizar (@RequestBody @Valid FormaPagamentoInput formaPagamentoInput, @PathVariable long id) {
        var formaPagamentoActual = formaPagamentoService.buscarOuFalhar(id);
        formaPagamentoModelDisassemble.copyToObject(formaPagamentoInput, formaPagamentoActual);
        try {
            return ResponseEntity.ok(formaPagamentoModelAssemble.
                    toModel(formaPagamentoService.salvar(formaPagamentoActual)));
        } catch (Exception ex) {
            throw new NegocioException(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable long id) {
        formaPagamentoService.excluir(id);
    }
}
