package com.algaworks.carlosfood_api.api.exceptionHandler;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Problem {

    private LocalDateTime dataHora;
    private String mensagem;
}
