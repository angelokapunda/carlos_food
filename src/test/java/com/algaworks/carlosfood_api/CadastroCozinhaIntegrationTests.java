package com.algaworks.carlosfood_api;

import static org.assertj.core.api.Assertions.assertThat;



import com.algaworks.carlosfood_api.domain.model.Cozinha;
import com.algaworks.carlosfood_api.domain.service.CadastroCozinhaService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CadastroCozinhaIntegrationTests {

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

}
