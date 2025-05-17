package com.algaworks.carlosfood_api.api.controller;

import com.algaworks.carlosfood_api.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.carlosfood_api.domain.model.Restaurante;
import com.algaworks.carlosfood_api.domain.repository.CozinhaRepository;
import com.algaworks.carlosfood_api.domain.repository.RestauranteRepository;
import com.algaworks.carlosfood_api.domain.service.CadastroRestauranteService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping
    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscar (@PathVariable Long restauranteId) {
        Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId);
        if (restaurante.isPresent()) {
            return ResponseEntity.ok(restaurante.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> salvar(@RequestBody Restaurante restaurante) {
        try {
            restaurante = cadastroRestaurante.salvar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<?> actualizar (@RequestBody Restaurante restaurante, @PathVariable Long restauranteId) {
        try {
            Optional<Restaurante> restauranteActual = restauranteRepository.findById(restauranteId);
            if (restauranteActual.isPresent()) {
                BeanUtils.copyProperties(restaurante, restauranteActual.get(), "id", "formasPagamento", "endereco", "dataCadastro","produto");
                Restaurante restauranteSalvo = cadastroRestaurante.salvar(restauranteActual.get());
                return ResponseEntity.ok(restauranteSalvo);
            }
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{restauranteId}")
    public ResponseEntity<?> excluir(@PathVariable Long restauranteId) {
        try {
            cadastroRestaurante.excluir(restauranteId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
