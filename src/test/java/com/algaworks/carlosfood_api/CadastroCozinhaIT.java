package com.algaworks.carlosfood_api;

import static org.assertj.core.api.Assertions.assertThat;


import com.algaworks.carlosfood_api.domain.exception.EntidadeEmUsoException;
import com.algaworks.carlosfood_api.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.carlosfood_api.domain.model.Cozinha;
import com.algaworks.carlosfood_api.domain.service.CadastroCozinhaService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CadastroCozinhaIT {

	@Autowired
	private CadastroCozinhaService cozinhaService;

	@Test
	public void deveAtribuirId_QuandoCadastrarCozinhaComDadosCorrertos() {
		// Cenário
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Brasilleiraa");

		//Acção
		novaCozinha = cozinhaService.salvar(novaCozinha);

		// Validação
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
	}

	@Test
	public void deveFalhar_QuandoCadastrarCozinhaSemNome() {

		Cozinha cozinha = new Cozinha();
		cozinha.setNome(null);
		ConstraintViolationException erroEsperado =
				assertThrows(ConstraintViolationException.class, () -> {
			cozinhaService.salvar(cozinha);
		});
		assertThat(erroEsperado).isNotNull();
	}

	@Test
	public void devefalhar_QuandoExcluirCozinhaEmUso() {
		assertThrows(EntidadeEmUsoException.class,  () -> {
			cozinhaService.excluir(1L);
		});
	}

	@Test
	public void deveFalhar_QuandoExcluirCozinhaInexistente() {
		assertThrows(EntidadeNaoEncontradaException.class,  () -> {
			cozinhaService.excluir(100L);
		});
	}

}
